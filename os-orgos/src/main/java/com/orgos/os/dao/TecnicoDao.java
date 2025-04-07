package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.orgos.os.model.Tecnico;
import com.orgos.os.util.OperacaoResultado;

public interface TecnicoDao {

	OperacaoResultado inserirTecnico(Tecnico tecnico, Connection conn) throws SQLException;
	OperacaoResultado atualizarTecnico(Tecnico tecnico, Connection conn) throws SQLException;
	OperacaoResultado removerTecnico(int id, Connection conn) throws SQLException;
	List<Tecnico> listarTodosOsTecnicos(Connection conn) throws SQLException;
	List<Tecnico> listarTecnicosPorNome(String nome, Connection conn) throws SQLException;
	Tecnico buscarTecnicoPorId(int id, Connection conn) throws SQLException;
	Tecnico buscarTecnicoPorNome(String nome, Connection conn) throws SQLException;
}
