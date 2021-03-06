grails.plugin.springsecurity.userLookup.userDomainClassName = 'deep.dive.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'deep.dive.UserRole'
grails.plugin.springsecurity.authority.className = 'deep.dive.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']]
]
grails.plugin.springsecurity.useBasicAuth = true

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/api/**', 		 filters: 'JOINED_FILTERS,-exceptionTranslationFilter'],
	[pattern: '/**',             filters: 'JOINED_FILTERS,-basicAuthenticationFilter,-basicExceptionTranslationFilter']
]
grails.plugin.springsecurity.logout.postOnly = false

grails.plugin.springsecurity.securityConfigType = "Annotation" // The default

//grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
//grails.plugin.springsecurity.interceptUrlMap = [
//		[pattern: '/newsletter/**', access: ['permitAll']],
//		// Default pages
//		[pattern: '/',               access: ['permitAll']],
//		[pattern: '/error',          access: ['permitAll']],
//		[pattern: '/index',          access: ['permitAll']],
//		[pattern: '/index.gsp',      access: ['permitAll']],
//		[pattern: '/shutdown',       access: ['permitAll']],
//		[pattern: '/assets/**',      access: ['permitAll']],
//		[pattern: '/**/js/**',       access: ['permitAll']],
//		[pattern: '/**/css/**',      access: ['permitAll']],
//		[pattern: '/**/images/**',   access: ['permitAll']],
//		[pattern: '/**/favicon.ico', access: ['permitAll']],
//		[pattern: '/login',          access: ['permitAll']],
//		[pattern: '/login/**',       access: ['permitAll']],
//		[pattern: '/logout',         access: ['permitAll']],
//		[pattern: '/logout/**',      access: ['permitAll']]
//]
//
