package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;
import com.orgos.os.util.PasswordUtil;

public class UsuarioDAO {

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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return permissoes;
	}

	public List<Usuario> listarTodos() {
		String sql = "SELECT id_usuario, username FROM usuarios";
		List<Usuario> usuarios = new ArrayList<>();

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id_usuario");
					String username = rs.getString("username");
					List<Permissao> permissoes = buscarPermissoes(id);
					usuarios.add(new Usuario(id, username, permissoes));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public boolean cadastrarUsuario(String username, String password) {
		String sql = "INSERT INTO usuarios (username, password_hash) VALUES (?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			String hashedPassword = PasswordUtil.hashPassword(password);
			pstmt.setString(1, username);
			pstmt.setString(2, hashedPassword);

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean adicionarPermissao(int usuarioId, Funcionalidade funcionalidade) {
		String sql = "INSERT INTO permissoes (usuario_id, funcionalidade) VALUES (?, ?)";

		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, usuarioId);
			pstmt.setString(2, funcionalidade.toString());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean removerPermissao(int usuarioId, Funcionalidade funcionalidade) {
		String sql = "DELETE FROM permissoes WHERE usuario_id = ? AND funcionalidade = ?";
		
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, usuarioId);
			pstmt.setString(2, funcionalidade.toString());

			int rowsAffected = pstmt.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
