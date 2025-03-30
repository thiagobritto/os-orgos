package com.orgos.os.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.model.SessaoUsuario;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.util.AppFactory;
import com.orgos.os.view.LoginScreen;

public class LoginController implements Controller{
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	private LoginScreen screen;
	private UsuarioService usuarioService;
	private int countLogin = 1;
	
	public LoginController(LoginScreen screen, UsuarioService usuarioService) {
		super();
		this.screen = screen;
		this.usuarioService = usuarioService;
		iniciarController();
	}
	
	private void iniciarController() {
		screen.setController(this);
	}

	@Override
	public void inicializar() {
		
	}

	public void autenticar(String username, String password) {
		Usuario usuario = usuarioService.autenticar(username, password);
		if (usuario != null) {
			SessaoUsuario.getInstancia().setUsuarioLogado(usuario);
			AppFactory.getDashboardScreen().setVisible(true);
			screen.close();
			logger.info("Usuário '{}' fez login na tentativa '{}'.", username, countLogin);
		} else {
			screen.exibirMensagemErro("Usuário ou senha invalidos!");
			logger.error("Usuário '{}' tentou logar '{}' vezes.", username, countLogin++);
		}
	}

	

}
