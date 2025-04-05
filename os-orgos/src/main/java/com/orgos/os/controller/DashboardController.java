package com.orgos.os.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFileChooser;

import com.orgos.os.service.BackupService;
import com.orgos.os.util.AppFactory;
import com.orgos.os.view.CadastroClienteScreen;
import com.orgos.os.view.CadastroOrdemServicoScreen;
import com.orgos.os.view.CadastroTecnicoScreen;
import com.orgos.os.view.DashboardScreen;

public class DashboardController implements Controller {
	private DashboardScreen screen;
	private BackupService backupService;

	public DashboardController(DashboardScreen screen, BackupService backupService) {
		this.screen = screen;
		this.backupService = backupService;
	}

	@Override
	public void inicializar() {
		screen.addImportarBackupListener(this::importarBackup);
		screen.addExportarBackupListener(this::exportarBackup);
		screen.addCadastroClienteListener(this::cadastroCliente);
		screen.addCadastroTecnicoListener(this::cadastroTecnico);		
		screen.addCadastroOrdemServicoListener(this::cadastroOrdemServico);
	}
	
	private void importarBackup(ActionEvent actionevent1) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Selecione o arquivo de backup");
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos SQLite (*.db)", "db"));

		int userSelection = fileChooser.showOpenDialog(screen);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File arquivoBackup = fileChooser.getSelectedFile();
			String caminhoBackup = arquivoBackup.getAbsolutePath();
			
			if (backupService.importarBackup(caminhoBackup)) {
				screen.exibirMensagem("Backup importado com sucesso!");
			} else {
				screen.exibirMensagemErro("Erro ao importar backup.");			
			}
		}
	}

	private void exportarBackup(ActionEvent actionevent1) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Escolha o diretório para salvar o backup");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int userSelection = fileChooser.showSaveDialog(screen);
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File diretorio = fileChooser.getSelectedFile();
			
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String nomeArquivo = "os-orgos_" + timeStamp + ".db";
			File arquivoBackup = new File(diretorio, nomeArquivo);
			String caminhoDestino = arquivoBackup.getAbsolutePath();

			if (backupService.exportarBackup(caminhoDestino)) {
				screen.exibirMensagem("Backup exportado com sucesso para:\n" + caminhoDestino);
			} else {
				screen.exibirMensagemErro("Erro ao exportar backup.");	
			}
		}
	}
	
	private void cadastroCliente(ActionEvent actionevent1) {
		CadastroClienteScreen cadastroClienteScreen = AppFactory.getCadastroClienteScreen(screen);
		cadastroClienteScreen.setVisible(true);
	}
	
	private void cadastroTecnico(ActionEvent actionevent1) {
		CadastroTecnicoScreen cadastroTecnicoScreen = AppFactory.getCadastroTecnicoScreen(screen);
		cadastroTecnicoScreen.setVisible(true);
	}

	private void cadastroOrdemServico(ActionEvent actionevent1) {
		CadastroOrdemServicoScreen cadastroOrdemServicoScreen = AppFactory.getCadastroOrdemServicoScreen();
		screen.addDesktop(cadastroOrdemServicoScreen);
	}

	

	


}
