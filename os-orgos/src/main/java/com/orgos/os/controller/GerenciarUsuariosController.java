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