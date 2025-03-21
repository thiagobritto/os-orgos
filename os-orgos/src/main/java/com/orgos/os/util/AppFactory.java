package com.orgos.os.util;

import com.orgos.os.controller.CadastroUsuarioController;
import com.orgos.os.controller.DashboardController;
import com.orgos.os.controller.EditarPermissoesController;
import com.orgos.os.controller.GerenciarUsuariosController;
import com.orgos.os.controller.LoginController;
import com.orgos.os.controller.UsuarioController;
import com.orgos.os.dao.UsuarioDAO;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.BackupService;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.CadastroUsuarioScreen;
import com.orgos.os.view.DashboardScreen;
import com.orgos.os.view.EditarPermissoesScreen;
import com.orgos.os.view.GerenciarUsuariosScreen;
import com.orgos.os.view.LoginScreen;

public class AppFactory {

	// Instâncias únicas (Singleton)
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();

	private static UsuarioService usuarioService = new UsuarioService(usuarioDAO);
	private static BackupService backupService = new BackupService();

	private static UsuarioController usuarioController = new UsuarioController(usuarioService);

	private static LoginScreen loginScreen = new LoginScreen(null);
	private static LoginController loginController = new LoginController(loginScreen, usuarioService);

	private static DashboardScreen dashboardScreen = new DashboardScreen(null);
	private static DashboardController dashboardController = new DashboardController(dashboardScreen, backupService);

	private static CadastroUsuarioScreen cadastroUsuarioScreen = new CadastroUsuarioScreen(dashboardScreen, null);
	private static CadastroUsuarioController cadastroUsuarioController = new CadastroUsuarioController( cadastroUsuarioScreen, usuarioController);

	private static GerenciarUsuariosScreen gerenciarUsuariosScreen = new GerenciarUsuariosScreen(dashboardScreen, null);
	private static GerenciarUsuariosController gerenciarUsuariosController = new GerenciarUsuariosController( gerenciarUsuariosScreen, usuarioController);
	
	private static EditarPermissoesScreen editarPermissoesScreen = new EditarPermissoesScreen(dashboardScreen, null);
	private static EditarPermissoesController editarPermissoesController = new EditarPermissoesController( editarPermissoesScreen, usuarioController);
	
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
	
	public static EditarPermissoesScreen getEditarPermissoesScreen(Usuario usuario) {
		editarPermissoesScreen.setController(editarPermissoesController);
		editarPermissoesScreen.setUsuarioSelecionado(usuario);
		return editarPermissoesScreen;
	}

}
