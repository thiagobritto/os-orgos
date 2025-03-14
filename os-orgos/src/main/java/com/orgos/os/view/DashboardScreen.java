package com.orgos.os.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.DashboardController;
import com.orgos.os.model.Usuario;

public class DashboardScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DashboardController controller;

	/**
	 * Create the frame.
	 */
	public DashboardScreen(Usuario usuario) {
		setTitle("Dashboaed");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		controller = new DashboardController(this, usuario);
		controller.carregarDadosUsuario();
		
		setContentPane(contentPane);
	}
	
	public void exibirDadosUsuario(String username, int id) {
		setTitle(String.format("%s - %s", getTitle(), username));
	}

}
