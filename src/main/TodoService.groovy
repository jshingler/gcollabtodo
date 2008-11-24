import http.utils.Get
import http.utils.Delete
import http.utils.Post
import http.utils.Put

import net.sf.json.*
import net.sf.json.util.*
import net.sf.json.groovy.*
import net.sf.ezmorph.bean.*

class TodoService {
	static String APP_URL = 'http://localhost:8080/collab-todo/json/todo'

	static List list(appContext) {
		def userContext = appContext.userContext

        def get = new Get(url: APP_URL,
                userName: userContext.userName,
                password: userContext.password)
		
		def todoJson = get.text
	    def str = JsonUtil.makeJSONStrict(todoJson)
	    def jsonarray = JSONSerializer.toJSON(str)
	    
		def todo
		def outputList = []
	    jsonarray.each {
			todo = JsonUtil.jsonToObject(it.toString(), Todo.class)
			outputList.add todo
		}
		return outputList
	}
	
	static void save(appContext, todo) {
		println "===================== Saving Todo: ${todo}"
		def userContext = appContext.userContext
	
		def put = new Put(url: APP_URL,
				userName: userContext.userName,
                password: userContext.password)
				
		put.queryString.add("name", todo.name)
		put.queryString.add("priority", todo.priority)
		put.queryString.add("status", todo.status)
		put.queryString.add("note", todo.note)
		put.queryString.add("owner.id", userContext.id)
		put.queryString.addDate("createdDate", todo.createdDate)
		
		println "<<<<<<<<<<<<<<<<<<<<<<<<<<  ${put.toString()}"
		def putResponse = put.text
	}
	
	static void delete(appContext, todo) {
		def userContext = appContext.userContext
	
		def delete = new Delete(url: APP_URL + "/${todo.id}",
                userName: userContext.userName,
                password: userContext.password)
        delete.text
	}
}
