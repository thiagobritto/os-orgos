package com.orgos.os.service;

import java.util.List;

import com.orgos.os.dao.TecnicoDAO;
import com.orgos.os.model.Tecnico;

public class TecnicoService {

	private TecnicoDAO tecnicoDAO;

	
	public TecnicoService(TecnicoDAO tecnicoDAO) {
		super();
		this.tecnicoDAO = tecnicoDAO;
	}

	public List<Tecnico> listarTodos() {
		return tecnicoDAO.listarTodos();
	}

	public Tecnico buscarPorId(int id) {
		return tecnicoDAO.buscarPorId(id);
	}

	public List<Tecnico> buscarPorNome(String nome) {
		return tecnicoDAO.buscarPorNome(nome);
	}

}
