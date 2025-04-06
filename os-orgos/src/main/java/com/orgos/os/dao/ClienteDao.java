package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.orgos.os.model.Cliente;
import com.orgos.os.util.OperacaoResultado;

public interface ClienteDao {
	OperacaoResultado salvar(Cliente cliente, Connection conn) throws SQLException;
	OperacaoResultado atualizar(Cliente cliente, Connection conn) throws SQLException;
	OperacaoResultado remover(int id, Connection conn) throws SQLException;
	List<Cliente> listarTodos(Connection conn) throws SQLException;
	List<Cliente> listarPorNome(String nome, Connection conn) throws SQLException;
	Cliente buscarPorId(int id, Connection conn) throws SQLException;
	Cliente buscarPorNome(String nome, Connection conn) throws SQLException;
}
