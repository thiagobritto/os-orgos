package com.orgos.os.controller;

import com.orgos.os.model.Usuario;
import com.orgos.os.view.DashboardScreen;

public class DashboardController {
	private DashboardScreen view;
	private Usuario usuario;
	
	public DashboardController(DashboardScreen view, Usuario usuario) {
		super();
		this.view = view;
		this.usuario = usuario;
	}
	
	public void carregarDadosUsuario() {
		view.exibirDadosUsuario(usuario.getUsername(), usuario.getId());
	}
}
