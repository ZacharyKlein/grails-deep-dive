package deep.dive

import grails.validation.Validateable

class NewsletterSubscriber implements Validateable {
    String email
    String name

    static constraints = {
        email nullable: false, blank: false, email: true
        name nullable: true
    }
}
