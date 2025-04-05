package com.orgos.os.service;

import java.util.List;
import java.util.Objects;

import com.orgos.os.dao.ClienteDao;
import com.orgos.os.model.Cliente;
import com.orgos.os.util.ClienteValidator;
import com.orgos.os.util.OperacaoResultado;

public class ClienteService {

	private ClienteDao dao;
	private ClienteValidator validator;

	public ClienteService(ClienteValidator validator, ClienteDao dao) {
		this.validator = validator;
		this.dao = dao;
	}

	public List<Cliente> listarTodos() {
		return dao.listarTodos();
	}

	public Cliente buscarPorId(int id) {
		return dao.buscarPorId(id);
	}

	public OperacaoResultado salvar(Cliente cliente) {	
		validator.validar(cliente);
		
		Cliente clienteNoBanco = dao.buscarPorNome(cliente.getNome());
		if (Objects.nonNull(clienteNoBanco)) {
			throw new IllegalArgumentException("Já existe um Cliente com esse nome!");			
		}
		
		prepararDadosCliente(cliente);
		return dao.salvar(cliente);
	}

	public OperacaoResultado atualizar(Cliente cliente) {
		validator.validar(cliente);
		
		Cliente clienteNoBanco = dao.buscarPorNome(cliente.getNome());
		if (Objects.nonNull(clienteNoBanco)) {
			if (cliente.getId() != clienteNoBanco.getId()) {
				throw new IllegalArgumentException("Já existe um Cliente com esse nome!");				
			}
		}

		prepararDadosCliente(cliente);
		return dao.atualizar(cliente);
	}
	
	private void prepararDadosCliente(Cliente cliente) {
		if (cliente.getCpfCnpj().replaceAll("\\D", "").length() < 11) {
			cliente.setCpfCnpj(null);
		}
		if (cliente.getTelefone().replaceAll("\\D", "").length() < 10) {
			cliente.setTelefone(null);
		}
		if (cliente.getEmail().trim().isEmpty()) {
			cliente.setEmail(null);
		}
		if (cliente.getEndereco().trim().isEmpty()) {
			cliente.setEndereco(null);
		}
	}

	public OperacaoResultado remover(Cliente cliente) {
		return dao.remover(cliente.getId());
	}

	public List<Cliente> listarPorNome(String nome) {
		return dao.listarPorNome(nome);
	}

}
