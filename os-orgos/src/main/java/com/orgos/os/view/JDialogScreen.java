package com.orgos.os.view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public abstract class JDialogScreen extends JDialog {

	private static final long serialVersionUID = 1L;

	public JDialogScreen(JFrame owner, boolean modal) {
		super(owner, modal);
		
		KeyStroke keyEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(this::close, keyEscape, JComponent.WHEN_IN_FOCUSED_WINDOW);
	}
	
	private void close(ActionEvent event) {
		dispose();
	}

}
