package com.orgos.os.service;

import java.util.List;

import com.orgos.os.dao.UsuarioDAO;
import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;

public class UsuarioService {
	private UsuarioDAO usuarioDAO;

	public UsuarioService(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public Usuario autenticar(String username, String password) {
		return usuarioDAO.autenticar(username, password);
	}

	public List<Usuario> listarTodos() {
		return usuarioDAO.listarTodos();
	}

	public Usuario buscarPorId(int usuarioId) {
		return usuarioDAO.buscarPorId(usuarioId);
	}

	public List<Usuario> buscarTodosPorNome(String nome) {
		return usuarioDAO.buscarUsuariosPorNome(nome);
	}

	public List<Permissao> buscarPermissoes(int usuarioId) {
		return usuarioDAO.buscarPermissoes(usuarioId);
	}

	public OperacaoResultado cadastrarUsuario(String username, String password) {
		return usuarioDAO.cadastrarUsuario(username, password);
	}

	public OperacaoResultado trocarSenha(int usuarioId, String password) {
		return usuarioDAO.trocarSenha(usuarioId, password);
	}

	public OperacaoResultado removerUsuario(int usuarioId) {
		return usuarioDAO.remover(usuarioId);
	}

	public OperacaoResultado adicionarPermissao(int usuarioId, Funcionalidade funcionalidade) {
		return usuarioDAO.adicionarPermissao(usuarioId, funcionalidade);
	}

	public OperacaoResultado removerPermissao(int usuarioId, Funcionalidade funcionalidade) {
		return usuarioDAO.removerPermissao(usuarioId, funcionalidade);
	}

}
