package com.orgos.os.view;

import java.awt.Frame;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public abstract class AbstractModalScreen extends JDialog {

	private static final long serialVersionUID = 1L;

	public AbstractModalScreen(Frame owner, boolean modal) {
		super(owner, modal);
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		KeyStroke keyEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(e -> dispose(), keyEscape, JComponent.WHEN_IN_FOCUSED_WINDOW);
	}

	

	
}
