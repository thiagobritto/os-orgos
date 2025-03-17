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

public class EditarPermissoesScreen extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel funcionalidadesPanel;
	private EditarPermissoesController controller;
	
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
		scrollPane.setBounds(530, 50, 230, 250);
		contentPanel.add(scrollPane);
		
		funcionalidadesPanel = new JPanel();
		funcionalidadesPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		funcionalidadesPanel.setLayout(new BoxLayout(funcionalidadesPanel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(funcionalidadesPanel);
	}

	public void exibirFoncionalidades(Funcionalidade[] funcionalidades) {
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
		setTitle("Editar Permissões " + username.toUpperCase());
	}
}
