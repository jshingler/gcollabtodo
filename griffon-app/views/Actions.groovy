
		action(id: 'exitAction',
			name: 'Exit',
			closure: controller.&exit,
			mnemonic: 'x',
			accelerator: 'F4',
			shortDescription: 'Exit Collab Todo'
		)		
		
		action(id: 'aboutAction',
			name: 'About',
			closure: controller.&showAbout,
			mnemonic: 'A',
			accelerator: 'F1',
			shortDescription: 'Find out about Collab Todo'
		)
				  
		action(id: 'tipsAction',
			name: 'Tips',
			closure: controller.&showTips,
			mnemonic: 'T',
			shortDescription: 'Tips'
		)
				  
		action(id: 'loginAction',
			name: 'Login',
			closure: controller.&showLogin,
			mnemonic: 'L',
			shortDescription: 'Login to the Collab Todo Service',
			smallIcon: imageIcon(resource:"icons/login.png", class:controller.getClass()),
			shortDescription: 'Login'
		)

		action(id: 'fullStackTracesAction',
			name: 'Show Full Stack Traces',
			closure: controller.&fullStackTraces,
			mnemonic: 'F'
		)

		action(id:'showToolbarAction',
			name: 'Show Toolbar',
			closure: controller.&showToolbar,
			mnemonic: 'T'
		)
			
		action(id: 'addAction',
			name: 'Add Todo',
			closure: controller.&addTodo,
			mnemonic: 'A',
			//accelerator: shortcut('A'),
			shortDescription: 'Add Todo'
		)

		action(id: 'saveAction',
			name: 'Save',
			closure: controller.&saveTodo,
			mnemonic: 'S',
			//accelerator: shortcut('S'),
			smallIcon: imageIcon(resource:"icons/disk.png", class:controller.getClass()),
			shortDescription: 'Save',
			enabled: true 
		)

		action(id: 'deleteAction',
			name: 'Delete',
			closure: controller.&deleteTodo,
			mnemonic: 'D',
			//accelerator: shortcut('D'),
			smallIcon: imageIcon(resource:"icons/cut.png", class:controller.getClass()),
			shortDescription: 'Delete'
		)
		
		action(id: 'refreshAction',
			name: 'Refresh',
			closure: controller.&refreshTodo,
			mnemonic: 'R',
			//accelerator: shortcut('R'),
			smallIcon: imageIcon(resource:"icons/refresh.png", class:controller.getClass()),
			shortDescription: 'Refresh Data'
		)