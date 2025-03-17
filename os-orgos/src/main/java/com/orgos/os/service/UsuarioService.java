package com.orgos.os.service;

import java.util.List;

import com.orgos.os.dao.UsuarioDAO;
import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;

public class UsuarioService {
	private UsuarioDAO usuarioDAO;

	public UsuarioService() {
		this.usuarioDAO = new UsuarioDAO();
	}

	public Usuario autenticar(String username, String password) {
		return usuarioDAO.autenticar(username, password);
	}


	public List<Usuario> listarTodos() {
		return usuarioDAO.listarTodos();
	}

	public Usuario buscarUsuarioPorId(int usuarioId) {
		return usuarioDAO.buscarUsuarioPorId(usuarioId);
	}

	public List<Usuario> buscarUsuariosPorNome(String nome) {
		return usuarioDAO.buscarUsuariosPorNome(nome);
	}

	public List<Permissao> buscarPermissoes(int usuarioId) {
		return usuarioDAO.buscarPermissoes(usuarioId);
	}

	public boolean cadastrarUsuario(String username, String password) {
		return usuarioDAO.cadastrarUsuario(username, password);
	}
	
	public boolean trocarSenha(int usuarioId, String password) {
		return usuarioDAO.trocarSenha(usuarioId, password);
	}
	
	public boolean removerUsuario(int usuarioId) {
		return usuarioDAO.removerUsuario(usuarioId);
	}

	public boolean adicionarPermissao(int usuarioId, Funcionalidade funcionalidade) {
		return usuarioDAO.adicionarPermissao(usuarioId, funcionalidade);
	}

	public boolean removerPermissao(int usuarioId, Funcionalidade funcionalidade) {
		return usuarioDAO.removerPermissao(usuarioId, funcionalidade);
	}

}
