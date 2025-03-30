package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.model.Cliente;
import com.orgos.os.model.ItemServico;
import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.model.OrdemServico;
import com.orgos.os.model.StatusOS;
import com.orgos.os.model.Tecnico;
import com.orgos.os.model.TransactionManager;

public class OrdemServicoDAO {
	private static final Logger logger = LogManager.getLogger(OrdemServicoDAO.class);
	private ClienteDAO clienteDAO;
	private TecnicoDAO tecnicoDAO;

	public OrdemServicoDAO() {
		clienteDAO = new ClienteDAO();
		tecnicoDAO = new TecnicoDAO();
	}

	// (SELECT 'OS-' || strftime('%Y%m%d', CURRENT_TIMESTAMP) || '-' ||
	// printf('%06d', count()+1)

	public OperacaoResultado salvaTrasacao(OrdemServico ordemServico) {
		Connection conn = null;
		try {
			if (ordemServico == null)
				throw new IllegalArgumentException("OrdemServico não pode ser nula");
			if (ordemServico.getCliente() == null || ordemServico.getTecnico() == null
					|| ordemServico.getStatus() == null)
				throw new IllegalArgumentException("Cliente, Técnico ou Status não podem ser nulos");

			conn = DatabaseConnection.getConnection();
			conn.setAutoCommit(false);

			pstmtInsertOS(conn, ordemServico);
			pstmtUpdateOS(conn, ordemServico);
			pstmtInsertItensOS(conn, ordemServico);

			conn.commit();
			return new OperacaoResultado(true, "OS inserida com sucesso!");
		} catch (SQLException e) {
			logger.error("Erro ao salvar transação: " + e.getMessage(), e);
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException e1) {
				logger.error("Erro ao executar rollback: " + e1.getMessage(), e1);
			}
			return new OperacaoResultado(false, "Erro ao inserir OS: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("Dados inválidos: " + e.getMessage(), e);
			return new OperacaoResultado(false, "Dados inválidos: " + e.getMessage());
		} finally {
			try {
				if (conn != null)
					if (!conn.isClosed())
						conn.close();
			} catch (SQLException e) {
				logger.error("Erro ao fechar conexão: " + e.getMessage(), e);
			}
		}

	}

	private void pstmtInsertOS(Connection conn, OrdemServico ordemServico) throws SQLException {
		String insert = "INSERT INTO ordem_servico (numero_os, cliente_id, tecnico_id, descricao_problema, status) VALUES ("
				+ "(SELECT 'OS-' || strftime('%Y%m%d', CURRENT_TIMESTAMP) || '-' || printf('%06d', count() + 1) FROM ordem_servico), ?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setInt(1, ordemServico.getCliente().getId());
			pstmt.setInt(2, ordemServico.getTecnico().getId());
			pstmt.setString(3, ordemServico.getDescricaoProblema());
			pstmt.setInt(4, ordemServico.getStatus().getId());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected == 0) {
				throw new SQLException("Erro ao inserir OS");
			}

			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (!rs.next())
					throw new SQLException("Erro ao recuperar ID OS");

				int id = rs.getInt(1);
				ordemServico.setId(id);
				ordemServico.setNumeroOS(gerarNumeroOS(id));
			}
		}
	}

	private String gerarNumeroOS(int id) {
		String data = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		return "OS-" + data + "-" + new DecimalFormat("000000").format(id);
	}

	private void pstmtUpdateOS(Connection conn, OrdemServico ordemServico) throws SQLException {
		String update = "UPDATE ordem_servico SET numero_os=? WHERE id_ordem_servico = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(update)) {

			pstmt.setString(1, ordemServico.getNumeroOS());
			pstmt.setInt(2, ordemServico.getId());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected == 0) {
				throw new SQLException("Erro ao atualizar numero de OS");
			}
		}
	}

	private void pstmtInsertItensOS(Connection conn, OrdemServico ordemServico) throws SQLException {
		if (ordemServico.getItens() == null || ordemServico.getItens().isEmpty()) {
			conn.rollback();
			logger.warn("Nenhum item encontrado na OS");
			throw new IllegalArgumentException("Nenhum item encontrado na OS");
		}

		String insert = "INSERT INTO item_servico (id_item_servico, descricao, custo, ordem_servico_id) VALUES (?,?,?,?)";
		for (ItemServico it : ordemServico.getItens()) {
			try (PreparedStatement pstmt = conn.prepareStatement(insert)) {
				pstmt.setInt(1, it.getId());
				pstmt.setString(2, it.getDescricao());
				pstmt.setDouble(3, it.getCusto());
				pstmt.setInt(4, ordemServico.getId());

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected == 0) {
					throw new SQLException("Erro ao inserir itens!");
				}
			}
		}

	}

	public void salvar(OrdemServico ordemServico) {
		Connection connection = null;
		String insert = "INSERT INTO ordem_servico (numero_os, cliente_id, tecnico_id, descricao_problema, status) VALUES (?,?,?,?,?)";
		try {
			connection = DatabaseConnection.getConnection();
			connection.setAutoCommit(false);

			try (PreparedStatement pstmt = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
				pstmt.setString(1, ordemServico.getNumeroOS());
				pstmt.setInt(2, ordemServico.getCliente().getId());
				pstmt.setInt(3, ordemServico.getTecnico().getId());
				pstmt.setString(4, ordemServico.getDescricaoProblema());
				pstmt.setInt(5, ordemServico.getStatus().getId());

				int rowsAffected = pstmt.executeUpdate();
				if (rowsAffected == 0) {
					throw new SQLException("Erro ao inserir OS");
				}

				try (ResultSet rs = pstmt.getGeneratedKeys()) {
					int id = rs.getInt(1);
					String update = "UPDATE ordem_servico SET numero_os=? WHERE id_ordem_servico = ?";
					try (PreparedStatement pstmt2 = connection.prepareStatement(update)) {

						String numeroOS = gerarNumeroOS(id);

						pstmt2.setString(1, numeroOS);
						pstmt2.setInt(2, id);

						int rowsAffected2 = pstmt2.executeUpdate();
						if (rowsAffected2 == 0) {
							throw new SQLException("Erro ao definir numero de OS");
						}

						ordemServico.setId(id);
						ordemServico.setNumeroOS(numeroOS);
					}
				}

			} // end ordem_servico

			connection.commit();
			connection.close();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean atualizar(OrdemServico ordemServico) {
		String sql = "UPDATE ordem_servico SET numero_os=?, cliente_id=?, tecnico_id=?, descricao_problema=?, status=? WHERE id_ordem_servico = ?";
		try (Connection conn = TransactionManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, ordemServico.getNumeroOS());
			pstmt.setInt(2, ordemServico.getCliente().getId());
			pstmt.setInt(3, ordemServico.getTecnico().getId());
			pstmt.setString(4, ordemServico.getDescricaoProblema());
			pstmt.setInt(5, ordemServico.getStatus().getId());
			pstmt.setInt(6, ordemServico.getId());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			logger.error("Erro ao atualizar 'ordem' de servico: " + e.getMessage(), e);
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

				ordemsServico
						.add(new OrdemServico(id, numeroOS, dataAbertura, cliente, tecnico, descricao, status, itens));
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

}
