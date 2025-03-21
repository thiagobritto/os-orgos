package com.orgos.os.controller;

import com.orgos.os.dao.UsuarioDAO;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.SenhaScreen;

public class SenhaController {
	private SenhaScreen view;
	private Usuario usuario;
	private UsuarioService usuarioServico;

	public SenhaController(SenhaScreen view, Usuario usuario) {
		super();
		this.view = view;
		this.usuario = usuario;
		this.usuarioServico = new UsuarioService(new UsuarioDAO());
	}

	public void alterarSenha(String password) {
		int usuarioId = usuario.getId();
		boolean resposta = usuarioServico.trocarSenha(usuarioId, password);
		if (resposta) {
			view.exibirMensagem("Senha alterada com sucesso!");
			view.dispose();
		}
	}
	
	
}
