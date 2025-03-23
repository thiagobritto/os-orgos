package com.orgos.os.controller;

import com.orgos.os.model.OperacaoResultado;
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
		OperacaoResultado resultado = usuarioService.cadastrarUsuario(username, password);
		if (resultado.isSucesso()) {
            screen.mostrarMensagem(resultado.getMensagem());
        } else {
            screen.mostrarMensagem(resultado.getMensagem());
        }
	}
	
}