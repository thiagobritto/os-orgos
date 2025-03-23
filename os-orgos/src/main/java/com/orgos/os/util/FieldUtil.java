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

public class FieldUtil {

	private static final Logger logger = LogManager.getLogger(FieldUtil.class);

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
	
	public static void numericFilter(JTextField field) {
		 ((AbstractDocument) field.getDocument()).setDocumentFilter(new NumericFilter());
	}

	// Classe para restringir a entrada de caracteres apenas para números
	private static class NumericFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
            if (text.matches("\\d+")) { // Permite apenas dígitos (0-9)
                super.insertString(fb, offset, text, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr) throws BadLocationException {
            if (text.matches("\\d+")) { // Permite apenas dígitos (0-9)
                super.replace(fb, offset, length, text, attr);
            }
        }
	}
}
