import net.sf.json.*
import net.sf.json.util.*


class JsonUtil {
	static dateRegex = ~/\s*new Date\((\d*)\)\s*/

	static String makeJSONStrict(sourceJson) {
//			println "Before: ${sourceJson}"
			def returnValue = makeDateJsonCompatible(sourceJson)
//			println "After: ${returnValue}"
			return returnValue
	}
	private static makeDateJsonCompatible(sourceJson) {
		if ( sourceJson instanceof String || sourceJson instanceof GString ) {
			def matcher = dateRegex.matcher(sourceJson)
			
			if ( matcher.getCount() > 0 ) {
				Long timestamp = new Long( matcher[0][1] )
				def jsonDate = JSONSerializer.toJSON( new Date(timestamp)).toString()
				sourceJson = sourceJson.replaceAll("new Date\\(${matcher[0][1]}\\)".toString(),jsonDate)
				sourceJson = makeDateJsonCompatible(sourceJson)
			}
			
			return sourceJson
		}
		return sourceJson
	}
	
	static Object jsonToObject(sourceJson, toClass ) {
		sourceJson = makeJSONStrict(sourceJson)
		JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON( sourceJson )
		JsonConfig jsonConfig = new JsonConfig()
		jsonConfig.setRootClass( toClass )
		jsonConfig.setJsonPropertyFilter(new NullFilter())
		JSONSerializer.toJava( jsonObject, jsonConfig )
	}
	
	static void hi() {
		println "HI"
	}
}

class NullFilter implements PropertyFilter {
	
	public boolean apply( Object source, String name, Object value ) { 
//          print "=============> ${source} ${name} ${value} ${value.getClass().getName()} "	
	      if( value == null ){  
//		     println "true"
	         return true;  
	      }  
//		  println "false"
	      return false;   
	}
}