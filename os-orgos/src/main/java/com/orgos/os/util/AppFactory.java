package com.orgos.os.util;

import com.orgos.os.controller.BuscaClienteController;
import com.orgos.os.controller.BuscaTecnicoController;
import com.orgos.os.controller.CadastroClienteController;
import com.orgos.os.controller.CadastroTecnicoController;
import com.orgos.os.controller.CadastroUsuarioController;
import com.orgos.os.controller.DashboardController;
import com.orgos.os.controller.GerenciarUsuariosController;
import com.orgos.os.controller.LoginController;
import com.orgos.os.controller.SenhaController;
import com.orgos.os.dao.ClienteDAO;
import com.orgos.os.dao.TecnicoDAO;
import com.orgos.os.dao.UsuarioDAO;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.BackupService;
import com.orgos.os.service.ClienteService;
import com.orgos.os.service.TecnicoService;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.BuscaClienteScreen;
import com.orgos.os.view.BuscaTecnicoScreen;
import com.orgos.os.view.CadastroClienteScreen;
import com.orgos.os.view.CadastroTecnicoScreen;
import com.orgos.os.view.CadastroUsuarioScreen;
import com.orgos.os.view.DashboardScreen;
import com.orgos.os.view.GerenciarUsuariosScreen;
import com.orgos.os.view.LoginScreen;
import com.orgos.os.view.SenhaScreen;

public class AppFactory {

	// Instâncias únicas (Singleton)
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();
	private static ClienteDAO clienteDAO = new ClienteDAO();
	private static TecnicoDAO tecnicoDAO = new TecnicoDAO();

	private static BackupService backupService = new BackupService();
	private static UsuarioService usuarioService = new UsuarioService(usuarioDAO);
	private static ClienteService clienteService = new ClienteService(clienteDAO);
	private static TecnicoService tecnicoService = new TecnicoService(tecnicoDAO);
	
	public static LoginScreen getLoginScreen() {
		LoginController loginController = new LoginController(new LoginScreen(), usuarioService);
		return loginController.getScreen();
	}

	private static DashboardScreen dashboardScreen;
	private static DashboardController dashboardController;

	private static CadastroUsuarioScreen cadastroUsuarioScreen;
	private static CadastroUsuarioController cadastroUsuarioController;

	private static GerenciarUsuariosScreen gerenciarUsuariosScreen;
	private static GerenciarUsuariosController gerenciarUsuariosController;

	private static SenhaScreen senhaScreen;
	private static SenhaController senhaController;

	private static CadastroClienteScreen cadastroClienteScreen;
	private static CadastroClienteController cadastroClienteController;

	private static BuscaClienteScreen buscaClienteScreen;
	private static BuscaClienteController buscaClienteController;

	private static CadastroTecnicoScreen cadastroTecnicoScreen;
	private static CadastroTecnicoController cadastroTecnicoController;

	private static BuscaTecnicoScreen buscaTecnicoScreen;
	private static BuscaTecnicoController buscaTecnicoController;

	// Métodos para obter instâncias
	

	public static DashboardScreen getDashboardScreen() {
		if (dashboardScreen == null)
			dashboardScreen = new DashboardScreen(null);
		if (dashboardController == null)
			dashboardController = new DashboardController(dashboardScreen, backupService);

		dashboardController.inicializar();
		return dashboardScreen;
	}

	public static CadastroUsuarioScreen getCadastroUsuarioScreen() {
		if (cadastroUsuarioScreen == null)
			cadastroUsuarioScreen = new CadastroUsuarioScreen(dashboardScreen, null);
		if (cadastroUsuarioController == null)
			cadastroUsuarioController = new CadastroUsuarioController(cadastroUsuarioScreen, usuarioService);

		cadastroUsuarioController.inicializar();
		return cadastroUsuarioScreen;
	}

	public static GerenciarUsuariosScreen getGerenciarUsuariosScreen() {
		if (gerenciarUsuariosScreen == null)
			gerenciarUsuariosScreen = new GerenciarUsuariosScreen(dashboardScreen, null);
		if (gerenciarUsuariosController == null)
			gerenciarUsuariosController = new GerenciarUsuariosController(gerenciarUsuariosScreen, usuarioService);

		gerenciarUsuariosController.inicializar();
		return gerenciarUsuariosScreen;
	}

	public static SenhaScreen getSenhaScreen(Usuario usuario) {
		senhaScreen = new SenhaScreen(dashboardScreen, usuario);
		senhaController = new SenhaController(senhaScreen, usuarioService);

		senhaController.inicializar();
		return senhaScreen;
	}

	public static CadastroClienteScreen getCadastroClienteScreen() {
		if (cadastroClienteScreen == null)
			cadastroClienteScreen = new CadastroClienteScreen(dashboardScreen, null);
		if (cadastroClienteController == null)
			cadastroClienteController = new CadastroClienteController(cadastroClienteScreen, clienteService);

		cadastroClienteController.inicializar();
		return cadastroClienteScreen;
	}

	public static BuscaClienteScreen getBuscaClienteScreen() {
		if (buscaClienteScreen == null)
			buscaClienteScreen = new BuscaClienteScreen(dashboardScreen, null);
		if (buscaClienteController == null)
			buscaClienteController = new BuscaClienteController(buscaClienteScreen, clienteService);

		buscaClienteController.inicializar();
		return buscaClienteScreen;
	}

	public static CadastroTecnicoScreen getCadastroTecnicoScreen() {
		if (cadastroTecnicoScreen == null)
			cadastroTecnicoScreen = new CadastroTecnicoScreen(dashboardScreen, null);
		if (cadastroTecnicoController == null)
			cadastroTecnicoController = new CadastroTecnicoController(cadastroTecnicoScreen, tecnicoService);

		cadastroTecnicoController.inicializar();
		return cadastroTecnicoScreen;
	}

	public static BuscaTecnicoScreen getBuscaTecnicoScreen() {
		if (buscaTecnicoScreen == null)
			buscaTecnicoScreen = new BuscaTecnicoScreen(dashboardScreen, null);
		if (buscaTecnicoController == null)
			buscaTecnicoController = new BuscaTecnicoController(buscaTecnicoScreen, tecnicoService);

		buscaTecnicoController.inicializar();
		return buscaTecnicoScreen;
	}

}
