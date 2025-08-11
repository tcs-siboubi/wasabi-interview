// NOTE: This file has been simplified for the interview exercise.
package edu.stsci.proper

/**
 * Defines a person participating on a proposal
 */
class ProposalParticipant {

    Proposal proposal
    Person person
    ProperRole role
    Boolean isPrincipalInvestigator = Boolean.FALSE
    Boolean isAdministrativePrincipalInvestigator = Boolean.FALSE
    Boolean isContact = Boolean.FALSE

    Date dateCreated
    Date lastUpdated

    Boolean logInvestigatorChange = Boolean.TRUE

    static transients = ['logInvestigatorChange']

    static belongsTo = [proposal: Proposal]

    static mapping = {
        proposal column: 'prop_id'
        isPrincipalInvestigator column: 'pi_flg'
    }

    static constraints = {
        person(unique: ['role', 'proposal'])
        proposal(nullable: true)
        dateCreated(nullable: true)
        lastUpdated(nullable: true)
    }

    String toString() {
        "${person} (${role})"
    }
}
