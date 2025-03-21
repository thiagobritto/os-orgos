package com.orgos.os.controller;

import com.orgos.os.view.CadastroUsuarioScreen;

public class CadastroUsuarioController {
	private CadastroUsuarioScreen view;
	private UsuarioController usuarioController;

	public CadastroUsuarioController(CadastroUsuarioScreen view, UsuarioController usuarioController) {
		super();
		this.view = view;
		this.usuarioController = usuarioController;
	}

	public void cadastrarUsuario(String username, String password) {
		boolean sucesso = usuarioController.cadastrarUsuario(username, password);
		if (sucesso) {
            view.mostrarMensagem("Usuário cadastrado com sucesso!");
        } else {
            view.mostrarMensagem("Erro ao cadastrar usuário. Verifique se o username já existe.");
        }
	}
	
}
