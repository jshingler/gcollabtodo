import ca.odell.glazedlists.*

class GCollabTodoModel {
   
   void mvcGroupInit(Map args) {
        println ">>>>>>>>>>>>> INITIALIZE:  M O D E L"
   }
   
   EventList todos = new BasicEventList()
   def priorities =  ["High", "Medium", "Low" ]
   def statuses = ["Incomplete", "Complete"]
}