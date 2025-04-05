package com.orgos.os.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

import com.orgos.os.model.Tecnico;
import com.orgos.os.service.TecnicoService;
import com.orgos.os.util.Consulta;
import com.orgos.os.view.CadastroTecnicoScreen;

public class CadastroTecnicoController implements Controller {

	private final Consulta[] CONSULTAS = { new ConsultaId(), new ConsultaNome() };
	private CadastroTecnicoScreen screen;
	private TecnicoService tecnicoService;

	public CadastroTecnicoController(CadastroTecnicoScreen screen, TecnicoService tecnicoService) {
		this.screen = screen;
		this.tecnicoService = tecnicoService;
	}

	@Override
	public void inicializar() {
		screen.exibirConsultas(CONSULTAS);
		screen.exibirTecnicos(tecnicoService.listarTodos());

		screen.addConsultaKeyListener(new ConsultaKeyAdepter());
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

			Tecnico tecnico = tecnicoService.buscarPorId(id);
			if (Objects.nonNull(tecnico)) {
				screen.exibirTecnicos(List.of(tecnico));				
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
			screen.exibirTecnicos(tecnicoService.listarPorNome(text));
		}

		@Override
		public String toString() {
			return "Nome";
		}
	}

	private class ConsultaKeyAdepter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			CONSULTAS[screen.getConsultaIndex()].procurar(screen.getConsultaText());
		}
	}

}
