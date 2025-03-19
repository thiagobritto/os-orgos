package com.orgos.os.util;

import com.orgos.os.controller.DashboardController;
import com.orgos.os.controller.LoginController;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.BackupService;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.DashboardScreen;
import com.orgos.os.view.LoginScreen;

public class AppFactory {

	// Instâncias únicas (Singleton)
	private static UsuarioService usuarioService = new UsuarioService();
	private static BackupService backupService = new BackupService();

	private static LoginScreen loginScreen;
	private static LoginController loginController;
	
	private static DashboardScreen dashboardScreen;
	private static DashboardController dashboardController;

	// Métodos para obter instâncias
	public static LoginScreen getLoginScreen() {
		if (loginScreen == null)
			loginScreen = new LoginScreen(null);
		if (loginController == null)
			loginController = new LoginController(loginScreen, usuarioService);
		
		loginScreen.setController(loginController);
		return loginScreen;
	}

	public static DashboardScreen getDashboardScreen(Usuario usuario) {
		if (dashboardScreen == null)
			dashboardScreen = new DashboardScreen(null, usuario);	
		if (dashboardController == null)
			dashboardController = new DashboardController(dashboardScreen, backupService);
		
		dashboardScreen.setController(dashboardController);
		return dashboardScreen;
	}

}
