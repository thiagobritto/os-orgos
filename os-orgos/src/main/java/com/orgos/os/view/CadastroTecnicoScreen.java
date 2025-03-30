package com.orgos.os.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.orgos.os.controller.CadastroTecnicoController;
import com.orgos.os.model.Tecnico;
import com.orgos.os.util.AppFactory;
import com.orgos.os.util.FieldUtil;
import com.orgos.os.util.ValidacaoUtil;

public class CadastroTecnicoScreen extends CadastroScreen{

	private static final long serialVersionUID = 1L;
	private JTextField nomeField;
	private JTextField emailField;
	private JFormattedTextField cpfField;
	private JFormattedTextField telefoneField;
	private JTextArea especializacaoField;
	private JFormattedTextField cnpjField;
	private JFormattedTextField celularField;
	private JRadioButton cpfRadioButton;
	private JRadioButton cnpjRadioButton;
	private JRadioButton telefoneRadioButton;
	private JRadioButton celularRadioButton;
	
	private Tecnico tecnicoSelecionado;
	private CadastroTecnicoController controller;

	public CadastroTecnicoScreen(JFrame owner, CadastroTecnicoController controller) {
		super(owner);
		this.controller = controller;
		
		setTitle("Cadastro de Técnicos");
		setLocationRelativeTo(owner);
		
		JLabel tituloLabel = new JLabel("Cadastro de Técnicos");
		tituloLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tituloLabel.setLocation(20, 0);
		tituloLabel.setSize(262, 60);
		contentPanel.add(tituloLabel);
		
		JLabel nomeLabel = new JLabel("Nome *");
		nomeLabel.setBounds(20, 80, 69, 14);
		contentPanel.add(nomeLabel);
		
		nomeField = new JTextField();
		nomeField.setBounds(20, 100, 360, 36);
		contentPanel.add(nomeField);
		nomeField.setColumns(10);
		
		cpfField = new JFormattedTextField();
		cpfField.setBounds(400, 100, 200, 36);
		contentPanel.add(cpfField);
		FieldUtil.applyMask(cpfField, "###.###.###-##");
		
		cnpjField = new JFormattedTextField();
		cnpjField.setBounds(400, 100, 200, 36);
		cnpjField.setVisible(false);
		contentPanel.add(cnpjField);
		FieldUtil.applyMask(cnpjField, "##.###.###/####-##");
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(20, 150, 80, 14);
		contentPanel.add(emailLabel);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(20, 170, 330, 36);
		contentPanel.add(emailField);
		
		JLabel telefoneLabel = new JLabel("Telefone *");
		telefoneLabel.setBounds(370, 150, 64, 14);
		contentPanel.add(telefoneLabel);
		
		telefoneField = new JFormattedTextField();
		telefoneField.setBounds(370, 170, 200, 36);
		contentPanel.add(telefoneField);
		FieldUtil.applyMask(telefoneField, "(##) ####-####");
		
		celularField = new JFormattedTextField();
		celularField.setBounds(370, 170, 200, 36);
		celularField.setVisible(false);
		contentPanel.add(celularField);
		FieldUtil.applyMask(celularField, "(##) #####-####");
		
		JLabel enderecoLabel = new JLabel("Endereço *");
		enderecoLabel.setBounds(20, 220, 80, 14);
		contentPanel.add(enderecoLabel);
		
		JScrollPane enderecoScrollPane = new JScrollPane();
		enderecoScrollPane.setBounds(20, 240, 380, 80);
		contentPanel.add(enderecoScrollPane);
		
		especializacaoField = new JTextArea();
		enderecoScrollPane.setViewportView(especializacaoField);
		
		cpfRadioButton = new JRadioButton("CPF", true);
		cpfRadioButton.setBounds(400, 76, 45, 23);
		contentPanel.add(cpfRadioButton);
		
		cnpjRadioButton = new JRadioButton("CNPJ");
		cnpjRadioButton.setBounds(446, 76, 69, 23);
		contentPanel.add(cnpjRadioButton);
		
		ButtonGroup cpfCnpjButtonGroup = new ButtonGroup();
		cpfCnpjButtonGroup.add(cpfRadioButton);
		cpfCnpjButtonGroup.add(cnpjRadioButton);
		
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
		
		telefoneRadioButton = new JRadioButton("Fixo", true);
		telefoneRadioButton.setBounds(437, 146, 56, 23);
		contentPanel.add(telefoneRadioButton);
		
		celularRadioButton = new JRadioButton("Celular");
		celularRadioButton.setBounds(495, 146, 75, 23);
		contentPanel.add(celularRadioButton);
		
		ButtonGroup telefoneButtonGroup = new ButtonGroup();
		telefoneButtonGroup.add(telefoneRadioButton);
		telefoneButtonGroup.add(celularRadioButton);
		
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
	}

	@Override
	public void onStart() {
		nomeField.setEnabled(true);
		cpfField.setEnabled(true);
		cnpjField.setEnabled(true);
		emailField.setEnabled(true);
		telefoneField.setEnabled(true);
		celularField.setEnabled(true);
		especializacaoField.setEnabled(true);
	}

	@Override
	public void onReset() {
		tecnicoSelecionado = null;
		nomeField.setEnabled(false);
		cpfField.setEnabled(false);
		cnpjField.setEnabled(false);
		emailField.setEnabled(false);
		telefoneField.setEnabled(false);
		celularField.setEnabled(false);
		especializacaoField.setEnabled(false);
		showCpf(true);
		showTelefone(true);
		limparCampos();
	}

	@Override
	public void onNew() {
		nomeField.requestFocus();
		start();
	}

	private void showCpf(boolean flag) {
		cpfRadioButton.setSelected(flag);
		cpfField.setVisible(flag);
		cnpjRadioButton.setSelected(!flag);
		cnpjField.setVisible(!flag);
	}
	
	private void showTelefone(boolean flag) {
		telefoneRadioButton.setSelected(flag);
		telefoneField.setVisible(flag);
		celularRadioButton.setSelected(!flag);
		celularField.setVisible(!flag);
	}

	private void limparCampos() {
		nomeField.setText("");
		cpfField.setText("");
		cnpjField.setText("");
		emailField.setText("");
		telefoneField.setText("");
		celularField.setText("");
		especializacaoField.setText("");
	}

	@Override
	public void onEdit() {
		if (tecnicoSelecionado == null) {
			exibirMensagem("Selecione um técnico");
		} else {
			nomeField.requestFocus();
			preencherCampos();
			start();			
		}
	}

	private void preencherCampos() {
		nomeField.setText(tecnicoSelecionado.getNome());
		emailField.setText(tecnicoSelecionado.getEmail());
		especializacaoField.setText(tecnicoSelecionado.getEspecializacao());
		preencherCPF();
		preencherTelefone();
	}

	private void preencherTelefone() {
		String telefone = tecnicoSelecionado.getTelefone();
		if (telefone.length() > 14) {
			showTelefone(false);
			celularField.setText(telefone);
		} else {
			showTelefone(true);
			telefoneField.setText(telefone);
		}
	}

	private void preencherCPF() {
		String cpfCnpj = tecnicoSelecionado.getCpf();
		if (cpfCnpj.length() > 14) {
			showCpf(false);
			cnpjField.setText(cpfCnpj);
		} else {
			showCpf(true);
			cpfField.setText(cpfCnpj);
		}
	}

	@Override
	public void onRemove() {
		if (tecnicoSelecionado == null) {
			exibirMensagem("Selecione um técnico");
		} else {
			int confirm = JOptionPane.showConfirmDialog(
					this, 
					"Deseja realmente revover esse registro?\nNome: " + tecnicoSelecionado.getNome(), 
					"Confirmar exclusão", 
					JOptionPane.YES_NO_OPTION, 
					JOptionPane.QUESTION_MESSAGE);
			
			if (confirm == JOptionPane.YES_OPTION)
				controller.removerTecnico(tecnicoSelecionado);
		}
	}

	@Override
	public void onSearch() {
		BuscaTecnicoScreen buscaTecnicoScreen = AppFactory.getBuscaTecnicoScreen();
		buscaTecnicoScreen.setVisible(true);
		
		Tecnico tecnico = buscaTecnicoScreen.getTecnicoSelecionado();
		if (tecnico != null) {
			tecnicoSelecionado = tecnico;
			preencherCampos();			
		}
	}
	
	@Override
	public void onSave() {
		if(!isValidFields()) {
			exibirMensagem("campo invalido!");
		} else {
			int id = (tecnicoSelecionado == null || tecnicoSelecionado.getId() < 1) ? 0 : tecnicoSelecionado.getId();
			String nome = nomeField.getText();
			String cpfCnpj = cpfField.isVisible() ? cpfField.getText() : cnpjField.getText();
			String email = emailField.getText();
			String telefone = telefoneField.isVisible() ? telefoneField.getText() : celularField.getText();
			String especializacao = especializacaoField.getText();

			Tecnico tecnico = new Tecnico(id, nome, cpfCnpj, telefone, email, especializacao);
			controller.salvarTecnico(tecnico);
		}
	}
	
	private boolean isValidFields() {
		if (ValidacaoUtil.isEmpty(nomeField))
			return false;	
		if (telefoneField.isVisible())
			if (!ValidacaoUtil.telefone(telefoneField))
				return false;	
		if (celularField.isVisible())
			if (!ValidacaoUtil.celular(celularField))
				return false;	
		
		return true;
	}

	@Override
	public void onCancel() {
		dispose();
	}

	public void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}

	public void setController(CadastroTecnicoController controller) {
		this.controller = controller;
	}
}
