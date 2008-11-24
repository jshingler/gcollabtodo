import javax.swing.BorderFactory as BF

import java.awt.*
import javax.swing.*
import javax.swing.table.DefaultTableModel

import net.miginfocom.swing.MigLayout

import org.jdesktop.swingx.JXPanel
import org.jdesktop.swingx.JXLabel

ResourceUtils.setBundlePath("Resource")

JPanel buildTitlePanel() {
	def mess = """ ${ResourceUtils.getString('about.message1')}
    	\n\n\251${ResourceUtils.getString('about.message2')}
    	\n\n${ResourceUtils.getString("version")} - ${ResourceUtils.getString('version.date')}"""

    panel( title: "Title", layout: new MigLayout("inset 0 0 0 0", "grow, fill", "grow, fill")) {
		textArea(mess.toString())
    }    	
}
	
JPanel buildSystemPropertiesPanel() {
	m_model = new DefaultTableModel()
	m_model.addColumn("Property")
	m_model.addColumn("Value")
	
	// load the data
	try {
		Properties props = System.getProperties();
		props.each{k,v -> 
			Object[] row = new Object[2]
			row[0] = k
			row[1] = v
			m_model.addRow(row)
		}
	} catch (Exception e) {	}

	panel(title: "System", layout: new MigLayout("insets 0 0 0 0", "grow, fill", "grow, fill"), opaque: false) {
		scrollPane() {
			table(id: 'table', model: m_model)
		}
	}
}	

JScrollPane addTab(final String tabTitle, final String resource) {
	URL url = getClass().getResource(resource)
	scrollPane(title: tabTitle) {
		editorPane(page: url, preferredSize: new Dimension(500,300), 
			background: UIManager.getColor("control"), editable: false)
	}
}

class AboutGradientPanel extends JXPanel
{
	public void paintComponent(Graphics g) {
		if( !this.visible ) return
		super.paintComponent(g)
		if(!isOpaque()) {
			return
		} else {
			Color control = UIManager.getColor("control")
			int width = getWidth()
			int height = getHeight()
			Graphics2D g2 = (Graphics2D)g
			java.awt.Paint storedPaint = g2.getPaint()
			g2.setPaint(new GradientPaint(0.0F, 0.0F, getBackground(), width, 0.0F, control))
			g2.fillRect(0, 0, width, height)
			g2.setPaint(storedPaint)
			return
		}
	}
}

JPanel buildHeader() {
	MigLayout mlayout = new MigLayout("insets 0 0 0 0", "[][grow, fill]")
	
	AboutGradientPanel apanel = new AboutGradientPanel(background: Color.WHITE)
	apanel.setLayout(mlayout)
	apanel.add(new JLabel(ResourceUtils.getIcon("about.header.image")))
	apanel.add(new JLabel(ResourceUtils.getString("about.title")))
	return apanel
}
			
dialog(title: ResourceUtils.getString("about.title"), id: "aboutDialog", modal: true  ) {
	panel(layout: new MigLayout("insets 0 0 0 0", "grow, fill") ) {
		container(buildHeader(), constraints:"cell 0 0")		
		separator(constraints:"cell 0 1")
		tabbedPane(constraints: "cell 0 2") {
			buildTitlePanel()
			buildSystemPropertiesPanel()
			addTab("Release Notes", "/docs/release.html")
			addTab("Libraries", "/docs/libraries.html")
		}
		panel(constraints: "cell 0 4" ) {
			button("Close", actionPerformed: {event ->
				aboutDialog.dispose()
			})
		}
	}
}