package com.orgos.os.controller;

import javax.swing.JOptionPane;

import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.DashboardScreen;
import com.orgos.os.view.LoginScreen;

public class LoginController {
	private LoginScreen view;
	private UsuarioService usuarioService;

	public LoginController(LoginScreen view) {
		super();
		this.view = view;
		this.usuarioService = new UsuarioService();
	}

	public void autenticar(String username, String password) {
		Usuario usuario = usuarioService.autenticar(username, password);

		if (usuario != null) {
			new DashboardScreen(usuario).setVisible(true);
			view.dispose();
		} else {
			JOptionPane.showMessageDialog(view, "Usuário ou senha invalidos!", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

}
