package deep.dive

import geb.Module
import geb.module.TextInput

class NewsletterSubscribeForm extends Module {
    static content = {
        nameInput { $('input#name').module(TextInput) }
        emailInput { $('input#email').module(TextInput) }
        submitButton { $('input', type: 'submit') }
    }

    void subscribe(String name, String email) {
        nameInput.text = name
        emailInput.text = email
        submitButton.click()
    }
}
