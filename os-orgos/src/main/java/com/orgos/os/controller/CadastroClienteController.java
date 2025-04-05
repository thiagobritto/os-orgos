package com.orgos.os.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

import com.orgos.os.model.Cliente;
import com.orgos.os.service.ClienteService;
import com.orgos.os.util.Consulta;
import com.orgos.os.util.OperacaoResultado;
import com.orgos.os.view.CadastroClienteScreen;

public class CadastroClienteController implements Controller {
	private CadastroClienteScreen screen;
	private ClienteService clienteService;

	public CadastroClienteController(CadastroClienteScreen screen, ClienteService clienteService) {
		this.screen = screen;
		this.clienteService = clienteService;
	}

	@Override
	public void inicializar() {
		screen.exibirConsultas(getListConsulta());
		screen.exibirClientes(clienteService.listarTodos());
		screen.addNovoListener(this::novo);
		screen.addAlterarListener(this::alterar);
		screen.addExcluirListener(this::excluir);
		screen.addSalvarListener(this::salvar);
		screen.addConsultaKeyListener(new ConsultaKeyAdapter());
	}

	private void novo(ActionEvent actionevent1) {
		screen.setClienteSelecionado(null);
		screen.liberarParaInserir();
	}

	private void alterar(ActionEvent actionevent1) {
		Cliente clienteSelecionado = screen.getClienteSelecionado();
		if (Objects.isNull(clienteSelecionado)) {
			screen.exibirMensagem("Selecione um 'Cliente' para continuar.");
		} else {
			screen.liberarParaAlterar();
		}
	}

	private void excluir(ActionEvent actionevent1) {
		Cliente clienteSelecionado = screen.getClienteSelecionado();
		if (Objects.isNull(clienteSelecionado)) {
			screen.exibirMensagem("Selecione um 'Cliente' para continuar.");
		} else {
			boolean resposta = screen
					.exibirMensagemConfirmacao("Tem certeza que deseja excluir: " + clienteSelecionado.getNome());
			if (resposta) {
				OperacaoResultado resultado = clienteService.remover(clienteSelecionado);
				if (resultado.isSucesso()) {
					screen.resetarTela();
					screen.exibirMensagem(resultado.getMensagem());
					screen.exibirClientes(clienteService.listarTodos());
				} else {
					screen.exibirMensagemAviso(resultado.getMensagem());
				}
			}
		}
	}

	private void salvar(ActionEvent actionevent1) {
		if (screen.validarDados()) {
			String nome = screen.getNome();
			String cpfCnpj = screen.getCpfcnpj();
			String email = screen.getEmail();
			String telefone = screen.getTelefone();
			String endereco = screen.getEndereco();
			Cliente clienteData = new Cliente(0, nome, cpfCnpj, telefone, email, endereco);

			Cliente clienteSelecionado = screen.getClienteSelecionado();
			if (Objects.isNull(clienteSelecionado)) {
				salvar(clienteData);
			} else {
				atualizar(clienteSelecionado, clienteData);
			}
		}
	}

	private void salvar(Cliente cliente) {
		try {
			OperacaoResultado resultado = clienteService.salvar(cliente);
			if (resultado.isSucesso()) {
				screen.resetarTela();
				screen.exibirMensagem(resultado.getMensagem());
				screen.exibirClientes(clienteService.listarTodos());
			} else {
				screen.exibirMensagemAviso(resultado.getMensagem());
			}
		} catch (IllegalArgumentException e) {
			screen.exibirMensagemErro(e.getMessage());
		}
	}

	private void atualizar(Cliente oldClienteData, Cliente newClienteData) {
		newClienteData.setId(oldClienteData.getId());
		try {
			OperacaoResultado resultado = clienteService.atualizar(newClienteData);
			if (resultado.isSucesso()) {
				screen.resetarTela();
				screen.exibirMensagem(resultado.getMensagem());
				screen.exibirClientes(clienteService.listarTodos());
			} else {
				screen.exibirMensagemAviso(resultado.getMensagem());
			}
		} catch (IllegalArgumentException e) {
			screen.exibirMensagemErro(e.getMessage());
		}
	}

	private Consulta[] getListConsulta() {
		return new Consulta[] { new ConsultaClienteId(), new ConsultaClienteNome() };
	}

	private class ConsultaClienteId implements Consulta {
		private int id;

		@Override
		public void procurar(String text) {
			try {
				id = Integer.parseInt(text);
			} catch (NumberFormatException e) {
				return;
			}
			screen.exibirClientes(List.of(clienteService.buscarPorId(id)));
		}

		@Override
		public String toString() {
			return "Código";
		}
	}

	private class ConsultaClienteNome implements Consulta {
		@Override
		public void procurar(String text) {
			screen.exibirClientes(clienteService.listarPorNome(text));
		}

		@Override
		public String toString() {
			return "Nome";
		}
	}

	private class ConsultaKeyAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			Consulta consulta = screen.getConsulta();
			consulta.procurar(screen.getConsultaTexto());
		}
	}
}
