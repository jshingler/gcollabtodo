import java.awt.Component
import javax.swing.*
import java.util.prefs.Preferences

import org.jdesktop.swingx.JXLoginPane
import org.jdesktop.swingx.JXTipOfTheDay
import org.jdesktop.swingx.tips.TipOfTheDayModel
import org.jdesktop.swingx.tips.TipLoader
import org.jdesktop.swingx.JXErrorPane
import org.jdesktop.swingx.error.ErrorInfo

class GCollabTodoController {
    // these will be injected by Griffon
    def model
    def view
    def frame

    void mvcGroupInit(Map args) {
        // this method is called after model and view are injected
    	println ">>>>>>>>>>>>> INITIALIZE:  C O N T R O L L E R"
    	
    }

    static String ICON_PATH = '/icons/Icon.png'
    static String APP_URL = 'http://localhost:8080/collab-todo/json/todo'
    static String USERINFO_URL = 'http://localhost:8080/collab-todo/userInfo'
    	
    	
    // This could be injected
    private CTLoginService loginService = new CTLoginService()
    private AppContext appContext = new AppContext()
    
//  Preferences
    private prefs = Preferences.userNodeForPackage(GCollabTodoController)
    boolean fullStackTraces = prefs.getBoolean('fullStackTraces',
            Boolean.valueOf(System.getProperty("groovy.full.stacktrace", "false")))
    boolean showToolbar = prefs.getBoolean('showToolbar', true)
	
	Component toolbar
	JLabel statusLabel
    JXTipOfTheDay totd
    JXLoginPane loginPane
    
    void setStatus(String message ) {
    	statusLabel.text = message
    }
    
    
	def busy 
	def norm 
    
//    EventList todoEventList = new BasicEventList()
//	SortedList sortedTodos
    def userInfo
	def selectedTodo
	def makeTodoSelected
    
	void exit(event) {
        System.exit(0)
    }

    void doTips() {
        if (!totd) {
            Properties tips = new Properties()
			tips.load(this.getClass().getResourceAsStream( "tips.properties" ));
            totd = new JXTipOfTheDay(TipLoader.load(tips));
        }
        totd.showDialog(frame);
    }

    void showTips(event) {
        doTips()
    }

    void showAbout(event) {
		showDialog("aboutDialog")
    }

    void showLogin(event) {
        if (!loginPane) {
            loginPane = new JXLoginPane()
            loginPane.loginService = loginService
            loginPane.bannerText = "Collab Todo Login"
            loginPane.message = "You MUST Login before you can access your Collab Todos"
        }
        loginPane.showLoginDialog(frame, loginPane)
		
		UserContext userContext = new UserContext()
		userContext.userName = loginService.name
		userContext.password = new String(loginService.password)
		appContext.userContext = userContext
		
		execWithExceptionHandling {
		   loadUserInfo()
           loadData()
		} 
		
		loginPane = null
    }

    void fullStackTraces(EventObject evt) {
        fullStackTraces = evt.source.selected
        System.setProperty("groovy.full.stacktrace",
                Boolean.toString(fullStackTraces))
        prefs.putBoolean('fullStackTraces', fullStackTraces)
    }

    void showToolbar(EventObject evt) {
        showToolbar = evt.source.selected
        prefs.putBoolean('showToolbar', showToolbar)
        toolbar.visible = showToolbar
    }
    
    
	private void showDialog( dialogName ) {
		def dialog = view."$dialogName"
	    if( dialog.visible ) return
	    dialog.pack()
	    int x = app.appFrames[0].x + (app.appFrames[0].width - dialog.width) / 2
	    int y = app.appFrames[0].y + (app.appFrames[0].height - dialog.height) / 2
	    dialog.setLocation(x, y)
	    dialog.show()
	}
     
	private void hideDialog( dialogName ) {
		def dialog = view."$dialogName"
		dialog.hide()
	}
	   
	private void handleException(Exception e) {
		def errorInfo = new ErrorInfo(e.getClass().getName(),
				e.getMessage(),
				null,
				null,
				e,
				null,
				null)
		JXErrorPane.showDialog(null, errorInfo)
	}
	   
	private void execWithExceptionHandling(Closure clozure) {
		try {
			clozure.call()
		} catch (Exception e) {
			handleException(e)
		}
	}
	
    void loadUserInfo() {
    	setStatus("Load User Info")
        busy
		
		UserContext uContext = UserService.find(appContext)
		
		norm
		setStatus("Finish Loading User Info")
    }
	

    void loadData() {
        setStatus("Loading Data")
        busy
		
		model.todos.clear()
		model.todos.addAll TodoService.list(appContext)

        norm
        setStatus("Finished Loading Data")
    }

    void deleteTodo(event) {
	    execWithExceptionHandling {
		    TodoService.delete(appContext, selectedTodo())
            loadData()
		} 
    }
	
	void refreshTodo(event) {
		setStatus("Loading Data")
        busy()
	    execWithExceptionHandling { loadData() }
		norm()
		setStatus("Finished Loading Data")
    }

    void saveTodo(event) {
		fillSelectedTodoFromView()
		
		def todo = selectedTodo()

		if(shouldBeSaved(todo)) {
		    execWithExceptionHandling {
			    TodoService.save(appContext, todo)
                loadData()
			} 
        } else {
            JOptionPane.showMessageDialog(frame,
                    "If this had been a completed application the Todo would have been updated:")
        }
    }
	
	private boolean shouldBeSaved(todo) {
		if (todo.id == "" || !todo.id ) {
			return true
		}
		return false
	}
	
	private void fillSelectedTodoFromView() {
		selectedTodo().with {
	        name = view.nameTextField?.text
            priority = view.priorityComboBOx?.selectedItem
            selectedTodo().status = view.statusComboBox?.selectedItem
            completedDate = view.completedDateField?.date
            createdDate = view.createDateField?.date
            dueDate = view.dueDateField?.date
            note = view.noteTextField?.text
		}
	}	

    void addTodo(event) {
		Todo todo = new Todo()
		model.todos.add(todo)
		makeTodoSelected(todo) 
    }
	    
	    
    
}