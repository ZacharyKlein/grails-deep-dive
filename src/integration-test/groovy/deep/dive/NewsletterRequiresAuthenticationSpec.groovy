package deep.dive

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

@Integration
class NewsletterRequiresAuthenticationSpec extends GebSpec {

    void "newsletter subscription form requires the user to be authenticated"() {
        given:
        browser.baseUrl = "http://localhost:$serverPort"

        when:
        browser.go '/newsletter'

        then:
        browser.at(LoginPage)
    }


}
