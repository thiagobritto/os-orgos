package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.OperacaoResultado;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;
import com.orgos.os.util.PasswordUtil;

public class UsuarioDAO {
	private static final Logger logger = LogManager.getLogger(UsuarioDAO.class);

	public Usuario autenticar(String username, String password) {
		String sql = "SELECT id_usuario, password_hash FROM usuarios WHERE username = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, username);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String hashedPassword = rs.getString("password_hash");
					if (PasswordUtil.checkPassword(password, hashedPassword)) {
						int id = rs.getInt("id_usuario");
						List<Permissao> permissoes = buscarPermissoes(id);

						return new Usuario(id, username, permissoes);
					}
				}
			}

		} catch (SQLException e) {
			logger.error("Erro ao autenticar o usuário: " + e.getMessage(), e);
		}
		return null;
	}

	public List<Permissao> buscarPermissoes(int usuarioId) {
		String sql = "SELECT id_permissoes, funcionalidade FROM permissoes WHERE usuario_id = ?";
		List<Permissao> permissoes = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, usuarioId);
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id_permissoes");
					Funcionalidade funcionalidade = Funcionalidade.valueOf(rs.getString("funcionalidade"));

					permissoes.add(new Permissao(id, usuarioId, funcionalidade));
				}
			}

		} catch (SQLException e) {
			logger.error("Erro ao buscar permissões do usuário (" + usuarioId + "):" + e.getMessage(), e);
		}
		return permissoes;
	}

	public List<Usuario> listarTodos() {
		String sql = "SELECT id_usuario, username FROM usuarios";
		List<Usuario> usuarios = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("id_usuario");
				String username = rs.getString("username");
				List<Permissao> permissoes = buscarPermissoes(id); // Busca as permissões do usuário

				usuarios.add(new Usuario(id, username, permissoes));
			}

		} catch (SQLException e) {
			logger.error("Erro ao listar usuários: " + e.getMessage(), e);
		}
		return usuarios;
	}

	public Usuario buscarUsuarioPorId(int usuarioId) {
		String sql = "SELECT id_usuario, username FROM usuarios WHERE id_usuario = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, usuarioId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("id_usuario");
					String username = rs.getString("username");
					List<Permissao> permissoes = buscarPermissoes(id);

					return new Usuario(id, username, permissoes);
				}
			}

		} catch (SQLException e) {
			logger.error("Erro ao buscar o usuário (" + usuarioId + "):" + e.getMessage(), e);
		}
		return null;
	}

	public List<Usuario> buscarUsuariosPorNome(String nome) {
		String sql = "SELECT id_usuario, username FROM usuarios WHERE username LIKE ?";
		List<Usuario> usuarios = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + nome + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id_usuario");
					String username = rs.getString("username");
					List<Permissao> permissoes = buscarPermissoes(id);

					usuarios.add(new Usuario(id, username, permissoes));
				}
			}

		} catch (SQLException e) {
			logger.error("Erro ao buscar o usuário (" + nome + "):" + e.getMessage(), e);
		}
		return usuarios;
	}

	public OperacaoResultado cadastrarUsuario(String username, String password) {
		String sql = "INSERT INTO usuarios (username, password_hash) VALUES (?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			String hashedPassword = PasswordUtil.hashPassword(password);
			pstmt.setString(1, username);
			pstmt.setString(2, hashedPassword);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return new OperacaoResultado(true, "Usuário inserido com sucesso.");
			} else {
				return new OperacaoResultado(false, "Nenhum usuário foi inserido.");
			}

		} catch (SQLException e) {
			logger.error("Erro ao cadastrar o usuário (" + username + "):" + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao inserir usuário: " + e.getMessage());
		}
	}

	public OperacaoResultado trocarSenha(int usuarioId, String password) {
		String sql = "UPDATE usuarios SET password_hash = ? WHERE id_usuario = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			String hashedPassword = PasswordUtil.hashPassword(password);
			pstmt.setString(1, hashedPassword);
			pstmt.setInt(2, usuarioId);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return new OperacaoResultado(true, "Senha alterada com sucesso.");
			} else {
				return new OperacaoResultado(false, "Não houve alteração na senha.");
			}

		} catch (SQLException e) {
			logger.error("Erro ao trocar senha do usuário (" + usuarioId + "):" + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao trocar senha do usuário: " + e.getMessage());
		}
	}

	public OperacaoResultado existeUsuario(String username) {
		String sql = "SELECT id_usuario FROM usuarios WHERE username = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, username);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return new OperacaoResultado(true, "Esse usuário já existe.");
				} else {
					return new OperacaoResultado(false, "Não existe usuário com esse nome.");
				}
			}

		} catch (SQLException e) {
			logger.error("Erro ao verificar existência do usuário (" + username + "):" + e.getMessage(), e);
			return new OperacaoResultado(true, "Erro ao verificar existência do usuário: " + e.getMessage());
		}
	}

	public OperacaoResultado removerUsuario(int usuarioId) {
		String sql = "DELETE FROM usuarios WHERE id_usuario = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, usuarioId);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return new OperacaoResultado(true, "Usuário removido com sucesso.");
			} else {
				return new OperacaoResultado(false, "Nenhum usuário foi removido.");
			}

		} catch (SQLException e) {
			logger.error("Erro ao remover o usuário (" + usuarioId + "):" + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao remover o usuário: " + e.getMessage());
		}
	}

	public OperacaoResultado adicionarPermissao(int usuarioId, Funcionalidade funcionalidade) {
		String sql = "INSERT INTO permissoes (usuario_id, funcionalidade) VALUES (?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, usuarioId);
			pstmt.setString(2, funcionalidade.toString());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return new OperacaoResultado(true, "Permissão adicionada com sucesso.");
			} else {
				return new OperacaoResultado(false, "Nenhum permissão foi adicionada.");
			}

		} catch (SQLException e) {
			logger.error("Erro ao adicionar permissão ao usuário (" + usuarioId + "):" + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao adicionar permissão ao usuário: " + e.getMessage());
		}
	}

	public OperacaoResultado removerPermissao(int usuarioId, Funcionalidade funcionalidade) {
		String sql = "DELETE FROM permissoes WHERE usuario_id = ? AND funcionalidade = ?";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, usuarioId);
			pstmt.setString(2, funcionalidade.toString());

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return new OperacaoResultado(true, "Permissão removida com sucesso.");
			} else {
				return new OperacaoResultado(false, "Nenhum permissão foi removida.");
			}

		} catch (SQLException e) {
			logger.error("Erro ao remover permissão do usuário (" + usuarioId + "):" + e.getMessage(), e);
			return new OperacaoResultado(false, "Erro ao remover permissão do usuário: " + e.getMessage());
		}
	}

}
