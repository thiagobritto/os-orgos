package com.orgos.os.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orgos.os.dao.TecnicoDao;
import com.orgos.os.model.Tecnico;
import com.orgos.os.util.OperacaoResultado;

public class TecnicoDaoImpl implements TecnicoDao {

	@Override
	public OperacaoResultado salvar(Tecnico tecnico, Connection conn) throws SQLException {
		String sql = "INSERT INTO tecnico (nome, cpf, email, telefone, especializacao) VALUES (?,?,?,?,?)";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, tecnico.getNome());
			pstmt.setString(2, tecnico.getCpf());
			pstmt.setString(3, tecnico.getEmail());
			pstmt.setString(4, tecnico.getTelefone());
			pstmt.setString(5, tecnico.getEspecializacao());
			
			return pstmt.executeUpdate() > 0 
					? OperacaoResultado.sucesso("Técnico inserido com sucesso!")
					: OperacaoResultado.erro("Nenhum técnico foi inserido.");
		}
	}

	@Override
	public OperacaoResultado atualizar(Tecnico tecnico, Connection conn) throws SQLException {
		String sql = "UPDATE tecnico SET nome=?, cpf=?, email=?, telefone=?, especializacao=? WHERE id_tecnico=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, tecnico.getNome());
			pstmt.setString(2, tecnico.getCpf());
			pstmt.setString(3, tecnico.getEmail());
			pstmt.setString(4, tecnico.getTelefone());
			pstmt.setString(5, tecnico.getEspecializacao());
			pstmt.setInt(6, tecnico.getId());
			
			return pstmt.executeUpdate() > 0 
					? OperacaoResultado.sucesso("Técnico atualizado com sucesso!")
					: OperacaoResultado.erro("Nenhum técnico foi atualizado.");

		}
	}

	@Override
	public OperacaoResultado remover(int id, Connection conn) throws SQLException {
		String sql = "DELETE FROM tecnico WHERE id_tecnico = ?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate() > 0 
					? OperacaoResultado.sucesso("Técnico removido com sucesso!")
					: OperacaoResultado.erro("Nenhum técnico foi removido.");
		}
	}

	@Override
	public List<Tecnico> listarTodos(Connection conn) throws SQLException {
		String sql = "SELECT id_tecnico, nome, cpf, email, telefone, especializacao FROM tecnico LIMIT 10";
		List<Tecnico> listTecnico = new ArrayList<>();		
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
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
		
		return listTecnico;
	}

	@Override
	public List<Tecnico> listarPorNome(String nomeDigitado, Connection conn) throws SQLException {
		List<Tecnico> listTecnico = new ArrayList<>();
		String sql = "SELECT id_tecnico, nome, cpf, email, telefone, especializacao FROM tecnico WHERE nome LIKE ? LIMIT 10";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
		}
		
		return listTecnico;
	}

	@Override
	public Tecnico buscarPorId(int id, Connection conn) throws SQLException {
		String sql = "SELECT nome, cpf, email, telefone, especializacao FROM tecnico WHERE id_tecnico=? LIMIT 10";		
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
		}
		
		return null;
	}

	@Override
	public Tecnico buscarPorNome(String nome, Connection conn) throws SQLException {
		String sql = "SELECT id_tecnico, cpf, email, telefone, especializacao FROM tecnico WHERE nome=? LIMIT 10";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
		}
		
		return null;
	}

}
