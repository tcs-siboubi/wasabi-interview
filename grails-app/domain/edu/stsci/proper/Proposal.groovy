// NOTE: This file has been simplified for the interview exercise.
package edu.stsci.proper

/**
 * Defines a proposal.
 */
class Proposal {

    Integer number
    String title
    String type
    String category
    Mission mission
    Integer cycle = 0

    Date dateCreated
    Date lastUpdated

    static hasMany = [
            participants: ProposalParticipant
    ]

    static mapping = {
        number column: 'proposal_number'
        type column: 'proposal_type'
        category column: 'proposal_category'
    }

    static constraints = {
        type(unique: ['number', 'mission'])
    }

    boolean getMayAccess() {
        // NOTE: This is a stub for the interview exercise. The real application has appropriate logic here.
        return true
    }

    String toString() {
        return "${number} ${title}"
    }
}
