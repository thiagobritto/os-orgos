package com.orgos.os.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.dao.TecnicoDao;
import com.orgos.os.model.Tecnico;
import com.orgos.os.util.OperacaoResultado;

public class TecnicoDaoImpl implements TecnicoDao {

	private static final Logger logger = LogManager.getLogger(TecnicoDaoImpl.class);

	@Override
	public OperacaoResultado salvar(Tecnico tecnico) {
		String sql = "INSERT INTO tecnico (nome, cpf, email, telefone, especializacao) VALUES (?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, tecnico.getNome());
			pstmt.setString(2, tecnico.getCpf());
			pstmt.setString(3, tecnico.getEmail());
			pstmt.setString(4, tecnico.getTelefone());
			pstmt.setString(5, tecnico.getEspecializacao());
			return pstmt.executeUpdate() > 0 
					? new OperacaoResultado(true, "Técnico inserido com sucesso!")
					: new OperacaoResultado(false, "Nenhum técnico foi inserido.");

		} catch (SQLException e) {
			logger.error("Erro ao inserir um novo técnico: " + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao inserir um novo técnico: " + e.getMessage());
		}
	}

	@Override
	public OperacaoResultado atualizar(Tecnico tecnico) {
		String sql = "UPDATE tecnico SET nome=?, cpf=?, email=?, telefone=?, especializacao=? WHERE id_tecnico=?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, tecnico.getNome());
			pstmt.setString(2, tecnico.getCpf());
			pstmt.setString(3, tecnico.getEmail());
			pstmt.setString(4, tecnico.getTelefone());
			pstmt.setString(5, tecnico.getEspecializacao());
			pstmt.setInt(6, tecnico.getId());
			return pstmt.executeUpdate() > 0 
					? new OperacaoResultado(true, "Técnico atualizado com sucesso!")
					: new OperacaoResultado(false, "Nenhum técnico foi atualizado.");

		} catch (SQLException e) {
			logger.error("Erro ao atualizar técnico: " + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao atualizar técnico: " + e.getMessage());
		}
	}

	@Override
	public OperacaoResultado remover(int id) {
		String sql = "DELETE FROM tecnico WHERE id_tecnico = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, id);
			return pstmt.executeUpdate() > 0 
					? new OperacaoResultado(true, "Técnico removido com sucesso!")
					: new OperacaoResultado(false, "Nenhum técnico foi removido.");

		} catch (SQLException e) {
			logger.error("Erro ao remover técnico: " + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao remover técnico: " + e.getMessage());
		}
	}

	@Override
	public List<Tecnico> listarTodos() {
		List<Tecnico> listTecnico = new ArrayList<>();
		{
			String sql = "SELECT id_tecnico, nome, cpf, email, telefone, especializacao FROM tecnico LIMIT 10";
			try (Connection conn = DatabaseConnection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id_tecnico");
					String nome = rs.getString("nome");
					String cpf = rs.getString("cpf");
					String telefone = rs.getString("telefone");
					String email = rs.getString("email");
					String especializacao = rs.getString("especializacao");

					listTecnico.add(new Tecnico(id, nome, cpf, telefone, email, especializacao));
				}
			} catch (SQLException e) {
				logger.error("Erro ao listar todos os técnicos: " + e.getMessage(), e);
			}
		}
		return listTecnico;
	}

	@Override
	public List<Tecnico> listarPorNome(String nomeDigitado) {
		List<Tecnico> listTecnico = new ArrayList<>();
		{
			String sql = "SELECT id_tecnico, nome, cpf, email, telefone, especializacao FROM tecnico WHERE nome LIKE ? LIMIT 10";
			try (Connection conn = DatabaseConnection.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql)) {
				
				pstmt.setString(1, "%" + nomeDigitado + "%");
				try (ResultSet rs = pstmt.executeQuery()) {
					while (rs.next()) {
						int id = rs.getInt("id_tecnico");
						String nome = rs.getString("nome");
						String cpf = rs.getString("cpf");
						String telefone = rs.getString("telefone");
						String email = rs.getString("email");
						String especializacao = rs.getString("especializacao");

						listTecnico.add(new Tecnico(id, nome, cpf, telefone, email, especializacao));
					}
				}
			} catch (SQLException e) {
				logger.error("Erro ao listar técnicos por nome: " + e.getMessage(), e);
			}
		}
		return listTecnico;
	}

	@Override
	public Tecnico buscarPorId(int id) {
		String sql = "SELECT nome, cpf, email, telefone, especializacao FROM tecnico WHERE id_tecnico=? LIMIT 10";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String nome = rs.getString("nome");
					String cpf = rs.getString("cpf");
					String telefone = rs.getString("telefone");
					String email = rs.getString("email");
					String especializacao = rs.getString("especializacao");

					return new Tecnico(id, nome, cpf, telefone, email, especializacao);
				}
			}
		} catch (SQLException e) {
			logger.error("Erro ao buscar técnicos por id: " + e.getMessage(), e);
		}
		return null;
	}

	@Override
	public Tecnico buscarPorNome(String nome) {
		String sql = "SELECT id_tecnico, cpf, email, telefone, especializacao FROM tecnico WHERE nome=? LIMIT 10";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, nome);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id_tecnico");
					String cpf = rs.getString("cpf");
					String telefone = rs.getString("telefone");
					String email = rs.getString("email");
					String especializacao = rs.getString("especializacao");

					return new Tecnico(id, nome, cpf, telefone, email, especializacao);
				}
			}
		} catch (SQLException e) {
			logger.error("Erro ao buscar técnicos por nome: " + e.getMessage(), e);
		}
		return null;
	}

}
