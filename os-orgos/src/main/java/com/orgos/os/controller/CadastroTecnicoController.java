package com.orgos.os.controller;

import com.orgos.os.service.TecnicoService;
import com.orgos.os.view.CadastroTecnicoScreen;

public class CadastroTecnicoController implements Controller {

	private CadastroTecnicoScreen screen;
	private TecnicoService tecnicoService;

	public CadastroTecnicoController(CadastroTecnicoScreen screen, TecnicoService tecnicoService) {
		this.screen = screen;
		this.tecnicoService = tecnicoService;
	}

	@Override
	public void inicializar() {
		screen.exibirTecnicos(tecnicoService.listarTodos());
	}

}
