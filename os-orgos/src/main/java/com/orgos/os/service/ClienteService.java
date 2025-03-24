package com.orgos.os.service;

import java.util.List;

import com.orgos.os.dao.ClienteDAO;
import com.orgos.os.model.Cliente;
import com.orgos.os.model.OperacaoResultado;

public class ClienteService {

	private ClienteDAO clienteDAO;

	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}
	
	public List<Cliente> listarTodos() {
		return clienteDAO.listarTodos();
	}
	
	public List<Cliente> buscarPorNome(String nome) {
		return clienteDAO.buscarPorNome(nome);
	}
	
	public Cliente buscarPorId(int id) {
		return clienteDAO.buscarPorId(id);
	}
	
	public OperacaoResultado salvar(Cliente cliente) {
		
		return clienteDAO.salvar(cliente);
	}

	public OperacaoResultado atualizar(Cliente cliente) {
		return clienteDAO.atualizar(cliente);
	}

	public OperacaoResultado remover(Cliente cliente) {
		return clienteDAO.remover(cliente.getId());
	}

}
