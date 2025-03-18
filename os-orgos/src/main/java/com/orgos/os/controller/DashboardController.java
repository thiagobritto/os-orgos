package com.orgos.os.controller;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Usuario;
import com.orgos.os.util.PermissaoUtil;
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
		view.exibirDadosUsuario(getUsuario().getUsername(), getUsuario().getId());
	}

	public boolean usuarioTemPermissao(Funcionalidade funcionalidade) {
		return PermissaoUtil.temPermissao(getUsuario(), funcionalidade);
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	
}
