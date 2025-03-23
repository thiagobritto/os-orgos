package com.orgos.os.controller;

import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.CadastroUsuarioScreen;

public class CadastroUsuarioController {
	private CadastroUsuarioScreen screen;
	private UsuarioService usuarioService;

	public CadastroUsuarioController(CadastroUsuarioScreen screen, UsuarioService usuarioService) {
		super();
		this.screen = screen;
		this.usuarioService = usuarioService;
	}

	public void cadastrarUsuario(String username, String password) {
		boolean sucesso = usuarioService.cadastrarUsuario(username, password);
		if (sucesso) {
            screen.mostrarMensagem("Usuário cadastrado com sucesso!");
        } else {
            screen.mostrarMensagem("Erro ao cadastrar usuário. Verifique se o username já existe.");
        }
	}
	
}