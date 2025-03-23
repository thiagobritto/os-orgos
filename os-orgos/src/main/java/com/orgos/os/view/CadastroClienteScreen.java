package com.orgos.os.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.CadastroClienteController;
import com.orgos.os.model.Cliente;
import com.orgos.os.util.FieldUtil;

public class CadastroClienteScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField nomeField;
	private JTextField emailField;

	private CadastroClienteController controller;
	private Cliente clienteSelecionado;
	private JFormattedTextField cpfField;
	private JFormattedTextField cnpjField;
	private JFormattedTextField telefoneField;
	private JFormattedTextField celularField;
	private JTextArea enderecoField;
	private JRadioButton cpfRadioButton;
	private JRadioButton cnpjRadioButton;
	private JRadioButton telefoneRadioButton;
	private JRadioButton celularRadioButton;
	private JButton salvarButton;
	private JLabel tituloLabel;
	private JButton excluirButton;

	/**
	 * Create the dialog.
	 */
	public CadastroClienteScreen(JFrame owner, CadastroClienteController controller) {
		super(owner, true);
		this.controller = controller;
		
		KeyStroke keyEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(this::close, keyEscape, JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close(null);
			}
		});
		
		this.initComponent();
	}

	public void initComponent() {
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(800, 540);
		setLocationRelativeTo(null);

		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);

		JLabel nomeLabel = new JLabel("Nome *");
		nomeLabel.setBounds(20, 70, 73, 16);
		contentPanel.add(nomeLabel);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(20, 143, 73, 16);
		contentPanel.add(emailLabel);

		JLabel enderecoLabel = new JLabel("Endereço *");
		enderecoLabel.setBounds(20, 210, 73, 16);
		contentPanel.add(enderecoLabel);

		salvarButton = new JButton("Salvar");
		salvarButton.addActionListener(e -> salvar());
		salvarButton.setMnemonic(KeyEvent.VK_S);
		salvarButton.setBounds(20, 332, 100, 36);
		contentPanel.add(salvarButton);

		tituloLabel = new JLabel("Cadastro de Clientes");
		tituloLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		tituloLabel.setBounds(20, 0, 350, 60);
		contentPanel.add(tituloLabel);

		JLabel lembreteLabel = new JLabel("Campos obrigatórios (*)");
		lembreteLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		lembreteLabel.setBounds(10, 474, 513, 16);
		contentPanel.add(lembreteLabel);

		cpfField = new JFormattedTextField();
		cpfField.setBounds(430, 90, 233, 36);
		cpfField.setColumns(18);
		contentPanel.add(cpfField);
		FieldUtil.applyMask(cpfField, "###.###.###-##");

		cnpjField = new JFormattedTextField();
		cnpjField.setBounds(430, 90, 233, 36);
		cnpjField.setColumns(18);
		cnpjField.setVisible(false);
		contentPanel.add(cnpjField);
		FieldUtil.applyMask(cnpjField, "##.###.###/####-##");

		cpfRadioButton = new JRadioButton("CPF", true);
		cpfRadioButton.setBounds(430, 67, 45, 23);
		contentPanel.add(cpfRadioButton);

		cnpjRadioButton = new JRadioButton("CNPJ");
		cnpjRadioButton.setBounds(478, 67, 64, 23);
		contentPanel.add(cnpjRadioButton);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(cpfRadioButton);
		buttonGroup.add(cnpjRadioButton);

		// Adiciona ação para alternar entre CPF e CNPJ
		ActionListener actionListenerCpfCnpj = e -> {
			if (cpfRadioButton.isSelected()) {
				String text = cnpjField.getText().replaceAll("\\D", ""); // Remove não numéricos
				cnpjField.setText("");
				cpfField.setText(text);
				cnpjField.setVisible(false);
				cpfField.setVisible(true);
				cpfField.requestFocus();
			} else {
				String text = cpfField.getText().replaceAll("\\D", ""); // Remove não numéricos
				cpfField.setText("");
				cnpjField.setText(text);
				cnpjField.setVisible(true);
				cpfField.setVisible(false);
				cnpjField.requestFocus();
			}
		};

		cpfRadioButton.addActionListener(actionListenerCpfCnpj);
		cnpjRadioButton.addActionListener(actionListenerCpfCnpj);

		nomeField = new JTextField();
		nomeField.setBounds(20, 90, 390, 36);
		contentPanel.add(nomeField);
		nomeField.setColumns(10);

		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(20, 163, 300, 36);
		contentPanel.add(emailField);

		JLabel telefoneLabel = new JLabel("Telefone *");
		telefoneLabel.setBounds(340, 143, 64, 16);
		contentPanel.add(telefoneLabel);

		telefoneField = new JFormattedTextField();
		telefoneField.setBounds(340, 163, 202, 36);
		telefoneField.setColumns(15);
		contentPanel.add(telefoneField);
		FieldUtil.applyMask(telefoneField, "(##) ####-####");

		celularField = new JFormattedTextField();
		celularField.setBounds(340, 163, 153, 36);
		celularField.setColumns(15);
		celularField.setVisible(false);
		contentPanel.add(celularField);
		FieldUtil.applyMask(celularField, "(##) #####-####");

		telefoneRadioButton = new JRadioButton("Fixo", true);
		telefoneRadioButton.setBounds(402, 143, 52, 16);
		contentPanel.add(telefoneRadioButton);

		celularRadioButton = new JRadioButton("Celular");
		celularRadioButton.setBounds(456, 143, 67, 16);
		contentPanel.add(celularRadioButton);

		ButtonGroup foneButtonGroup = new ButtonGroup();
		foneButtonGroup.add(telefoneRadioButton);
		foneButtonGroup.add(celularRadioButton);

		ActionListener actionListenerFone = e -> {
			if (telefoneRadioButton.isSelected()) {
				String text = celularField.getText().replaceAll("\\D", ""); // Remove não numéricos
				celularField.setText("");
				telefoneField.setText(text);
				telefoneField.setVisible(true);
				celularField.setVisible(false);
				telefoneField.requestFocus();
			} else {
				String text = telefoneField.getText().replaceAll("\\D", ""); // Remove não numéricos
				telefoneField.setText("");
				celularField.setText(text);
				telefoneField.setVisible(false);
				celularField.setVisible(true);
				celularField.requestFocus();
			}
		};

		telefoneRadioButton.addActionListener(actionListenerFone);
		celularRadioButton.addActionListener(actionListenerFone);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 230, 390, 73);
		contentPanel.add(scrollPane);

		enderecoField = new JTextArea();
		enderecoField.setLineWrap(true);
		enderecoField.setRows(4);
		scrollPane.setViewportView(enderecoField);
		
		excluirButton = new JButton("Excluir");
		excluirButton.addActionListener(e -> excluir());
		
		excluirButton.setBounds(138, 332, 100, 36);
		excluirButton.setVisible(false);
		contentPanel.add(excluirButton);

	}

	private void salvar() {
		int id = (clienteSelecionado == null || clienteSelecionado.getId() < 1) ? 0 : clienteSelecionado.getId();
		String nome = nomeField.getText();
		String cpfCnpj = cpfField.isVisible() ? cpfField.getText() : cnpjField.getText();
		String email = emailField.getText();
		String telefone = telefoneField.isVisible() ? telefoneField.getText() : celularField.getText();
		String endereco = enderecoField.getText();

		Cliente cliente = new Cliente(id, nome, cpfCnpj, telefone, email, endereco);
		controller.seveCliente(cliente);
	}
	
	private void excluir() {
		int confirm = JOptionPane.showConfirmDialog(this, 
				"Tem certeza que deseja apagar esse registro? \nNome: " + clienteSelecionado.getNome(), 
				"Confirmar exclusão", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE);
		if (confirm == JOptionPane.YES_OPTION) {
			controller.deleteCliente(clienteSelecionado);
		}
	}

	@Override
	public void setVisible(boolean b) {
		SwingUtilities.invokeLater(() -> nomeField.requestFocus());
		super.setVisible(b);
	}
	
	
	public void close(ActionEvent event) {
		limparCampos();
		dispose();
	}

	public void setController(CadastroClienteController controller) {
		this.controller = controller;
	}

	public void setCliente(Cliente cliente) {
		clienteSelecionado = cliente;

		setTitle("Editar Cliente");
		tituloLabel.setText("Editar Cliente");

		cpfField.setVisible(false);
		cnpjField.setVisible(false);
		telefoneField.setVisible(false);
		celularField.setVisible(false);

		nomeField.setText(cliente.getNome());
		String cpfCnpj = cliente.getCpfCnpj();
		if (cpfCnpj.length() > 14) {
			cnpjRadioButton.setSelected(true);
			cnpjField.setVisible(true);
			cnpjField.setText(cpfCnpj);
		} else {
			cpfRadioButton.setSelected(true);
			cpfField.setVisible(true);
			cpfField.setText(cpfCnpj);
		}
		emailField.setText(cliente.getEmail());
		String telefone = cliente.getTelefone();
		if (telefone.length() > 14) {
			celularRadioButton.setSelected(true);
			celularField.setVisible(true);
			celularField.setText(telefone);
		} else {
			telefoneRadioButton.setSelected(true);
			telefoneField.setVisible(true);
			telefoneField.setText(telefone);
		}
		enderecoField.setText(cliente.getEndereco());
		excluirButton.setVisible(true);
	}

	public void limparCampos() {
		clienteSelecionado = null;

		setTitle("Cadastro de Clientes");
		tituloLabel.setText("Cadastro de Clientes");

		cpfRadioButton.setSelected(true);
		telefoneRadioButton.setSelected(true);

		cpfField.setVisible(true);
		cnpjField.setVisible(false);
		telefoneField.setVisible(true);
		celularField.setVisible(false);

		nomeField.setText("");
		cpfField.setText("");
		cnpjField.setText("");
		emailField.setText("");
		telefoneField.setText("");
		celularField.setText("");
		enderecoField.setText("");
		
		excluirButton.setVisible(false);
	}

	public void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}
}
