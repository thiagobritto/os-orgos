package com.orgos.os.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.LoginController;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private LoginController controller;

	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(440, 55, 344, 30);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 440, 472);
		contentPane.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setBounds(453, 108, 52, 16);
		contentPane.add(lblNewLabel_1);
		
		usernameField = new JTextField();
		usernameField.setBounds(453, 126, 320, 36);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(453, 174, 52, 16);
		contentPane.add(lblNewLabel_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(453, 192, 320, 36);
		contentPane.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		controller = new LoginController(this);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				controller.autenticar(username, password);
			}
		});
		loginButton.setBounds(453, 253, 90, 36);
		contentPane.add(loginButton);
	}

	public void exibirMensagemErro(String message) {
		JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
