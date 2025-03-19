package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.model.Cliente;

public class ClienteDAO {
	private static final Logger logger = LogManager.getLogger(ClienteDAO.class);

	public boolean salvar(Cliente cliente) {
		String sql = "INSERT INTO cliente (nome, cpf_cnpj, telefone, email, endereco) VALUES (?,?,?,?,?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpfCnpj());
			pstmt.setString(3, cliente.getTelefone());
			pstmt.setString(4, cliente.getEmail());
			pstmt.setString(5, cliente.getEndereco());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			logger.error("Erro ao salvar cliente: " + e.getMessage(), e);
			return false;
		}
	}

	public List<Cliente> listarTodos() {
		String sql = "SELECT id_cliente, nome, cpf_cnpj, telefone, email, endereco  FROM cliente";
		List<Cliente> clientes = new ArrayList<Cliente>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id_cliente");
				String nome = rs.getString("nome");
				String cpfCnpj = rs.getString("cpf_cnpj");
				String telefone = rs.getString("telefone");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");

				clientes.add(new Cliente(id, nome, cpfCnpj, telefone, email, endereco));
			}

		} catch (SQLException e) {
			logger.error("Erro ao listar clientes: " + e.getMessage(), e);
		}
		return clientes;
	}

	public Cliente buscarPorId(int id) {
		String sql = "SELECT nome, cpf_cnpj, telefone, email, endereco  FROM cliente WHERE id_cliente = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String nome = rs.getString("nome");
					String cpfCnpj = rs.getString("cpf_cnpj");
					String telefone = rs.getString("telefone");
					String email = rs.getString("email");
					String endereco = rs.getString("endereco");

					return new Cliente(id, nome, cpfCnpj, telefone, email, endereco);
				}
			}

		} catch (SQLException e) {
			logger.error("Erro ao buscar cliente (" + id + "): " + e.getMessage(), e);
		}
		return null;
	}
}
