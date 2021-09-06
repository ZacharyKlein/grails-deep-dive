package deep.dive

import grails.testing.web.controllers.ControllerUnitTest
import io.micronaut.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

class ApiControllerAllowedMethodsSpec extends Specification implements ControllerUnitTest<ApiController> {

    @Unroll("/api/subscribers does not respond to #httpVerb")
    void "/api/subscribers only responds to POST"(String httpVerb) {
        when:
        request.method = httpVerb
        controller.subscribers()

        then:
        response.status == HttpStatus.METHOD_NOT_ALLOWED.code

        where:
        httpVerb << ['POST', 'PATCH', 'PUT', 'DELETE']
    }

    void "/api/subscribers responds to GET"() {
        when:
        request.method = 'GET'
        controller.newsletterSubscriberService = Mock(NewsletterSubscriberService)
        controller.subscribers()

        then:
        response.status == HttpStatus.OK.code
    }
}
