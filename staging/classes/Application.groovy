application {
    title='GCollabTodo'
    startupGroups = ['gCollabTodo']

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
    frameClass = 'org.jdesktop.swingx.JXFrame'
}
mvcGroups {
    // MVC Group for "gCollabTodo"
    gCollabTodo {
        model = 'GCollabTodoModel'
        view = 'GCollabTodoView'
        controller = 'GCollabTodoController'
    }

}
