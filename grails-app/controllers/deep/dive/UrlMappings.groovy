package deep.dive

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/api/subscribers"(version: '1.0', controller: 'api', namespace: 'v1')
        "/api/subscribers"(version: '2.0', controller: 'apiV2', namespace: 'v2')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "405"(view:'/notAllowed')
    }
}
