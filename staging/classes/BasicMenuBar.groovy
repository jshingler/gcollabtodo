
println "INITIALIZING: BasicMenuBar"

jxmenuBar {
    menu(text: 'File', mnemonic: 'F') {
        menuItem(loginAction)
        separator()
        menuItem(exitAction)
    }

    menu(text: 'Edit', mnemonic: 'E') {
        menuItem(addAction)
        menuItem(deleteAction)
        menuItem(saveAction)
    }

    menu(text: 'View', mnemonic: 'V') {
	    menuItem(refreshAction)
        checkBoxMenuItem(fullStackTracesAction, selected: controller.fullStackTraces)
        checkBoxMenuItem(showToolbarAction, selected: controller.showToolbar)
    }

    glue()
    menu(text: 'Help', mnemonic: 'H') {
        menuItem(aboutAction)
        menuItem(tipsAction)
    }
}
