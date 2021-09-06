package deep.dive

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class NewsletterSubscriberEntitySpec extends Specification  implements DomainUnitTest<NewsletterSubscriberEntity> {

    void "NewsletterSubscriberEntity::name is optional"() {
        when:
        NewsletterSubscriberEntity subscriber = new NewsletterSubscriberEntity()
        subscriber.name = null

        then:
        subscriber.validate(['name'])
    }

    void "NewsletterSubscriberEntity::email is required"() {
        when:
        NewsletterSubscriberEntity subscriber = new NewsletterSubscriberEntity()
        subscriber.email = null

        then:
        !subscriber.validate(['email'])
    }

    void "NewsletterSubscriberEntity::email must be a valid email address"() {
        when:
        NewsletterSubscriberEntity subscriber = new NewsletterSubscriberEntity()
        subscriber.email = 'delamos'

        then:
        !subscriber.validate(['email'])

        when:
        subscriber.email = 'delamos@objectcomputing.com'

        then:
        subscriber.validate(['email'])
    }

}
