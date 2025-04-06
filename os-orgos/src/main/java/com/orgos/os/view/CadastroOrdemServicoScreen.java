package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.orgos.os.model.Cliente;
import com.orgos.os.model.ItemServico;
import com.orgos.os.model.ItemServico.TableModelItemServico;
import com.orgos.os.model.OrdemServico;
import com.orgos.os.model.Tecnico;
import com.orgos.os.util.FilterField;

public class CadastroOrdemServicoScreen extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtValorServico;
	private JTextField txtValorPecas;
	private JTextField txtValorTotal;
	private JTable tableItemServico;
	private TableModelItemServico tableModelItemServico;
	private JComboBox<Cliente> cbClientes;
	private DefaultComboBoxModel<Cliente> listModelCliente;
	private JComboBox<Tecnico> cbTecnicos;
	private DefaultComboBoxModel<Tecnico> listModelTecnico;
	private JButton btnBuscarCliente;
	private JButton btnBuscarTecnico;

	public CadastroOrdemServicoScreen() {
		super("OS", false, true, true, true);
		setSize(800, 540);

		iniciarComponent();
	}

	private void iniciarComponent() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		{
			JPanel headerPanel = new JPanel(new GridLayout(0, 4, 0, 0));
			panel.add(headerPanel, BorderLayout.NORTH);
			{
				JPanel columnPanel_1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				headerPanel.add(columnPanel_1);
				{
					JLabel title = new JLabel("Nº OS:");
					columnPanel_1.add(title);

					JTextField txtNumeroOS = new JTextField(10);
					txtNumeroOS.setEditable(false);
					txtNumeroOS.setText("OS-20250330-000001");
					columnPanel_1.add(txtNumeroOS);
				}
				JPanel columnPanel_2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				headerPanel.add(columnPanel_2);
				{
					JLabel title = new JLabel("Data:");
					columnPanel_2.add(title);

					JFormattedTextField txtDataOS = new JFormattedTextField();
					txtDataOS.setColumns(10);
					txtDataOS.setEditable(false);
					txtDataOS.setText("24/05/2025");
					columnPanel_2.add(txtDataOS);
				}
				JPanel columnPanel_3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				headerPanel.add(columnPanel_3);
				{
					JLabel title = new JLabel("Tipo:");
					columnPanel_3.add(title);

					JComboBox<OrdemServico.Type> cbTipoOS = new JComboBox<>(OrdemServico.Type.values());
					columnPanel_3.add(cbTipoOS);
				}
				JPanel columnPanel_4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				headerPanel.add(columnPanel_4);
				{
					JLabel title = new JLabel("Status:");
					columnPanel_4.add(title);

					JComboBox<OrdemServico.Status> cbStatusOS = new JComboBox<>(OrdemServico.Status.values());
					columnPanel_4.add(cbStatusOS);
				}
			} // END headerPanel

			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
			mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			panel.add(mainPanel, BorderLayout.CENTER);
			{
				JPanel sectionPanel_1 = new JPanel(new BorderLayout(5, 5));
				mainPanel.add(sectionPanel_1);
				{
					JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
					header.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0));
					sectionPanel_1.add(header, BorderLayout.NORTH);
					{
						// CLIENTES
						JLabel lblCliente = new JLabel("Cliente:");
						header.add(lblCliente);

						cbClientes = new JComboBox<Cliente>();
						header.add(cbClientes);

						btnBuscarCliente = new JButton("...");
						header.add(btnBuscarCliente);

						// TÉCNICOS
						JLabel lblTecnico = new JLabel("Técnico:");
						header.add(lblTecnico);

						cbTecnicos = new JComboBox<Tecnico>();
						header.add(cbTecnicos);

						btnBuscarTecnico = new JButton("...");
						header.add(btnBuscarTecnico);
					}

					JPanel form = new JPanel();
					form.setLayout(new BoxLayout(form, BoxLayout.X_AXIS));
					sectionPanel_1.add(form, BorderLayout.CENTER);
					{
						JPanel formLeft = new JPanel(new GridBagLayout());
						GridBagConstraints gbc = new GridBagConstraints();
						form.add(formLeft);
						{
							gbc.fill = GridBagConstraints.HORIZONTAL;
							gbc.insets = new Insets(0, 0, 5, 5);

							gbc.gridy = 0;
							gbc.gridx = 0;
							JLabel lblEquipamento = new JLabel("Equipamento:");
							formLeft.add(lblEquipamento, gbc);

							gbc.gridy = 0;
							gbc.gridx = 1;
							gbc.weightx = 1.0;
							JTextField txtEquipamento = new JTextField();
							formLeft.add(txtEquipamento, gbc);

							gbc.gridy = 1;
							gbc.gridx = 0;
							gbc.weightx = 0;
							JLabel lblMarcaModelo = new JLabel("Marca/Modelo:");
							formLeft.add(lblMarcaModelo, gbc);

							gbc.gridy = 1;
							gbc.gridx = 1;
							gbc.weightx = 1.0;
							JTextField txtMarcaModelo = new JTextField();
							formLeft.add(txtMarcaModelo, gbc);

							gbc.gridy = 2;
							gbc.gridx = 0;
							gbc.weightx = 0;
							JLabel lblServico = new JLabel("Serviço:");
							formLeft.add(lblServico, gbc);

							gbc.gridy = 2;
							gbc.gridx = 1;
							gbc.weightx = 1.0;
							JTextField txtServico = new JTextField();
							formLeft.add(txtServico, gbc);

							gbc.gridy = 3;
							gbc.gridx = 0;
							gbc.gridwidth = 2;
							gbc.weightx = 1.0;
							JLabel lblDecricaoServico = new JLabel("Descrição de Serviço:");
							formLeft.add(lblDecricaoServico, gbc);

							gbc.gridy = 4;
							gbc.gridx = 0;
							gbc.gridwidth = 2;
							gbc.gridheight = 4;
							gbc.weighty = 1.0;
							gbc.weightx = 1.0;
							gbc.fill = GridBagConstraints.BOTH;
							JTextArea txtDecricaoServico = new JTextArea();
							txtDecricaoServico.setRows(3);
							JScrollPane scrollPane = new JScrollPane(txtDecricaoServico);
							formLeft.add(scrollPane, gbc);

						}

						JPanel formReight = new JPanel(new GridBagLayout());
						GridBagConstraints gbc2 = new GridBagConstraints();
						form.add(formReight);
						{
							gbc2.fill = GridBagConstraints.HORIZONTAL;
							gbc2.insets = new Insets(0, 5, 5, 0);

							gbc2.gridy = 0;
							gbc2.gridx = 0;
							JLabel lblValorServico = new JLabel("Valor do Serviço (R$):");
							formReight.add(lblValorServico, gbc2);

							gbc2.gridy = 0;
							gbc2.gridx = 1;
							gbc2.weightx = 1.0;
							txtValorServico = new JTextField();
							txtValorServico.setText("0,00");
							FilterField.decimal(txtValorServico);
							txtValorServico.getDocument().addDocumentListener(new ValorDocumentListener());
							formReight.add(txtValorServico, gbc2);

							gbc2.gridy = 1;
							gbc2.gridx = 0;
							gbc2.weightx = 0;
							JLabel lblValorPecas = new JLabel("Valor das Peças (R$):");
							formReight.add(lblValorPecas, gbc2);

							gbc2.gridy = 1;
							gbc2.gridx = 1;
							gbc2.weightx = 1.0;
							txtValorPecas = new JTextField();
							txtValorPecas.setText("0,00");
							txtValorPecas.setEditable(false);
							formReight.add(txtValorPecas, gbc2);

							gbc2.gridy = 2;
							gbc2.gridx = 0;
							gbc2.weightx = 0;
							JLabel lblValorTotal = new JLabel("Valor Total (R$):");
							formReight.add(lblValorTotal, gbc2);

							gbc2.gridy = 2;
							gbc2.gridx = 1;
							gbc2.weightx = 1.0;
							txtValorTotal = new JTextField();
							txtValorTotal.setText("0,00");
							txtValorTotal.setEditable(false);
							formReight.add(txtValorTotal, gbc2);

							gbc2.gridy = 3;
							gbc2.gridx = 0;
							gbc2.gridwidth = 2;
							gbc2.weightx = 1.0;
							JLabel lblDecricaoServico = new JLabel("Solução Aplicada:");
							formReight.add(lblDecricaoServico, gbc2);

							gbc2.gridy = 4;
							gbc2.gridx = 0;
							gbc2.gridwidth = 2;
							gbc2.gridheight = 4;
							gbc2.weighty = 1.0;
							gbc2.weightx = 1.0;
							gbc2.fill = GridBagConstraints.BOTH;
							JTextArea txtDecricaoServico = new JTextArea();
							txtDecricaoServico.setRows(3);
							JScrollPane scrollPane = new JScrollPane(txtDecricaoServico);
							formReight.add(scrollPane, gbc2);
						}
					}

				}

				JPanel sectionPanel_2 = new JPanel(new BorderLayout(0, 0));
				mainPanel.add(sectionPanel_2);
				{
					JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
					sectionPanel_2.add(header, BorderLayout.NORTH);
					{
						JLabel lblPecas = new JLabel("Peças Utilizadas");
						header.add(lblPecas);
					}

					tableModelItemServico = new ItemServico.TableModelItemServico();
					tableItemServico = new JTable(tableModelItemServico);
					JScrollPane scrollPane = new JScrollPane(tableItemServico);
					sectionPanel_2.add(scrollPane);

					JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
					footer.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0));
					sectionPanel_2.add(footer, BorderLayout.SOUTH);
					{
						JButton btnAdicionarPeca = new JButton("Adicionar Peça");
						btnAdicionarPeca.addActionListener(e -> adicionarPeca());
						footer.add(btnAdicionarPeca);

						JButton btnRemoverPeca = new JButton("Remover Peça");
						btnRemoverPeca.addActionListener(e -> removerPeca());
						footer.add(btnRemoverPeca);
					}
				}
			} // END mainPanel

			JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
			footerPanel.setBorder(BorderFactory.createEmptyBorder(0, -5, 5, -5));
			panel.add(footerPanel, BorderLayout.SOUTH);
			{
				JButton btnImprimir = new JButton("Imprimir");
				footerPanel.add(btnImprimir);

				JButton btnCancelar = new JButton("Cancelar");
				footerPanel.add(btnCancelar);

				JButton btnSalvar = new JButton("Salvar");
				footerPanel.add(btnSalvar);
			} // END footerPanel
		} // END Panel
	}

	private void adicionarPeca() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

		JLabel lblDexcricao = new JLabel("Descrição:");
		panel.add(lblDexcricao);

		JTextField txtDescricao = new JTextField();
		panel.add(txtDescricao);

		JLabel lblQuantidade = new JLabel("Quantidade:");
		panel.add(lblQuantidade);

		JTextField txtQuantidade = new JTextField(10);
		txtQuantidade.setText("1");
		FilterField.decimal(txtQuantidade);
		panel.add(txtQuantidade);

		JLabel lblValor = new JLabel("Valor Unitario (R$):");
		panel.add(lblValor);

		JTextField txtValor = new JTextField(10);
		txtValor.setText("0,00");
		FilterField.decimal(txtValor);
		panel.add(txtValor);

		int confirm = JOptionPane.showConfirmDialog(this, panel, "Adicionar Peça", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (confirm == JOptionPane.OK_OPTION) {
			try {
				String descricao = txtDescricao.getText();
				double quantidade = Double.parseDouble(txtQuantidade.getText().replace(",", "."));
				double valor = Double.parseDouble(txtValor.getText().replace(",", "."));

				if (descricao.trim().isEmpty())
					throw new IllegalArgumentException("Descrição não pode ser vazia.");

				ItemServico pecas = new ItemServico(descricao, quantidade, valor);
				tableModelItemServico.add(pecas);
				atualizarValoresPecas();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Valores inválidos para quantidade ou valor unitário.", "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void removerPeca() {
		int selectedRow = tableItemServico.getSelectedRow();
		if (selectedRow >= 0) {
			tableModelItemServico.remove(selectedRow);
			atualizarValoresPecas();
		} else {
			JOptionPane.showMessageDialog(this, "Selecione uma peça para remover", "Atenção",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void atualizarValoresPecas() {
		txtValorPecas.setText(String.format("%.2f", getSomaPecas()));
		calcularTotal();
	}

	public double getSomaPecas() {
		return tableModelItemServico.getItensServico().stream()
				.mapToDouble(peca -> peca.getValor() * peca.getQuantidade()).sum();
	}

	private void calcularTotal() {
		try {
			double valorServico = 0;
			if (!txtValorServico.getText().trim().isEmpty())
				valorServico = Double.parseDouble(txtValorServico.getText().trim().replace(",", "."));

			double total = valorServico + getSomaPecas();
			txtValorTotal.setText(String.format("%.2f", total));
		} catch (NumberFormatException e) {
			txtValorTotal.setText(String.format("%.2f", getSomaPecas()));
		}
	}

	// Classe interna para monitorar alterações no campo de valor do serviço
	class ValorDocumentListener implements javax.swing.event.DocumentListener {
		@Override
		public void insertUpdate(javax.swing.event.DocumentEvent e) {
			calcularTotal();
		}

		@Override
		public void removeUpdate(javax.swing.event.DocumentEvent e) {
			calcularTotal();
		}

		@Override
		public void changedUpdate(javax.swing.event.DocumentEvent e) {
			calcularTotal();
		}
	}

	/*
	 * Controller Methods
	 */

	public void addBuscarClienteListener(ActionListener listene) {
		btnBuscarCliente.addActionListener(listene);
	}
	
	public void addBuscarTecnicoListener(ActionListener listene) {
		btnBuscarTecnico.addActionListener(listene);
	}


	public void exibirClientes(List<Cliente> listCliente) {
		listModelCliente = new DefaultComboBoxModel<Cliente>();
		listCliente.forEach(listModelCliente::addElement);
		cbClientes.setModel(listModelCliente);
	}

	public void exibirTecnicos(List<Tecnico> listTecnico) {
		listModelTecnico = new DefaultComboBoxModel<Tecnico>();
		listTecnico.forEach(listModelTecnico::addElement);
		cbTecnicos.setModel(listModelTecnico);
	}

	public <T> Busca<T> getBusca() {
		return new Busca<T>(this, "");
	}
	
	public class Busca<T> {

		private JTextField txtConsulta;
		private DefaultListModel<T> listModel;
		private JList<T> list;
		private JOptionPane optionPane;
		private JDialog dialog;

		public Busca(java.awt.Component parentComponent, String title) {
			JPanel panel = new JPanel(new BorderLayout(0, 0));
			txtConsulta = new JTextField();
			panel.add(txtConsulta, BorderLayout.NORTH);

			listModel = new DefaultListModel<>();
			list = new JList<>(listModel);
			panel.add(list, BorderLayout.CENTER);

			optionPane = new JOptionPane(
					panel, 
					JOptionPane.PLAIN_MESSAGE, 
					JOptionPane.DEFAULT_OPTION, 
					null,
					new Object[] {}, 
					null);
			
			
			
			dialog = optionPane.createDialog(parentComponent, title);
			dialog.setSize(300, 180);
		}	
		
		public String getTextConsulta() {
			return txtConsulta.getText();
		}
		
		public void addConsultaKeyListener(KeyAdapter adapter) {
			txtConsulta.addKeyListener(adapter);
		}
		
		public void addListMouseListener(MouseAdapter adapter) {
			list.addMouseListener(adapter);
		}
		
		public void exibirLista(List<T> list) {
			listModel.clear();
			list.forEach(listModel::addElement);
		}
		
		public T getSelectedValue() {
			return list.getSelectedValue();
		}
		
		public void setVisible(boolean vsible) {
			dialog.setVisible(vsible);
		}
		
		public void dispose() {
			dialog.dispose();
		}
	}

	
}
