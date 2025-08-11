package edu.stsci.proper

import grails.gorm.transactions.Transactional

@Transactional
class ProposalController {

    ProposalService proposalService

    static defaultAction = "getReplacementNames"

    static def allowedMethods = [
            'replaceParticipant': 'POST',
            'getReplacementNames': 'GET',
            'getReplacementList': 'GET'
    ]

    @Transactional(readOnly = true)
    def getReplacementNames() {
        [:]
    }

    @Transactional(readOnly = true)
    def getReplacementList() {
        def oldPersonId = params.oldParticipant
        def oldPersonInstance = oldPersonId ? Person.get(oldPersonId) : null
        if (!oldPersonInstance) {
            if (!flash.errors) flash.errors = [];
            flash.errors << 'You must specify an existing Investigator'
        }

        def newPersonId = params.newParticipant
        def newPersonInstance = newPersonId ? Person.get(newPersonId) : null
        if (!newPersonInstance) {
            if (!flash.errors) flash.errors = [];
            flash.errors << 'You must specify a new Investigator'
        }

        if (newPersonInstance && oldPersonInstance && newPersonInstance.id == oldPersonInstance.id) {
            if (!flash.errors) flash.errors = [];
            flash.errors << 'The new Investigator cannot be the same as the existing investigator'
        }

        if (flash.errors) {
            render(view: 'getReplacementNames', model: [params: params])
            return
        }

        def proposalList = proposalService.findListMayAccessProposal(oldPersonInstance)

        render(view: 'replacementList', model: [proposalList: proposalList,
                                                newPersonInstance: newPersonInstance,
                                                oldPersonInstance: oldPersonInstance])
    }

    def replaceParticipant() {
        List<Long> propIdList = []
        params.propId.each { key, value ->
            if (key =~ /^\d+$/) {
                propIdList << key.toLong()
            }
        }

        if (!propIdList) {
            flash.message = 'No proposals selected, nothing updated'
            redirect(action: 'getReplacementNames')
            return
        }

        def newPerson = Person.read(params.newPersonId)
        if (!newPerson) {
            log.error 'new person undefined in replaceParticipant'
            flash.message = 'You must specify a new Investigator'
            redirect(action: 'getReplacementNames')
            return
        }

        def oldPerson = Person.read(params.oldPersonId)
        if (!oldPerson) {
            log.error 'old person undefined in replaceParticipant'
            flash.message = 'You must specify an existing Investigator'
            redirect(action: 'getReplacementNames')
            return
        }

        def cnt = proposalService.replaceProposalParticipant([oldPersonInstance: oldPerson,
                                                              newPersonInstance: newPerson,
                                                              proposalIdList   : propIdList])

        flash.message = "Replaced ${oldPerson} with ${newPerson} in ${cnt} proposals."
        redirect(action: 'getReplacementNames')
    }
}
