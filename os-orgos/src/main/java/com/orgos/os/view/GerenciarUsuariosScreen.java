package com.orgos.os.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.orgos.os.controller.GerenciarUsuariosController;
import com.orgos.os.model.Usuario;
import com.orgos.os.model.UsuarioTableModel;

public class GerenciarUsuariosScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable listagemUsuariosTable;
	private JTextField pesquisaField;
	private JComboBox<String> pesquisaComboBox;
	private GerenciarUsuariosController controller;
	private UsuarioTableModel usuarioTableModel;

	/**
	 * Create the dialog.
	 */
	public GerenciarUsuariosScreen(JFrame owner) {
		super(owner, true);
		this.controller = new GerenciarUsuariosController(this);
		this.initCoponent();
		this.controller.carregarDadosPesquisa();
		this.controller.carregarUsuarios();
	}

	private void initCoponent() {
		setTitle("Gerenciar Usuários");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(800, 540);
		setLocationRelativeTo(null);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 603, 215);
		contentPanel.add(scrollPane);

		usuarioTableModel = new UsuarioTableModel();
		listagemUsuariosTable = new JTable(usuarioTableModel);
		listagemUsuariosTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int usuarioIndex = listagemUsuariosTable.getSelectedRow();
				if (usuarioIndex >= 0) {
					controller.setUsuario(usuarioIndex);
				}
			}
		});
		listagemUsuariosTable.getColumnModel().getColumn(0).setMaxWidth(120);
		scrollPane.setViewportView(listagemUsuariosTable);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(619, 30, 155, 215);
		buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));
		contentPanel.add(buttonPanel);

		JButton novoButton = new JButton("Novo");
		novoButton.setMnemonic(KeyEvent.VK_N);
		novoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame owner = (JFrame) GerenciarUsuariosScreen.this.getOwner();
				new CadastroUsuarioScreen(owner).setVisible(true);
				controller.carregarUsuarios();
			}
		});
		buttonPanel.add(novoButton);

		JButton alterarSenhaButton = new JButton("Trocar senha");
		alterarSenhaButton.setMnemonic(KeyEvent.VK_S);
		alterarSenhaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.usuarioSelecionado()) {
					JFrame owner = (JFrame) GerenciarUsuariosScreen.this.getOwner();
					Usuario usuario = controller.getUsuario();
					new SenhaScreen(owner, usuario).setVisible(true);
				} else {
					exibirMenssagem("Selecione um 'Usuário' para continuar!");
				}
			}
		});
		buttonPanel.add(alterarSenhaButton);

		JButton excluirUsuarioButton = new JButton("Excluir usuário");
		excluirUsuarioButton.setMnemonic(KeyEvent.VK_E);
		excluirUsuarioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.usuarioSelecionado()) {
					controller.removerUsuario();
				} else {
					exibirMenssagem("Selecione um 'Usuário' para continuar!");
				}
			}
		});
		buttonPanel.add(excluirUsuarioButton);

		JButton editarPermicoesButton = new JButton("Editar Permissões");
		editarPermicoesButton.setMnemonic(KeyEvent.VK_P);
		editarPermicoesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (controller.usuarioSelecionado()) {
					JFrame owner = (JFrame) GerenciarUsuariosScreen.this.getOwner();
					Usuario usuario = controller.getUsuario();
					new EditarPermissoesScreen(owner, usuario).setVisible(true);
				} else {
					exibirMenssagem("Selecione um 'Usuário' para continuar!");
				}
			}
		});
		buttonPanel.add(editarPermicoesButton);

		JLabel lblNewLabel = new JLabel("Encotrar usuário por:");
		lblNewLabel.setBounds(10, 260, 280, 14);
		contentPanel.add(lblNewLabel);

		pesquisaField = new JTextField();
		pesquisaField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					int modoIndex = pesquisaComboBox.getSelectedIndex();
					String pesquisa = pesquisaField.getText();
					controller.buscarUsuario(pesquisa, modoIndex);
				}
			}
		});
		pesquisaField.setBounds(120, 280, 280, 30);
		contentPanel.add(pesquisaField);
		pesquisaField.setColumns(10);

		pesquisaComboBox = new JComboBox<>();
		pesquisaComboBox.setBounds(10, 280, 100, 30);
		contentPanel.add(pesquisaComboBox);

		JButton pesquisaButton = new JButton("Pesquisar");
		pesquisaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int modoIndex = pesquisaComboBox.getSelectedIndex();
				String pesquisa = pesquisaField.getText();

				controller.buscarUsuario(pesquisa, modoIndex);
			}
		});
		pesquisaButton.setBounds(410, 280, 120, 30);
		contentPanel.add(pesquisaButton);

		JLabel lblNewLabel_1 = new JLabel("Lista de usuários");
		lblNewLabel_1.setBounds(10, 10, 233, 14);
		contentPanel.add(lblNewLabel_1);

		SwingUtilities.invokeLater(() -> pesquisaField.requestFocus());
	}

	public void exibirDadosPesquisa(String[] dadosPesquisa) {
		pesquisaComboBox.setModel(new DefaultComboBoxModel<>(dadosPesquisa));
		pesquisaComboBox.setSelectedIndex(1);
	}

	public void atualizarListaUsuarios(List<Usuario> usuarios) {
		usuarioTableModel = new UsuarioTableModel(usuarios);
		listagemUsuariosTable.setModel(usuarioTableModel);
		listagemUsuariosTable.getColumnModel().getColumn(0).setMaxWidth(120);
	}

	public void exibirMenssagem(String menssagem) {
		JOptionPane.showMessageDialog(this, menssagem);
	}

	public boolean comfirmarExclusao(String mensagem) {
		int resposta = JOptionPane.showConfirmDialog(this, mensagem, "Confirmação de Exclusão",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		return resposta == JOptionPane.YES_OPTION;
	}
}
