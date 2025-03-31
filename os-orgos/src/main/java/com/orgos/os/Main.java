package com.orgos.os;

import java.awt.EventQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.formdev.flatlaf.FlatLightLaf;
import com.orgos.os.util.AppFactory;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(Main::start);
	}

	private static void start() {
		try {
			FlatLightLaf.setup();
			AppFactory.getLoginScreen().setVisible(true);
		} catch (Exception e) {
			logger.error("Erro ao iniciar aplicação: " + e.getMessage(), e);
		}
	}

}
