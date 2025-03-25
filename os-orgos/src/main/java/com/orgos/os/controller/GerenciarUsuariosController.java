package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.PesquisaUsuario;
import com.orgos.os.model.PesquisaUsuarioId;
import com.orgos.os.model.PesquisaUsuarioNome;
import com.orgos.os.model.Usuario;
import com.orgos.os.service.UsuarioService;
import com.orgos.os.view.GerenciarUsuariosScreen;

public class GerenciarUsuariosController implements Controller {
	private GerenciarUsuariosScreen screen;
	private UsuarioService usuarioService;

	private PesquisaUsuarioId pesquisaUsuarioId;
	private PesquisaUsuarioNome pesquisaUsuarioNome;

	public GerenciarUsuariosController(GerenciarUsuariosScreen screen, UsuarioService usuarioService) {
		super();
		this.screen = screen;
		this.usuarioService = usuarioService;
		iniciarController();
	}

	/*
	 * Inicia o controller
	 */
	private void iniciarController() {
		screen.setController(this);
		pesquisaUsuarioId = new PesquisaUsuarioId(usuarioService);
		pesquisaUsuarioNome = new PesquisaUsuarioNome(usuarioService);
	}

	/**
	 * É chamado sempre que a tela e carregada
	 */
	@Override
	public void inicializar() {
		carregarDados();
	}

	public void carregarDados() {
		PesquisaUsuario[] pesquisasUsuario = { pesquisaUsuarioId, pesquisaUsuarioNome };
		screen.setPesquisa(pesquisasUsuario);

		List<Usuario> usuarios = usuarioService.listarTodos();
		screen.exibirUsuarios(usuarios);
	}

	public void atualizarPermissao(Usuario usuario, Funcionalidade funcionalidade, boolean permitir) {
		int id = usuario.getId();
		if (permitir) {
			OperacaoResultado resultado = usuarioService.adicionarPermissao(id, funcionalidade);
			if (!resultado.isSucesso()) {
				screen.exibirMensagemErro(resultado.getMensagem());
			}
		} else {
			OperacaoResultado resultado = usuarioService.removerPermissao(id, funcionalidade);
			if (!resultado.isSucesso()) {
				screen.exibirMensagemErro(resultado.getMensagem());
			}
		}

		List<Permissao> permissoes = usuarioService.buscarPermissoes(id);
		usuario.setPermissoes(permissoes);
	}

}