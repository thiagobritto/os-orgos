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
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
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

import com.orgos.os.model.Tecnico;
import com.orgos.os.model.Tecnico.TableModelTecnico;
import com.orgos.os.util.Images;
import com.orgos.os.util.StringValidator;

public class CadastroTecnicoScreen extends AbstractModalScreen {
	private static final long serialVersionUID = 1L;
	private MaskFormatter maskCPF, maskCNPJ, maskFixo, maskCelular;
	private JFormattedTextField txtCpfCnpj, txtTelefone;
	private JRadioButton rbCPF, rbCNPJ, rbFixo, rbCelular;
	private JTextField txtNome, txtEmail, txtEspecializacao;
	private JButton btnNovo, btnAlterar, btnExcluir, btnSalvar, btnCancelar;

	private TableModelTecnico tableModelTecnico = new Tecnico.TableModelTecnico();
	private JTable tabelaTecnico;
	private Tecnico tecnicoSelecionado;

	public CadastroTecnicoScreen(JFrame owner) {
		super(owner, true);
		setSize(600, 500);
		setTitle("Sistema de Ordem de Serviço - Cadastro de Técnico");
		setLocationRelativeTo(owner);

		iniciarComponente();
	}

	private void iniciarComponente() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setContentPane(panel);
		{
			JLabel title = new JLabel("Cadastro de Tecnicos");
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

						txtCpfCnpj = new JFormattedTextField(maskCPF);
						txtCpfCnpj.setEnabled(false);

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
									String text = txtCpfCnpj.getText().replaceAll("\\D", "");
									txtCpfCnpj.setValue(null);
									txtCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(maskCPF));
									txtCpfCnpj.setText(text);
								}
							}
						});

						rbCNPJ.addItemListener(new ItemListener() {
							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									String text = txtCpfCnpj.getText().replaceAll("\\D", "");
									txtCpfCnpj.setValue(null);
									txtCpfCnpj.setFormatterFactory(new DefaultFormatterFactory(maskCNPJ));
									txtCpfCnpj.setText(text);
								}
							}
						});
					}

					// coluna 4
					gbc.gridx = 3;
					gbc.weightx = 1.0; // add peso em x
					section_1.add(txtCpfCnpj, gbc);

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
					txtEspecializacao = new JTextField();
					txtEspecializacao.setEnabled(false);
					section_1.add(txtEspecializacao, gbc);

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
							btnNovo.setIcon(Images.getImageIcon("new_icon_48x48.png", 16, 16));
							leftButtonPanel.add(btnNovo);

							btnAlterar = new JButton("Alterar");
							btnAlterar.setMnemonic(KeyEvent.VK_A);
							btnAlterar.setIcon(Images.getImageIcon("edit_icon_48x48.png", 16, 16));
							leftButtonPanel.add(btnAlterar);

							btnExcluir = new JButton("Excluir");
							btnExcluir.setMnemonic(KeyEvent.VK_E);
							btnExcluir.setIcon(Images.getImageIcon("remove_icon_48x48.png", 16, 16));
							leftButtonPanel.add(btnExcluir);
						}
						
						JPanel rightButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
						rightButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, -5));
						buttonPanel.add(rightButtonPanel);
						{
							btnSalvar = new JButton("Salvar");
							btnSalvar.setMnemonic(KeyEvent.VK_S);
							btnSalvar.setIcon(Images.getImageIcon("check_icon_48x48.png", 16, 16));
							btnSalvar.setEnabled(false);
							rightButtonPanel.add(btnSalvar);

							btnCancelar = new JButton("Cancelar");
							btnCancelar.setMnemonic(KeyEvent.VK_C);
							btnCancelar.setIcon(Images.getImageIcon("cancel_icon_48x48.png", 16, 16));
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
					tabelaTecnico = new JTable(tableModelTecnico);
					tabelaTecnico.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					tabelaTecnico.getSelectionModel().addListSelectionListener(e -> {
						int selectedRow = tabelaTecnico.getSelectedRow();
						if (selectedRow >= 0) {
							setTecnicoSelecionado(tableModelTecnico.getListTecnico().get(selectedRow));
							exibirTecnicoSelecionado();
						}
					});

					JScrollPane scrollPane = new JScrollPane(tabelaTecnico);
					section_2.add(scrollPane, BorderLayout.CENTER);
					
					JPanel footer = new JPanel(new FlowLayout(FlowLayout.LEFT));
					footer.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, -5));
					section_2.add(footer, BorderLayout.SOUTH);
					{
						JComboBox<Object> tipo = new JComboBox<>(new Object[] { "Nome", "Codigo" });
						footer.add(tipo);

						JTextField txtPesquise = new JTextField(15);
						footer.add(txtPesquise);
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

	// Show
	public void exibirTecnicos(List<Tecnico> listTecnico) {
		tableModelTecnico = new Tecnico.TableModelTecnico(listTecnico);
		tabelaTecnico.setModel(tableModelTecnico);
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

	private void exibirTecnicoSelecionado() {
		if (Objects.nonNull(tecnicoSelecionado)) {
			txtNome.setText(tecnicoSelecionado.getNome());
			
			String cpfCnpj = tecnicoSelecionado.getCpf();
			if (Objects.isNull(cpfCnpj) || cpfCnpj.replaceAll("\\D", "").length() < 14) {
				rbCPF.setSelected(true);
			} else {
				rbCNPJ.setSelected(true);
			}
			
			txtCpfCnpj.setText(tecnicoSelecionado.getCpf());
			txtEmail.setText(tecnicoSelecionado.getEmail());
			
			String telefone = tecnicoSelecionado.getTelefone();
			if (Objects.isNull(telefone) || telefone.replaceAll("\\D", "").length() < 11) {
				rbFixo.setSelected(true);
			} else {
				rbCelular.setSelected(true);
			}
			
			txtTelefone.setText(tecnicoSelecionado.getTelefone());
			txtEspecializacao.setText(tecnicoSelecionado.getEspecializacao());
		}
	}

	// Modify
	public Tecnico getTecnicoSelecionado() {
		return tecnicoSelecionado;
	}

	public void setTecnicoSelecionado(Tecnico tecnicoSelecionado) {
		this.tecnicoSelecionado = tecnicoSelecionado;
	}
	
	public String getNome() {
		return txtNome.getText();
	}
	
	public String getCpfCnpj() {
		return txtCpfCnpj.getText();
	}
	
	public String getEmail() {
		return txtEmail.getText();
	}
	
	public String getTelefone() {
		return txtTelefone.getText();
	}
	
	public String getgetEspecializacao() {
		return txtEspecializacao.getText();
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
		tabelaTecnico.clearSelection();
		setTecnicoSelecionado(null);
	}

	private void limparCampos() {
		txtNome.setText("");
		rbCPF.setSelected(true);
		txtCpfCnpj.setText("");
		txtEmail.setText("");
		rbFixo.setSelected(true);
		txtTelefone.setText("");
		txtEspecializacao.setText("");
	}

	private void bloquearTela(boolean b) {
		txtNome.setEnabled(!b);
		rbCPF.setEnabled(!b);
		rbCNPJ.setEnabled(!b);
		txtCpfCnpj.setEnabled(!b);
		txtEmail.setEnabled(!b);
		rbFixo.setEnabled(!b);
		rbCelular.setEnabled(!b);
		txtTelefone.setEnabled(!b);
		txtEspecializacao.setEnabled(!b);

		btnNovo.setEnabled(b);
		btnAlterar.setEnabled(b);
		btnExcluir.setEnabled(b);
		tabelaTecnico.setEnabled(b);
		
		btnSalvar.setEnabled(!b);
		btnCancelar.setEnabled(!b);
	}
	
	public boolean validarDados() {
		if (StringValidator.isEmpty(txtNome.getText())) {
			exibirMensagemAviso("O nome do Técnico não pode ser vazio.");
			txtNome.requestFocus();
			return false;
		}
		return true;
	}

}