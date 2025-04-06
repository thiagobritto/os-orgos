package com.orgos.os.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

import com.orgos.os.model.Cliente;
import com.orgos.os.model.Tecnico;
import com.orgos.os.service.ClienteService;
import com.orgos.os.service.TecnicoService;
import com.orgos.os.view.CadastroOrdemServicoScreen;
import com.orgos.os.view.CadastroOrdemServicoScreen.Busca;

public class CadastroOrdemServicoController implements Controller {

	private CadastroOrdemServicoScreen screen;
	private ClienteService clienteService;
	private TecnicoService tecnicoService;

	public CadastroOrdemServicoController(CadastroOrdemServicoScreen screen, ClienteService clienteService, TecnicoService tecnicoService) {
		this.screen = screen;
		this.clienteService = clienteService;
		this.tecnicoService = tecnicoService;
	}

	@Override
	public void inicializar() {
		screen.exibirClientes(clienteService.listarTodos());
		screen.exibirTecnicos(tecnicoService.listarTodos());
		screen.addBuscarClienteListener(this::buscarCliente);
		screen.addBuscarTecnicoListener(this::buscarTecnico);
	}
	
	

	private void buscarCliente(ActionEvent actionevent1) {
		Busca<Cliente> busca = screen.getBusca();
		busca.addConsultaKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				List<Cliente> listCliente = clienteService.listarPorNome(busca.getTextConsulta());
				busca.exibirLista(listCliente);
			}
		});
		
		busca.addListMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					Cliente cliente = busca.getSelectedValue();
					if (Objects.nonNull(cliente)) {
						screen.exibirClientes(List.of(cliente));
						busca.dispose();
					}
				}
			}
		});
		
		busca.setVisible(true);
	}
	
	private void buscarTecnico(ActionEvent actionevent1) {
		Busca<Tecnico> busca = screen.getBusca();
		busca.addConsultaKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);
				List<Tecnico> listCliente = tecnicoService.listarPorNome(busca.getTextConsulta());
				busca.exibirLista(listCliente);
			}
		});
		
		busca.addListMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (e.getClickCount() == 2) {
					Tecnico tecnico = busca.getSelectedValue();
					if (Objects.nonNull(tecnico)) {
						screen.exibirTecnicos(List.of(tecnico));
						busca.dispose();
					}
				}
			}
		});
		
		busca.setVisible(true);
	}
	
	
}
