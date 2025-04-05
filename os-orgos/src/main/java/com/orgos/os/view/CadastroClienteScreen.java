package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.orgos.os.model.Cliente;
import com.orgos.os.model.Cliente.TableModelCliente;
import com.orgos.os.util.Consulta;
import com.orgos.os.util.ImageUtil;
import com.orgos.os.util.StringValidator;

public class CadastroClienteScreen extends AbstractModalScreen {
	private static final long serialVersionUID = 1L;
	private MaskFormatter maskCPF, maskCNPJ, maskFixo, maskCelular;
	private JFormattedTextField txtCpfcnpj, txtTelefone;
	private JRadioButton rbCPF, rbCNPJ, rbFixo, rbCelular;
	private JTextField txtNome, txtEmail, txtEndereco;
	private JButton btnNovo, btnAlterar, btnExcluir, btnSalvar, btnCancelar;

	private TableModelCliente tableModelCliente = new Cliente.TableModelCliente();
	private JTable tabelaCliente;
	private Cliente clienteSelecionado;
	private JComboBox<Consulta> cbConsulta;
	private DefaultComboBoxModel<Consulta> listModelConsulta;
	private JTextField txtConsulta;

	public CadastroClienteScreen(JFrame owner) {
		super(owner, true);
		setSize(600, 500);
		setTitle("Sistema de Ordem de Serviço - Cadastro de Clientes");
		setLocationRelativeTo(owner);

		iniciarComponente();
	}

	private void iniciarComponente() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		{
			JLabel title = new JLabel("Cadastro de Clientes");
			title.setFont(new Font("Arial", Font.BOLD, 16));
			panel.add(title, BorderLayout.NORTH);

			JPanel section = new JPanel();
			section.setLayout(new BoxLayout(section, BoxLayout.Y_AXIS));
			panel.add(section, BorderLayout.CENTER);
			{
				JPanel section_1 = new JPanel(new GridBagLayout());
				section.add(section_1);
				{
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.fill = GridBagConstraints.HORIZONTAL;
					gbc.insets = new Insets(5, 5, 0, 5);

					// linha 1, coluna 1
					gbc.gridy = 0;
					gbc.gridx = 0;
					section_1.add(new JLabel("Nome: *"), gbc);

					// coluna 2
					gbc.gridx = 1;
					gbc.weightx = 1.0; // add peso em x
					txtNome = new JTextField();
					txtNome.setEnabled(false);
					section_1.add(txtNome, gbc);

					// coluna 3
					gbc.gridx = 2;
					gbc.weightx = 0; // remover em x
					JPanel rbCpfCnpjPanel = new JPanel();
					rbCpfCnpjPanel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
					section_1.add(rbCpfCnpjPanel, gbc);
					{
						try {
							maskCPF = new MaskFormatter("###.###.###-##");
							maskCNPJ = new MaskFormatter("##.###.###/####-##");
						} catch (ParseException e) {
							e.printStackTrace();
						}

						txtCpfcnpj = new JFormattedTextField(maskCPF);
						txtCpfcnpj.setEnabled(false);

						rbCPF = new JRadioButton("CPF", true);
						rbCPF.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
						rbCPF.setEnabled(false);

						rbCNPJ = new JRadioButton("CNPJ");
						rbCNPJ.setEnabled(false);
						rbCNPJ.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

						ButtonGroup btngCpfCnpj = new ButtonGroup();
						btngCpfCnpj.add(rbCPF);
						btngCpfCnpj.add(rbCNPJ);

						rbCpfCnpjPanel.add(rbCPF);
						rbCpfCnpjPanel.add(rbCNPJ);

						rbCPF.addItemListener(new ItemListener() {
							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									String text = txtCpfcnpj.getText().replaceAll("\\D", "");
									txtCpfcnpj.setValue(null);
									txtCpfcnpj.setFormatterFactory(new DefaultFormatterFactory(maskCPF));
									txtCpfcnpj.setText(text);
								}
							}
						});

						rbCNPJ.addItemListener(new ItemListener() {
							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									String text = txtCpfcnpj.getText().replaceAll("\\D", "");
									txtCpfcnpj.setValue(null);
									txtCpfcnpj.setFormatterFactory(new DefaultFormatterFactory(maskCNPJ));
									txtCpfcnpj.setText(text);
								}
							}
						});
					}

					// coluna 4
					gbc.gridx = 3;
					gbc.weightx = 1.0; // add peso em x
					section_1.add(txtCpfcnpj, gbc);

					// linha 2, coluna 1
					gbc.gridy = 1;
					gbc.gridx = 0;
					gbc.weightx = 0; // remove peso em x
					section_1.add(new JLabel("Email:"), gbc);

					// coluna 2
					gbc.gridx = 1;
					gbc.weightx = 1.0; // add peso em x
					txtEmail = new JTextField();
					txtEmail.setEnabled(false);
					section_1.add(txtEmail, gbc);

					// coluna 3
					gbc.gridx = 2;
					gbc.weightx = 0; // remove peso em x
					JPanel rbTelefonePanel = new JPanel();
					rbTelefonePanel.setBorder(BorderFactory.createEmptyBorder(-5, -5, -5, -5));
					section_1.add(rbTelefonePanel, gbc);
					{
						try {
							maskFixo = new MaskFormatter("(##) ####-####");
							maskCelular = new MaskFormatter("(##) #####-####");
						} catch (ParseException e) {
							e.printStackTrace();
						}

						txtTelefone = new JFormattedTextField(maskFixo);
						txtTelefone.setEnabled(false);

						rbFixo = new JRadioButton("Fixo", true);
						rbFixo.setEnabled(false);
						rbFixo.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

						rbCelular = new JRadioButton("Celular");
						rbCelular.setEnabled(false);
						rbCelular.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

						ButtonGroup btngTelefone = new ButtonGroup();
						btngTelefone.add(rbFixo);
						btngTelefone.add(rbCelular);

						rbTelefonePanel.add(rbFixo);
						rbTelefonePanel.add(rbCelular);

						rbFixo.addItemListener(new ItemListener() {
							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									String text = txtTelefone.getText().replaceAll("\\D", "");
									txtTelefone.setValue(null);
									txtTelefone.setFormatterFactory(new DefaultFormatterFactory(maskFixo));
									txtTelefone.setText(text);
								}
							}
						});

						rbCelular.addItemListener(new ItemListener() {
							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									String text = txtTelefone.getText().replaceAll("\\D", "");
									txtTelefone.setValue(null);
									txtTelefone.setFormatterFactory(new DefaultFormatterFactory(maskCelular));
									txtTelefone.setText(text);
								}
							}
						});
					}

					// coluna 4
					gbc.gridx = 3;
					gbc.weightx = 1.0; // add peso em x
					section_1.add(txtTelefone, gbc);

					// linha 3, coluna 1
					gbc.gridy = 2;
					gbc.gridx = 0;
					gbc.weightx = 0; // remove peso em x
					section_1.add(new JLabel("Endereço:"), gbc);

					// coluna 2
					gbc.gridx = 1;
					gbc.weightx = 1.0; // add peso em x
					gbc.gridwidth = 3;
					txtEndereco = new JTextField();
					txtEndereco.setEnabled(false);
					section_1.add(txtEndereco, gbc);

					// linha 4, coluna 1
					gbc.gridy = 3;
					gbc.gridx = 0;
					gbc.gridwidth = 4;
					JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
					section_1.add(buttonPanel, gbc);
					{
						JPanel leftButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
						leftButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0));
						buttonPanel.add(leftButtonPanel);
						{
							btnNovo = new JButton("Novo");
							btnNovo.setMnemonic(KeyEvent.VK_N);
							btnNovo.setIcon(ImageUtil.getImageIcon("new_icon_48x48.png", 16, 16));
							leftButtonPanel.add(btnNovo);

							btnAlterar = new JButton("Alterar");
							btnAlterar.setMnemonic(KeyEvent.VK_A);
							btnAlterar.setIcon(ImageUtil.getImageIcon("edit_icon_48x48.png", 16, 16));
							leftButtonPanel.add(btnAlterar);

							btnExcluir = new JButton("Excluir");
							btnExcluir.setMnemonic(KeyEvent.VK_E);
							btnExcluir.setIcon(ImageUtil.getImageIcon("remove_icon_48x48.png", 16, 16));
							leftButtonPanel.add(btnExcluir);
						}
						
						JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						rightButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -5));
						buttonPanel.add(rightButtonPanel);
						{
							btnSalvar = new JButton("Salvar");
							btnSalvar.setMnemonic(KeyEvent.VK_S);
							btnSalvar.setIcon(ImageUtil.getImageIcon("check_icon_48x48.png", 16, 16));
							btnSalvar.setEnabled(false);
							rightButtonPanel.add(btnSalvar);

							btnCancelar = new JButton("Cancelar");
							btnCancelar.setMnemonic(KeyEvent.VK_C);
							btnCancelar.setIcon(ImageUtil.getImageIcon("cancel_icon_48x48.png", 16, 16));
							btnCancelar.setEnabled(false);
							btnCancelar.addActionListener(e -> resetarTela());
							rightButtonPanel.add(btnCancelar);
						}
					}
				}

				JPanel section_2 = new JPanel(new BorderLayout(5, 5));
				section_2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				section.add(section_2);
				{
					tabelaCliente = new JTable(tableModelCliente);
					tabelaCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tabelaCliente.getSelectionModel().addListSelectionListener(e -> {
						int selectedRow = tabelaCliente.getSelectedRow();
						if (selectedRow >= 0) {
							setClienteSelecionado(tableModelCliente.getClientes().get(selectedRow));
							exibirClienteSelecionado();
						}
					});

					JScrollPane scrollPane = new JScrollPane(tabelaCliente);
					section_2.add(scrollPane, BorderLayout.CENTER);
					
					JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
					footer.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, -5));
					section_2.add(footer, BorderLayout.SOUTH);
					{
						cbConsulta = new JComboBox<Consulta>();
						footer.add(cbConsulta);

						txtConsulta = new JTextField(15);
						footer.add(txtConsulta);
					}

				}
			} // end section
		} // end panel

	}

	// Actions
	public void addNovoListener(ActionListener listener) {
		btnNovo.addActionListener(listener);
	}

	public void addAlterarListener(ActionListener listener) {
		btnAlterar.addActionListener(listener);
	}

	public void addExcluirListener(ActionListener listener) {
		btnExcluir.addActionListener(listener);
	}

	public void addSalvarListener(ActionListener listener) {
		btnSalvar.addActionListener(listener);
	}

	public void addConsultaKeyListener(KeyAdapter adepter) {
		txtConsulta.addKeyListener(adepter);
	}

	// Show
	public void exibirConsultas(Consulta[] listConsulta) {
		listModelConsulta = new DefaultComboBoxModel<Consulta>(listConsulta);
		cbConsulta.setModel(listModelConsulta);
		cbConsulta.setSelectedIndex(1);
	}
	
	public void exibirClientes(List<Cliente> listarTodos) {
		tableModelCliente = new Cliente.TableModelCliente(listarTodos);
		tabelaCliente.setModel(tableModelCliente);
	}

	public void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}
	
	public void exibirMensagemAviso(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Aviso", 
				JOptionPane.WARNING_MESSAGE);		
	}
	
	public void exibirMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Erro", 
				JOptionPane.ERROR_MESSAGE);		
	}

	public boolean exibirMensagemConfirmacao(String mensagem) {
		return JOptionPane.showConfirmDialog(this, mensagem, "Confimar Exclusão", 
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION;

	}

	private void exibirClienteSelecionado() {
		if (Objects.nonNull(clienteSelecionado)) {
			txtNome.setText(clienteSelecionado.getNome());
			
			String cpfCnpj = clienteSelecionado.getCpfCnpj();
			if (Objects.isNull(cpfCnpj) || cpfCnpj.replaceAll("\\D", "").length() < 14) {
				rbCPF.setSelected(true);
			} else {
				rbCNPJ.setSelected(true);
			}
			
			txtCpfcnpj.setText(clienteSelecionado.getCpfCnpj());
			txtEmail.setText(clienteSelecionado.getEmail());
			
			String telefone = clienteSelecionado.getTelefone();
			if (Objects.isNull(telefone) || telefone.replaceAll("\\D", "").length() < 11) {
				rbFixo.setSelected(true);
			} else {
				rbCelular.setSelected(true);
			}
			
			txtTelefone.setText(clienteSelecionado.getTelefone());
			txtEndereco.setText(clienteSelecionado.getEndereco());
		}
	}

	// Modify
	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}
	
	public String getNome() {
		return txtNome.getText();
	}
	
	public String getCpfcnpj() {
		return txtCpfcnpj.getText();
	}
	
	public String getEmail() {
		return txtEmail.getText();
	}
	
	public String getTelefone() {
		return txtTelefone.getText();
	}
	
	public String getEndereco() {
		return txtEndereco.getText();
	}
	
	public Consulta getConsulta() {
		return listModelConsulta.getElementAt(cbConsulta.getSelectedIndex());
	}
	
	public String getConsultaTexto() {
		return txtConsulta.getText();
	}

	public void liberarParaInserir() {
		limparCampos();
		bloquearTela(false);
		txtNome.requestFocus();
	}

	public void liberarParaAlterar() {
		bloquearTela(false);
		txtNome.requestFocus();
	}
	
	public void resetarTela() {
		limparCampos();
		bloquearTela(true);
		tabelaCliente.clearSelection();
		setClienteSelecionado(null);
	}

	private void limparCampos() {
		txtNome.setText("");
		rbCPF.setSelected(true);
		txtCpfcnpj.setText("");
		txtEmail.setText("");
		rbFixo.setSelected(true);
		txtTelefone.setText("");
		txtEndereco.setText("");
	}

	private void bloquearTela(boolean b) {
		txtNome.setEnabled(!b);
		rbCPF.setEnabled(!b);
		rbCNPJ.setEnabled(!b);
		txtCpfcnpj.setEnabled(!b);
		txtEmail.setEnabled(!b);
		rbFixo.setEnabled(!b);
		rbCelular.setEnabled(!b);
		txtTelefone.setEnabled(!b);
		txtEndereco.setEnabled(!b);

		btnNovo.setEnabled(b);
		btnAlterar.setEnabled(b);
		btnExcluir.setEnabled(b);
		tabelaCliente.setEnabled(b);
		
		btnSalvar.setEnabled(!b);
		btnCancelar.setEnabled(!b);
	}
	
	public boolean validarDados() {
		if (StringValidator.isEmpty(txtNome.getText())) {
			exibirMensagemAviso("O nome do Cliente não pode ser vazio.");
			txtNome.requestFocus();
			return false;
		}
		return true;
	}

	

}