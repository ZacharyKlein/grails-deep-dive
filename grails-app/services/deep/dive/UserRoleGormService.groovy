package deep.dive

import grails.gorm.services.Service

@Service(UserRole)
interface UserRoleGormService {
    UserRole save(User user, Role role)
}
