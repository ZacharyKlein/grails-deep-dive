package deep.dive

import com.nimbusds.jwt.JWT
import com.nimbusds.jwt.JWTParser
import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_USER")
class NewsletterController {

    static allowedMethods = [
            index: ['GET'],
            confirm: ['GET'],
            subscribe: ['POST']
    ]

    NewsletterSubscriberService newsletterSubscriberService

    TransactionalEmailComposerService transactionalEmailComposerService

    def index(String message) {
        [msg: message]
    }

    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    def confirm(String token) {
        if (!transactionalEmailComposerService.verifyJwt(token)) {
            render(view: '/notFound')
            return
        }
        JWT jwt = JWTParser.parse(token)
        String email = jwt.getJWTClaimsSet().getSubject()
        newsletterSubscriberService.verifyByEmail(email)
        [:]
    }

    def subscribe(NewsletterSubscriber newsletterSubscriber) {
        if (newsletterSubscriber.hasErrors()) {
            redirect(action: 'index', params: [message: 'email is required'])
            return
        }
        newsletterSubscriberService.save(newsletterSubscriber)
        [name: newsletterSubscriber.name]
    }
}
