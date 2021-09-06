package deep.dive

import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_ADMIN")
class ApiV2Controller {
    static namespace = "v2"

    static allowedMethods = [
            subscribers: ['GET']
    ]

    NewsletterSubscriberService newsletterSubscriberService

    def subscribers() {
        render(view: '/apiv2/subscribers', model:[confirmedUsers: newsletterSubscriberService.subscribers()])
    }
}
