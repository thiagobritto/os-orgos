package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.orgos.os.model.Usuario;
import com.orgos.os.util.PasswordUtil;

public class UsuarioDAO {

	public Usuario autenticar(String username, String password) {
		String sql = "SELECT rowId, password_hash FROM usuarios WHERE username = ?";
		
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, username);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String hashedPassword = rs.getString("password_hash");
					if (PasswordUtil.checkPassword(password, hashedPassword)) {
						int id = rs.getInt("rowId");
						return new Usuario(id, username);
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
