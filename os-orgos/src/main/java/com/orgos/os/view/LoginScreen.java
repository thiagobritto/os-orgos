package com.orgos.os.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.LoginController;

public class LoginScreen extends JFrame implements LoginScreenInterface {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private LoginController controller;

	/**
	 * Create the frame.
	 */
	public LoginScreen(LoginController controller) {
		this.controller = controller;
		this.initComponent();
	}

	private void initComponent() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setResizable(false);

		getRootPane().registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(453, 55, 320, 30);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 440, 472);
		contentPane.add(panel);

		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setBounds(453, 110, 52, 16);
		contentPane.add(lblNewLabel_1);

		usernameField = new JTextField();
		usernameField.setBounds(453, 130, 320, 36);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(453, 170, 52, 16);
		contentPane.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(453, 190, 320, 36);
		contentPane.add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		loginButton.setBounds(453, 250, 90, 36);
		contentPane.add(loginButton);

	}

	public void setController(LoginController loginController) {
		this.controller = loginController;
	}
	
	@Override
	public void login() {
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());
		controller.autenticar(username, password);
	}

	@Override
	public void close() {
		dispose();
	}
	
	@Override
	public void exibirMensagemErro(String message) {
		JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	
}
