package com.orgos.os;

import java.awt.EventQueue;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.orgos.os.view.LoginScreen;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatMacLightLaf.setup();
					new LoginScreen().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
