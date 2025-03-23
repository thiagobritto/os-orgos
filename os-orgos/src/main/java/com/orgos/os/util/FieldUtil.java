package com.orgos.os.util;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FieldUtil {
	
	private static final Logger logger = LogManager.getLogger(FieldUtil.class);

	public static  void applyMask(JFormattedTextField field, String mask) {
		try {
			MaskFormatter formatter = new MaskFormatter(mask);
			formatter.setValueContainsLiteralCharacters(false);
			formatter.install(field);
		} catch (ParseException e) {
			logger.error("Erro ao aplicar mascara: " + e.getMessage(), e);
		}
	}
}
