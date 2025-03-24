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
import com.orgos.os.model.Cliente;
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

	private static SenhaScreen senhaScreen = new SenhaScreen(dashboardScreen, null);
	private static SenhaController senhaController = new SenhaController(senhaScreen, usuarioService);
	
	private static CadastroClienteScreen cadastroClienteScreen = new CadastroClienteScreen(dashboardScreen, null);
	private static CadastroClienteController cadastroClienteController = new CadastroClienteController(cadastroClienteScreen, clienteService);
	
	private static BuscaClienteScreen buscaClienteScreen = new BuscaClienteScreen(dashboardScreen, null);
	private static BuscaClienteController buscaClienteController = new BuscaClienteController(buscaClienteScreen, clienteService);
	
	private static CadastroTecnicoScreen cadastroTecnicoScreen = new CadastroTecnicoScreen(dashboardScreen, null);
	private static CadastroTecnicoController cadastroTecnicoController = new CadastroTecnicoController(cadastroTecnicoScreen);
	
	private static BuscaTecnicoScreen buscaTecnicoScreen = new BuscaTecnicoScreen(dashboardScreen, null);
	private static BuscaTecnicoController buscaTecnicoController = new BuscaTecnicoController(buscaTecnicoScreen, tecnicoService);
	
	
	
	
	
	
	
	
	
	
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
	
	public static SenhaScreen getSenhaScreen(Usuario usuario) {
		senhaScreen.setController(senhaController);
		senhaScreen.setUsuario(usuario);		
		return null;
	}
	
	public static CadastroClienteScreen getCadastroClienteScreen() {
		cadastroClienteScreen.setController(cadastroClienteController);
		return cadastroClienteScreen;
	}
	
	public static CadastroClienteScreen getEditarClienteScreen(Cliente cliente) {
		cadastroClienteScreen.setController(cadastroClienteController);
		cadastroClienteScreen.setCliente(cliente);
		return cadastroClienteScreen;
	}
	
	public static BuscaClienteScreen getBuscaClienteScreen() {
		buscaClienteScreen.setController(buscaClienteController);
		return buscaClienteScreen;
	}
	
	public static CadastroTecnicoScreen getCadastroTecnicoScreen() {
		cadastroTecnicoScreen.setController(cadastroTecnicoController);
		return cadastroTecnicoScreen;
	}
	
	public static BuscaTecnicoScreen getBuscaTecnicoScreen() {
		buscaTecnicoScreen.setController(buscaTecnicoController);
		return buscaTecnicoScreen;
	}

}
