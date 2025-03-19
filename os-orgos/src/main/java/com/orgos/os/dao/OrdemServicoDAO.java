package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.model.OrdemServico;

public class OrdemServicoDAO {
	private static final Logger logger =LogManager.getLogger(OrdemServicoDAO.class);
	private ClienteDAO clienteDAO;
	
	public OrdemServicoDAO() {
		ClienteDAO clienteDAO = new ClienteDAO();
	}
	
	public boolean salvar(OrdemServico ordemServico) {
		String sql = "INSERT INTO ordem_servico (numero_os, data_abertura, cliente_id, tecnico_id, descricao_problema, status) VALUES (?,?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, ordemServico.getNumeroOS());
			pstmt.setDate(2, new Date(ordemServico.getDataAbertura().getTime()));
			pstmt.setInt(3, ordemServico.getCliente().getId());
			pstmt.setInt(4, ordemServico.getTecnico().getId());
			pstmt.setString(5, ordemServico.getDescricaoProblema());
			pstmt.setString(6, ordemServico.getStatus().toString());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			logger.error("Erro ao salvar ordem de servico: " + e.getMessage(), e);
			return false;
		}
	}

	public List<OrdemServico> listarTodos() {
		String sql = "SELECT id_ordem_servico, numero_os, data_abertura, cliente_id, tecnico_id, descricao_problema, status FROM ordem_servico";
		List<OrdemServico> ordemsServico = new ArrayList<OrdemServico>();
		
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			
			while (rs.next()) {
				//int id = rs.getInt("id_ordem_servico");
				//String numeroOS = rs.getString("numero_os");
				//Date date = rs.getDate("data_abertura");
				//Cliente cliente = clienteDAO.buscarPorId(rs.getInt("cliente_id"));
			}

		} catch (SQLException e) {
			logger.error("Erro ao buscar ordens de servico: " + e.getMessage(), e);
		}
		return ordemsServico;
	}

	public OrdemServico buscarPorId(int id) {
		// Implementação para buscar uma OS por ID
		return null;
	}
}
