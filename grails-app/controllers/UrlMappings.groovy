class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"()

        "/"(redirect: '/proposal/getReplacementNames')
    }
}
