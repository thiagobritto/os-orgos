package com.orgos.os.view;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemServicoScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JTextField descricaoField;
	private JTextField valorField;
	
	private String descricao;
	private double valor;
	

	/**
	 * Create the dialog.
	 */
	public ItemServicoScreen(JFrame owner) {
		super(owner, true);
		setTitle("Item");
		setSize(400, 300);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initComponent();
	}

	private void initComponent() {
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JLabel descricaoLabel = new JLabel("Descrição");
		descricaoLabel.setBounds(20, 40, 60, 14);
		contentPanel.add(descricaoLabel);
		
		descricaoField = new JTextField();
		descricaoField.setBounds(20, 60, 343, 36);
		contentPanel.add(descricaoField);
		descricaoField.setColumns(10);
		
		JLabel valorLabel = new JLabel("Valor R$");
		valorLabel.setBounds(20, 110, 46, 14);
		contentPanel.add(valorLabel);
		
		valorField = new JTextField();
		valorField.setColumns(10);
		valorField.setBounds(20, 130, 201, 36);
		contentPanel.add(valorField);
		
		JButton adicionarButton = new JButton("Adicionar");
		adicionarButton.addActionListener(e -> adicionar());
		adicionarButton.setBounds(20, 203, 80, 36);
		contentPanel.add(adicionarButton);
	}
	
	public String getDescricao() {
		return descricao;
	}

	public double getValor() {
		return valor;
	}

	private void adicionar() {
		if (descricaoField.getText().trim().isEmpty()) {
			exibirMensagem("Informe um Descrição.");
			descricaoField.requestFocus();
			return;
		}
		descricao = descricaoField.getText();
		
		try {
			String strValor = valorField.getText().replace(",", ".");
			valor = Double.parseDouble(strValor);
		} catch (Exception e) {
			exibirMensagem("Informe um numero (Valor R$) valido!");
			valorField.requestFocus();
			return;
		}
		dispose();
	}

	private void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}
}
