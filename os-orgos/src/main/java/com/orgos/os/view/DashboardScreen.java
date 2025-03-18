package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.orgos.os.controller.BackupController;
import com.orgos.os.controller.DashboardController;
import com.orgos.os.model.Usuario;

public class DashboardScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JLabel usernameLabel;
	private DashboardController controller;
	 private BackupController backupController;

	/**
	 * Create the frame.
	 */
	public DashboardScreen(Usuario usuario) {
		this.controller = new DashboardController(this, usuario);
		this.backupController = new BackupController();
		
		this.initCoponent();
		this.controller.carregarDadosUsuario();
	}

	private void initCoponent() {
		setTitle("Dashboaed");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// menu
		menuBar = new JMenuBar();

		JMenu arquivoMenu = new JMenu("Arquivo");
		menuBar.add(arquivoMenu);
		
		JMenuItem arquivoExportarBackupMenuItem = new JMenuItem("Exportar Backup");
		arquivoExportarBackupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarBackup();
			}
		});
		arquivoMenu.add(arquivoExportarBackupMenuItem);
		
		JMenuItem arquivoImportarBackupMenuItem = new JMenuItem("Importar Backup");
		arquivoImportarBackupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importarBackup();
			}
		});
		arquivoMenu.add(arquivoImportarBackupMenuItem);

		JMenuItem arquivoSobreMenuItem = new JMenuItem("Sobre");
		arquivoMenu.add(arquivoSobreMenuItem);

		JMenuItem arquivoSairMenuItem = new JMenuItem("Sair");
		arquivoSairMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		arquivoMenu.add(arquivoSairMenuItem);

		JMenu cadastroMenu = new JMenu("Cadastro");
		menuBar.add(cadastroMenu);

		JMenuItem cadastroClienteMenuItem = new JMenuItem("Cliente");
		cadastroMenu.add(cadastroClienteMenuItem);

		JMenuItem cadastroServicoMenuItem = new JMenuItem("Serviço");
		cadastroMenu.add(cadastroServicoMenuItem);

		JMenu cadastroUsuarioMenu = new JMenu("Usuário");
		cadastroMenu.add(cadastroUsuarioMenu);

		JMenuItem cadastroUsuarioNovoMenuItem = new JMenuItem("Novo");
		cadastroUsuarioNovoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastroUsuarioScreen(DashboardScreen.this).setVisible(true);
			}
		});
		cadastroUsuarioMenu.add(cadastroUsuarioNovoMenuItem);

		JMenuItem cadastroUsuarioGerenciarMenuItem = new JMenuItem("Gerenciar");
		cadastroUsuarioGerenciarMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new GerenciarUsuariosScreen(DashboardScreen.this).setVisible(true);
			}
		});
		cadastroUsuarioMenu.add(cadastroUsuarioGerenciarMenuItem);

		JMenu relatorioMenu = new JMenu("Relatório");
		menuBar.add(relatorioMenu);

		JMenuItem relatorioClienteMenuItem = new JMenuItem("Cliente");
		relatorioMenu.add(relatorioClienteMenuItem);

		JMenuItem relatorioServicoMenuItem = new JMenuItem("Serviço");
		relatorioMenu.add(relatorioServicoMenuItem);

		JMenuItem relatorioUsuarioMenuItem = new JMenuItem("Usuário");
		relatorioMenu.add(relatorioUsuarioMenuItem);

		setJMenuBar(menuBar);

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

		usernameLabel = new JLabel("");
		fooderPane.add(usernameLabel);

		setContentPane(contentPane);
	}

	public void exibirDadosUsuario(String username, int id) {
		usernameLabel.setText(username.toUpperCase());
	}
	
	// Método para exportar o backup
    private void exportarBackup() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha o diretório para salvar o backup");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File diretorio = fileChooser.getSelectedFile();

            // Gera o nome do arquivo com data e hora
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String nomeArquivo = "backup_" + timeStamp + ".db";
            File arquivoBackup = new File(diretorio, nomeArquivo);

            // Chama o controller para exportar o backup
            boolean sucesso = backupController.exportarBackup(arquivoBackup.getAbsolutePath());
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Backup exportado com sucesso para:\n" + arquivoBackup.getAbsolutePath());
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao exportar backup.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para importar o backup
    private void importarBackup() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Selecione o arquivo de backup");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos SQLite (*.db)", "db"));

        int userSelection = fileChooser.showOpenDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File arquivoBackup = fileChooser.getSelectedFile();

            // Chama o controller para importar o backup
            boolean sucesso = backupController.importarBackup(arquivoBackup.getAbsolutePath());
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Backup importado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao importar backup.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
