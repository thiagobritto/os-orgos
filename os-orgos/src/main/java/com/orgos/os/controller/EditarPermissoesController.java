package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;
import com.orgos.os.view.EditarPermissoesScreen;

public class EditarPermissoesController {
	private EditarPermissoesScreen screen;
	private UsuarioController controller;

	public EditarPermissoesController(EditarPermissoesScreen screen, UsuarioController controller) {
		this.screen = screen;
		this.controller = controller;
	}

	public void atualizarPermissao(Usuario usuario, Funcionalidade funcionalidade, boolean permitir) {
		if (permitir) {
			controller.adicionarPermissao(usuario.getId(), funcionalidade);
		} else {
			controller.removerPermissao(usuario.getId(), funcionalidade);		
		}
		
		List<Permissao> permissoes = controller.buscarPermissoes(usuario.getId());
		usuario.setPermissoes(permissoes);
	}

}
