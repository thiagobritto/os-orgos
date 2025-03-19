package com.orgos.os.service;

import java.util.List;

import com.orgos.os.dao.OrdemServicoDAO;
import com.orgos.os.model.OrdemServico;

public class OrdemServicoService {
	private OrdemServicoDAO ordemServicoDAO;

    public OrdemServicoService() {
        this.ordemServicoDAO = new OrdemServicoDAO();
    }

    public void criarOrdemServico(OrdemServico ordemServico) {
        // Validações e lógica de negócios
        ordemServicoDAO.salvar(ordemServico);
    }

    public List<OrdemServico> listarOrdensServico() {
        return ordemServicoDAO.listarTodos();
    }
}
