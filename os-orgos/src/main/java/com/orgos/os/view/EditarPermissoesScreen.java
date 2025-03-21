package com.orgos.os.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.orgos.os.controller.EditarPermissoesController;
import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.Usuario;
import com.orgos.os.util.PermissaoUtil;

public class EditarPermissoesScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel funcionalidadesPanel;
	private JLabel usernameLabel;

	private EditarPermissoesController controller;

	/**
	 * Create the dialog.
	 */
	public EditarPermissoesScreen(JFrame owner, EditarPermissoesController controller) {
		super(owner, true);
		this.controller = controller;
		this.initComponent();
	}

	private void initComponent() {
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

	public void setController(EditarPermissoesController controller) {
		this.controller = controller;
	}

	public void setUsuarioSelecionado(Usuario usuario) {
		usernameLabel.setText(usuario.getUsername());

		funcionalidadesPanel.removeAll();
		for (Funcionalidade f : Funcionalidade.values()) {
			JCheckBox checkBox = new JCheckBox(f.getDescricao());
			checkBox.setSelected(PermissaoUtil.temPermissao(usuario, f));
			checkBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.atualizarPermissao(usuario, f, checkBox.isSelected());
				}
			});

			funcionalidadesPanel.add(checkBox);
		}
	}

}
