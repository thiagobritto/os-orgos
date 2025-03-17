package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.GerenciarUsuariosScreen;

public class GerenciarUsuariosController {
	private final String[] dadosPesquisa = { "Código", "Nome" };
	private GerenciarUsuariosScreen view;
	private UsuarioService usuarioService;
	private List<Usuario> usuarios;
	private Usuario usuario;

	public GerenciarUsuariosController(GerenciarUsuariosScreen view) {
		super();
		this.view = view;
		this.usuarioService = new UsuarioService();
	}

	public void carregarDadosPesquisa() {
		view.exibirDadosPesquisa(dadosPesquisa);
	}

	public void carregarUsuarios() {
		usuarios = usuarioService.listarTodos();
		view.atualizarListaUsuarios(usuarios);
	}
	
	public void setUsuario(int usuarioIndex) {
		usuario =  usuarios.get(usuarioIndex);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

}
