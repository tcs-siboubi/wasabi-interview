package edu.stsci.proper

/**
 * An enumeration of missions recognized by ProPer.
 */
enum Mission {
    HST("Hubble Space Telescope"),
    JWST("James Webb Space Telescope")

    final String description

    Mission(String description) {
        this.description = description
    }
}
