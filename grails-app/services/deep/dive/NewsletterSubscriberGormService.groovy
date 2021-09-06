package deep.dive

import grails.gorm.services.Query
import grails.gorm.services.Service

@Service(NewsletterSubscriberEntity)
interface NewsletterSubscriberGormService {

    NewsletterSubscriberEntity save(String name, String email)

    List<NewsletterSubscriberEntity> findAll()

    NewsletterSubscriberEntity findByEmail(String email)

    void deleteByEmail(String email)

    List<NewsletterSubscriberEntity> findAllByVerified(boolean verified)

    @Query("update ${NewsletterSubscriberEntity subscriber} set ${subscriber.verified} = $verified where subscriber.email = $email")
    void updateVerifiedByEmail(String email, boolean verified)
}
