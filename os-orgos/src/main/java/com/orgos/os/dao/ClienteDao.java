package com.orgos.os.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.orgos.os.model.Cliente;
import com.orgos.os.util.OperacaoResultado;

public interface ClienteDao {
	OperacaoResultado inserirCliente(Cliente cliente, Connection conn) throws SQLException;
	OperacaoResultado atualizarCliente(Cliente cliente, Connection conn) throws SQLException;
	OperacaoResultado removerCliente(int id, Connection conn) throws SQLException;
	List<Cliente> listarTodosOsClientes(Connection conn) throws SQLException;
	List<Cliente> listarClientesPorNome(String nome, Connection conn) throws SQLException;
	Cliente buscarClientePorId(int id, Connection conn) throws SQLException;
	Cliente buscarClientePorNome(String nome, Connection conn) throws SQLException;
}
