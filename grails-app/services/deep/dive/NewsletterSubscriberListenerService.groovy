package deep.dive

import grails.events.annotation.Subscriber
import groovy.util.logging.Slf4j
import org.grails.datastore.mapping.engine.event.PostInsertEvent

@Slf4j
class NewsletterSubscriberListenerService {

    TransactionalEmailComposerService transactionalEmailComposerService

    TransactionalEmailService transactionalEmailService

    @Subscriber
    void afterInsert(PostInsertEvent event) {
        if (event.entityAccess.getEntity() instanceof NewsletterSubscriberEntity) {
            String email = event.entityAccess.getPropertyValue("email")
            log.info("received async subscription with email: ${email}")
            String htmlEmail = transactionalEmailComposerService.compose(email)
            log.info("composed html email: {}", htmlEmail)
            transactionalEmailService.send(email, htmlEmail)
        }
    }

}
