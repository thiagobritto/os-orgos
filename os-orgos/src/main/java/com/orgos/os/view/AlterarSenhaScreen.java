package com.orgos.os.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.AlterarSenhaController;
import com.orgos.os.model.Usuario;

public class AlterarSenhaScreen extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private AlterarSenhaController controller;

	/**
	 * Create the dialog.
	 */
	public AlterarSenhaScreen(JFrame owner, Usuario usuario) {
		super(owner, true);
		this.controller = new AlterarSenhaController(this, usuario);
		this.initCoponent();
	}

	private void initCoponent() {
		setTitle("Alterar Senha");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(300, 235);
		setLocationRelativeTo(null);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(22, 10, 140, 16);
		contentPanel.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(22, 30, 242, 36);
		contentPanel.add(passwordField);

		JLabel lblNewLabel_3 = new JLabel("Confirmação de senha");
		lblNewLabel_3.setBounds(22, 70, 140, 16);
		contentPanel.add(lblNewLabel_3);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(22, 90, 242, 36);
		contentPanel.add(confirmPasswordField);
		
		JButton alterarButton = new JButton("Alterar");
		alterarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(passwordField.getPassword());
				String confirmPassword = new String(confirmPasswordField.getPassword());
				
				if (password.isEmpty()) {
					JOptionPane.showMessageDialog(AlterarSenhaScreen.this, "O campo 'Senha' é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					passwordField.requestFocus();
					return;
				}
				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(AlterarSenhaScreen.this, "As senhas não coincidem.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					passwordField.requestFocus();
					return;
				}
				
				controller.alterarSenha(password);
			}
		});
		alterarButton.setBounds(22, 145, 242, 30);
		contentPanel.add(alterarButton);
	}
}
