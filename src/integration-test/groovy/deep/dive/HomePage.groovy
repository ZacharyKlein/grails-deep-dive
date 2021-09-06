package deep.dive

import geb.Page

class HomePage extends Page {

    static url = '/'

    static at = { title == 'Welcome to Grails' }
}
