package deep.dive

import geb.Page

class LoginPage extends Page {

    static url = '/login/auth'

    static at = { title.contains('Login') }

    static content = {
        loginForm { $('form#loginForm').module(LoginForm) }
    }

}
