
import org.jdesktop.swingx.JXStatusBar.Constraint
import org.jdesktop.swingx.JXStatusBar.Constraint.ResizeBehavior

println "INITIALIZING: StatusBar"

statusBar() {
    label(id: 'status',
        text: 'Welcome to Collab Todo.',
        constraints: new Constraint(ResizeBehavior.FILL)
    )

    label(id: 'status2',
        text: '',
        preferredSize : [50, 10],
        constraints: new Constraint(ResizeBehavior.FIXED)
    )
}
