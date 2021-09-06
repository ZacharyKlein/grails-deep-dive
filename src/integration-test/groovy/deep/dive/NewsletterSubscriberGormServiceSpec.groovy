package deep.dive

import grails.testing.mixin.integration.Integration
import spock.lang.Shared
import spock.lang.Specification

@Integration
class NewsletterSubscriberGormServiceSpec extends Specification {

    @Shared
    NewsletterSubscriberGormService newsletterSubscriberGormService

    void "updateVerifiedByEmail throws no exception if the email is not found"() {
        when:
        newsletterSubscriberGormService.updateVerifiedByEmail('foo@bar.com', true)

        then:
        noExceptionThrown()
    }
}
