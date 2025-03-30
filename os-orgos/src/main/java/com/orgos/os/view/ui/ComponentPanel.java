package com.orgos.os.view.ui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComponentPanel {

	public static JPanel createTitlaPanel(String title) {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.add(new JLabel(title), BorderLayout.NORTH);
		return panel;
	}
}
