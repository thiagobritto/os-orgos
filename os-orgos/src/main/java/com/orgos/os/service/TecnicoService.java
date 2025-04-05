package com.orgos.os.service;

import java.util.List;

import com.orgos.os.dao.TecnicoDao;
import com.orgos.os.model.Tecnico;
import com.orgos.os.util.OperacaoResultado;

public class TecnicoService {

	private TecnicoDao dao;

	public TecnicoService(TecnicoDao dao) {
		this.dao = dao;
	}

	public OperacaoResultado salvar(Tecnico tecnico) {
		return dao.salvar(tecnico);
	}

	public OperacaoResultado atualizar(Tecnico tecnico) {
		return dao.atualizar(tecnico);
	}

	public OperacaoResultado remover(Tecnico tecnico) {
		return dao.remover(tecnico.getId());
	}

	public List<Tecnico> listarTodos() {
		return dao.listarTodos();
	}

	public List<Tecnico> listarPorNome(String nome) {
		return dao.listarPorNome(nome);
	}

	public Tecnico buscarPorId(int id) {
		return dao.buscarPorId(id);
	}

	public Tecnico buscarPorNome(String nome) {
		return dao.buscarPorNome(nome);
	}

}
