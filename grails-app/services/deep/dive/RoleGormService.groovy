package deep.dive

import grails.gorm.services.Service

@Service(Role)
interface RoleGormService {
    Role save(String authority)
}
