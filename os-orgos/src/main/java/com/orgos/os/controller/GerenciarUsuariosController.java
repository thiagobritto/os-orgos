package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.GerenciarUsuariosScreen;

public class GerenciarUsuariosController {
	private final String[] modoPesquisa = { "Código", "Nome" };
	private GerenciarUsuariosScreen view;
	private UsuarioService usuarioService;
	private List<Usuario> usuarios;
	private Usuario usuario;

	public GerenciarUsuariosController(GerenciarUsuariosScreen view) {
		super();
		this.view = view;
		this.usuarioService = new UsuarioService();
	}

	public void carregarDadosPesquisa() {
		view.exibirDadosPesquisa(modoPesquisa);
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

	public void buscarUsuario(String pesquisa, int modoIndex) {
		String modo = modoPesquisa[modoIndex];
		pesquisa = pesquisa.trim();

		if ("".equals(pesquisa)) {
			carregarUsuarios();
		} else if ("Código".equals(modo)) {
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
		} else if ("Nome".equals(modo)) {
			usuarios = usuarioService.buscarUsuariosPorNome(pesquisa);
			view.atualizarListaUsuarios(usuarios);
		}

		usuario = null;
	}

	public void removerUsuario() {
		int usuarioId = usuario.getId();
		String nome = usuario.getUsername();
		boolean confirmacao = view.comfirmarExclusao(
				String.format("Tem certeza que deseja excluir este usuário? (%s - %s) ", usuarioId, nome));

		if (confirmacao) {
			usuarioService.removerUsuario(usuarioId);
			view.exibirMenssagem("Usuario excluido com sucesso!");
			carregarUsuarios();
		}
	}

}
