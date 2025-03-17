package com.orgos.os.view;

import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public abstract class JDialogScreen extends JDialog {

	private static final long serialVersionUID = 1L;

	public JDialogScreen(JFrame owner, boolean modal) {
		super(owner, modal);
		getRootPane().registerKeyboardAction( e -> dispose(),
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

}
