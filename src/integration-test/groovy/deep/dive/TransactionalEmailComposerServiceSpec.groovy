package deep.dive

import grails.testing.mixin.integration.Integration
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Subject

@Integration
class TransactionalEmailComposerServiceSpec extends Specification {

    @Subject
    @Shared
    TransactionalEmailComposerService transactionalEmailComposerService

    void "secret is populated via configuration"() {
        expect:
        transactionalEmailComposerService.signer

        and:
        transactionalEmailComposerService.verifier

        and:
        transactionalEmailComposerService.verifyJwt(transactionalEmailComposerService.signedJwt('http://locahost:8080', 'delamos@objectcomputing.com'))
    }
}
