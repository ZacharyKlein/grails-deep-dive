package deep.dive

import grails.testing.web.controllers.ControllerUnitTest
import io.micronaut.http.HttpStatus
import spock.lang.Specification
import spock.lang.Unroll

class NewsletterControllerSpec extends Specification  implements ControllerUnitTest<NewsletterController> {

    @Unroll("/newsletter/subscribe does not respond to #httpVerb")
    void "/newsletter/subscribe only responds to POST"(String httpVerb) {
        when:
        request.method = httpVerb
        controller.subscribe(new NewsletterSubscriber())

        then:
        response.status == HttpStatus.METHOD_NOT_ALLOWED.code

        where:
        httpVerb << ['GET', 'PATCH', 'PUT', 'DELETE']
    }

    void "/newsletter/subscribe responds to POST"() {
        when:
        request.method = 'POST'
        controller.newsletterSubscriberService = Mock(NewsletterSubscriberService)
        controller.subscribe(new NewsletterSubscriber())

        then:
        response.status == HttpStatus.OK.code
    }

    @Unroll("/newsletter/index does not respond to #httpVerb")
    void "/newsletter/index only responds to POST"(String httpVerb) {
        when:
        request.method = httpVerb
        controller.index()

        then:
        response.status == HttpStatus.METHOD_NOT_ALLOWED.code

        where:
        httpVerb << ['POST', 'PATCH', 'PUT', 'DELETE']
    }

    void "/newsletter/index responds to GET"() {
        when:
        request.method = 'GET'
        controller.newsletterSubscriberService = Mock(NewsletterSubscriberService)
        controller.index()

        then:
        response.status == HttpStatus.OK.code
    }
}
