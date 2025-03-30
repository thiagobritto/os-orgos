package com.orgos.os.view.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.text.JTextComponent;

public class TextFieldPanel<T extends JTextComponent> extends JPanel {

	private static final long serialVersionUID = 1L;
	public static final int TITLE_TOP = 0;
	public static final int TITLE_LEFT = 1;
	public static final int TITLE_RIGHT = 2;

	private JLabel labelTitle = new JLabel();
	private T textField;
	private JLabel labelError = new JLabel();

	private int hgap = 5;
	private int vgap = 5;

	public TextFieldPanel(T textField) {
		setTextField(textField);
		setLayout(new BorderLayout(hgap, vgap));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(labelTitle, BorderLayout.NORTH);
		add(textField, BorderLayout.CENTER);
		add(labelError, BorderLayout.SOUTH);
		labelError.setForeground(Color.RED);
	}

	public TextFieldPanel(String title, T textField) {
		this(textField);
		setTitle(title);
	}

	public TextFieldPanel(String title, T textField, int titleOption) {
		this(textField);
		setTitle(title);
		setTitlePosition(titleOption);
	}

	public String getTitle() {
		return labelTitle.getText();
	}

	public void setTitle(String string) {
		labelTitle.setText(string);
	}

	public void setTitlePosition(int titleOption) {
		remove(labelTitle);
		switch (titleOption) {
		case TITLE_LEFT -> add(labelTitle, BorderLayout.WEST);
		case TITLE_RIGHT -> add(labelTitle, BorderLayout.EAST);
		case TITLE_TOP -> add(labelTitle, BorderLayout.NORTH);
		default -> add(labelTitle, BorderLayout.NORTH);
		}
	}

	public void setTextField(T textField) {
		this.textField = textField;
	}

	public T getTextField() {
		return textField;
	}

	public String getError() {
		return labelError.getText();
	}

	public void setError(String string) {
		labelError.setText(string);
		alertError(!string.trim().isEmpty());
	}

	private void alertError(boolean flag) {
		if (flag) {
			textField.setBorder(BorderFactory.createLineBorder(Color.RED));
		} else {
			textField.setBorder(UIManager.getBorder("TextField.border"));
		}
	}

}
