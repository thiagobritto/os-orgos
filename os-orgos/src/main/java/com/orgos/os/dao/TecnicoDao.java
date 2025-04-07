package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.orgos.os.model.Tecnico;
import com.orgos.os.util.OperacaoResultado;

public interface TecnicoDao {

	OperacaoResultado salvar(Tecnico tecnico, Connection conn) throws SQLException;
	OperacaoResultado atualizar(Tecnico tecnico, Connection conn) throws SQLException;
	OperacaoResultado remover(int id, Connection conn) throws SQLException;
	List<Tecnico> listarTodos(Connection conn) throws SQLException;
	List<Tecnico> listarPorNome(String nome, Connection conn) throws SQLException;
	Tecnico buscarPorId(int id, Connection conn) throws SQLException;
	Tecnico buscarPorNome(String nome, Connection conn) throws SQLException;
	
}
