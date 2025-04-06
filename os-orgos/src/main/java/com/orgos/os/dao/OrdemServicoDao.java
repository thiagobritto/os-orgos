package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.orgos.os.model.ItemServico;
import com.orgos.os.model.OrdemServico;

public interface OrdemServicoDao {
	int inserirOrdemServico(OrdemServico ordemServico, Connection conn) throws SQLException;
	void inserirItemServico(int ordemServicoId, ItemServico item, Connection conn) throws SQLException;
}
