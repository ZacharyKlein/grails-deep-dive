package deep.dive

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.scheduling.annotation.Scheduled

@Slf4j
@CompileStatic
class SendEmailJobService {

    static lazyInit = false

    NewsletterSubscriberGormService newsletterSubscriberGormService
    TransactionalEmailComposerService emailComposerService
    TransactionalEmailService emailService

    @Scheduled(cron = "0 0/5 * * * ?")
    void sendEmailNewsletters() {
        List<NewsletterSubscriberEntity> verifiedSubscribers = newsletterSubscriberGormService.findAllByVerified(true)

        log.info "Sending newsletters to {} verified subscribers", verifiedSubscribers.size()

        verifiedSubscribers.each { subscriber ->
            String html = emailComposerService.composeNewsletter(subscriber.name)
            emailService.send(subscriber.email, html)
        }

    }
}
