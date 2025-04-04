package com.orgos.os.service;

import com.orgos.os.dao.UsuarioDao;
import com.orgos.os.model.Usuario;

public class UsuarioService {
	private UsuarioDao usuarioDao;

	public UsuarioService(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public Usuario autenticar(String usuario, String senha) {
		return usuarioDao.autenticar(usuario, senha);
	}
}
