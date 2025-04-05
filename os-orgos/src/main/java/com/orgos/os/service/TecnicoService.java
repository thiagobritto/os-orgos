package com.orgos.os.service;

import java.util.List;

import com.orgos.os.dao.TecnicoDao;
import com.orgos.os.model.Tecnico;

public class TecnicoService {

	private TecnicoDao tecnicoDao;

	public TecnicoService(TecnicoDao tecnicoDao) {
		this.tecnicoDao = tecnicoDao;
	}
	
	public List<Tecnico> listarTodos(){
		return tecnicoDao.listarTodos();
	}
}
