package com.orgos.os.controller;

import java.util.List;

import com.orgos.os.model.Cliente;
import com.orgos.os.model.PesquisaCliente;
import com.orgos.os.model.PesquisaClienteId;
import com.orgos.os.model.PesquisaClienteNome;
import com.orgos.os.service.ClienteService;
import com.orgos.os.view.BuscaClienteScreen;

public class BuscaClienteController {

	private BuscaClienteScreen screen;
	private ClienteService clienteService;

	public BuscaClienteController(BuscaClienteScreen screen, ClienteService clienteService) {
		this.screen = screen;
		this.clienteService = clienteService;
	}
	
	public void carregarTela() {
		PesquisaClienteId pesquisaClienteId = new PesquisaClienteId(clienteService);
		PesquisaClienteNome pesquisaClienteNome = new PesquisaClienteNome(clienteService);
		
		PesquisaCliente[] pesquisasCliente = {pesquisaClienteId, pesquisaClienteNome};
		screen.setPesquisa(pesquisasCliente);
		
		List<Cliente> listarTodos = clienteService.listarTodos();
		screen.exibirClientes(listarTodos);
	}

}
