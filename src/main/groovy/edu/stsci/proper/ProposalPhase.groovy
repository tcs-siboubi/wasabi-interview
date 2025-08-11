package edu.stsci.proper

/**
 * List of valid proposal phases and information about it. Used to manage access to the proposals.
 */
enum ProposalPhase {
    APPROVED( Boolean.TRUE, ['Approved','Phase II','Phase I']),
    SUBMITTED( Boolean.FALSE, ['Submitted']),
    DRAFT( Boolean.FALSE, ['Draft'])

    // is this phase visible to the public
    Boolean isPublic

    // list of values in the XML files that map to this phase
    List<String> xmlValues

    ProposalPhase(Boolean isPublic, List<String> xmlValues) {
        this.isPublic = isPublic
        this.xmlValues = []
        this.xmlValues.addAll(xmlValues)
    }

    // Can this user access any proposal in this phase
    Boolean getMayAccess() {
        // NOTE: Stubbed for interview exercise.
        return true
    }

    static ProposalPhase get(String id) {
        return ProposalPhase.values().find{it.name().equalsIgnoreCase(id)}
    }

    static ProposalPhase fromXml(String txt) {
        return ProposalPhase.values().find{
            it.xmlValues.any{x-> x.equalsIgnoreCase(txt)}
        }
    }

    static List<ProposalPhase> getPublicPhaseList() {
        return ProposalPhase.values().findAll{it.isPublic}.toList()
    }

    static String getPublicSqlInTerm() {
        return ' (' + ProposalPhase.getPublicPhaseList().collect{"'${it.name()}'"}.join(',') + ') '
    }

    // List of phase this user can see based on their privileges
    static List<ProposalPhase> getAuthorizedPhaseList() {
        // NOTE: Stubbed for interview exercise.
        return ProposalPhase.values().toList()
    }

    // SQL in term. Useful for appending to an SQL query.
    static String getAuthorizedSqlInTerm() {
        return ' (' + getAuthorizedPhaseList().collect{"'${it.name()}'"}.join(',') + ') '
    }

}
