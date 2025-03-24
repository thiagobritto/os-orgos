package com.orgos.os.model;

import java.util.Collections;
import java.util.List;

import com.orgos.os.service.TecnicoService;

public class PesquisaTecnicoId implements PesquisaTecnico {

	private TecnicoService tecnicoService;

	public PesquisaTecnicoId(TecnicoService tecnicoService) {
		super();
		this.tecnicoService = tecnicoService;
	}

	@Override
	public List<Tecnico> buscar(String valor) {
		try {
			int id = Integer.parseInt(valor);
			return List.of(tecnicoService.buscarPorId(id));
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}
	
	@Override
	public String toString() {
		return "Códido";
	}

}
