package deep.dive

import groovy.util.logging.Slf4j

@Slf4j
class TestTransactionalEmailService implements TransactionalEmailService {

    Map<String, String> recipientsToTokens = [:]

    @Override
    void send(String recipient, String htmlEmail) {
        //Cache confirmation tokens if welcome email...
        if (htmlEmail.contains("token")) {
            String email = htmlEmail.substring(htmlEmail.indexOf('token=') + 'token='.length())
            String token = email.substring(0, email.indexOf("\""))
            log.info("for {} parsed token: {}", recipient, token)
            recipientsToTokens.put(recipient, token)
        }

        log.info "Sending email to ${recipient}..."
    }
}
