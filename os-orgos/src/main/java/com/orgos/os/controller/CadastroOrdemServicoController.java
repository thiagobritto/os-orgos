package com.orgos.os.controller;

import com.orgos.os.service.ClienteService;
import com.orgos.os.service.TecnicoService;
import com.orgos.os.view.CadastroOrdemServicoScreen;

public class CadastroOrdemServicoController implements Controller {

	private CadastroOrdemServicoScreen screen;
	private ClienteService clienteService;
	private TecnicoService tecnicoService;

	public CadastroOrdemServicoController(CadastroOrdemServicoScreen screen, ClienteService clienteService, TecnicoService tecnicoService) {
		this.screen = screen;
		this.clienteService = clienteService;
		this.tecnicoService = tecnicoService;
	}

	@Override
	public void inicializar() {
		screen.exibirClientes(clienteService.listarTodos());
		screen.exibirTecnicos(tecnicoService.listarTodos());
	}

	
}
