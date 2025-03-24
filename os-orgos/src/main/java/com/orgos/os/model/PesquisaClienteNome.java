package com.orgos.os.model;

import java.util.List;

import com.orgos.os.service.ClienteService;

public class PesquisaClienteNome implements PesquisaCliente {

	private ClienteService clienteService;

	public PesquisaClienteNome(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public List<Cliente> buscar(String valor) {
		return clienteService.buscarPorNome(valor);
	}
	
	@Override
	public String toString() {
		return "Nome";
	}

}
