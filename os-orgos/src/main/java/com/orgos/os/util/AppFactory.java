package com.orgos.os.util;

import com.orgos.os.controller.CadastroUsuarioController;
import com.orgos.os.controller.DashboardController;
import com.orgos.os.controller.GerenciarUsuariosController;
import com.orgos.os.controller.LoginController;
import com.orgos.os.dao.UsuarioDAO;
import com.orgos.os.service.BackupService;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.CadastroUsuarioScreen;
import com.orgos.os.view.DashboardScreen;
import com.orgos.os.view.GerenciarUsuariosScreen;
import com.orgos.os.view.LoginScreen;

public class AppFactory {

	// Instâncias únicas (Singleton)
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();

	private static UsuarioService usuarioService = new UsuarioService(usuarioDAO);
	private static BackupService backupService = new BackupService();

	private static LoginScreen loginScreen = new LoginScreen(null);
	private static LoginController loginController = new LoginController(loginScreen, usuarioService);

	private static DashboardScreen dashboardScreen = new DashboardScreen(null);
	private static DashboardController dashboardController = new DashboardController(dashboardScreen, backupService);

	private static CadastroUsuarioScreen cadastroUsuarioScreen = new CadastroUsuarioScreen(dashboardScreen, null);
	private static CadastroUsuarioController cadastroUsuarioController = new CadastroUsuarioController(
			cadastroUsuarioScreen, usuarioService);

	private static GerenciarUsuariosScreen gerenciarUsuariosScreen = new GerenciarUsuariosScreen(dashboardScreen, null);
	private static GerenciarUsuariosController gerenciarUsuariosController = new GerenciarUsuariosController(
			gerenciarUsuariosScreen, usuarioService);

	// Métodos para obter instâncias
	public static LoginScreen getLoginScreen() {
		loginScreen.setController(loginController);
		return loginScreen;
	}

	public static DashboardScreen getDashboardScreen() {
		dashboardScreen.setController(dashboardController);
		return dashboardScreen;
	}

	public static CadastroUsuarioScreen getCadastroUsuarioScreen() {
		cadastroUsuarioScreen.setController(cadastroUsuarioController);
		return cadastroUsuarioScreen;
	}

	public static GerenciarUsuariosScreen getGerenciarUsuariosScreen() {
		gerenciarUsuariosScreen.setController(gerenciarUsuariosController);
		return gerenciarUsuariosScreen;
	}

}
