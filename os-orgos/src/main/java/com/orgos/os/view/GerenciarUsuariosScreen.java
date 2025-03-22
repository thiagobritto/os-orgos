package com.orgos.os.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.PesquisaUsuario;
import com.orgos.os.model.SessaoUsuario;
import com.orgos.os.model.Usuario;
import com.orgos.os.model.UsuarioTableModel;
import com.orgos.os.util.PermissaoUtil;

public class GerenciarUsuariosScreen extends JDialogScreen implements GerenciarUsuariosScreenInterface {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel permissoesPanel;
	private JTable listagemUsuariosTable;
	private JTextField pesquisaField;
	private JComboBox<PesquisaUsuario> pesquisaComboBox;

	private UsuarioTableModel usuarioTableModel = new UsuarioTableModel();
	private GerenciarUsuariosController controller;
	private LinkedHashMap<Funcionalidade, JCheckBox> checkBoxHashMap;

	/**
	 * Create the dialog.
	 */
	public GerenciarUsuariosScreen(JFrame owner, GerenciarUsuariosController controller) {
		super(owner, true);
		this.controller = controller;
		this.iniciarComponentes();
	}

	private void iniciarComponentes() {
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

		listagemUsuariosTable = new JTable(usuarioTableModel);
		listagemUsuariosTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selectedRow = listagemUsuariosTable.getSelectedRow();
				if (selectedRow < 0) {
					limparPertmissoes();
				} else {
					Usuario usuario = usuarioTableModel.getUsuario(selectedRow);
					exibirPrtmissoes(usuario);
				}
			}
		});
		listagemUsuariosTable.getColumnModel().getColumn(0).setMaxWidth(120);
		scrollPane.setViewportView(listagemUsuariosTable);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(619, 30, 155, 215);
		buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));
		contentPanel.add(buttonPanel);

		if (SessaoUsuario.getInstancia().temPermissao(Funcionalidade.CADASTRAR_USUARIO)) {
			JButton novoButton = new JButton("Novo");
			novoButton.setMnemonic(KeyEvent.VK_N);
			novoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// novoUsuario();
				}
			});
			buttonPanel.add(novoButton);
		}

		JButton alterarSenhaButton = new JButton("Trocar senha");
		alterarSenhaButton.setMnemonic(KeyEvent.VK_S);
		alterarSenhaButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = listagemUsuariosTable.getSelectedRow();
				if (selectedRow < 0) {
					exibirMensagem("Selecione um 'Usuário' para continuar!");
				} else {

				}
			}
		});
		buttonPanel.add(alterarSenhaButton);

		JButton excluirUsuarioButton = new JButton("Excluir usuário");
		excluirUsuarioButton.setMnemonic(KeyEvent.VK_E);
		excluirUsuarioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = listagemUsuariosTable.getSelectedRow();
				if (selectedRow < 0) {
					exibirMensagem("Selecione um 'Usuário' para continuar!");
				} else {

				}
			}
		});
		buttonPanel.add(excluirUsuarioButton);

		JLabel lblNewLabel = new JLabel("Encotrar usuário por:");
		lblNewLabel.setBounds(10, 260, 280, 14);
		contentPanel.add(lblNewLabel);

		pesquisaField = new JTextField();
		pesquisaField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					pesquisar();
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
				pesquisar();
			}
		});
		pesquisaButton.setBounds(410, 280, 120, 30);
		contentPanel.add(pesquisaButton);

		JLabel lblNewLabel_1 = new JLabel("Lista de usuários");
		lblNewLabel_1.setBounds(10, 10, 233, 14);
		contentPanel.add(lblNewLabel_1);

		permissoesPanel = new JPanel();
		permissoesPanel.setLayout(new GridLayout(6, 0, 10, 1)); // Linhas dinâmicas, 5 colunas, espaçamento 10px

		checkBoxHashMap = new LinkedHashMap<Funcionalidade, JCheckBox>();
		for (Funcionalidade funcionalidade : Funcionalidade.values()) {
			JCheckBox jCheckBox = new JCheckBox(funcionalidade.getDescricao());
			jCheckBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int selectedRow = listagemUsuariosTable.getSelectedRow();
					if (selectedRow >= 0) {
						Usuario usuario = usuarioTableModel.getUsuario(selectedRow);
						controller.atualizarPermissao(usuario, funcionalidade, jCheckBox.isSelected());
					}
				}
			});
			checkBoxHashMap.put(funcionalidade, jCheckBox);
			permissoesPanel.add(jCheckBox);
		}

		// Criando um JScrollPane para adicionar rolagem horizontal caso necessário
		JScrollPane scrollPane2 = new JScrollPane(permissoesPanel);
		scrollPane2.setBounds(10, 345, 764, 145);
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane2.setPreferredSize(new Dimension(380, 100)); // Definindo tamanho do painel de rolagem

		contentPanel.add(scrollPane2);

		JLabel lblNewLabel_2 = new JLabel("Permissões");
		lblNewLabel_2.setBounds(10, 325, 100, 14);
		contentPanel.add(lblNewLabel_2);
	}

	public void exibirPrtmissoes(Usuario usuario) {
		checkBoxHashMap.forEach((funcionalidade, jCheckBox) -> 
			jCheckBox.setSelected(PermissaoUtil.temPermissao(usuario, funcionalidade)));
	}

	public void setController(GerenciarUsuariosController controller) {
		this.controller = controller;
	}

	@Override
	public void setVisible(boolean b) {
		controller.carregarTela();
		SwingUtilities.invokeLater(() -> pesquisaField.requestFocus());
		super.setVisible(b);		
	}

	@Override
	public void exibirMensagem(String menssagem) {
		JOptionPane.showMessageDialog(this, menssagem);
	}

	@Override
	public void exibirUsuarios(List<Usuario> usuarios) {
		usuarioTableModel = new UsuarioTableModel(usuarios);
		listagemUsuariosTable.setModel(new UsuarioTableModel(usuarios));
		listagemUsuariosTable.getColumnModel().getColumn(0).setMaxWidth(120);
	}

	private void limparPertmissoes() {
		checkBoxHashMap.forEach((funcionalidade, jCheckBox) -> jCheckBox.setSelected(false));
	}

	@Override
	public void setPesquisa(PesquisaUsuario[] pesquisasUsuario) {
		pesquisaComboBox.setModel(new DefaultComboBoxModel<>(pesquisasUsuario));
		pesquisaComboBox.setSelectedIndex(1);
	}

	public void pesquisar() {
		PesquisaUsuario pesquisa = (PesquisaUsuario) pesquisaComboBox.getSelectedItem();
		exibirUsuarios(pesquisa.buscar(pesquisaField.getText()));
	}
}
