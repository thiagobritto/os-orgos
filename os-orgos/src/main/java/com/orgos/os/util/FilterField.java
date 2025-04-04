package com.orgos.os.util;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FilterField {

	private static final Logger logger = LogManager.getLogger(FilterField.class);

	/**
	 * Aplica mascara de numero
	 * 
	 * @param field O campo {@code JFormattedTextField} que a mascara sera aplicada
	 * @param mask  O padrão da mascara
	 */
	public static void applyMask(JFormattedTextField field, String mask) {
		try {
			MaskFormatter formatter = new MaskFormatter(mask);
			formatter.setPlaceholderCharacter('_');
			formatter.setValueContainsLiteralCharacters(false);
			formatter.install(field);
		} catch (ParseException e) {
			logger.error("Erro ao aplicar mascara: " + e.getMessage(), e);
		}
	}

	/**
	 * Aplica filtro de número inteiro
	 * 
	 * @param field O campo {@code JTextField} que recebe o filtro
	 */
	public static void integer(JTextField field) {
		((AbstractDocument) field.getDocument()).setDocumentFilter(new IntegerFilter());
	}

	// Classe para restringir a entrada de caracteres apenas para números
	private static class IntegerFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
				throws BadLocationException {
			if (text.matches("\\d+")) { // Permite apenas dígitos (0-9)
				super.insertString(fb, offset, text, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr)
				throws BadLocationException {
			if (text.matches("\\d+")) { // Permite apenas dígitos (0-9)
				super.replace(fb, offset, length, text, attr);
			}
		}
	}

	/**
	 * Aplica filtro de número decimal
	 * 
	 * @param field O campo {@code JTextField} que recebe o filtro
	 */
	public static void decimal(JTextField field) {
		((AbstractDocument) field.getDocument()).setDocumentFilter(new DecimalFilter());
	}

	// Classe para restringir a entrada de caracteres apenas para: números, ',' ou
	// '.'
	private static class DecimalFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
				throws BadLocationException {
			if (text.matches("[0-9,\\.]")) {
				super.insertString(fb, offset, text, attr);
			}
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr)
				throws BadLocationException {
			if (text.matches("[0-9,\\.]")) {
				super.replace(fb, offset, length, text, attr);
			}
		}
	}
}
