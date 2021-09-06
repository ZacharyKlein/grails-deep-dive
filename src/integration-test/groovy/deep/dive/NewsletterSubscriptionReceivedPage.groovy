package deep.dive

import geb.Page

class NewsletterSubscriptionReceivedPage extends Page {
    static url = '/newsletter/subscribe'

    static at = { title == 'Subscription successful' }
}
