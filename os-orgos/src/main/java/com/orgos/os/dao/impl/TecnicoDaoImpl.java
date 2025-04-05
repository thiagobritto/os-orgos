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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperacaoResultado atualizar(Tecnico tecnico) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperacaoResultado remover(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tecnico> listarTodos() {
		String sql = "SELECT id_tecnico, nome, cpf, telefone, especializacao FROM tecnico LIMIT 10";
		List<Tecnico> listTecnico = new ArrayList<>();
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id_tecnico");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String telefone = rs.getString("telefone");
				String especializacao = rs.getString("especializacao");
				
				listTecnico.add(new Tecnico(id, nome, cpf, telefone, telefone, especializacao));
			}
		} catch (SQLException e) {
			logger.error("Erro ao listar todos os técnicos: " + e.getMessage(), e);
		}
		return listTecnico;
	}

	@Override
	public List<Tecnico> listarPorNome(String nome) {
		return null;
	}

	@Override
	public Tecnico buscarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tecnico buscarPorNome(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
