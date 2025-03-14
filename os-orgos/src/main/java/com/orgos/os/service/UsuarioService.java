package com.orgos.os.service;

import com.orgos.os.dao.UsuarioDAO;
import com.orgos.os.model.Usuario;

public class UsuarioService {
	private UsuarioDAO usuarioDAO;

	public UsuarioService() {
		this.usuarioDAO = new UsuarioDAO();
	}

	// Método para autenticar o usuário
	public Usuario autenticar(String username, String password) {
		return usuarioDAO.autenticar(username, password);
	}

	// Método para cadastrar um novo usuário
	public boolean cadastrarUsuario(String username, String password) {
		//return usuarioDAO.cadastrarUsuario(username, password);
		return false;
	}
}
