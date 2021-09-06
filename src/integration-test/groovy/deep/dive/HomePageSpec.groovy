package deep.dive

import geb.spock.GebSpec
import grails.testing.mixin.integration.Integration

@Integration
class HomePageSpec extends GebSpec {

    void "home page is not secured"() {
        given:
        browser.baseUrl = "http://localhost:$serverPort"

        when:
        browser.to(HomePage)

        then:
        browser.at(HomePage)
    }
}
