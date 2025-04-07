package com.orgos.os.service;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.dao.OrdemServicoDao;
import com.orgos.os.dao.impl.TransactionManager;
import com.orgos.os.model.ItemServico;
import com.orgos.os.model.OrdemServico;
import com.orgos.os.util.OperacaoResultado;

public class OrdemServicoService {

	private static final Logger logger = LogManager.getLogger(OrdemServicoService.class);
	private OrdemServicoDao dao;

	public OrdemServicoService(OrdemServicoDao dao) {
		this.dao = dao;
	}
	
	public OperacaoResultado salvar(OrdemServico ordemServico) {
		try {
			return TransactionManager.executeInTransaction(conn -> {
				int ordemId = dao.inserirOrdemServico(ordemServico, conn);
				ordemServico.setId(ordemId);
				
				for (ItemServico item : ordemServico.getListItemServico()) {
					dao.inserirItemServico(ordemId, item, conn);
				}
				
				return OperacaoResultado.sucesso("OS inserida com sucesso!");
			});
		}  catch (SQLException e) {
			logger.error("Erro ao salvar OS", e);
			return OperacaoResultado.erro("Falha ao inserir OS: " + e.getMessage());
		} catch (Exception e) {
			logger.error("Erro ao salvar OS", e);
			return OperacaoResultado.erro("Erro inesperado: " + e.getMessage());
		}
	}	
	
}
