package http.utils

class Post{
	String url
	QueryString queryString = new QueryString()
	String content
	String text
	def userName
	def password
	
	String getText(){
		def response
		def conn = new URL(toString()).openConnection()
		conn.requestMethod = "POST"
		conn.doOutput = true
		conn.doInput = true
		
		
		conn.outputStream.withWriter { out ->
		  out.write(content)
		  out.flush()
		}
		
		if (conn.responseCode == conn.HTTP_OK) {
			  conn.inputStream.withStream {
			    response = it
			  }
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