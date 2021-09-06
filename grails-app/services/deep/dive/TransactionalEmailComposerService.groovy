package deep.dive

import com.nimbusds.jose.*
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.JWTClaimsSet
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.util.logging.Slf4j

import java.text.ParseException

@Slf4j
class TransactionalEmailComposerService implements GrailsConfigurationAware {

    JWSSigner signer
    JWSVerifier verifier
    String issuer

    @Override
    void setConfiguration(Config co) {
        issuer = co.getProperty('grails.serverURL', String)
        String secret = co.getProperty('demo.secret', String)
        this.signer = new MACSigner(secret)
        verifier = new MACVerifier(secret)
    }

    String compose(String email) {
        //TODO do this GSP,
        String jwt = signedJwt(issuer, email)
        log.info('generated JWT {}', jwt)
        'Thanks for subscribing, please confirm your email address by clicking <a href="'+ issuer +'/newsletter/confirm?token=' + jwt +'">Here</a>'
    }

    private static JWSObject createJwsObject(String issuer, String email) {
        new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(new JWTClaimsSet.Builder()
                .subject(email)
                .issuer(issuer)
                .build()
                .toString()))
    }

    boolean verifyJwt(String token) {
        try {
            JWSObject jwsObject = JWSObject.parse(token)
            return jwsObject.verify(verifier)
        } catch(ParseException e) {
            return false
        }
    }

    String signedJwt(String issuer, String email) {
        JWSObject jwsObject = createJwsObject(issuer, email)
        jwsObject.sign(signer)
        return jwsObject.serialize()
    }
}
