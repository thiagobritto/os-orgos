package com.orgos.os.controller;

import com.orgos.os.service.ClienteService;
import com.orgos.os.view.CadastroOrdemServicoScreen;

public class CadastroOrdemServicoController implements Controller {

	private CadastroOrdemServicoScreen screen;
	private ClienteService clienteService;

	public CadastroOrdemServicoController(CadastroOrdemServicoScreen screen, ClienteService clienteService) {
		this.screen = screen;
		this.clienteService = clienteService;
	}

	@Override
	public void inicializar() {
		screen.exibirClientes(clienteService.listarTodos());
	}

	
}
