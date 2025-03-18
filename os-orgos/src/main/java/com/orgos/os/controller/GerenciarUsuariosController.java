package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.util.PermissaoUtil;
import com.orgos.os.view.GerenciarUsuariosScreen;

public class GerenciarUsuariosController {
	private final String[] indices = { "Código", "Nome" };
	private GerenciarUsuariosScreen view;
	private UsuarioService usuarioService;
	private List<Usuario> usuarios;
	private Usuario usuarioOperador;
	private Usuario usuario;

	public GerenciarUsuariosController(GerenciarUsuariosScreen view, Usuario usuarioOperador) {
		super();
		this.view = view;
		this.usuarioOperador = usuarioOperador;
		this.usuarioService = new UsuarioService();
	}

	public void carregarDadosPesquisa() {
		view.exibirDadosPesquisa(indices);
	}

	public void carregarUsuarios() {
		usuarios = usuarioService.listarTodos();
		view.atualizarListaUsuarios(usuarios);
	}

	public void setUsuario(int usuarioIndex) {
		usuario = usuarios.get(usuarioIndex);
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public boolean usuarioSelecionado() {
		return usuario != null;
	}

	public void buscarUsuario(String pesquisa, int index) {
		String indice = indices[index];
		
		if ("".equals(pesquisa.trim())) {
			carregarUsuarios();
		} else if ("Código".equals(indice)) {
			int usuarioId = 0;
			try {
				usuarioId = Integer.parseInt(pesquisa);
			} catch (NumberFormatException e) {
				return;
			}

			Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
			if (usuario != null) {
				usuarios = List.of(usuario);
				view.atualizarListaUsuarios(usuarios);
			}
		} else if ("Nome".equals(indice)) {
			usuarios = usuarioService.buscarUsuariosPorNome(pesquisa);
			view.atualizarListaUsuarios(usuarios);
		}

		usuario = null;
	}

	public void removerUsuario() {
		int usuarioId = usuario.getId();
		usuarioService.removerUsuario(usuarioId);
		
		view.exibirMenssagem("Usuario excluido com sucesso!");
		carregarUsuarios();
	}

	public boolean usuarioTemPermissao(Funcionalidade funcionalidade) {
		return PermissaoUtil.temPermissao(usuarioOperador, funcionalidade);
	}

}
