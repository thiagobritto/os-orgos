package com.orgos.os.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.dao.TecnicoDao;
import com.orgos.os.dao.impl.TransactionManager;
import com.orgos.os.model.Tecnico;
import com.orgos.os.util.OperacaoResultado;
import com.orgos.os.util.TecnicoSanitizer;
import com.orgos.os.util.TecnicoValidator;

public class TecnicoService {

	private static final Logger logger = LogManager.getLogger(TecnicoService.class);
	private TecnicoValidator validator;
	private TecnicoSanitizer sanitizer;
	private TecnicoDao dao;

	public TecnicoService(TecnicoValidator validator, TecnicoSanitizer sanitizer, TecnicoDao dao) {
		this.validator = validator;
		this.sanitizer = sanitizer;
		this.dao = dao;
	}

	public OperacaoResultado salvar(Tecnico tecnico) {
		try {
			validator.validar(tecnico);
			sanitizer.sanitizar(tecnico);
			
			return TransactionManager.executeInTransaction(conn -> {
				Tecnico existente = dao.buscarTecnicoPorNome(tecnico.getNome(), conn);
				if (existente != null) {
					return OperacaoResultado.erro("Nome já está sendo usado por outro técnico.");
				}		
				return dao.inserirTecnico(tecnico, conn);
			});
						
		} catch (IllegalArgumentException e) {
			logger.error("Erro ao salvar técnico", e);
			return OperacaoResultado.erro("Erro de validação: " + e.getMessage());
		} catch (SQLException e) {
			logger.error("Erro ao salvar técnico", e);
			return OperacaoResultado.erro("Erro ao salvar técnico: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Erro ao salvar técnico", e);
			return OperacaoResultado.erro("Erro de inesperado: " + e.getMessage());			
		}
	}

	public OperacaoResultado atualizar(Tecnico tecnico) {
		try {
			validator.validar(tecnico);
			sanitizer.sanitizar(tecnico);
			
			return TransactionManager.executeInTransaction(conn -> {
				Tecnico existente = dao.buscarTecnicoPorNome(tecnico.getNome(), conn);
				if (existente != null && existente.getId() != tecnico.getId()) {
					return OperacaoResultado.erro("Nome já está sendo usado por outro técnico.");
				}		
				return dao.atualizarTecnico(tecnico, conn);
			});
						
		} catch (IllegalArgumentException e) {
			logger.error("Erro ao atualizar técnico", e);
			return OperacaoResultado.erro("Erro de validação: " + e.getMessage());
		} catch (SQLException e) {
			logger.error("Erro ao atualizar técnico", e);
			return OperacaoResultado.erro("Erro ao atualizar técnico: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Erro ao atualizar técnico", e);
			return OperacaoResultado.erro("Erro de inesperado: " + e.getMessage());			
		}
	}

	public OperacaoResultado remover(Tecnico tecnico) {
		try {
			return TransactionManager.executeInTransaction(conn -> dao.removerTecnico(tecnico.getId(), conn));
		} catch (SQLException e) {
			logger.error("Erro ao remover técnico", e);
			return OperacaoResultado.erro("Erro ao remover técnico: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Erro ao remover técnico", e);
			return OperacaoResultado.erro("Erro inesperado: " + e.getMessage());
		}
	}

	public List<Tecnico> listarTodos() {
		try {
			return TransactionManager.executeInTransaction(dao::listarTodosOsTecnicos);
		} catch (SQLException e) {
			logger.error("Erro ao listar todos os técnicos", e);
			return Collections.emptyList();
		}
	}

	public List<Tecnico> listarPorNome(String nome) {
		try {
			return TransactionManager.executeInTransaction(conn -> dao.listarTecnicosPorNome(nome, conn));
		} catch (SQLException e) {
			logger.error("Erro ao listar técnicos pelo nome: " + nome, e);
			return Collections.emptyList();
		}
	}

	public Tecnico buscarPorId(int id) {
		try {
			return TransactionManager.executeInTransaction(conn -> dao.buscarTecnicoPorId(id, conn));
		} catch (SQLException e) {
			logger.error("Erro ao buscar técnico por ID: " + id, e);
			return null;
		}
	}

	public Tecnico buscarPorNome(String nome) {
		try {
			return TransactionManager.executeInTransaction(conn -> dao.buscarTecnicoPorNome(nome, conn));
		} catch (SQLException e) {
			logger.error("Erro ao buscar técnico por nome: " + nome, e);
			return null;
		}
	}

}
