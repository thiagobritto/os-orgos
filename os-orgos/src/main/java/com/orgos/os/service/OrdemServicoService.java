package com.orgos.os.service;

import java.sql.SQLException;
import java.util.List;

import com.orgos.os.dao.OrdemServicoDAO;
import com.orgos.os.model.OrdemServico;
import com.orgos.os.model.TransactionManager;

public class OrdemServicoService {
	private OrdemServicoDAO ordemServicoDAO;

	public OrdemServicoService(OrdemServicoDAO ordemServicoDAO) {
		this.ordemServicoDAO = ordemServicoDAO;
	}

	public void criarOrdemServico(OrdemServico ordemServico) {
		// Validações e lógica de negócios
		ordemServicoDAO.salvar(ordemServico);
	}

	public List<OrdemServico> listarOrdensServico() {
		return ordemServicoDAO.listarTodos();
	}

	public void salvar(OrdemServico ordemServico) {
		try {
			TransactionManager.beginTransaction();
			ordemServicoDAO.salvar(ordemServico);
			System.out.println(ordemServico);
			TransactionManager.commit();
		} catch (SQLException e) {
			try {
				TransactionManager.rollback();
			} catch (SQLException rollbackEx) {
				e.printStackTrace();
			}
		}
	}
}
