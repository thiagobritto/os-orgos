package com.orgos.os.controller;

import java.util.Collections;
import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;
import com.orgos.os.view.GerenciarUsuariosScreenInterface;

public class GerenciarUsuariosController {
	private final String[] CHAVES_PESQUISA = { "Código", "Nome" };

	private GerenciarUsuariosScreenInterface screen;
	private UsuarioController usuarioController;

	public GerenciarUsuariosController(GerenciarUsuariosScreenInterface screen, UsuarioController usuarioController) {
		super();
		this.screen = screen;
		this.usuarioController = usuarioController;
	}

	public void carregarTela() {
		screen.exibirChavesPesquisa(CHAVES_PESQUISA);

		List<Usuario> usuarios = usuarioController.listarTodos();
		screen.exibieUsuarios(usuarios);
	}

	public void buscarUsuarios(int chave, String valorDigitado) {
		String valor = valorDigitado.trim();
		List<Usuario> usuarios = Collections.emptyList();

		if (valor.isEmpty()) {
			usuarios = usuarioController.listarTodos();
		} else if (chave == 0) { // codigo
			try {
				int id = Integer.parseInt(valor);
				usuarios = List.of(usuarioController.buscarUsuarioPorId(id));
			} catch (NumberFormatException e) {
				screen.exibirMensagem("Consulta invalida! \n" + CHAVES_PESQUISA[chave] + ": " + valorDigitado);
				return;
			}
		} else if (chave == 1) { // nome
			usuarios = usuarioController.buscarUsuariosPorNome(valor);
		}

		screen.exibieUsuarios(usuarios);
	}

	public void atualizarPermissao(Usuario usuario, Funcionalidade funcionalidade, boolean permitir) {
		if (permitir) {
			usuarioController.adicionarPermissao(usuario.getId(), funcionalidade);
		} else {
			usuarioController.removerPermissao(usuario.getId(), funcionalidade);		
		}
		
		List<Permissao> permissoes = usuarioController.buscarPermissoes(usuario.getId());
		usuario.setPermissoes(permissoes);
	}

}