package com.orgos.os.view;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.orgos.os.util.FieldUtil;

public class CadastroTecnicoScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField nomeField;
	private JTextField emailField;

	/**
	 * Create the dialog.
	 */
	public CadastroTecnicoScreen(JFrame owner) {
		super(owner, true);
		this.initComponent();
	}

	public void initComponent() {
		setTitle("Cadastro de Técnicos");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(800, 540);
		setLocationRelativeTo(null);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JLabel nomeLabel = new JLabel("Nome *");
		nomeLabel.setBounds(20, 70, 73, 16);
		contentPanel.add(nomeLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setBounds(20, 143, 73, 16);
		contentPanel.add(emailLabel);
		
		JLabel especializacaoLabel = new JLabel("Especialização");
		especializacaoLabel.setBounds(20, 210, 73, 16);
		contentPanel.add(especializacaoLabel);
		
		JButton salvarButton = new JButton("Salvar");
		salvarButton.setMnemonic(KeyEvent.VK_S);
		salvarButton.setBounds(20, 332, 100, 36);
		contentPanel.add(salvarButton);
		
		JLabel tituloLabel = new JLabel("Cadastro de Técnicos");
		tituloLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		tituloLabel.setBounds(20, 0, 350, 60);
		contentPanel.add(tituloLabel);
		
		JLabel lembreteLabel = new JLabel("Campos obrigatórios (*)");
		lembreteLabel.setFont(new Font("Dialog", Font.ITALIC, 12));
		lembreteLabel.setBounds(10, 474, 513, 16);
		contentPanel.add(lembreteLabel);
		
		JFormattedTextField cpfField = new JFormattedTextField();
		cpfField.setBounds(430, 90, 233, 36);
		cpfField.setColumns(18);
		contentPanel.add(cpfField);
		FieldUtil.applyMask(cpfField, "###.###.###-##");
		
		JFormattedTextField cnpjField = new JFormattedTextField();
		cnpjField.setBounds(430, 90, 233, 36);
		cnpjField.setColumns(18);
		cnpjField.setVisible(false);
		contentPanel.add(cnpjField);
		FieldUtil.applyMask(cnpjField, "##.###.###/####-##");		

		JRadioButton cpfRadioButton = new JRadioButton("CPF", true);
		cpfRadioButton.setBounds(430, 67, 45, 23);
		contentPanel.add(cpfRadioButton);

		JRadioButton cnpjRadioButton = new JRadioButton("CNPJ");
		cnpjRadioButton.setBounds(478, 67, 64, 23);
		contentPanel.add(cnpjRadioButton);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(cpfRadioButton);
		buttonGroup.add(cnpjRadioButton);
		
		// Adiciona ação para alternar entre CPF e CNPJ
        ActionListener actionListener = e -> {
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

        cpfRadioButton.addActionListener(actionListener);
        cnpjRadioButton.addActionListener(actionListener);
		
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
		
		JFormattedTextField telefoneField = new JFormattedTextField();
		telefoneField.setBounds(340, 163, 202, 36);
		telefoneField.setColumns(15);
		contentPanel.add(telefoneField);
		FieldUtil.applyMask(telefoneField, "(##) ####-####");

		JFormattedTextField celularField = new JFormattedTextField();
		celularField.setBounds(340, 163, 153, 36);
		celularField.setColumns(15);
		celularField.setVisible(false);
		contentPanel.add(celularField);
		FieldUtil.applyMask(celularField, "(##) #####-####");

		JRadioButton telefoneRadioButton = new JRadioButton("Fixo", true);
		telefoneRadioButton.setBounds(402, 143, 52, 16);
		contentPanel.add(telefoneRadioButton);

		JRadioButton celularRadioButton = new JRadioButton("Celular");
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
		
		JTextArea especializacaoField = new JTextArea();
		especializacaoField.setLineWrap(true);
		especializacaoField.setRows(4);
		scrollPane.setViewportView(especializacaoField);
	}
	
	@Override
	public void setVisible(boolean b) {
		SwingUtilities.invokeLater(() -> nomeField.requestFocus());
		super.setVisible(b);		
	}
}
