package com.orgos.os.util;

import javax.swing.JFrame;

import com.orgos.os.controller.CadastroClienteController;
import com.orgos.os.controller.CadastroOrdemServicoController;
import com.orgos.os.controller.CadastroTecnicoController;
import com.orgos.os.controller.DashboardController;
import com.orgos.os.controller.LoginController;
import com.orgos.os.dao.impl.ClienteDaoImpl;
import com.orgos.os.dao.impl.TecnicoDaoImpl;
import com.orgos.os.dao.impl.UsuarioDaoImpl;
import com.orgos.os.service.BackupService;
import com.orgos.os.service.ClienteService;
import com.orgos.os.service.TecnicoService;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.CadastroClienteScreen;
import com.orgos.os.view.CadastroOrdemServicoScreen;
import com.orgos.os.view.CadastroTecnicoScreen;
import com.orgos.os.view.DashboardScreen;
import com.orgos.os.view.LoginScreen;

public class AppFactory {

	// Instâncias únicas (Singleton)
	private static ClienteValidator clienteValidator = new ClienteValidator();
	private static TecnicoValidator tecnicoValidator = new TecnicoValidator();
	
	private static ClienteSanitizer clienteSanitizer = new ClienteSanitizer();	
	private static TecnicoSanitizer tecnicoSanitizer = new TecnicoSanitizer();	

	private static UsuarioDaoImpl usuarioDAO = new UsuarioDaoImpl();
	private static ClienteDaoImpl clienteDAO = new ClienteDaoImpl();
	private static TecnicoDaoImpl tecnicoDAO = new TecnicoDaoImpl();

	private static BackupService backupService = new BackupService();
	private static UsuarioService usuarioService = new UsuarioService(usuarioDAO);
	private static ClienteService clienteService = new ClienteService(clienteValidator, clienteSanitizer, clienteDAO);
	private static TecnicoService tecnicoService = new TecnicoService(tecnicoValidator, tecnicoSanitizer, tecnicoDAO);

	/**
	 * Devolve uma {@code JFrame} - 'LoginScreen'
	 * 
	 * @return A janela {@code LoginScreen}.
	 */
	public static LoginScreen getLoginScreen() {
		LoginScreen loginScreen = new LoginScreen();
		LoginController loginController = new LoginController(loginScreen, usuarioService);
		loginController.inicializar();
		return loginScreen;
	}

	/**
	 * Devolve uma {@code JFrame} - 'DashboardScreen'
	 * 
	 * @return A janela {@code DashboardScreen}.
	 */
	public static DashboardScreen getDashboardScreen() {
		DashboardScreen dashboardScreen = new DashboardScreen();
		DashboardController dashboardController = new DashboardController(dashboardScreen, backupService);
		dashboardController.inicializar();
		return dashboardScreen;
	}

	/**
	 * Devolve uma {@code JDialog} - 'CadastroClienteScreen'
	 * 
	 * @param owner O parente {@code JFreame} da janela
	 *              {@code CadastroClienteScreen}.
	 * @return A janela {@code CadastroClienteScreen}.
	 */
	public static CadastroClienteScreen getCadastroClienteScreen(JFrame owner) {
		CadastroClienteScreen cadastroClienteScreen = new CadastroClienteScreen(owner);
		CadastroClienteController cadastroClienteController = new CadastroClienteController(cadastroClienteScreen,
				clienteService);
		cadastroClienteController.inicializar();
		return cadastroClienteScreen;
	}

	/**
	 * Devolve uma {@code JDialog} - 'CadastroTecnicoScreen'
	 * 
	 * @param owner O parente {@code JFreame} da janela
	 *              {@code CadastroTecnicoScreen}.
	 * @return A janela {@code CadastroTecnicoScreen}.
	 */
	public static CadastroTecnicoScreen getCadastroTecnicoScreen(JFrame owner) {
		CadastroTecnicoScreen cadastroTecnicoScreen = new CadastroTecnicoScreen(owner);
		CadastroTecnicoController cadastroTecnicoController = new CadastroTecnicoController(cadastroTecnicoScreen,
				tecnicoService);
		cadastroTecnicoController.inicializar();
		return cadastroTecnicoScreen;
	}

	/**
	 * Devolve uma {@code JInternalFrame} - 'CadastroOrdemServicoScreen'
	 * 
	 * @return A janela {@code CadastroOrdemServicoScreen}.
	 */
	public static CadastroOrdemServicoScreen getCadastroOrdemServicoScreen() {
		CadastroOrdemServicoScreen cadastroOrdemServicoScreen = new CadastroOrdemServicoScreen();
		CadastroOrdemServicoController cadastroOrdemServicoController = new CadastroOrdemServicoController(
				cadastroOrdemServicoScreen, clienteService, tecnicoService);
		cadastroOrdemServicoController.inicializar();
		return cadastroOrdemServicoScreen;
	}

}
