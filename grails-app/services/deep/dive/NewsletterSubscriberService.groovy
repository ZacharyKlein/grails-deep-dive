package deep.dive

import grails.gorm.DetachedCriteria
import grails.gorm.transactions.ReadOnly
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.grails.datastore.mapping.query.api.BuildableCriteria

@CompileStatic
@Slf4j
class NewsletterSubscriberService {

    NewsletterSubscriberGormService newsletterSubscriberGormService

    void save(NewsletterSubscriber subscriber) {
        if (!newsletterSubscriberGormService.findByEmail(subscriber.email)) {
            NewsletterSubscriberEntity entity = newsletterSubscriberGormService.save(subscriber.name, subscriber.email)
            log.info("saving ${subscriber.name} ${subscriber.email} with id ${entity.id}") //TODO do this wiht a log
        } else {
            //TODO write log statements
        }

    }

    @Transactional
    void saveWithDynamicFinder(NewsletterSubscriber subscriber) {
        NewsletterSubscriberEntity entity = new NewsletterSubscriberEntity()
        entity.with {
            name = subscriber.name
            email = subscriber.email
            save()
        } // same as =>
//        entity.name = subscriber.name
//        entity.email = subscriber.email
//        entity.save()
    }

    Set<NewsletterSubscriber> subscribers() {
        verifiedSubscribersWithGormDataService()
    }

    void verifyByEmail(String email) {
        newsletterSubscriberGormService.updateVerifiedByEmail(email, true)
    }

    Set<NewsletterSubscriber> verifiedSubscribersWithGormDataService() {
        Collection<NewsletterSubscriberEntity> entities = newsletterSubscriberGormService.findAllByVerified(true)
        mapTo(entities)
    }

    Set<NewsletterSubscriber> subscribersWithGormDataService() {
        Collection<NewsletterSubscriberEntity> entities = newsletterSubscriberGormService.findAll()
        mapTo(entities)
    }

    @ReadOnly
    Set<NewsletterSubscriber> subscribersWithWhereQueries() {
        DetachedCriteria<NewsletterSubscriberEntity> c = NewsletterSubscriberEntity.where {}
        List<NewsletterSubscriberEntity> entities = c.list()
        mapTo(entities)
    }

    @ReadOnly
    Set<NewsletterSubscriber> subscribersWithCriteria() {
        BuildableCriteria c = NewsletterSubscriberEntity.createCriteria()
        List<NewsletterSubscriberEntity> entities = c.list {

        } as List<NewsletterSubscriberEntity>
        mapTo(entities)
    }

    @ReadOnly
    Set<NewsletterSubscriber> subscribersWithDynamicFinder() {
        List<NewsletterSubscriberEntity> entities = NewsletterSubscriberEntity.findAll()
        mapTo(entities)
    }

    private Set<NewsletterSubscriber> mapTo(Collection<NewsletterSubscriberEntity> entities) {
        List<NewsletterSubscriber> result = entities.collect { new NewsletterSubscriber(email: it.email, name: it.name) }
        result as Set<NewsletterSubscriber>
    }

    @ReadOnly
    Integer numberOfSubscribers() {
        NewsletterSubscriberEntity.count()
    }
}
