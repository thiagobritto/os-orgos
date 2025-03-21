package com.orgos.os.controller;

import com.orgos.os.model.SessaoUsuario;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.util.AppFactory;
import com.orgos.os.view.LoginScreenInterface;

public class LoginController {
	private LoginScreenInterface screen;
	private UsuarioService usuarioService;

	public LoginController(LoginScreenInterface screen, UsuarioService usuarioService) {
		super();
		this.screen = screen;
		this.usuarioService = usuarioService;
	}

	public void autenticar(String username, String password) {
		Usuario usuario = usuarioService.autenticar(username, password);
		if (usuario != null) {
			SessaoUsuario.getInstancia().setUsuarioLogado(usuario);
			AppFactory.getDashboardScreen().setVisible(true);
			screen.close();
		} else {
			screen.exibirMensagemErro("Usuário ou senha invalidos!");
		}
	}

}
