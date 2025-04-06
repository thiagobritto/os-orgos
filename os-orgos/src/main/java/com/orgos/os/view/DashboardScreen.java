package com.orgos.os.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.orgos.os.util.Images;

public class DashboardScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;
	private JMenuItem mntmCadastroOrdemServico;
	private Image image;
	private JMenuItem mntmCadastroCliente;
	private JMenuItem mntmImportarBackup;
	private JMenuItem mntmExportarBackup;
	private JMenuItem mntmCadastroTecnico;

	public DashboardScreen() {
		setTitle("Sistema de Ordem de Serviço - Dashboaed");
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		image = Images.getImage("logo_icon_48x48.png");
		setIconImage(image);

		iniciarComponentes();
	}

	private void iniciarComponentes() {

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		{
			JMenu mnArquivo = new JMenu("Arquivo");
			menuBar.add(mnArquivo);
			{
				mntmImportarBackup = new JMenuItem("Importar Backup");
				mnArquivo.add(mntmImportarBackup);

				mntmExportarBackup = new JMenuItem("Exportar Backup");
				mnArquivo.add(mntmExportarBackup);

				JMenuItem mntmSobew = new JMenuItem("Sobre");
				mnArquivo.add(mntmSobew);

				JMenuItem mntmSair = new JMenuItem("Sair");
				mnArquivo.add(mntmSair);
			}

			JMenu mnCadastro = new JMenu("Cadastro");
			menuBar.add(mnCadastro);
			{
				mntmCadastroCliente = new JMenuItem("Cliente");
				mnCadastro.add(mntmCadastroCliente);

				mntmCadastroTecnico = new JMenuItem("Técnico");
				mnCadastro.add(mntmCadastroTecnico);

				mntmCadastroOrdemServico = new JMenuItem("Ordem de Serviço");
				mnCadastro.add(mntmCadastroOrdemServico);

				JMenuItem mntmCadastroUsuario = new JMenuItem("Usuário");
				mnCadastro.add(mntmCadastroUsuario);
			}

			JMenu mnRelatorio = new JMenu("Relatorios");
			menuBar.add(mnRelatorio);
			{
				JMenuItem mntmRelatorioCliente = new JMenuItem("Cliente");
				mnRelatorio.add(mntmRelatorioCliente);

				JMenuItem mntmRelatorioTecnico = new JMenuItem("Técnico");
				mnRelatorio.add(mntmRelatorioTecnico);

				JMenuItem mntmRelatorioOrdemServico = new JMenuItem("Ordem de Serviço");
				mnRelatorio.add(mntmRelatorioOrdemServico);

				JMenuItem mntmRelatorioUsuario = new JMenuItem("Usuário");
				mnRelatorio.add(mntmRelatorioUsuario);
			}
		}

		JPanel panel = new JPanel(new BorderLayout(5, 5));
		setContentPane(panel);
		{
			desktopPane = new JDesktopPane();
			panel.add(desktopPane, BorderLayout.CENTER);
			{
				
			} // end desktopPane
			
			JPanel footerPanel = new JPanel(new GridLayout(1, 3));
			panel.add(footerPanel, BorderLayout.SOUTH);
			{
				JPanel footerLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
				footerPanel.add(footerLeft);
				{
					
				} // end footerLeft
				
				JPanel footerCenter = new JPanel();
				footerPanel.add(footerCenter);
				{
					JLabel lblUsername = new JLabel("Usuario");
					footerCenter.add(lblUsername);
				} // end footerCenter
				
				JPanel footerRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
				footerPanel.add(footerRight);
				{
					
				} // end footerRight
			} // rnd footerPanel
		} // end panel
	}
	
	public void addImportarBackupListener(ActionListener listener) {
		mntmImportarBackup.addActionListener(listener);
	}
	
	public void addExportarBackupListener(ActionListener listener) {
		mntmExportarBackup.addActionListener(listener);
	}
	
	public void addCadastroClienteListener(ActionListener listener) {
		mntmCadastroCliente.addActionListener(listener);
	}
	
	public void addCadastroTecnicoListener(ActionListener listener) {
		mntmCadastroTecnico.addActionListener(listener);
	}
	
	public void addCadastroOrdemServicoListener(ActionListener listener) {
		mntmCadastroOrdemServico.addActionListener(listener);
	}
	
	// Show
	public void exibirMensagem(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem);
	}

	public void exibirMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);		
	}
	
	/**
	 * Exibe um JInternalFrame no desktopPane
	 * 
	 * @param <T> {@code ? extends JInternalFrame}
	 * @param internalFrame O JInternalFrame à ser adicionado
	 */
	public <T extends JInternalFrame> void addDesktop(T internalFrame) {		
		ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(16, 16, Image.SCALE_SMOOTH));
		internalFrame.setFrameIcon(imageIcon);
		
		int width = (desktopPane.getWidth() - internalFrame.getWidth()) / 2;
		internalFrame.setLocation(width, 0);
		
		internalFrame.setVisible(true);		
		desktopPane.add(internalFrame);
	}

}
