package deep.dive

import groovy.transform.CompileStatic

@CompileStatic
class BootStrap {

    UserGormService userGormService

    RoleGormService roleGormService

    UserRoleGormService userRoleGormService

    def init = { servletContext ->

        Role roleAdmin = roleGormService.save('ROLE_ADMIN')
        Role roleUser = roleGormService.save('ROLE_USER')

        User alexander = userGormService.save('alexander', 'supersecret')
        userRoleGormService.save(alexander, roleAdmin)
        userRoleGormService.save(alexander, roleUser)

        User claude = userGormService.save('claude', 'supersecret')
        userRoleGormService.save(claude, roleUser)

        User dave = userGormService.save('dave', 'supersecret')
        userRoleGormService.save(dave, roleUser)

        User greg = userGormService.save('greg', 'supersecret')
        userRoleGormService.save(greg, roleUser)

        User michael = userGormService.save('michael', 'supersecret')
        userRoleGormService.save(michael, roleUser)
    }
    def destroy = {
    }
}
