package deep.dive

import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_ADMIN")
class ApiController {
    static namespace = "v1"

    static allowedMethods = [
            subscribers: ['GET']
    ]

    NewsletterSubscriberService newsletterSubscriberService

    def subscribers() {
        render(view: '/api/subscribers', model: [confirmedUsers: newsletterSubscriberService.subscribers()])
    }
}
