package http.utils

class QueryString{
	Map params = [:]
	
	//	this constructor allows you to pass in a Map
	QueryString(Map params){
		if(params){
			this.params.putAll(params)
		}
	}
	
	//	this method allows you to add name/value pairs
	void add(String name, Object value){
		params.put(name, value)
	}
	
	//	this method allows you to add name/value pairs
	void addDate(String name, Date forDate){
		if (forDate != null ) {
		    Calendar workCalendar = Calendar.getInstance();
			workCalendar.time = forDate
	        def cdYear = workCalendar.get(Calendar.YEAR)
	        def cdMonth = workCalendar.get(Calendar.MONTH) + 1
	        def cdDay = workCalendar.get(Calendar.DAY_OF_MONTH)
	        def cdHour = workCalendar.get(Calendar.HOUR_OF_DAY)
	        def cdMin = workCalendar.get(Calendar.MINUTE)

			params.put(name, "struct&${name}_hour=${cdHour}&${name}_month=${cdMonth}&${name}_minute=${cdMin}&${name}_year=${cdYear}&${name}_day=${cdDay}")
		}
	}
	
	//	this method returns a well-formed QueryString
	String toString(){
		def list = []
		params.each{name,value->
			list << "$name=" + value.toString()
		}
		return list.join("&" )
	}
}