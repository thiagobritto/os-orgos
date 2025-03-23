package com.orgos.os.controller;

import com.orgos.os.model.Cliente;
import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.service.ClienteService;
import com.orgos.os.view.CadastroClienteScreen;

public class CadastroClienteController {

	private CadastroClienteScreen screen;
	private ClienteService clienteService;

	public CadastroClienteController(CadastroClienteScreen screen, ClienteService clienteService) {
		this.screen = screen;
		this.clienteService = clienteService;
	}

	public void seveCliente(Cliente cliente) {
		if (cliente.getId() < 1) {
			OperacaoResultado resultado = clienteService.salvar(cliente);
			if (resultado.isSucesso()) {
				screen.exibirMensagem(resultado.getMensagem());
				screen.limparCampos();			
			}
		} else {
			OperacaoResultado resultado = clienteService.atualizar(cliente);
			if (resultado.isSucesso()) {
				screen.exibirMensagem(resultado.getMensagem());
				screen.limparCampos();			
			}
		}
	}

	public void deleteCliente(Cliente cliente) {
		OperacaoResultado resultado = clienteService.remover(cliente);
		if (resultado.isSucesso()) {
			screen.exibirMensagem(resultado.getMensagem());
			screen.limparCampos();					
		}
	}
	
	


}
