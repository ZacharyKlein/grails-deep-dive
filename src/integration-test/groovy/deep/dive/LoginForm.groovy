package deep.dive

import geb.Module
import geb.module.PasswordInput
import geb.module.TextInput

class LoginForm extends Module {

    static content = {
        usernameInput { $('input#username').module(TextInput) }
        passwordInput { $('input#password').module(PasswordInput) }
        submitButton { $('input#submit') }
    }

    void login(String username, String password) {
        usernameInput.text = username
        passwordInput.text = password
        submitButton.click()
    }
}
