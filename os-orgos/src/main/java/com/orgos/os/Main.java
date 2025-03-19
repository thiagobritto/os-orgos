package com.orgos.os;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;
import com.orgos.os.util.AppFactory;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatLightLaf.setup();
					abrirLoginScreen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void abrirLoginScreen() {
		AppFactory.getLoginScreen().setVisible(true);
	}

}
