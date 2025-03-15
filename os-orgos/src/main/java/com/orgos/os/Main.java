package com.orgos.os;

import java.awt.EventQueue;

import com.formdev.flatlaf.FlatLightLaf;
import com.orgos.os.view.LoginScreen;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatLightLaf.setup();
					new LoginScreen().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
