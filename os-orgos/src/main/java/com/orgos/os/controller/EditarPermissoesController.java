package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.EditarPermissoesScreen;

public class EditarPermissoesController {
	private EditarPermissoesScreen view;
	private UsuarioService usuarioService;
	private Usuario usuario;
	
	public EditarPermissoesController(EditarPermissoesScreen view, Usuario usuario) {
		super();
		this.view = view;
		this.usuario = usuario;
		this.usuarioService = new UsuarioService();
	}

	public void carregarFuncionalidades() {
		Funcionalidade[] funcionalidades = Funcionalidade.values();
		view.exibirFuncionalidades(funcionalidades);
	}
	
	public boolean usuarioTemPermissao(Funcionalidade funcionalidade) {
		List<Permissao> permissoes = usuario.getPermissoes();
		return permissoes.stream().anyMatch(p -> p.getFuncionalidade().equals(funcionalidade));
	}

	public void atualizarPermissao(Funcionalidade funcionalidade, boolean permitir) {
		int usuarioId = usuario.getId();
		
		if (permitir) {
			usuarioService.adicionarPermissao(usuarioId, funcionalidade);
		} else {
			usuarioService.removerPermissao(usuarioId, funcionalidade);		
		}
		
		List<Permissao> permissoes = usuarioService.buscarPermissoes(usuarioId);
		usuario.setPermissoes(permissoes);
	}

	public void carregarDadosUsuario() {
		String username = usuario.getUsername();
		view.exibirDadosUsuario(username);
	}

}
