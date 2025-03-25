package com.orgos.os.controller;

import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.model.Tecnico;
import com.orgos.os.service.TecnicoService;
import com.orgos.os.view.CadastroTecnicoScreen;

public class CadastroTecnicoController {

	private CadastroTecnicoScreen screen;
	private TecnicoService tecnicoService;

	public CadastroTecnicoController(CadastroTecnicoScreen screen, TecnicoService tecnicoService) {
		super();
		this.screen = screen;
		this.tecnicoService = tecnicoService;
	}

	public void salvarTecnico(Tecnico tecnico) {
		if (tecnico.getId() < 1) {
			OperacaoResultado resultado = tecnicoService.salvar(tecnico);
			screen.exibirMensagem(resultado.getMensagem());
			if (resultado.isSucesso()) {
				screen.reset();
			}
		} else {
			OperacaoResultado resultado = tecnicoService.atualizar(tecnico);
			screen.exibirMensagem(resultado.getMensagem());
			if (resultado.isSucesso()) {
				screen.reset();
			}
		}
	}

	public void removerTecnico(Tecnico tecnico) {
		OperacaoResultado resultado = tecnicoService.remover(tecnico);
		screen.exibirMensagem(resultado.getMensagem());
		if (resultado.isSucesso()) {
			screen.reset();
		}
	}

}
