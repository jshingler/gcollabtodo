
import net.miginfocom.swing.MigLayout
import javax.swing.*
import java.awt.Cursor

println "INITIALIZING: View"

    mainFrame = application(title:"Collab-Todo", size:[450,630], locationByPlatform:true,
		iconImage: imageIcon(controller.ICON_PATH).image ) {
		build(Actions)
		build(AppDialogs2)
		
		mainPanel = panel(layout: new MigLayout("fill")) {
		    build(BasicContentPane)
		}
//		label('Content Goes Here') // deleteme
		
		build(BasicMenuBar)
		build(BasicToolBar)
		build(DefaultStatusBar)
	}

	controller.statusLabel = status
	controller.toolbar = toolbar

	Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR)
    Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR)
	
    def busy = { mainFrame.setCursor(hourglassCursor) }
    def notBusy = { mainFrame.setCursor(normalCursor) }
	
	controller.busy = busy
	controller.norm = notBusy

void mvcGroupInit(Map args) {
    println ">>>>>>>>>>>>> INITIALIZE:  V I E W"
    
}