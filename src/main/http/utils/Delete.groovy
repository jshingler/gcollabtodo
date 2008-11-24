package http.utils

class Delete{
	String url
	QueryString queryString = new QueryString()
	def userName
	def password
	
	String getText(){
		def response
		def conn = new URL(toString()).openConnection()
		conn.requestMethod = "DELETE"
		conn.doOutput = true

		if (userName && password) {
		    conn.setRequestProperty("Authorization", "Basic ${userName}:${password}")
		}

		if (conn.responseCode == conn.HTTP_OK) {
		    def input = conn.inputStream
		    input.eachLine { response += it }
        } else {
        	return "URL: " + this.toString() + "\n" +
			"RESPONSE CODE: " + responseCode
        }

		conn.disconnect()
		return response
	}
        
    String toString(){
		return url + "?" + queryString.toString()
	}
}