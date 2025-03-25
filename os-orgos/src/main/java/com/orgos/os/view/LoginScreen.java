package com.orgos.os.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 320);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	private void initComponent() {

		String sourceImg = LoginScreen.class.getResource("/images/").getPath();
		Image image = Toolkit.getDefaultToolkit().getImage(sourceImg + "logo_jframe_48x48.png");
		setIconImage(image);

		KeyStroke keyEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(e -> dispose(), keyEscape, JComponent.WHEN_IN_FOCUSED_WINDOW);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(20, 20, 320, 30);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Usuário");
		lblNewLabel_1.setBounds(20, 70, 52, 16);
		contentPane.add(lblNewLabel_1);

		usernameField = new JTextField();
		usernameField.setBounds(20, 90, 344, 36);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Senha");
		lblNewLabel_2.setBounds(20, 146, 52, 16);
		contentPane.add(lblNewLabel_2);

		passwordField = new JPasswordField();
		passwordField.setBounds(20, 166, 344, 36);
		contentPane.add(passwordField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		loginButton.setBounds(20, 222, 344, 36);
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
	public void exibirMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(
				this, 
				mensagem, 
				"Erro", 
				JOptionPane.ERROR_MESSAGE);
	}

}
