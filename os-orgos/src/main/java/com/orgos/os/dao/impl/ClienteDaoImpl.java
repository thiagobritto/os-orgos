package com.orgos.os.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orgos.os.dao.ClienteDao;
import com.orgos.os.model.Cliente;
import com.orgos.os.util.OperacaoResultado;

public class ClienteDaoImpl implements ClienteDao {

	@Override
	public OperacaoResultado inserirCliente(Cliente cliente, Connection conn) throws SQLException {
		String sql = "INSERT INTO cliente (nome, cpf_cnpj, telefone, email, endereco) VALUES (?,?,?,?,?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpfCnpj());
			pstmt.setString(3, cliente.getTelefone());
			pstmt.setString(4, cliente.getEmail());
			pstmt.setString(5, cliente.getEndereco());

			return pstmt.executeUpdate() > 0 
					? OperacaoResultado.sucesso("Cliente inserido com sucesso.")
					: OperacaoResultado.erro("Nenhum cliente foi inserido.");
		}
	}

	@Override
	public OperacaoResultado atualizarCliente(Cliente cliente, Connection conn) throws SQLException {
		String sql = "UPDATE cliente SET nome=?, cpf_cnpj=?, telefone=?, email=?, endereco=? WHERE id_cliente = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cliente.getNome());
			pstmt.setString(2, cliente.getCpfCnpj());
			pstmt.setString(3, cliente.getTelefone());
			pstmt.setString(4, cliente.getEmail());
			pstmt.setString(5, cliente.getEndereco());
			pstmt.setInt(6, cliente.getId());

			return pstmt.executeUpdate() > 0 
					? OperacaoResultado.sucesso("Cliente atualizado com sucesso.")
					: OperacaoResultado.erro("Nenhum cliente foi atualizado.");
		}
	}

	@Override
	public OperacaoResultado removerCliente(int id, Connection conn) throws SQLException {
		String sql = "DELETE FROM cliente WHERE id_cliente = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);

			return pstmt.executeUpdate() > 0 
					? OperacaoResultado.sucesso("Cliente removido com sucesso.")
					: OperacaoResultado.erro("Nenhum cliente foi removido.");
		}
	}

	@Override
	public List<Cliente> listarTodosOsClientes(Connection conn) throws SQLException {
		String sql = "SELECT id_cliente, nome, cpf_cnpj, telefone, email, endereco  FROM cliente LIMIT 10";
		List<Cliente> clientes = new ArrayList<Cliente>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id_cliente");
				String nome = rs.getString("nome");
				String cpfCnpj = rs.getString("cpf_cnpj");
				String telefone = rs.getString("telefone");
				String email = rs.getString("email");
				String endereco = rs.getString("endereco");

				clientes.add(new Cliente(id, nome, cpfCnpj, telefone, email, endereco));
			}
		}
		return clientes;
	}

	@Override
	public List<Cliente> listarClientesPorNome(String nomeDigitado, Connection conn) throws SQLException {
		String sql = "SELECT id_cliente, nome, cpf_cnpj, telefone, email, endereco  FROM cliente WHERE nome LIKE ? LIMIT 10";
		List<Cliente> clientes = new ArrayList<Cliente>();
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, "%" + nomeDigitado + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id_cliente");
					String nome = rs.getString("nome");
					String cpfCnpj = rs.getString("cpf_cnpj");
					String telefone = rs.getString("telefone");
					String email = rs.getString("email");
					String endereco = rs.getString("endereco");

					clientes.add(new Cliente(id, nome, cpfCnpj, telefone, email, endereco));
				}
			}
		}
		return clientes;
	}

	@Override
	public Cliente buscarClientePorId(int id, Connection conn) throws SQLException {
		String sql = "SELECT nome, cpf_cnpj, telefone, email, endereco  FROM cliente WHERE id_cliente = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
				return null;
			}
		}
	}

	@Override
	public Cliente buscarClientePorNome(String nomeDigitado, Connection conn) throws SQLException {
		String sql = "SELECT id_cliente, nome, cpf_cnpj, telefone, email, endereco  FROM cliente WHERE nome = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, nomeDigitado);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id_cliente");
					String nome = rs.getString("nome");
					String cpfCnpj = rs.getString("cpf_cnpj");
					String telefone = rs.getString("telefone");
					String email = rs.getString("email");
					String endereco = rs.getString("endereco");

					return new Cliente(id, nome, cpfCnpj, telefone, email, endereco);
				}
				return null;
			}
		}
	}

}
