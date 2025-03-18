package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.orgos.os.controller.DashboardController;
import com.orgos.os.model.Usuario;

public class DashboardScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JLabel usernameLabel;
	private DashboardController controller;

	/**
	 * Create the frame.
	 */
	public DashboardScreen(Usuario usuario) {
		this.controller = new DashboardController(this, usuario);
		this.initCoponent();
		this.controller.carregarDadosUsuario();
	}

	private void initCoponent() {
		setTitle("Dashboaed");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// menu
		menuBar = new JMenuBar();

		JMenu arquivoMenu = new JMenu("Arquivo");
		menuBar.add(arquivoMenu);

		JMenuItem arquivoSobreMenuItem = new JMenuItem("Sobre");
		arquivoMenu.add(arquivoSobreMenuItem);

		JMenuItem arquivoSairMenuItem = new JMenuItem("Sair");
		arquivoSairMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		arquivoMenu.add(arquivoSairMenuItem);

		JMenu cadastroMenu = new JMenu("Cadastro");
		menuBar.add(cadastroMenu);

		JMenuItem cadastroClienteMenuItem = new JMenuItem("Cliente");
		cadastroMenu.add(cadastroClienteMenuItem);

		JMenuItem cadastroServicoMenuItem = new JMenuItem("Serviço");
		cadastroMenu.add(cadastroServicoMenuItem);

		JMenu cadastroUsuarioMenu = new JMenu("Usuário");
		cadastroMenu.add(cadastroUsuarioMenu);

		JMenuItem cadastroUsuarioNovoMenuItem = new JMenuItem("Novo");
		cadastroUsuarioNovoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroUsuarioScreen(DashboardScreen.this).setVisible(true);
			}
		});
		cadastroUsuarioMenu.add(cadastroUsuarioNovoMenuItem);

		JMenuItem cadastroUsuarioGerenciarMenuItem = new JMenuItem("Gerenciar");
		cadastroUsuarioGerenciarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GerenciarUsuariosScreen(DashboardScreen.this).setVisible(true);
			}
		});
		cadastroUsuarioMenu.add(cadastroUsuarioGerenciarMenuItem);

		JMenu relatorioMenu = new JMenu("Relatório");
		menuBar.add(relatorioMenu);

		JMenuItem relatorioClienteMenuItem = new JMenuItem("Cliente");
		relatorioMenu.add(relatorioClienteMenuItem);

		JMenuItem relatorioServicoMenuItem = new JMenuItem("Serviço");
		relatorioMenu.add(relatorioServicoMenuItem);

		JMenuItem relatorioUsuarioMenuItem = new JMenuItem("Usuário");
		relatorioMenu.add(relatorioUsuarioMenuItem);

		setJMenuBar(menuBar);

		// content
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.BLUE);
		contentPane.add(desktopPane, BorderLayout.CENTER);

		JPanel fooderPane = new JPanel();
		contentPane.add(fooderPane, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("Logado como:");
		fooderPane.add(lblNewLabel);

		usernameLabel = new JLabel("");
		fooderPane.add(usernameLabel);

		setContentPane(contentPane);
	}

	public void exibirDadosUsuario(String username, int id) {
		usernameLabel.setText(username.toUpperCase());
	}

}
