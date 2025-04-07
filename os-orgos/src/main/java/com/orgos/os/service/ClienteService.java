package com.orgos.os.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.dao.ClienteDao;
import com.orgos.os.dao.impl.TransactionManager;
import com.orgos.os.model.Cliente;
import com.orgos.os.util.ClienteSanitizer;
import com.orgos.os.util.ClienteValidator;
import com.orgos.os.util.OperacaoResultado;

public class ClienteService {

	private static final Logger logger = LogManager.getLogger(ClienteService.class);
	private ClienteValidator validator;
	private ClienteSanitizer sanitizer;
	private ClienteDao dao;

	public ClienteService(ClienteValidator validator, ClienteSanitizer sanitizer, ClienteDao dao) {
		this.validator = validator;
		this.sanitizer = sanitizer;
		this.dao = dao;
	}

	public OperacaoResultado salvar(Cliente cliente) {
		try {
			validator.validar(cliente);
			sanitizer.sanitizar(cliente);

			return TransactionManager.executeInTransaction(conn -> {
				Cliente existente = dao.buscarClientePorNome(cliente.getNome(), conn);
				if (existente != null) {
					return OperacaoResultado.erro("Já existe um cliente com esse nome.");
				}
				return dao.inserirCliente(cliente, conn);
			});

		} catch (IllegalArgumentException e) {
			logger.error("Erro ao salvar cliente", e);
			return OperacaoResultado.erro("Erro de validação: " + e.getMessage());
		} catch (SQLException e) {
			logger.error("Erro ao salvar cliente", e);
			return OperacaoResultado.erro("Erro ao salvar cliente: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Erro ao salvar cliente", e);
			return OperacaoResultado.erro("Erro inesperado: " + e.getMessage());
		}
	}

	public OperacaoResultado atualizar(Cliente cliente) {
		try {
			validator.validar(cliente);
			sanitizer.sanitizar(cliente);

			return TransactionManager.executeInTransaction(conn -> {
				Cliente existente = dao.buscarClientePorNome(cliente.getNome(), conn);
				if (existente != null && existente.getId() != cliente.getId()) {
					return OperacaoResultado.erro("Nome já está sendo usado por outro cliente.");
				}
				return dao.atualizarCliente(cliente, conn);
			});

		} catch (IllegalArgumentException e) {
			logger.error("Erro ao atualizar cliente", e);
			return OperacaoResultado.erro("Erro de validação: " + e.getMessage());
		} catch (SQLException e) {
			logger.error("Erro ao atualizar cliente", e);
			return OperacaoResultado.erro("Erro ao atualizar cliente: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Erro ao atualizar cliente", e);
			return OperacaoResultado.erro("Erro inesperado: " + e.getMessage());
		}
	}

	public OperacaoResultado remover(Cliente cliente) {
		try {
			return TransactionManager.executeInTransaction(conn -> dao.removerCliente(cliente.getId(), conn));
		} catch (SQLException e) {
			logger.error("Erro ao remover cliente", e);
			return OperacaoResultado.erro("Erro ao remover cliente: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Erro ao remover cliente", e);
			return OperacaoResultado.erro("Erro inesperado: " + e.getMessage());
		}
	}

	public List<Cliente> listarTodos() {
		try {
			return TransactionManager.executeInTransaction(dao::listarTodosOsClientes);
		} catch (SQLException e) {
			logger.error("Erro ao listar todos os clientes", e);
			return Collections.emptyList();
		}
	}

	public List<Cliente> listarPorNome(String nome) {
		try {
			return TransactionManager.executeInTransaction(conn -> dao.listarClientesPorNome(nome, conn));
		} catch (SQLException e) {
			logger.error("Erro ao listar clientes pelo nome: " + nome, e);
			return Collections.emptyList();
		}
	}

	public Cliente buscarPorId(int id) {
		try {
			return TransactionManager.executeInTransaction(conn -> dao.buscarClientePorId(id, conn));
		} catch (SQLException e) {
			logger.error("Erro ao buscar cliente por ID: " + id, e);
			return null;
		}
	}

	public Cliente buscarPorNome(String nome) {
		try {
			return TransactionManager.executeInTransaction(conn -> dao.buscarClientePorNome(nome, conn));
		} catch (SQLException e) {
			logger.error("Erro ao buscar técnico por nome: " + nome, e);
			return null;
		}
	}

}
