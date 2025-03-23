package com.orgos.os.controller;

import com.orgos.os.model.OperacaoResultado;
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
		OperacaoResultado resultado = usuarioService.trocarSenha(usuarioId, password);
		if (resultado.isSucesso()) {
			screen.exibirMensagem(resultado.getMensagem());
			screen.dispose();
		}
	}
	
	
}
