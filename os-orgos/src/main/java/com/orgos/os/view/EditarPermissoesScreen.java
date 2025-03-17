package com.orgos.os.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.EditarPermissoesController;
import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Usuario;
import javax.swing.JLabel;
import java.awt.Font;

public class EditarPermissoesScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel funcionalidadesPanel;
	private EditarPermissoesController controller;
	private JLabel usernameLabel;
	
	/**
	 * Create the dialog.
	 */
	public EditarPermissoesScreen(JFrame owner, Usuario usuario) {
		super(owner, true);
		this.controller = new EditarPermissoesController(this, usuario);
		this.initCoponent();
		this.controller.carregarDadosUsuario();
		this.controller.carregarFuncionalidades();
	}
	
	private void initCoponent() {
		setTitle("Editar Permissões");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(800, 540);
		setLocationRelativeTo(null);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(457, 20, 290, 340);
		contentPanel.add(scrollPane);
		
		funcionalidadesPanel = new JPanel();
		funcionalidadesPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		funcionalidadesPanel.setLayout(new BoxLayout(funcionalidadesPanel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(funcionalidadesPanel);
		
		JLabel lblNewLabel = new JLabel("Permissões do Sistema");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		lblNewLabel.setBounds(36, 20, 426, 25);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuário:");
		lblNewLabel_1.setBounds(36, 64, 43, 16);
		contentPanel.add(lblNewLabel_1);
		
		usernameLabel = new JLabel("");
		usernameLabel.setBounds(82, 64, 170, 16);
		contentPanel.add(usernameLabel);
	}

	public void exibirFuncionalidades(Funcionalidade[] funcionalidades) {
		for (Funcionalidade funcionalidade : funcionalidades) {
			JCheckBox checkBox = new JCheckBox(funcionalidade.getDescricao());
			checkBox.setSelected(controller.usuarioTemPermissao(funcionalidade));
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					boolean permitir = checkBox.isSelected();
					controller.atualizarPermissao(funcionalidade, permitir);
				}
			});
			funcionalidadesPanel.add(checkBox);
		}
	}

	public void exibirDadosUsuario(String username) {
		usernameLabel.setText(username);
	}
}
