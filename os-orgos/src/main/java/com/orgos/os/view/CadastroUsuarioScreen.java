package com.orgos.os.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.CadastroUsuarioController;

public class CadastroUsuarioScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField usernameField;
	private JPasswordField confirmPasswordField;
	private CadastroUsuarioController controller;

	/**
	 * Create the dialog.
	 */
	public CadastroUsuarioScreen(JFrame owner) {
		super(owner, true);
		this.controller = new CadastroUsuarioController(this);
		this.initCoponent();
	}

	private void initCoponent() {
		setTitle("Cadastro de Usuário");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(800, 540);
		setLocationRelativeTo(null);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);

		JLabel lblNewLabel = new JLabel("CADASTRO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(453, 55, 320, 30);
		contentPanel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome de usuário");
		lblNewLabel_1.setBounds(453, 110, 150, 16);
		contentPanel.add(lblNewLabel_1);

		usernameField = new JTextField();
		usernameField.setBounds(453, 130, 320, 36);
		usernameField.setColumns(10);
		contentPanel.add(usernameField);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(453, 170, 150, 16);
		contentPanel.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(453, 190, 320, 36);
		contentPanel.add(passwordField);

		JLabel lblNewLabel_3 = new JLabel("Confirmação de senha");
		lblNewLabel_3.setBounds(453, 230, 150, 16);
		contentPanel.add(lblNewLabel_3);

		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(453, 250, 320, 36);
		contentPanel.add(confirmPasswordField);

		JButton loginButton = new JButton("Cadastrar");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String confirmPassword = new String(confirmPasswordField.getPassword());

				if (username.isEmpty()) {
					JOptionPane.showMessageDialog(CadastroUsuarioScreen.this,
							"O campo 'Nome de usuário' é obrigatório.", "Erro", JOptionPane.ERROR_MESSAGE);
					usernameField.requestFocus();
					return;
				}
				if (password.isEmpty()) {
					JOptionPane.showMessageDialog(CadastroUsuarioScreen.this, "O campo 'Senha' é obrigatório.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					passwordField.requestFocus();
					return;
				}
				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(CadastroUsuarioScreen.this, "As senhas não coincidem.", "Erro",
							JOptionPane.ERROR_MESSAGE);
					passwordField.requestFocus();
					return;
				}

				controller.cadastrarUsuario(username, password);
			}
		});
		loginButton.setBounds(453, 310, 90, 36);
		contentPanel.add(loginButton);
	}

	public void mostrarMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}

}
