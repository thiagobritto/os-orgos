package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.PesquisaTecnico;
import com.orgos.os.model.PesquisaTecnicoId;
import com.orgos.os.model.PesquisaTecnicoNome;
import com.orgos.os.model.Tecnico;
import com.orgos.os.service.TecnicoService;
import com.orgos.os.view.BuscaTecnicoScreen;

public class BuscaTecnicoController implements Controller {

	private BuscaTecnicoScreen screen;
	private TecnicoService tecnicoService;
	
	public BuscaTecnicoController(BuscaTecnicoScreen screen, TecnicoService tecnicoService) {
		super();
		this.screen = screen;
		this.tecnicoService = tecnicoService;
		iniciarController();
	}

	private void iniciarController() {
		screen.setController(this);
	}
	
	@Override
	public void inicializar() {
		
	}

	public void carregarTela() {
		PesquisaTecnicoId pesquisaTecnicoId = new PesquisaTecnicoId(tecnicoService);
		PesquisaTecnicoNome pesquisaTecnicoNome = new PesquisaTecnicoNome(tecnicoService);
		
		PesquisaTecnico[] pesquisasTecnico = {pesquisaTecnicoId, pesquisaTecnicoNome};
		screen.setPesquisa(pesquisasTecnico);
		
		List<Tecnico> tecnicos = tecnicoService.listarTodos();
		screen.exibirTecnicos(tecnicos);
	}

	
}
