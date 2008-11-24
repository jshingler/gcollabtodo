
import java.awt.*
import java.awt.event.ActionListener
import javax.swing.*
import javax.swing.event.*
import javax.swing.ListSelectionModel
import static javax.swing.JSplitPane.VERTICAL_SPLIT
import javax.swing.text.Style
import javax.swing.text.StyleContext
import javax.swing.text.StyledDocument

import ca.odell.glazedlists.*
import ca.odell.glazedlists.gui.*
import ca.odell.glazedlists.swing.*

import net.miginfocom.swing.MigLayout

println "INITIALIZING: ContentPane"

def selectedIndex = 0

// Create Sorted List for use by the table
EventList todoEventList = model.todos

/*
* Helper method to get the current model / row
*/
def selectedTodo = {
    selectedIndex = bcptable.getSelectedRow()
	
    if (selectedIndex != -1) {
        return model.todos[selectedIndex]
    }
	
    if (bcptable.getRowCount() > 0) {
        // return the last item i the model.todos
        return model.todos[(bcptable.getRowCount() - 1)]
    }
}
controller.selectedTodo = selectedTodo

def makeTodoSelected = {todo ->
	def todoIdx = model.todos.indexOf(todo)
	ListSelectionModel selectionModel = bcptable.selectionModel
	selectionModel.setSelectionInterval(todoIdx, todoIdx)
}
controller.makeTodoSelected = makeTodoSelected

/*
* Define a Summary Table
*/
def columnNames = ["Name", "Priority", "Status", "Note"]

def summaryTable = scrollPane() {
    jxtable(id: 'bcptable', model:
    new EventTableModel(model.todos, [
            getColumnCount: { return columnNames.size() },
            getColumnName: {index ->
                columnNames[index]
            },
            getColumnValue: {object, index ->
                object."${columnNames[index].toLowerCase()}"
            }] as TableFormat))
    bcptable.setSortable(false)
}

myPane = panel(layout: new MigLayout("insets 0 0 0 0, wrap 1",))
        {	
			panel(layout: new MigLayout("insets 0 0 0 0, wrap 1","[430lp, align right, grow, fill]","[200lp][]")) {
                widget(id: "summaryTable", summaryTable)
                widget(constraints: "width 120lp!", id: "addButton", new JButton(addAction))
            }
			
			label('Detail ', constraints: 'split, span')
			separator(constraints: 'growx, wrap')
			
			panel(layout: new MigLayout("wrap 2","[][fill]")) {
                label(text: "Name:")
                widget(textField(columns: 20), id: "nameTextField")
				label(text: "Priority:")
				comboBox(items: model.priorities, id: "priorityComboBOx")
                //textField(columns: 20, id: "priorityTextField")
				label(text: "Status:")
				comboBox(items: model.statuses, id: "statusComboBox")
                //textField(columns: 20, id: "statusTextField")
				label(text: "Completed Date:")
                datePicker(id: "completedDateField")
				label(text: "Create Date:")
                datePicker(id: "createDateField")
				label(text: "Due Date:")
                datePicker(id: "dueDateField")
				label(text: "Note:")
                //textField(columns: 30, id: "noteTextField")
                scrollPane() {
                    textArea(rows: 5, columns: 30, id: "noteTextField")
                 }
				label("")
				widget(constraints: "split 2, align right", id: "saveButton",
                        new JButton(saveAction))
                widget(id: "deleteButton",
                        new JButton(deleteAction))
            }

            // Data Bind the fields (view) to the model.
            // sourceValue = model
            // target      = view
			
            bind(source: bcptable.selectionModel, sourceEvent: 'valueChanged',
                    sourceValue: { selectedTodo()?.name },
                    target: nameTextField, targetProperty: 'text')
					/**/
					
            bind(source: bcptable.selectionModel, sourceEvent: 'valueChanged',
                    sourceValue: { selectedTodo()?.priority },
                    target: priorityComboBOx, targetProperty: 'selectedItem')
            bind(source: bcptable.selectionModel, sourceEvent: 'valueChanged',
                    sourceValue: { selectedTodo()?.status },
                    target: statusComboBox, targetProperty: 'selectedItem')
            bind(source: bcptable.selectionModel, sourceEvent: 'valueChanged',
                    sourceValue: { selectedTodo()?.completedDate },
                    target: completedDateField, targetProperty: 'date')
            bind(source: bcptable.selectionModel, sourceEvent: 'valueChanged',
                    sourceValue: { selectedTodo()?.createdDate },
                    target: createDateField, targetProperty: 'date')
            bind(source: bcptable.selectionModel, sourceEvent: 'valueChanged',
                    sourceValue: { selectedTodo()?.dueDate },
                    target: dueDateField, targetProperty: 'date')	
            bind(source: bcptable.selectionModel, sourceEvent: 'valueChanged',
                    sourceValue: { selectedTodo()?.note },
                    target: noteTextField, targetProperty: 'text')			
        }
		
return myPane

