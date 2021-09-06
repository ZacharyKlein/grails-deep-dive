package deep.dive

import geb.Page

class NewsletterSubscriptionConfirmationPage extends Page {
    static url = '/newsletter/confirm'

    static at = { title == 'Email verified' }
}
