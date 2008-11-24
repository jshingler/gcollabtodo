import http.utils.Get

class UserService {
	static String USERINFO_URL = 'http://localhost:8080/collab-todo/userInfo'

	static Object find(appContext) {
		UserContext userContext = appContext.userContext
		
		def get = new Get(url: USERINFO_URL,
                userName: userContext.userName,
                password: userContext.password)
        get.queryString.add("rest", "rest")
		
		def response = get.getText()
		//println "UserService.find() response = ${response}"
	
        def userInfo = new XmlSlurper().parseText(response)
		userContext.id = userInfo.@id
		userContext.firstName = userInfo.firstName
		
		userContext
	}
}