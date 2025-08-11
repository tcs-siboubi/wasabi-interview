package edu.stsci.proper

import grails.events.EventPublisher
import grails.events.annotation.Subscriber
import grails.gorm.transactions.Transactional

@Transactional()
class ProposalService implements EventPublisher {

    def grailsApplication

    def findListMayAccessProposal(Person oldPersonInstance) {
        def proposalList = ProposalParticipant.findAllByPerson(oldPersonInstance)*.proposal
        proposalList.remove(null)
        proposalList.unique()
        proposalList = proposalList.findAll { it.mayAccess }
        proposalList.sort { a, b -> a.number <=> b.number }
        return proposalList
    }

    def replaceProposalParticipant(Map<?, ?> args) {
        log.info 'Start replaceProposalParticipant'
        log.trace "args: ${args}"

        if (!args.oldPersonInstance) throw new IllegalArgumentException('Required argument oldPersonInstance missing')
        if (!args.newPersonInstance) throw new IllegalArgumentException('Required argument newPersonInstance missing')
        if (!args.proposalIdList) throw new IllegalArgumentException('Required argument proposalIdList missing')

        log.trace "oldPersonInstance: ${args.oldPersonInstance.id}:${args.oldPersonInstance}"
        log.trace "newPersonInstance: ${args.newPersonInstance.id}:${args.newPersonInstance}"

        int cnt = 0;
        List proposalIdList = [];
        Boolean logChanges = Boolean.TRUE
        if (args.doingMerge == true) logChanges = Boolean.FALSE

        if (args.proposalIdList == 'all') {
            log.debug 'generate list of all proposals'
            proposalIdList = Proposal.withCriteria {
                projections {
                    distinct('id')
                }

                participants {
                    eq('person', args.oldPersonInstance)
                }
            }
        } else {
            log.debug 'use passed list of proposals'
            proposalIdList = args.proposalIdList
        }

        if (proposalIdList) {
            log.debug "proposal list size: ${proposalIdList.size()}"

            Proposal.withTransaction { status ->

                log.debug "Get list of participant records for oldPersonInstance"
                def participantList = ProposalParticipant.withCriteria {
                    proposal {
                        'in'('id', proposalIdList)
                    }

                    eq('person', args.oldPersonInstance)
                }

                log.debug "Either update old person to new, or if new is there already, delete old"
                participantList.each { ProposalParticipant pp ->
                    pp.logInvestigatorChange = logChanges
                    if (pp.proposal.participants.any { dup -> dup.person == args.newPersonInstance && dup.role == pp.role }) {
                        log.trace "delete duplicate pp: ${pp}"
                        Proposal proposal = pp.proposal
                        proposal.removeFromParticipants(pp)
                        pp.delete()
                        proposal.save(failOnError: true)
                    } else {
                        pp.person = args.newPersonInstance
                        pp.save(failOnError: true)
                    }
                }

                cnt = participantList.size()
            }

            def idList = []
            idList << args.oldPersonInstance.id
            idList << args.newPersonInstance.id
            Mission.values().each { mission ->
                log.trace "verify roles for mission: ${mission}"
                notify('verifyInvestigatorRoleEvent', idList, mission)
            }
        } else {
            log.debug 'list of proposals is empty, nothing to do'
        }

        log.trace "replace count: ${cnt}"
        log.info 'End replaceProposalParticipant'
        return cnt

    }

    @Subscriber
    void verifyInvestigatorRoleEvent(List<Long> personIds, Mission mission) {
        // NOTE: This has been stubbed out for the interview exercise.
        // The original purpose of this method was to ensure that a person's
        // assigned roles were consistent with their participation in proposals.
    }
}
