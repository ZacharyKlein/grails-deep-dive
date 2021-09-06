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

    void "issuer is populated via configuration"() {
        expect:
        transactionalEmailComposerService.issuer

    }
}
