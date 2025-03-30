package com.orgos.os.view.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SearchPanel<T> extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int TITLE_TOP = 0;
	public static final int TITLE_LEFT = 1;

	private JLabel label = new JLabel();
	private JComboBox<T> combobox = new JComboBox<>();
	private JButton button = new JButton("...");

	private int hgap = 5;
	private int vgap = 5;

	public SearchPanel() {
		setLayout(new BorderLayout(hgap, vgap));
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(label, BorderLayout.NORTH);
		add(combobox, BorderLayout.CENTER);
		add(button, BorderLayout.EAST);
	}

	public SearchPanel(String title) {
		this();
		setTitle(title);
	}

	public SearchPanel(String title, int titleOption) {
		this();
		setTitle(title);
		setTitlePosition(titleOption);
	}

	public <R extends List<T>> SearchPanel(String title, R items) {
		this();
		setTitle(title);
		setItems(items);
	}

	public <R extends List<T>> SearchPanel(String title, R items, int titleOption) {
		this();
		setTitle(title);
		setItems(items);
		setTitlePosition(titleOption);
	}
	
	
	public <R extends List<T>> SearchPanel(R items) {
		this();
		setItems(items);
	}

	public void setTitle(String title) {
		label.setText(title);
	}

	public void setTitlePosition(int titleOption) {
		remove(label);
		switch (titleOption) {
		case TITLE_LEFT -> add(label, BorderLayout.WEST);
		case TITLE_TOP -> add(label, BorderLayout.NORTH);
		default -> add(label, BorderLayout.NORTH);
		}
	}

	public <R extends List<T>> void setItems(R items) {
		DefaultComboBoxModel<T> defaultComboBoxModel = new DefaultComboBoxModel<>();
		items.forEach(item -> defaultComboBoxModel.addElement(item));
		combobox.setModel(defaultComboBoxModel);
	}

	public void addActionListener(ActionListener L) {
		button.addActionListener(L);
	}

	@SuppressWarnings("unchecked")
	public T getSelectedItem() {
		return (T) combobox.getSelectedItem();
	}

}
