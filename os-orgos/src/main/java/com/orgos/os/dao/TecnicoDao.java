package com.orgos.os.dao;

import java.util.List;

import com.orgos.os.model.Tecnico;
import com.orgos.os.util.OperacaoResultado;

public interface TecnicoDao {

	OperacaoResultado salvar(Tecnico tecnico);
	OperacaoResultado atualizar(Tecnico tecnico);
	OperacaoResultado remover(int id);
	List<Tecnico> listarTodos();
	List<Tecnico> listarPorNome(String nome);
	Tecnico buscarPorId(int id);
	Tecnico buscarPorNome(String nome);
	
}
