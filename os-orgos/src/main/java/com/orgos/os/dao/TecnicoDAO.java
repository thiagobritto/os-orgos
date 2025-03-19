package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.model.Tecnico;

public class TecnicoDAO {
	private static final Logger logger = LogManager.getLogger(TecnicoDAO.class);

	public Tecnico buscarPorId(int id) {
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
			logger.error("Erro ao buscar cliente (" + id + "): " + e.getMessage(), e);
		}
		return null;
	}
}
