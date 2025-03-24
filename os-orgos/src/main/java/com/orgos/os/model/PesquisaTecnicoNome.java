package com.orgos.os.model;

import java.util.List;

import com.orgos.os.service.TecnicoService;

public class PesquisaTecnicoNome implements PesquisaTecnico {

	private TecnicoService tecnicoService;

	public PesquisaTecnicoNome(TecnicoService tecnicoService) {
		super();
		this.tecnicoService = tecnicoService;
	}

	@Override
	public List<Tecnico> buscar(String nome) {
		return tecnicoService.buscarPorNome(nome);
	}
	
	@Override
	public String toString() {
		return "Nome";
	}

}
