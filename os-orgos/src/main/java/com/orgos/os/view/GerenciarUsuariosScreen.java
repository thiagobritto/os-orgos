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

public class GerenciarUsuariosScreen extends JDialogScreen {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private JPanel permissoesPanel;
	private JTextField pesquisaField;
	private JTable listagemUsuariosTable;
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

		setTitle("Gerenciar Usuários");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(800, 540);
		setLocationRelativeTo(owner);

		iniciarComponentes();
	}

	private void iniciarComponentes() {
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		setContentPane(contentPanel);

		JLabel lblNewLabel_1 = new JLabel("Lista de usuários");
		lblNewLabel_1.setBounds(10, 10, 233, 14);
		contentPanel.add(lblNewLabel_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 603, 215);
		contentPanel.add(scrollPane);
		{
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
		}

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBounds(619, 30, 155, 215);
		buttonPanel.setLayout(new GridLayout(6, 1, 5, 5));
		contentPanel.add(buttonPanel);
		{
			if (SessaoUsuario.getInstancia().temPermissao(Funcionalidade.CADASTRAR_USUARIO)) {
				JButton novoButton = new JButton("Novo");
				novoButton.setMnemonic(KeyEvent.VK_N);
				novoButton.addActionListener(e -> {
				});
				buttonPanel.add(novoButton);
			}

			JButton alterarSenhaButton = new JButton("Trocar senha");
			alterarSenhaButton.setMnemonic(KeyEvent.VK_S);
			alterarSenhaButton.addActionListener(e -> {
			});
			buttonPanel.add(alterarSenhaButton);

			JButton excluirUsuarioButton = new JButton("Excluir usuário");
			excluirUsuarioButton.setMnemonic(KeyEvent.VK_E);
			excluirUsuarioButton.addActionListener(e -> {
			});
			buttonPanel.add(excluirUsuarioButton);
		}

		JLabel lblNewLabel = new JLabel("Encotrar usuário por:");
		lblNewLabel.setBounds(10, 260, 280, 14);
		contentPanel.add(lblNewLabel);

		pesquisaComboBox = new JComboBox<>();
		pesquisaComboBox.setBounds(10, 280, 100, 30);
		contentPanel.add(pesquisaComboBox);

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

		JButton pesquisaButton = new JButton("Pesquisar");
		pesquisaButton.addActionListener(e -> pesquisar());
		pesquisaButton.setBounds(410, 280, 120, 30);
		contentPanel.add(pesquisaButton);

		JLabel lblNewLabel_2 = new JLabel("Permissões");
		lblNewLabel_2.setBounds(10, 325, 100, 14);
		contentPanel.add(lblNewLabel_2);

		permissoesPanel = new JPanel();
		permissoesPanel.setLayout(new GridLayout(6, 0, 10, 1)); // Linhas dinâmicas, 5 colunas, espaçamento 10px
		{
			boolean permissao = SessaoUsuario.getInstancia().temPermissao(Funcionalidade.GERENCIAR_PERMISSOES);
			checkBoxHashMap = new LinkedHashMap<Funcionalidade, JCheckBox>();
			for (Funcionalidade funcionalidade : Funcionalidade.values()) {
				JCheckBox jCheckBox = new JCheckBox(funcionalidade.getDescricao());
				jCheckBox.setEnabled(permissao);
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
		}

		JScrollPane scrollPane2 = new JScrollPane(permissoesPanel);
		scrollPane2.setBounds(10, 345, 764, 145);
		scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollPane2.setPreferredSize(new Dimension(380, 100)); // Definindo tamanho do painel de rolagem
		contentPanel.add(scrollPane2);
	}
	
	/**
	 * Exibe a lista de usuários na tabela, atualizando seu modelo e ajustes visuais.
	 *
	 * <p>Este método executa os seguintes passos:</p>
	 * <ul>
	 *   <li>Limpa a seleção atual da tabela.</li>
	 *   <li>Cria um novo modelo de tabela baseado na lista de usuários fornecida.</li>
	 *   <li>Define o novo modelo na tabela.</li>
	 *   <li>Ajusta a largura das colunas para melhor exibição dos dados.</li>
	 * </ul>
	 *
	 * @param usuarios A lista de {@code Usuario} que será exibida na tabela.
	 */
	public void exibirUsuarios(List<Usuario> usuarios) {
		limparSelecao();
		criarTabelaModelo(usuarios);
		setTabelaModelo();
		ajustaLarguraColunaTabela();
	}
	
	/**
	 * Limpa a seleção atual da tabela de listagem de usuários.
	 *
	 * <p>Este método garante que nenhuma linha da tabela esteja selecionada,
	 * facilitando a atualização da exibição sem manter seleções anteriores.</p>
	 */
	private void limparSelecao() {
		listagemUsuariosTable.clearSelection();
	}
	
	/**
	 * Cria um novo modelo de tabela para exibir a lista de usuários.
	 *
	 * <p>Este método instancia um novo {@code UsuarioTableModel} com a lista de usuários 
	 * fornecida, substituindo o modelo atual.</p>
	 *
	 * @param usuarios A lista de {@code Usuario} que será usada para criar o modelo da tabela.
	 */
	private void criarTabelaModelo(List<Usuario> usuarios) {
		usuarioTableModel = new UsuarioTableModel(usuarios);	
	}
	
	/**
	 * Define o novo modelo na tabela de listagem.
	 * 
	 * <p>Esse método define um novo modelo a tabela de listagem de usuários.<\p>
	 */
	private void setTabelaModelo() {
		listagemUsuariosTable.setModel(usuarioTableModel);
	}
	
	/**
	 * Ajusta a largura das colunas para melhor exibição dos dados.
	 * 
	 * <p>Esse método ajusta a largura da coluna 0 para o maximo de 120 pixels.</p>
	 */
	private void ajustaLarguraColunaTabela() {
		listagemUsuariosTable.getColumnModel().getColumn(0).setMaxWidth(120);
		
	}

	/**
	 * Atualiza a seleção de checkboxes com base nas permissões do usuário.
	 *
	 * <p>Este método percorre o mapa de checkboxes e verifica se o usuário possui 
	 * permissão para cada funcionalidade. Caso tenha, o checkbox correspondente é marcado.</p>
	 *
	 * @param usuario O usuário cujas permissões serão verificadas.
	 */
	public void exibirPrtmissoes(Usuario usuario) {
		checkBoxHashMap.forEach((funcionalidade, jCheckBox) -> {
			boolean permissao = PermissaoUtil.temPermissao(usuario, funcionalidade);
			jCheckBox.setSelected(permissao);
		});
	}
	
	/**
	 * Desmarca todas as checkboxes de permissões.
	 *
	 * <p>Este método percorre o mapa de checkboxes e define todas as seleções como falsas,
	 * removendo qualquer permissão previamente marcada.</p>
	 */
	private void limparPertmissoes() {
		checkBoxHashMap.forEach((funcionalidade, jCheckBox) -> jCheckBox.setSelected(false));
	}

	/**
	 * Define o controlador responsável pelo gerenciamento da tela.
	 *
	 * @param controller O objeto {@code GerenciarUsuariosController} a ser associado a esta classe.
	 */
	public void setController(GerenciarUsuariosController controller) {
		this.controller = controller;
	}
	
	/**
	 * Define as opções de pesquisa no combobox de pesquisa de usuários.
	 *
	 * <p>Este método atualiza o modelo do combobox com as pesquisas fornecidas
	 * e define a segunda opção (índice 1) como selecionada por padrão.</p>
	 *
	 * @param pesquisasUsuario Um array de {@code PesquisaUsuario} contendo as opções de pesquisa.
	 */
	public void setPesquisa(PesquisaUsuario[] pesquisasUsuario) {
		pesquisaComboBox.setModel(new DefaultComboBoxModel<>(pesquisasUsuario));
		pesquisaComboBox.setSelectedIndex(1);
	}
	
	/**
	 * 
	 */
	public void pesquisar() {
		PesquisaUsuario pesquisa = (PesquisaUsuario) pesquisaComboBox.getSelectedItem();
		exibirUsuarios(pesquisa.buscar(pesquisaField.getText()));
	}

	/**
	 * Exibe mensagem para o usuário
	 * 
	 * <p>Esse método exibe mensagens na tela do usuário</p>
	 * 
	 * @param mensagem A menssage à ser exibida
	 */
	public void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}

	/**
	 * Exibe mensagem de erro para o usuário.
	 * 
	 * <p>Esse método exibe mensagens de erro na tela do usuário</p>
	 * 
	 * @param mensagem A menssage de erro à ser exibida
	 */
	public void exibirMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

}
