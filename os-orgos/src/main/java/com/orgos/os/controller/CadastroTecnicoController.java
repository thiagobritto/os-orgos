package com.orgos.os.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

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
		if (tecnicoSelecionado == null) {
			screen.exibirMensagem("Selecione um 'Técnico' para continuar.");
		} else {
			screen.liberarParaAlterar();
		}
	}

	private void excluir(ActionEvent actionevent1) {
		Tecnico tecnicoSelecionado = screen.getTecnicoSelecionado();
		if (tecnicoSelecionado == null) {
			screen.exibirMensagem("Selecione um 'Técnico' para continuar.");
			return;
		}

		String nome = tecnicoSelecionado.getNome();
		boolean confirma = screen.exibirMensagemConfirmacao("Tem certeza que deseja excluir: " + nome);
		if (!confirma) {
			return;
		}

		OperacaoResultado resultado = tecnicoService.remover(tecnicoSelecionado);
		if (resultado.isSucesso()) {
			screen.resetarTela();
			screen.exibirMensagem(resultado.getMensagem());
			screen.exibirTecnicos(tecnicoService.listarTodos());
		} else {
			screen.exibirMensagemAviso(resultado.getMensagem());
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
			if (tecnicoSelecionado == null) {
				salvar(tecnicoData);
			} else {
				atualizar(tecnicoSelecionado, tecnicoData);
			}
		}
	}

	private void salvar(Tecnico tecnico) {
		OperacaoResultado resultado = tecnicoService.salvar(tecnico);
		if (resultado.isSucesso()) {
			screen.resetarTela();
			screen.exibirMensagem(resultado.getMensagem());
			screen.exibirTecnicos(tecnicoService.listarTodos());
		} else {
			screen.exibirMensagemAviso(resultado.getMensagem());
		}
	}

	private void atualizar(Tecnico oldTecnico, Tecnico newTecnico) {
		newTecnico.setId(oldTecnico.getId());
		OperacaoResultado resultado = tecnicoService.atualizar(newTecnico);
		if (resultado.isSucesso()) {
			screen.resetarTela();
			screen.exibirMensagem(resultado.getMensagem());
			screen.exibirTecnicos(tecnicoService.listarTodos());
		} else {
			screen.exibirMensagemAviso(resultado.getMensagem());
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
			if (tecnico != null) {
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
