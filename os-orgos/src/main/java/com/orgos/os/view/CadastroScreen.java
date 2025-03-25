package com.orgos.os.view;

import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class CadastroScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	protected JPanel contentPanel;
	protected JPanel bottomPanel;
	protected JButton novoButton;
	protected JButton alterarButton;
	protected JButton excluirButton;
	protected JButton salvarButton;
	protected JButton cancelarButton;
	
	public CadastroScreen(JFrame owner) {
		super(owner, true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(800, 540);
		setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);
		
		contentPanel = new JPanel();
		contentPanel.setBounds(0, 0, 784, 431);
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel);
		
		bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 429, 784, 60);
		bottomPanel.setLayout(null);
		getContentPane().add(bottomPanel);
		{
			novoButton = new JButton("Novo");
			novoButton.addActionListener(e -> onNew());
			novoButton.setBounds(20, 10, 70, 36);
			novoButton.setMnemonic(KeyEvent.VK_N);
			bottomPanel.add(novoButton);
			
			alterarButton = new JButton("Alterar");
			alterarButton.addActionListener(e -> onEdit());
			alterarButton.setBounds(110, 10, 80, 36);
			alterarButton.setMnemonic(KeyEvent.VK_A);
			bottomPanel.add(alterarButton);			
			
			excluirButton = new JButton("Excluir");
			excluirButton.addActionListener(e -> onRemove());
			excluirButton.setBounds(210, 10, 80, 36);
			excluirButton.setMnemonic(KeyEvent.VK_E);
			bottomPanel.add(excluirButton);			
			
			salvarButton = new JButton("Salvar");
			salvarButton.addActionListener(e -> onSave());
			salvarButton.setBounds(589, 10, 80, 36);
			salvarButton.setMnemonic(KeyEvent.VK_S);
			bottomPanel.add(salvarButton);			
			
			cancelarButton = new JButton("Cancelar");
			cancelarButton.addActionListener(e -> onCancel());
			cancelarButton.setBounds(684, 10, 80, 36);
			cancelarButton.setMnemonic(KeyEvent.VK_C);
			bottomPanel.add(cancelarButton);	
		}
		
	}
	
	public abstract void onStart();
	public abstract void onReset();
	public abstract void onNew();
	public abstract void onEdit();
	public abstract void onRemove();
	public abstract void onSave();
	public abstract void onCancel();
	
	public void start() {
		contentPanel.setEnabled(true);
		salvarButton.setEnabled(true);
		cancelarButton.setEnabled(true);
		novoButton.setEnabled(false);
		alterarButton.setEnabled(false);
		excluirButton.setEnabled(false);
		onStart();
	}
	
	public void reset() {
		contentPanel.setEnabled(false);
		salvarButton.setEnabled(false);
		cancelarButton.setEnabled(false);
		novoButton.setEnabled(true);
		alterarButton.setEnabled(true);
		excluirButton.setEnabled(true);
		onReset();
	}
}
