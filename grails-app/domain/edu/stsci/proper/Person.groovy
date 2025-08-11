// NOTE: This file has been simplified for the interview exercise.
package edu.stsci.proper

/**
 * Defines a person.
 */
class Person {

    String firstName
    String lastName
    String email
    String sortName

    Date dateCreated
    Date lastUpdated

    static mapping = {
        table 'people'
        sort sortName: 'asc'
        id column: 'person_id'
    }

    static constraints = {
        firstName(nullable: true, maxSize: 60)
        lastName(blank: false, maxSize: 60)
        email(nullable: true, email: true)
        sortName(nullable: true)
    }

    String toString() {
        return sortName ?: lastName
    }

    def beforeInsert() {
        setFields(true)
        return true
    }

    def beforeUpdate() {
        setFields(false)
        return true
    }

    private void setFields(boolean isInsert) {
        if (!sortName || isInsert || isDirty('firstName') || isDirty('lastName')) {
            String givenName = firstName?.trim()
            if (givenName) {
                sortName = "${lastName}, ${givenName}"
            } else {
                sortName = lastName
            }
        }
    }
}
