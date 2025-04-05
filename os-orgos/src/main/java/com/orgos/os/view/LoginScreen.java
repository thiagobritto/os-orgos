package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import com.orgos.os.util.Images;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JButton btnLogin;
	private JButton btnCancelar;
	
	public LoginScreen() {
		setTitle("Sistema de Ordem de Serviço - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 200);
		setLocationRelativeTo(null);
		setResizable(false);
		
		Image image = Images.getImage("logo_icon_48x48.png");
		setIconImage(image);
		
		KeyStroke keyEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(e -> dispose(), keyEscape, JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		JPanel panel = new JPanel(new BorderLayout(10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		setContentPane(panel);
		{
			JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			panel.add(headerPanel, BorderLayout.NORTH);
			{
				JLabel title = new JLabel("Acesso ao Sistema");
				title.setFont(new Font("Arial", Font.BOLD, 16));
				headerPanel.add(title);
			} // emd headerPanel
			
			JPanel sectionPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gbc = new GridBagConstraints();
			panel.add(sectionPanel, BorderLayout.CENTER);
			{
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.insets = new Insets(5, 5, 5, 5);

				gbc.gridx = 0;
				gbc.gridy = 0;				
				JLabel lblUsuario = new JLabel("Usuário:");
				sectionPanel.add(lblUsuario, gbc);

				gbc.gridx = 1;
				gbc.weightx = 1.0;
				txtUsuario = new JTextField(15);
				sectionPanel.add(txtUsuario, gbc);

				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.weightx = 0;
				JLabel lblSenha = new JLabel("Senha:");
				sectionPanel.add(lblSenha, gbc);
				
				gbc.gridx = 1;
				gbc.weightx = 1.0;
				txtSenha = new JPasswordField(15);				
				sectionPanel.add(txtSenha, gbc);
				
			} // end sectionPanel
			
			JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			panel.add(footerPanel, BorderLayout.SOUTH);
			{
				btnLogin = new JButton("Login");
				footerPanel.add(btnLogin);
				
				btnCancelar = new JButton("Cancelar");
				footerPanel.add(btnCancelar);
			}	// end footerPanel
			
		} // end panel
	}
		

	public String getUsuario() {
		return txtUsuario.getText();
	}
	
	public String getSenha() {
		return new String(txtSenha.getPassword());
	}
	
	public void addCancelarListener(ActionListener listener) {
		btnCancelar.addActionListener(listener);
	}
	
	public void addLoginListener(ActionListener listener) {
		btnLogin.addActionListener(listener);
	}

	public void exibirMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}
	
	
	
}
