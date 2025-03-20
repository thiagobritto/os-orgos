package com.orgos.os.util;

import com.orgos.os.controller.DashboardController;
import com.orgos.os.controller.LoginController;
import com.orgos.os.service.BackupService;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.DashboardScreen;
import com.orgos.os.view.LoginScreen;

public class AppFactory {

	// Instâncias únicas (Singleton)
	private static UsuarioService usuarioService = new UsuarioService();
	private static BackupService backupService = new BackupService();

	private static LoginScreen loginScreen = new LoginScreen(null);
	private static LoginController loginController = new LoginController(loginScreen, usuarioService);

	private static DashboardScreen dashboardScreen = new DashboardScreen(null);
	private static DashboardController dashboardController = new DashboardController(dashboardScreen, backupService);

	// Métodos para obter instâncias
	public static LoginScreen getLoginScreen() {
		loginScreen.setController(loginController);
		return loginScreen;
	}

	public static DashboardScreen getDashboardScreen() {
		dashboardScreen.setController(dashboardController);
		return dashboardScreen;
	}

}
