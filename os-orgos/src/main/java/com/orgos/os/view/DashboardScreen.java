package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.orgos.os.controller.DashboardController;
import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Usuario;

public class DashboardScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DashboardController controller;
	private JDesktopPane desktopPane;

	/**
	 * Create the frame.
	 */
	public DashboardScreen(Usuario usuario) {
		setTitle("Dashboaed");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu cadastroMenu = new JMenu("Cadastro");
		menuBar.add(cadastroMenu);
		
		if (usuario.getPermissoes().stream().anyMatch(p -> p.getFuncionalidade().equals(Funcionalidade.GERENCIAR_USUARIO))) {
			JMenuItem usuarioMenuItem = new JMenuItem("Usuário");
			usuarioMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Admin");
				}
			});		
			cadastroMenu.add(usuarioMenuItem);
		}
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.BLUE);
		contentPane.add(desktopPane);		

		controller = new DashboardController(this, usuario);
		controller.carregarDadosUsuario();
		
	}

	public void exibirDadosUsuario(String username, int id) {
		setTitle(String.format("%s - %s", getTitle(), username));
	}

}
