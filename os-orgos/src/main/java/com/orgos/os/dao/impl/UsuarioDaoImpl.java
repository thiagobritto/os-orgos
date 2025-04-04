package com.orgos.os.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.orgos.os.dao.UsuarioDao;
import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Permissao;
import com.orgos.os.model.Usuario;
import com.orgos.os.util.OperacaoResultado;
import com.orgos.os.util.PasswordUtil;

public class UsuarioDaoImpl implements UsuarioDao {

	private static final Logger logger = LogManager.getLogger(UsuarioDaoImpl.class);

	@Override
	public OperacaoResultado salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperacaoResultado atualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperacaoResultado remover(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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
	
	// 
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
			logger.error("Erro ao buscar permissões do usuário: " + e.getMessage(), e);
		}
		return permissoes;
	}

}
