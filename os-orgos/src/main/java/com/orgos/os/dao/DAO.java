package com.orgos.os.dao;

import java.util.List;

import com.orgos.os.model.OperacaoResultado;

public interface DAO<T, ID> {
	OperacaoResultado salvar(T object);
	OperacaoResultado atualizar(T object);
	OperacaoResultado remover(ID id);
	List<T> listarTodos();
	T buscarPorId(ID id);
}
