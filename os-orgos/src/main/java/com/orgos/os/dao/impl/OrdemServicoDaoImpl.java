package com.orgos.os.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.orgos.os.dao.OrdemServicoDao;
import com.orgos.os.model.ItemServico;
import com.orgos.os.model.OrdemServico;

public class OrdemServicoDaoImpl implements OrdemServicoDao {

	@Override
	public int inserirOrdemServico(OrdemServico ordemServico, Connection conn) throws SQLException {
		String sql = "INSERT INTO ordem_servico "
				+ "(type, status, cliente_id, tecnico_id, equipamento, marca, servico, descricao_problema, solucao_problema, valor_servico) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, ordemServico.getType().getId());
			pstmt.setInt(2, ordemServico.getStatus().getId());
			pstmt.setInt(3, ordemServico.getCliente().getId());
			pstmt.setInt(4, ordemServico.getTecnico().getId());
			pstmt.setString(5, ordemServico.getEquipamento());
			pstmt.setString(6, ordemServico.getMarca());
			pstmt.setString(7, ordemServico.getServico());
			pstmt.setString(8, ordemServico.getDescricaoProblema());
			pstmt.setString(9, ordemServico.getSolucaoProblema());
			pstmt.setDouble(10, ordemServico.getValorServico());

			int rows = pstmt.executeUpdate();
			if (rows == 0) {
				throw new SQLException("Falha ao inserir ordem de serviço.");
			}

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}
		}
		throw new SQLException("ID da ordem de serviço não retornado.");
	}

	@Override
	public void inserirItemServico(int ordemServicoId, ItemServico item, Connection conn) throws SQLException {
		String sql = "INSERT INTO item_servico (ordem_servico_id, descricao, valor) VALUES (?,?,?)";

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, ordemServicoId);
			pstmt.setString(2, item.getDescricao());
			pstmt.setDouble(3, item.getValor());

			int rows = pstmt.executeUpdate();
			if (rows == 0) {
				throw new SQLException("Falha ao inserir item de serviço.");
			}
		}
	}

}
