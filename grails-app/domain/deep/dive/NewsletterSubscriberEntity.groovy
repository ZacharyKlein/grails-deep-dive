package deep.dive

class NewsletterSubscriberEntity {
    String name

    String email

    boolean verified

    static constraints = {
        name nullable: true
        email blank: false, nullable: false, email: true
    }
}
