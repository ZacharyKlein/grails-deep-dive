package deep.dive

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.util.logging.Slf4j

@Slf4j
class TransactionalEmailComposerService implements GrailsConfigurationAware {

    String issuer

    @Override
    void setConfiguration(Config co) {
        issuer = co.getProperty('grails.serverURL', String)

    }

    String composeWelcome(String email) {
        String token = Base64.getEncoder().encodeToString(email.getBytes())

        log.info('generated token {}', token)
        'Thanks for subscribing, please confirm your email address by clicking <a href="' + issuer + '/newsletter/confirm?token=' + token + '">Here</a>'
    }

    String composeNewsletter(String name) {
        'Hello, ' + name + '! We hope you enjoy this latest edition of our newsletter.'
    }

    String verifyToken(String token) {
        //TODO: Check for subscriber in database

        return new String(Base64.getDecoder().decode(token))
    }

}
