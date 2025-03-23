package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.orgos.os.controller.DashboardController;
import com.orgos.os.model.Cliente;
import com.orgos.os.model.Funcionalidade;
import com.orgos.os.model.SessaoUsuario;
import com.orgos.os.util.AppFactory;

public class DashboardScreen extends JFrame implements DashboardScreenInterface {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private DashboardController controller;

	/**
	 * Create the frame.
	 */
	public DashboardScreen(DashboardController controller) {
		this.controller = controller;
		iniciarComponentes();
	}

	private void iniciarComponentes() {
		setTitle("Dashboaed");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		URL resource = DashboardScreen.class.getResource("/images/logo_jframe_48x48.png");
		Image image = Toolkit.getDefaultToolkit().getImage(resource);
		setIconImage(image);

		// content
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.BLUE);
		contentPane.add(desktopPane, BorderLayout.CENTER);

		JPanel fooderPane = new JPanel();
		contentPane.add(fooderPane, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("Logado como:");
		fooderPane.add(lblNewLabel);

		if (SessaoUsuario.getInstancia().iniciada()) {
			String username = SessaoUsuario.getInstancia().getUsuarioLogado().getUsername();

			JLabel usernameLabel = new JLabel(username.toUpperCase());
			fooderPane.add(usernameLabel);
		}

		setContentPane(contentPane);

		// menu
		menuBar = new JMenuBar();

		JMenu arquivoMenu = new JMenu("Arquivo");
		menuBar.add(arquivoMenu);

		if (SessaoUsuario.getInstancia().temPermissao(Funcionalidade.EXPORTAR_BACKUP)) {
			JMenuItem arquivoExportarBackupMenuItem = new JMenuItem("Exportar Backup");
			arquivoExportarBackupMenuItem.addActionListener(e -> exportarBackup());
			arquivoMenu.add(arquivoExportarBackupMenuItem);
		}

		if (SessaoUsuario.getInstancia().temPermissao(Funcionalidade.IMPORTAR_BACKUP)) {
			JMenuItem arquivoImportarBackupMenuItem = new JMenuItem("Importar Backup");
			arquivoImportarBackupMenuItem.addActionListener(e -> importarBackup());
			arquivoMenu.add(arquivoImportarBackupMenuItem);
		}

		JMenuItem arquivoSobreMenuItem = new JMenuItem("Sobre");
		arquivoMenu.add(arquivoSobreMenuItem);

		JMenuItem arquivoSairMenuItem = new JMenuItem("Sair");
		arquivoSairMenuItem.addActionListener(e -> close());
		arquivoMenu.add(arquivoSairMenuItem);

		JMenu cadastroMenu = new JMenu("Cadastro");
		menuBar.add(cadastroMenu);

		JMenu cadastroClienteMenu = new JMenu("Cliente");
		cadastroMenu.add(cadastroClienteMenu);
		
		JMenuItem cadastroClienteNovoMenuItem = new JMenuItem("Novo");
		cadastroClienteNovoMenuItem.addActionListener(e -> abrirTelaCadastroCliente());
		cadastroClienteMenu.add(cadastroClienteNovoMenuItem);
		
		JMenuItem cadastroClienteEditarMenuItem = new JMenuItem("Editar");
		cadastroClienteEditarMenuItem.addActionListener(e -> abrirTelaEditarCliente());
		cadastroClienteMenu.add(cadastroClienteEditarMenuItem);

		JMenuItem cadastroServicoMenuItem = new JMenuItem("Serviço");
		cadastroMenu.add(cadastroServicoMenuItem);

		if (SessaoUsuario.getInstancia().temPermissao(Funcionalidade.CADASTRAR_USUARIO)
				|| SessaoUsuario.getInstancia().temPermissao(Funcionalidade.GERENCIAR_USUARIO)) {
			JMenu cadastroUsuarioMenu = new JMenu("Usuário");
			cadastroMenu.add(cadastroUsuarioMenu);

			if (SessaoUsuario.getInstancia().temPermissao(Funcionalidade.CADASTRAR_USUARIO)) {
				JMenuItem cadastroUsuarioNovoMenuItem = new JMenuItem("Novo");
				cadastroUsuarioNovoMenuItem.addActionListener(e -> abrirTelaCadastroUsuario());
				cadastroUsuarioMenu.add(cadastroUsuarioNovoMenuItem);
			}

			if (SessaoUsuario.getInstancia().temPermissao(Funcionalidade.GERENCIAR_USUARIO)) {
				JMenuItem cadastroUsuarioGerenciarMenuItem = new JMenuItem("Gerenciar");
				cadastroUsuarioGerenciarMenuItem.addActionListener(e -> abrirTelaGerenciarUsuario());
				cadastroUsuarioMenu.add(cadastroUsuarioGerenciarMenuItem);
			}
		}

		if (SessaoUsuario.getInstancia().temPermissao(Funcionalidade.VISUALIZAR_RELATORIOS)) {
			JMenu relatorioMenu = new JMenu("Relatório");
			menuBar.add(relatorioMenu);

			JMenuItem relatorioClienteMenuItem = new JMenuItem("Cliente");
			relatorioMenu.add(relatorioClienteMenuItem);

			JMenuItem relatorioServicoMenuItem = new JMenuItem("Serviço");
			relatorioMenu.add(relatorioServicoMenuItem);

			JMenuItem relatorioUsuarioMenuItem = new JMenuItem("Usuário");
			relatorioMenu.add(relatorioUsuarioMenuItem);
		}

		setJMenuBar(menuBar);

	}
	
	private void abrirTelaEditarCliente() {
		BuscaClienteScreen buscaClienteScreen = AppFactory.getBuscaClienteScreen();
		buscaClienteScreen.setVisible(true);
		
		Cliente clienteSelecionado = buscaClienteScreen.getClienteSelecionado();
		if (clienteSelecionado == null) 
			return;
		
		AppFactory.getEditarClienteScreen(clienteSelecionado).setVisible(true);
	}

	private void abrirTelaCadastroCliente() {
		AppFactory.getCadastroClienteScreen().setVisible(true);
	}
	
	private void abrirTelaCadastroUsuario() {
		AppFactory.getCadastroUsuarioScreen().setVisible(true);
	}

	private void abrirTelaGerenciarUsuario() {
		AppFactory.getGerenciarUsuariosScreen().setVisible(true);
	}
	
	private void exportarBackup() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Escolha o diretório para salvar o backup");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int userSelection = fileChooser.showSaveDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File diretorio = fileChooser.getSelectedFile();
			controller.exportarBackup(diretorio);
		}
	}

	private void importarBackup() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecione o arquivo de backup");
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos SQLite (*.db)", "db"));

		int userSelection = fileChooser.showOpenDialog(this);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File arquivoBackup = fileChooser.getSelectedFile();
			controller.importarBackup(arquivoBackup);
		}
	}

	@Override
	public void close() {
		System.exit(0);
	}

	@Override
	public void exibirMensagem(String menssagem) {
		JOptionPane.showMessageDialog(this, menssagem);
	}

	@Override
	public void exibirMensagemErro(String menssagem) {
		JOptionPane.showMessageDialog(this, menssagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void setVisible(boolean b) {
		this.iniciarComponentes();
		super.setVisible(b);
	}
	
	public void atualizarComponentes() {
		this.iniciarComponentes();
	}

	public void setController(DashboardController controller) {
		this.controller = controller;
	}

	

}
