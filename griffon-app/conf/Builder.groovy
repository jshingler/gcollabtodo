// groovy.swing.SwingBuilder Support:
// SupportNodes
// - action
// - actions
// - map
// - imageIcon
// - buttonGroup

// Binding
// - bind
// - bindProxy


// PassThruNodes
// - widget
// - container
// - bean

// Windows
// - dialog
// - fileChooser
// - frame
// - optionPane
// - window

// ActionButtonWidgets
// - button
// - checkBox
// - checkBoxMenuItem
// - menuItem
// - radioButton
// - radioButtonMenuItem
// - toggleButton

// TextWidgets
// - editorPane
// - label
// - passwordField
// - textArea
// - textField
// - formattedTextField
// - textPane

// MDIWidgets
// - desktopPane
// - internalFrame

// BasicWidgets
// - colorChooser
// - comboBox
// - list
// - progressBar
// - separator
// - scrollBar
// - slider
// - spinner
// - tree

// MenuWidgets
// - menu
// - menuBar
// - popupMenu

// Containers
// - panel
// - scrollPane
// - splitPane
// - tabbedPane
// - toolBar
// - viewport
// - layeredPane

// DataModels
// - boundedRangeModel
// - spinnerDateModel
// - spinnerListModel
// - spinnerNumberMode

// TableComponents
// - table
// - tableColumn
// - tableModel
// - propertyColumn
// - closureColumn

// BasicLayouts
// - borderLayout
// - cardLayout
// - flowLayout
// - gridLayout
// - overlayLayout
// - springLayout
// - gridBagLayout
// - gridBagConstraints
// - gbc

// BoxLayout
// - boxLayout
// - box
// - hbox
// - hglue
// - hstrut
// - vbox
// - vglue
// - vstrut
// - glue
// - rigidArea

// TableLayout
// - tableLayout
// - tr
// - td

// Borders
// - lineBorder
// - loweredBevelBorder
// - raisedBevelBorder
// - etchedBorder
// - loweredEtchedBorder
// - raisedEtchedBorder
// - titledBorder
// - emptyBorder
// - compoundBorder
// - matteBorder

// Renderers
// - tableCellRenderer
// - listCellRenderer
// - onRender

//Threading
// - edt
// - doOutside
// - doLater

// ---------------------------------------------------------------
// groovy.swing.SwingXBuilder' Supports:

root {
    'groovy.swing.SwingBuilder' {
        controller = ['Threading','SupportNodes', 'Binding', 'Windows']
        view = '*'
    }
    'griffon.app.ApplicationBuilder' {
        view = '*'
    }
}
jx {
    'groovy.swing.SwingXBuilder' { 
	controller = ['SwingxLayouts','SwingxComponents']
	view = '*'
	}
}