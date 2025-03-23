package com.orgos.os.controller;

import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.SenhaScreen;

public class SenhaController {
	private SenhaScreen screen;
	private UsuarioService usuarioService;

	public SenhaController(SenhaScreen screen, UsuarioService usuarioService) {
		super();
		this.screen = screen;
		this.usuarioService = usuarioService;
	}

	public void alterarSenha(int usuarioId, String password) {
		boolean sucesso = usuarioService.trocarSenha(usuarioId, password);
		if (sucesso) {
			screen.exibirMensagem("Senha alterada com sucesso!");
			screen.dispose();
		}
	}
	
	
}
