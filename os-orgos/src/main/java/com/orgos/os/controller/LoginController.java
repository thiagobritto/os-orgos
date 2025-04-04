package com.orgos.os.controller;

import java.awt.event.ActionEvent;

import com.orgos.os.model.SessaoUsuario;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.util.AppFactory;
import com.orgos.os.view.LoginScreen;

public class LoginController implements Controller {

	private LoginScreen screen;
	private UsuarioService usuarioService;

	public LoginController(LoginScreen screen, UsuarioService usuarioService) {
		this.screen = screen;
		this.usuarioService = usuarioService;
	}

	@Override
	public void inicializar() {
		screen.addLoginListener(this::login);
		screen.addCancelarListener(this::cancelar);
	}

	private void login(ActionEvent actionevent1) {
		String usuario = screen.getUsuario();
		String senha = screen.getSenha();

		Usuario usuarioAuth = usuarioService.autenticar(usuario, senha);
		if (usuarioAuth == null) {
			screen.exibirMensagemErro("Usuário ou senha invalidos!");
		} else {
			SessaoUsuario.getInstancia().setUsuarioLogado(usuarioAuth);
			AppFactory.getDashboardScreen().setVisible(true);
			screen.dispose();
		}
	}

	private void cancelar(ActionEvent actionevent1) {
		screen.dispose();
	}

}
