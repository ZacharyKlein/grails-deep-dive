package deep.dive

import spock.lang.Specification

class NewsletterSubscriberSpec extends Specification {
    void "Groovy ships with a map parameter constructor"() {

//        Map<String, String> m = new HashMap<>()
//        m.put("name", "Sergio del Amo")
//        m.put("email", "delamos@objectcomputing.com")
//
//        Map<String, String> another = [:]
//        another["name"] = "Sergio del Amo"
//        another["email"] = "delamos@objectcomputing.com"
//
//        another.name = "Sergio del Amo"
//        another.email = "delamos@objectcomputing.com"
//
//        Map<String, String> third = [name: "Sergio del Amo",
//                                     email: "delamos@objectcomputing.com"]
//
//        NewsletterSubscriber subscriber = new NewsletterSubscriber()

        when:
        String ociEmailAddress = "@objectcomputing.com"
        NewsletterSubscriber subscriber = new NewsletterSubscriber(name: "Sergio del Amo",
                                               email: "delamos${ociEmailAddress}")

        then:
        assert "delamos${ociEmailAddress}" instanceof GString
        subscriber.email == "delamos@objectcomputing.com"
        subscriber.name == "Sergio del Amo"

        when:
        String str = "delamos${ociEmailAddress}"

        then:
        str instanceof String
    }

    void "for valid newsletter subscribers validate() returns true"() {
        when:
        NewsletterSubscriber subscriber = new NewsletterSubscriber(name: "Sergio del Amo",
                email: "delamos@objectcomputing.com")

        then:
        subscriber.validate()
    }

    void "for newsletter subscribers name is optional"() {
        when:
        NewsletterSubscriber subscriber = new NewsletterSubscriber()
        subscriber.name = null

        then:
        subscriber.validate(['name'])
    }
}
