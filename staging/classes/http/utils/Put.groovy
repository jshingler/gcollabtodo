package http.utils

class Put{
	
	String url
	QueryString queryString = new QueryString()
	String content
	String contentType
	String text
	def userName
	def password
	
	
	String getText(){
		def conn = new URL(url).openConnection()
		conn.setRequestMethod("PUT" )
		conn.setRequestProperty("Content-Type" , contentType)
		conn.doOutput = true
		conn.doInput = true
		
		if (userName && password) {
	        conn.setRequestProperty("Authorization", "Basic ${userName}:${password}")
		}
		
		conn.outputStream.withWriter { out ->
		  out.write(queryString.toString())
		  out.flush()
		  out.close()
		}
		
		def response
        if (conn.HTTP_OK == conn?.responseCode) {
        	response = conn.content.text
        } else {
        	response =  "URL: " + this.toString() + "\n" +
			"RESPONSE CODE: " + responseCode
        }
    	conn.disconnect()
    	return response
	}
        
    String toString(){
		return url + "?" + queryString.toString()
	}
}