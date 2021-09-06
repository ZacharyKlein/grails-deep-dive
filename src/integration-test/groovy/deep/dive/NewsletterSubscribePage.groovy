package deep.dive

import geb.Page

class NewsletterSubscribePage extends Page {

    static url = '/newsletter'

    static at = { title == 'Subscribe' }

    static content = {
        subscribeForm { $('form', action: '/newsletter/subscribe').module(NewsletterSubscribeForm) }
    }
}
