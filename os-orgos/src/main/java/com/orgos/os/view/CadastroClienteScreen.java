package com.orgos.os.view;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class CadastroClienteScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Create the dialog.
	 */
	public CadastroClienteScreen(JFrame owner) {
		super(owner, true);
		this.initComponent();
	}

	public void initComponent() {
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(800, 540);
		setLocationRelativeTo(null);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 255));
		panel.setBounds(0, 0, 220, 501);
		contentPanel.add(panel);
		
		JLabel lblNewLabel = new JLabel("Nome *");
		lblNewLabel.setBounds(251, 90, 124, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CPF/CNPJ");
		lblNewLabel_1.setBounds(572, 90, 85, 16);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Telefone *");
		lblNewLabel_1_1.setBounds(572, 160, 143, 16);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setBounds(251, 160, 167, 16);
		contentPanel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Endereço *");
		lblNewLabel_1_3.setBounds(251, 228, 167, 16);
		contentPanel.add(lblNewLabel_1_3);
		
		textField = new JTextField();
		textField.setBounds(251, 110, 303, 36);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setMnemonic(KeyEvent.VK_C);
		btnNewButton.setBounds(251, 308, 100, 36);
		contentPanel.add(btnNewButton);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(572, 110, 182, 36);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(251, 180, 303, 36);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(251, 248, 503, 36);
		contentPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(572, 180, 182, 36);
		contentPanel.add(textField_4);
		
		JLabel lblCadastroDeClientes = new JLabel("Cadastro de Clientes");
		lblCadastroDeClientes.setFont(new Font("Dialog", Font.BOLD, 18));
		lblCadastroDeClientes.setBounds(251, 0, 350, 60);
		contentPanel.add(lblCadastroDeClientes);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Campos obrigatórios (*)");
		lblNewLabel_1_3_1.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblNewLabel_1_3_1.setBounds(251, 473, 513, 16);
		contentPanel.add(lblNewLabel_1_3_1);
	}
}
