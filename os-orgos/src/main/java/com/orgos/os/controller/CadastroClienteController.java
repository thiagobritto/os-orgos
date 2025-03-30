package com.orgos.os.controller;

import com.orgos.os.model.Cliente;
import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.service.ClienteService;
import com.orgos.os.view.CadastroClienteScreen;

public class CadastroClienteController implements Controller {

	private CadastroClienteScreen screen;
	private ClienteService clienteService;

	public CadastroClienteController(CadastroClienteScreen screen, ClienteService clienteService) {
		this.screen = screen;
		this.clienteService = clienteService;
		iniciaController();
	}
	
	private void iniciaController() {
		screen.setController(this);
	}

	@Override
	public void inicializar() {
		screen.reset();
	}

	public void salvarCliente(Cliente cliente) {
		if (cliente.getId() < 1) {
			OperacaoResultado resultado = clienteService.salvar(cliente);
			screen.exibirMensagem(resultado.getMensagem());
			if (resultado.isSucesso()) {
				screen.reset();			
			}
		} else {
			OperacaoResultado resultado = clienteService.atualizar(cliente);
			screen.exibirMensagem(resultado.getMensagem());
			if (resultado.isSucesso()) {
				screen.reset();			
			}
		}
	}

	public void removerCliente(Cliente cliente) {
		OperacaoResultado resultado = clienteService.remover(cliente);
		screen.exibirMensagem(resultado.getMensagem());
		if (resultado.isSucesso()) {
			screen.reset();					
		}
	}

	
	
	


}
