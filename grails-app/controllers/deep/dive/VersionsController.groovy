package deep.dive

import grails.plugin.springsecurity.annotation.Secured
import org.springframework.beans.factory.annotation.Autowired

class VersionsController {

    @Autowired
    GrailsApplicationForgeClient grailsApplicationForgeClient

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def index() {
        render "Grails Version: " + grailsApplicationForgeClient.versions()
    }
}
