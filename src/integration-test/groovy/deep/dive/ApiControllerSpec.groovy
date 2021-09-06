package deep.dive

import geb.spock.GebReportingSpec
import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import io.micronaut.http.*
import io.micronaut.http.client.HttpClient
import io.micronaut.http.uri.UriBuilder
import org.springframework.context.ApplicationContext
import spock.lang.Shared
import spock.util.concurrent.PollingConditions

@Integration
class ApiControllerSpec extends GebReportingSpec {

    @Shared
    HttpClient httpClient

    @Shared
    NewsletterSubscriberGormService newsletterSubscriberGormService

    @Shared
    ApplicationContext applicationContext

    @OnceBefore
    void intialize() {
        this.httpClient = HttpClient.create(new URL("http://localhost:$serverPort"))
    }

    void "/api/subscribers returns a JSON object with total field indicating  the number of subscribers"() {
        given:
        String email = 'delamos@objectcomputing.com'
        String user = 'alexander'
        String password = 'supersecret'

        when:
        HttpRequest<?> request = HttpRequest.GET('/api/subscribers')
                .header('Accept-Version', '1.0')
                .basicAuth(user, password)
                .contentType(MediaType.APPLICATION_JSON)
        HttpResponse<SubscribersResponse> rsp = httpClient.toBlocking().exchange(request, SubscribersResponse)

        then:
        noExceptionThrown()
        rsp
        rsp.status() == HttpStatus.OK
        rsp.headers.get(HttpHeaders.CONTENT_TYPE).contains(MediaType.APPLICATION_JSON)
        rsp.body().total == 0

        when: "verify no token was sent to the user's email"
        def transactionalEmailService = applicationContext.getBean('transactionalEmailService')

        then:
        noExceptionThrown()
        !transactionalEmailService.recipientsToTokens.containsKey(email)

        when:
        browser.via(NewsletterSubscribePage)

        then:
        browser.at(LoginPage)

        when:
        LoginPage loginPage = browser.page(LoginPage)
        loginPage.loginForm.login('greg', 'supersecret')

        then:
        browser.at(NewsletterSubscribePage)

        when:
        NewsletterSubscribePage newsletterSubscribePage = browser.page(NewsletterSubscribePage)
        newsletterSubscribePage.subscribeForm.subscribe('Sergio', email)
        PollingConditions conditions = new PollingConditions(timeout: 5)

        then:
        browser.at(NewsletterSubscriptionReceivedPage)

        and:
        conditions.eventually {
            assert applicationContext.getBean('transactionalEmailService').recipientsToTokens.containsKey(email)
        }

        when: 'if I call the confirm request as a as user will do by clicking the email'
        String token = applicationContext.getBean('transactionalEmailService').recipientsToTokens[email]
        URI uri = UriBuilder.of("/newsletter/confirm")
                .queryParam("token", token)
                .build()
        HttpRequest<?> confirmRequest = HttpRequest.GET(uri)
        String html = httpClient.toBlocking().retrieve(confirmRequest, String)

        then: 'a page telling the user that his email was verified is displayed'
        noExceptionThrown()
        html.contains('Email verified')

        when:
        SubscribersResponse response = httpClient.toBlocking().retrieve(request, SubscribersResponse)

        then:
        response.total == 1
        response.subscribers.size() == 1
        response.subscribers.find { subscriber -> subscriber.e == email }

        cleanup:
        newsletterSubscriberGormService?.deleteByEmail(email)
    }
}
