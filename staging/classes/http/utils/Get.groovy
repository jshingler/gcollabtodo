package http.utils

class Get{
	String url
	QueryString queryString = new QueryString()
	String text
	def userName
	def password
	
	String getText(){
	    
	    try {
		    def response
			
			def conn = new URL(toString()).openConnection()
			conn.requestMethod = "GET"
			conn.doOutput = true

			if (userName && password) {
			    conn.setRequestProperty("Authorization", "Basic ${userName}:${password}")
			}
			
			def content
			if (conn.responseCode == conn.HTTP_OK) {
				response = conn.content.text
		    } else {
	        	response =  "URL: " + this.toString() + "\n" +
				"RESPONSE CODE: " + conn.responseCode
				throw new ResourceException(response)
	        }

			conn.disconnect()
		    return response	
		} catch (Exception e) {
			println "Caught Exception ${e}"
		    throw new ResourceException(e)
		}
		
		
	}
        
    String toString(){
		return url + "?" + queryString.toString()
	}
}