package deep.dive

import grails.gorm.services.Service

@Service(User)
interface UserGormService {

    User save(String username, String password)

}
