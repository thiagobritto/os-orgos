package com.orgos.os.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

import com.orgos.os.model.Tecnico;
import com.orgos.os.service.TecnicoService;
import com.orgos.os.util.Consulta;
import com.orgos.os.util.OperacaoResultado;
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
		screen.addNovoListener(this::novo);
		screen.addAlterarListener(this::alterar);
		screen.addExcluirListener(this::excluir);
		screen.addSalvarListener(this::salvar);
		screen.addConsultaKeyListener(new ConsultaKeyAdepter());
	}

	private void novo(ActionEvent actionevent1) {
		screen.setTecnicoSelecionado(null);
		screen.liberarParaInserir();
	}

	private void alterar(ActionEvent actionevent1) {
		Tecnico tecnicoSelecionado = screen.getTecnicoSelecionado();
		if (Objects.isNull(tecnicoSelecionado)) {
			screen.exibirMensagem("Selecione um 'Técnico' para continuar.");
		} else {
			screen.liberarParaAlterar();
		}
	}

	private void excluir(ActionEvent actionevent1) {
		Tecnico tecnicoSelecionado = screen.getTecnicoSelecionado();
		if (Objects.isNull(tecnicoSelecionado)) {
			screen.exibirMensagem("Selecione um 'Técnico' para continuar.");
		} else {
			boolean resposta = screen
					.exibirMensagemConfirmacao("Tem certeza que deseja excluir: " + tecnicoSelecionado.getNome());
			if (resposta) {
				OperacaoResultado resultado = tecnicoService.remover(tecnicoSelecionado);
				if (resultado.isSucesso()) {
					screen.resetarTela();
					screen.exibirMensagem(resultado.getMensagem());
					screen.exibirTecnicos(tecnicoService.listarTodos());
				} else {
					screen.exibirMensagemAviso(resultado.getMensagem());
				}
			}
		}
	}

	private void salvar(ActionEvent actionevent1) {
		if (screen.validarDados()) {
			String nome = screen.getNome();
			String cpf = screen.getCpfCnpj();
			String email = screen.getEmail();
			String telefone = screen.getTelefone();
			String especializacao = screen.getgetEspecializacao();
			Tecnico tecnicoData = new Tecnico(0, nome, cpf, telefone, email, especializacao);
			
			Tecnico tecnicoSelecionado = screen.getTecnicoSelecionado();
			if (Objects.isNull(tecnicoSelecionado)) {
				salvar(tecnicoData);
			} else {
				atualizar(tecnicoSelecionado, tecnicoData);
			}
		}
	}
	
	private void salvar(Tecnico tecnico) {
		try {
			OperacaoResultado resultado = tecnicoService.salvar(tecnico);
			if (resultado.isSucesso()) {
				screen.resetarTela();
				screen.exibirMensagem(resultado.getMensagem());
				screen.exibirTecnicos(tecnicoService.listarTodos());
			} else {
				screen.exibirMensagemAviso(resultado.getMensagem());
			}
		} catch (IllegalArgumentException e) {
			screen.exibirMensagemErro(e.getMessage());
		}
	}

	private void atualizar(Tecnico oldTecnico, Tecnico newTecnico) {
		newTecnico.setId(oldTecnico.getId());
		try {
			OperacaoResultado resultado = tecnicoService.atualizar(newTecnico);
			if (resultado.isSucesso()) {
				screen.resetarTela();
				screen.exibirMensagem(resultado.getMensagem());
				screen.exibirTecnicos(tecnicoService.listarTodos());
			} else {
				screen.exibirMensagemAviso(resultado.getMensagem());
			}
		} catch (IllegalArgumentException e) {
			screen.exibirMensagemErro(e.getMessage());
		}
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
