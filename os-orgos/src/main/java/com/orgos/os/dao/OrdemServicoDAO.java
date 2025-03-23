package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.model.Cliente;
import com.orgos.os.model.ItemServico;
import com.orgos.os.model.OrdemServico;
import com.orgos.os.model.StatusOS;
import com.orgos.os.model.Tecnico;

public class OrdemServicoDAO {
	private static final Logger logger = LogManager.getLogger(OrdemServicoDAO.class);
	private ClienteDAO clienteDAO;
	private TecnicoDAO tecnicoDAO;

	public OrdemServicoDAO() {
		clienteDAO = new ClienteDAO();
		tecnicoDAO = new TecnicoDAO();
	}

	public boolean salvar(OrdemServico ordemServico) {
		String sql = "INSERT INTO ordem_servico (numero_os, data_abertura, cliente_id, tecnico_id, descricao_problema, status) VALUES (?,?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			String numeroOS = gerarNumeroOS();
            ordemServico.setNumeroOS(numeroOS);
            Timestamp dataAbertura = new Timestamp(System.currentTimeMillis());
            ordemServico.setDataAbertura(dataAbertura);
			
			pstmt.setString(1, ordemServico.getNumeroOS());
			pstmt.setTimestamp(2, ordemServico.getDataAbertura());
			pstmt.setInt(3, ordemServico.getCliente().getId());
			pstmt.setInt(4, ordemServico.getTecnico().getId());
			pstmt.setString(5, ordemServico.getDescricaoProblema());
			pstmt.setString(6, ordemServico.getStatus().toString());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			logger.error("Erro ao salvar 'ordem' de servico: " + e.getMessage(), e);
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
				int id = rs.getInt("id_ordem_servico");
				String numeroOS = rs.getString("numero_os");
				Timestamp dataAbertura = rs.getTimestamp("data_abertura");
				Cliente cliente = clienteDAO.buscarPorId(rs.getInt("cliente_id"));
				Tecnico tecnico = tecnicoDAO.buscarPorId(rs.getInt("tecnico_id"));
				String descricao = rs.getString("descricao_problema");
				StatusOS status = StatusOS.valueOf(rs.getString("status"));
				List<ItemServico> itens = buscarItensServico(id);

				ordemsServico.add(new OrdemServico(id, numeroOS, dataAbertura, cliente, tecnico, descricao, status, itens));
			}

		} catch (SQLException e) {
			logger.error("Erro ao buscar 'itens' de servico: " + e.getMessage(), e);
		}
		return ordemsServico;
	}

	public List<ItemServico> buscarItensServico(int ordemServicoId) {
		String sql = "SELECT id_item_servico, descricao, custo FROM item_servico WHERE ordem_servico_id = ?";
		List<ItemServico> itensServico = new ArrayList<ItemServico>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, ordemServicoId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id_item_servico");
					String descricao = rs.getString("descricao");
					double custo = rs.getDouble("custo");

					itensServico.add(new ItemServico(id, descricao, custo));
				}
			}

		} catch (SQLException e) {
			logger.error("Erro ao buscar ordens de servico: " + e.getMessage(), e);
		}
		return itensServico;
	}

	public OrdemServico buscarPorId(int id) {
		// Implementação para buscar uma OS por ID
		return null;
	}
	
	private String gerarNumeroOS() throws SQLException {
		int number = obterProximoContador();
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
		String id = new DecimalFormat("000000").format(number);
		return "OS-" + date + "-" + id;
	}
	
	private int obterProximoContador() throws SQLException {
       String sql = "SELECT COUNT(id_ordem_servico) + 1 AS numero_os FROM ordem_servico";
		
       try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt("numero_os");
			}
		} catch (SQLException e) {
			logger.error("Erro ao obter proximo contados: " + e.getMessage(), e);
			throw e;
		}
	return 0;
    }
}
