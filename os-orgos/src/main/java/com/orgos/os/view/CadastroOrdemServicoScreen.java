package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CadastroOrdemServicoScreen extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	public CadastroOrdemServicoScreen() {
		super("OS", false, true, true, true);
		setBounds(190, 0, 900, 660);
		
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

					String[] tipos = { "Ordem de Serviço", "Orçamento" };

					JComboBox<String> cbTipoOS = new JComboBox<>(tipos);
					columnPanel_3.add(cbTipoOS);
				}
				JPanel columnPanel_4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
				headerPanel.add(columnPanel_4);
				{
					JLabel title = new JLabel("Status:");
					columnPanel_4.add(title);

					String[] status = { "Aberto", "Em andamento", "Aguardando peças", "Concluído", "Cancelado" };

					JComboBox<String> cbStatusOS = new JComboBox<>(status);
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

						String[] clientes = { "Antônio", "Jorge", "Vitor", "Manoel da Mercedes" };

						JComboBox<String> cbClientes = new JComboBox<>(clientes);
						header.add(cbClientes);

						JButton btnBuscarCliente = new JButton("...");
						header.add(btnBuscarCliente);

						// TÉCNICOS
						JLabel lblTecnico = new JLabel("Técnico:");
						header.add(lblTecnico);

						String[] tecnicos = { "Joares", "Antônio", "Jorge", "Vitor", "Manoel da Mercedes" };

						JComboBox<String> cbTecnicos = new JComboBox<>(tecnicos);
						header.add(cbTecnicos);

						JButton btnBuscarTecnico = new JButton("...");
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
							JTextField txtValorServico = new JTextField();
							formReight.add(txtValorServico, gbc2);
							
							gbc2.gridy = 1;
							gbc2.gridx = 0;
							gbc2.weightx = 0;
							JLabel lblValorPecas = new JLabel("Valor dos Peças (R$):");
							formReight.add(lblValorPecas, gbc2);
							
							gbc2.gridy = 1;
							gbc2.gridx = 1;
							gbc2.weightx = 1.0;
							JTextField txtValorPecas = new JTextField();
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
							JTextField txtValorTotal = new JTextField();
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

					String[] columns = { "Descrição", "Quantidade", "Valor Unitario", "Valor Total" };
					JTable table = new JTable(new DefaultTableModel(columns, 5));

					JScrollPane scrollPane = new JScrollPane(table);
					sectionPanel_2.add(scrollPane);

					JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
					footer.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0));
					sectionPanel_2.add(footer, BorderLayout.SOUTH);
					{
						JButton btnAdicionarPeca = new JButton("Adicionar Peça");
						footer.add(btnAdicionarPeca);

						JButton btnRemoverPeca = new JButton("Remover Peça");
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
		}// END Panel
	}

}
