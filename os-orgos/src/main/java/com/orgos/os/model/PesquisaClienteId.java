package com.orgos.os.model;

import java.util.Collections;
import java.util.List;

import com.orgos.os.service.ClienteService;

public class PesquisaClienteId implements PesquisaCliente {

	private ClienteService clienteService;
	
	public PesquisaClienteId(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@Override
	public List<Cliente> buscar(String valor) {
		try {
			int id = Integer.parseInt(valor);
			return List.of(clienteService.buscarPorId(id));
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
	@Override
	public String toString() {
		return "Código";
	}

}
