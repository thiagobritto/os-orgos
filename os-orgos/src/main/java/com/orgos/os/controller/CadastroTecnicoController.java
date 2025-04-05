package com.orgos.os.controller;

import com.orgos.os.service.TecnicoService;
import com.orgos.os.util.Consulta;
import com.orgos.os.view.CadastroTecnicoScreen;

public class CadastroTecnicoController implements Controller {

	private CadastroTecnicoScreen screen;
	private TecnicoService tecnicoService;

	public CadastroTecnicoController(CadastroTecnicoScreen screen, TecnicoService tecnicoService) {
		this.screen = screen;
		this.tecnicoService = tecnicoService;
	}

	@Override
	public void inicializar() {
		screen.exibirConsultas(getListConsilta());
		screen.exibirTecnicos(tecnicoService.listarTodos());
	}

	private Consulta[] getListConsilta() {
		return new Consulta[] { new ConsultaId(), new ConsultaNome() };
	}

	private class ConsultaId implements Consulta {
		private int id;

		@Override
		public void procurar(String text) {
			try {
				id = Integer.parseInt(text);
			} catch (NumberFormatException e) {
				return;
			}
		}

		@Override
		public String toString() {
			return "Código";
		}
	}

	private class ConsultaNome implements Consulta {
		@Override
		public void procurar(String text) {

		}

		@Override
		public String toString() {
			return "Nome";
		}
	}

}
