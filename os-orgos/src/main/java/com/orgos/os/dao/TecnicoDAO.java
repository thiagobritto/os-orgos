package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.model.Tecnico;

public class TecnicoDAO implements DAO<Tecnico, Integer> {
	private static final Logger logger = LogManager.getLogger(TecnicoDAO.class);

	@Override
	public OperacaoResultado salvar(Tecnico tecnico) {
		String sql = "INSERT INTO tecnico (nome, cpf, telefone, email, especializacao) VALUES (?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, tecnico.getNome());
			pstmt.setString(2, tecnico.getCpf());
			pstmt.setString(3, tecnico.getTelefone());
			pstmt.setString(4, tecnico.getEmail());
			pstmt.setString(5, tecnico.getEspecializacao());

			return pstmt.executeUpdate() > 0 
					? new OperacaoResultado(true, "Técnico inserido com sucesso.")
					: new OperacaoResultado(false, "Nenhum Técnico foi inserido.");

		} catch (SQLException e) {
			logger.error("Erro ao inserir tecnico: " + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao inserir tecnico: " + e.getMessage());
		}
	}

	@Override
	public OperacaoResultado atualizar(Tecnico tecnico) {
		String sql = "UPDATE tecnico SET nome=?, cpf=?, telefone=?, email=?, especializacao=? WHERE id_tecnico = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, tecnico.getNome());
			pstmt.setString(2, tecnico.getCpf());
			pstmt.setString(3, tecnico.getTelefone());
			pstmt.setString(4, tecnico.getEmail());
			pstmt.setString(5, tecnico.getEspecializacao());
			pstmt.setInt(6, tecnico.getId());

			return pstmt.executeUpdate() > 0 
					? new OperacaoResultado(true, "Técnico atualizado com sucesso.")
					: new OperacaoResultado(false, "Nenhum técnico foi atualizado.");

		} catch (SQLException e) {
			logger.error("Erro ao atualizar técnico: " + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao atualizar técnico: " + e.getMessage());
		}
	}

	@Override
	public OperacaoResultado remover(Integer id) {
		String sql = "DELETE FROM tecnico WHERE id_tecnico = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);

			return pstmt.executeUpdate() > 0 
					? new OperacaoResultado(true, "Técnico removido com sucesso.")
					: new OperacaoResultado(false, "Nenhum técnico foi removido.");
	
		} catch (SQLException e) {
			logger.error("Erro ao remover técnocp: " + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao remover técnocp: " + e.getMessage());
		}
	}

	@Override
	public List<Tecnico> listarTodos() {
		String sql = "SELECT id_tecnico, nome, cpf, telefone, email, especializacao FROM tecnico";
		ArrayList<Tecnico> tecnicos = new ArrayList<>();

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

				tecnicos.add(new Tecnico(id, nome, cpf, telefone, email, especializacao));
			}
		} catch (SQLException e) {
			logger.error("Erro ao listar tecnicos: " + e.getMessage(), e);
		}
		return tecnicos;
	}

	@Override
	public Tecnico buscarPorId(Integer id) {
		String sql = "SELECT nome, cpf, telefone, email, especializacao FROM tecnico WHERE id_tecnico = ?";
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
			logger.error("Erro ao buscar tecnico: " + e.getMessage(), e);
		}
		return null;
	}
	
	public List<Tecnico> buscarPorNome(String nomeDigitado) {
		String sql = "SELECT id_tecnico, nome, cpf, telefone, email, especializacao FROM tecnico WHERE nome LIKE ?";
		List<Tecnico> tecnicos = new ArrayList<Tecnico>();

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

					tecnicos.add(new Tecnico(id, nome, cpf, telefone, email, especializacao));
				}
			}
		} catch (SQLException e) {
			logger.error("Erro ao buscar tecnicos: " + e.getMessage(), e);
		}
		return tecnicos;
	}

}
