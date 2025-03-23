package com.orgos.os.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.BuscaClienteController;
import com.orgos.os.model.Cliente;
import com.orgos.os.model.ClienteTableModel;
import com.orgos.os.model.PesquisaCliente;

public class BuscaClienteScreen extends JDialogScreen {
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<PesquisaCliente> pesquisaComboBox;
	private JTextField nomeField;
	private JScrollPane scrollPane;
	private JTable table;

	private ClienteTableModel clienteTableModel = new ClienteTableModel();
	private Cliente clienteSelecionado;
	private JLabel tipLabel;
	private JLabel tipLabel_1;

	private BuscaClienteController controller;

	public BuscaClienteScreen(JFrame owner, BuscaClienteController controller) {
		super(owner, true);
		this.controller = controller;

		setTitle("Busca de Clientes");
		setSize(800, 540);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(owner);

		KeyStroke keyEscape = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
		getRootPane().registerKeyboardAction(this::cancelar, keyEscape, JComponent.WHEN_IN_FOCUSED_WINDOW);

		this.iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);

		JLabel nomeLabel = new JLabel("Buscar por:");
		nomeLabel.setBounds(20, 20, 150, 14);
		contentPanel.add(nomeLabel);

		pesquisaComboBox = new JComboBox<>();
		pesquisaComboBox.setBounds(20, 40, 90, 36);
		contentPanel.add(pesquisaComboBox);

		nomeField = new JTextField(10);
		nomeField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisar();
			}
		});
		nomeField.setBounds(120, 40, 260, 36);
		contentPanel.add(nomeField);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 90, 744, 300);
		contentPanel.add(scrollPane);

		table = new JTable(clienteTableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow >= 0) {
						clienteSelecionado = clienteTableModel.getCliente(selectedRow);
						dispose();
					}
				}
			}
		});
		scrollPane.setViewportView(table);

		tipLabel = new JLabel("Use o \"Duplo Click\" para selecionar");
		tipLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		tipLabel.setBounds(20, 456, 373, 14);
		contentPanel.add(tipLabel);

		tipLabel_1 = new JLabel("Pressione \"ESC\" para cancelar");
		tipLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 11));
		tipLabel_1.setBounds(20, 470, 373, 14);
		contentPanel.add(tipLabel_1);

	}

	private void cancelar(ActionEvent event) {
		clienteSelecionado = null;
		dispose();
	}

	private void pesquisar() {
		PesquisaCliente pesquisa = (PesquisaCliente) pesquisaComboBox.getSelectedItem();
		exibirClientes(pesquisa.buscar(nomeField.getText()));
	}

	public void exibirClientes(List<Cliente> cluentes) {
		clienteTableModel = new ClienteTableModel(cluentes);
		table.setModel(clienteTableModel);
	}

	public void setPesquisa(PesquisaCliente[] pesquisasCliente) {
		pesquisaComboBox.setModel(new DefaultComboBoxModel<>(pesquisasCliente));
		pesquisaComboBox.setSelectedIndex(1);
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setController(BuscaClienteController controller) {
		this.controller = controller;
	}

	@Override
	public void setVisible(boolean b) {
		controller.carregarTela();
		super.setVisible(b);
	}

}