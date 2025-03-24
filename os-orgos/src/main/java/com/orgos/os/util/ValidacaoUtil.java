package com.orgos.os.util;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ValidacaoUtil {
	
	public static boolean isEmpty(JTextField field) {
		if (field.getText().trim().isEmpty()) {
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
			field.requestFocus();
			return true;
		}
		field.setBorder(UIManager.getBorder("TextField.border"));
		return false;
	}
	
	public static boolean isEmpty(JTextArea field) {
		if (field.getText().trim().isEmpty()) {
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
			field.requestFocus();
			return true;
		}
		field.setBorder(UIManager.getBorder("TextField.border"));
		return false;
	}
	
	public static boolean telefone(JFormattedTextField field) {
		if (field.getText().replaceAll("\\D", "").length() < 10) {
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
			field.requestFocus();
			return false;
		}
		field.setBorder(UIManager.getBorder("TextField.border"));
		return true;
	}
	
	public static boolean celular(JFormattedTextField field) {
		if (field.getText().replaceAll("\\D", "").length() < 11) {
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
			field.requestFocus();
			return false;
		}
		field.setBorder(UIManager.getBorder("TextField.border"));
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Validação de campo obrigatório
	public static boolean validarObrigatorio(JTextField field, JLabel labelErro) {
		if (field.getText().trim().isEmpty()) {
			labelErro.setText("Campo obrigatório");
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
			return false;
		}
		labelErro.setText("");
		field.setBorder(UIManager.getBorder("TextField.border"));
		return true;
	}

	// Validação de email
	public static boolean validarEmail(JTextField field, JLabel labelErro) {
		String email = field.getText().trim();
		if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-z]{2,}$")) {
			labelErro.setText("Email inválido");
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
			return false;
		}
		labelErro.setText("");
		field.setBorder(UIManager.getBorder("TextField.border"));
		return true;
	}

	// Validação de número decimal
	public static boolean validarDecimal(JTextField field, JLabel labelErro) {
		try {
			Double.parseDouble(field.getText().trim());
			labelErro.setText("");
			field.setBorder(UIManager.getBorder("TextField.border"));
			return true;
		} catch (NumberFormatException e) {
			labelErro.setText("Valor deve ser numérico");
			field.setBorder(BorderFactory.createLineBorder(Color.RED));
			return false;
		}
	}
}
