package deep.dive

import groovy.util.logging.Slf4j

/**
 * @see <a href="https://aws.amazon.com/ses/>SES</a>
 */
@Slf4j
class AmazonSimpleEmailTransactionalEmailService implements TransactionalEmailService {

    @Override
    void send(String recipient, String htmlEmail) {
        //TODO Implement this
        log.info("send email with AWS SES to {}", recipient)
    }
}
