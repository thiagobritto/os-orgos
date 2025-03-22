package com.orgos.os.controller;

import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.CadastroUsuarioScreen;

public class CadastroUsuarioController {
	private CadastroUsuarioScreen view;
	private UsuarioService usuarioService;

	public CadastroUsuarioController(CadastroUsuarioScreen view, UsuarioService usuarioService) {
		super();
		this.view = view;
		this.usuarioService = usuarioService;
	}

	public void cadastrarUsuario(String username, String password) {
		boolean sucesso = usuarioService.cadastrarUsuario(username, password);
		if (sucesso) {
            view.mostrarMensagem("Usuário cadastrado com sucesso!");
        } else {
            view.mostrarMensagem("Erro ao cadastrar usuário. Verifique se o username já existe.");
        }
	}
	
}