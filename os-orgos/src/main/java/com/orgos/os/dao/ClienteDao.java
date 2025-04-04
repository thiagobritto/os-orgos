package com.orgos.os.dao;

import java.util.List;

import com.orgos.os.model.Cliente;
import com.orgos.os.util.OperacaoResultado;

public interface ClienteDao {
	OperacaoResultado salvar(Cliente cliente);
	OperacaoResultado atualizar(Cliente cliente);
	OperacaoResultado remover(int id);
	List<Cliente> listarTodos();
	List<Cliente> listarPorNome(String nome);
	Cliente buscarPorId(int id);
	Cliente buscarPorNome(String nome);
}
