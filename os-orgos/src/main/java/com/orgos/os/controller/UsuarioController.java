package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;

public class UsuarioController {

	private UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	public boolean cadastrarUsuario(String username, String password) {
		return usuarioService.cadastrarUsuario(username, password);
	}

	public List<Usuario> listarTodos() {
		return usuarioService.listarTodos();
	}

	public List<Usuario> buscarUsuariosPorNome(String nome) {
		return usuarioService.buscarUsuariosPorNome(nome);
	}

	public Usuario buscarUsuarioPorId(int usuarioId) {
		return usuarioService.buscarUsuarioPorId(usuarioId);
	}

	public boolean removerUsuario(int usuarioId) {
		return usuarioService.removerUsuario(usuarioId);
	}

	public void adicionarPermissao(int id, Funcionalidade funcionalidade) {
		usuarioService.adicionarPermissao(id, funcionalidade);
	}

	public void removerPermissao(int id, Funcionalidade funcionalidade) {
		usuarioService.removerPermissao(id, funcionalidade);
	}

	public List<Permissao> buscarPermissoes(int id) {
		return usuarioService.buscarPermissoes(id);
	}
	
}
