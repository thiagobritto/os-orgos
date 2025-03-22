package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.PesquisaUsuario;
import com.orgos.os.model.PesquisaUsuarioId;
import com.orgos.os.model.PesquisaUsuarioNome;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.GerenciarUsuariosScreenInterface;

public class GerenciarUsuariosController {
	private GerenciarUsuariosScreenInterface screen;
	private UsuarioService usuarioService;

	public GerenciarUsuariosController(GerenciarUsuariosScreenInterface screen, UsuarioService usuarioService) {
		super();
		this.screen = screen;
		this.usuarioService = usuarioService;
	}

	public void carregarTela() {
		PesquisaUsuarioId pesquisaUsuarioId = new PesquisaUsuarioId(usuarioService);
		PesquisaUsuarioNome pesquisaUsuarioNome = new PesquisaUsuarioNome(usuarioService);
		
		PesquisaUsuario[] pesquisasUsuario = { pesquisaUsuarioId, pesquisaUsuarioNome };
		screen.setPesquisa(pesquisasUsuario);

		List<Usuario> usuarios = usuarioService.listarTodos();
		screen.exibirUsuarios(usuarios);
	}

	public void atualizarPermissao(Usuario usuario, Funcionalidade funcionalidade, boolean permitir) {
		int id = usuario.getId();
		
		if (permitir) {
			usuarioService.adicionarPermissao(id, funcionalidade);
		} else {
			usuarioService.removerPermissao(id, funcionalidade);
		}

		List<Permissao> permissoes = usuarioService.buscarPermissoes(id);
		usuario.setPermissoes(permissoes);
	}

}