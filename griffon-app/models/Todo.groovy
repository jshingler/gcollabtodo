/**
 * 
 */


public class Todo{
	
	   //User owner
	   //Category category
	   Long id
	   Long owner
	   Long category
	   ArrayList keywords
	   String name
	   String note
	   Date createdDate = new Date()
	   Date startDate 
	   Date dueDate
	   Date completedDate
	   Date lastModifiedDate
	   String priority
	   String status
	   byte[] associatedFile
	   String fileName
	   String contentType
	   
	   String toString() {
	 	"""Todo  ${this.getClass().getName()}
	 		        id: ${id}
	 		        startDate: ${startDate}
	 		        keywords: ${keywords}
	 		        status: ${status}
	 		        contentType: ${contentType}
	 		        associatedFile: ${associatedFile}
	 		        category: ${category}
	 		        completedDate: ${completedDate}
	 		        priority: ${priority}
	 		        name: ${name}
	 		        owner: ${owner}
	 		        fileName: ${fileName}
	 		        lastModifiedDate: ${lastModifiedDate}
	 		        createdDate: ${createdDate}
	 		        dueDate: ${dueDate}
	 		        note: ${note}
	 			"""
	   }

	
	
}
