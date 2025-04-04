package com.orgos.os.service;

import java.util.List;
import java.util.Objects;

import com.orgos.os.dao.ClienteDao;
import com.orgos.os.model.Cliente;
import com.orgos.os.util.OperacaoResultado;
import com.orgos.os.util.Validador;

public class ClienteService {

	private ClienteDao clienteDAO;

	public ClienteService(ClienteDao clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public List<Cliente> listarTodos() {
		return clienteDAO.listarTodos();
	}

	public List<Cliente> buscarPorNome(String nome) {
		return clienteDAO.listarPorNome(nome);
	}

	public Cliente buscarPorId(int id) {
		return clienteDAO.buscarPorId(id);
	}

	public OperacaoResultado salvar(Cliente cliente) {
		if (Validador.isEmpty(cliente.getNome())) {
			throw new IllegalArgumentException("O nome do Cliente não pode ser vazio.");			
		}
		
		Cliente cliente2 = clienteDAO.buscarPorNome(cliente.getNome());
		if (Objects.nonNull(cliente2)) {
			throw new IllegalArgumentException("Já existe um Cliente cadastrado com esse nome!");			
		}
		
		return clienteDAO.salvar(prepararDadosCliente(cliente));
	}

	private Cliente prepararDadosCliente(Cliente cliente) {
		cliente.setCpfCnpj(cliente.getCpfCnpj().replaceAll("\\D", "").length() < 11 ? null : cliente.getCpfCnpj());
		cliente.setTelefone(cliente.getTelefone().replaceAll("\\D", "").length() < 10 ? null : cliente.getTelefone());
		cliente.setEmail(cliente.getEmail().trim().isEmpty() ? null: cliente.getEmail());
		cliente.setEndereco(cliente.getEndereco().trim().isEmpty() ? null: cliente.getEndereco());
		return cliente;
	}
	
	public OperacaoResultado atualizar(Cliente cliente) {
		if (Validador.isEmpty(cliente.getNome())) {
			throw new IllegalArgumentException("O nome do Cliente não pode ser vazio.");			
		}
		
		Cliente cliente2 = clienteDAO.buscarPorNome(cliente.getNome());
		if (Objects.nonNull(cliente2)) {
			if (cliente.getId() != cliente2.getId()) {
				throw new IllegalArgumentException("Já existe um Cliente cadastrado com esse nome!");				
			}
		}

		return clienteDAO.atualizar(prepararDadosCliente(cliente));
	}

	public OperacaoResultado remover(Cliente cliente) {
		return clienteDAO.remover(cliente.getId());
	}

}
