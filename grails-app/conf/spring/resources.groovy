import deep.dive.UserPasswordEncoderListener
import grails.util.Environment
import deep.dive.AmazonSimpleEmailTransactionalEmailService
import deep.dive.TestTransactionalEmailService

beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener)
    switch(Environment.current) {
        case Environment.PRODUCTION:
            transactionalEmailService(AmazonSimpleEmailTransactionalEmailService)
            break
        default:
            transactionalEmailService(TestTransactionalEmailService)
    }
}
