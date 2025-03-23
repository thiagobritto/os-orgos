package com.orgos.os.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BuscaClienteScreen extends JDialogScreen {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public BuscaClienteScreen(JFrame owner) {
		super(owner, true);
		setTitle("Busca de Clientes");
		setSize(800, 540);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);
		
		this.iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		
	}
	
}