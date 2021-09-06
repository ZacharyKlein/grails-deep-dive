package deep.dive

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
class SendEmailJobService {

    static lazyInit = false

    NewsletterSubscriberGormService newsletterSubscriberGormService
    TransactionalEmailComposerService transactionalEmailComposerService
    TransactionalEmailService transactionalEmailService

    @Scheduled(cron = "0 0/1 * * * ?")
    void sendEmailNewsletters() {
        List<NewsletterSubscriberEntity> verifiedSubscribers = newsletterSubscriberGormService.findAllByVerified(true)

        log.info "Sending newsletters to {} verified subscribers", verifiedSubscribers.size()

        verifiedSubscribers.each { subscriber ->
            String html = transactionalEmailComposerService.composeNewsletter(subscriber.name)
            transactionalEmailService.send(subscriber.email, html)
        }

    }
}
