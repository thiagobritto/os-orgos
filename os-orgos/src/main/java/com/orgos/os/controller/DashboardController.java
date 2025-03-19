package com.orgos.os.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.orgos.os.service.BackupService;
import com.orgos.os.view.DashboardScreenInterface;

public class DashboardController {
	private DashboardScreenInterface screen;
	private BackupService backupService;

	public DashboardController(DashboardScreenInterface screen, BackupService backupService) {
		super();
		this.screen = screen;
		this.backupService = backupService;
	}

	// Método para exportar o backup
	public void exportarBackup(File diretorio) {
		// Gera o nome do arquivo com data e hora
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

	// Método para importar o backup
	public void importarBackup(File arquivoBackup) {
		String caminhoBackup = arquivoBackup.getAbsolutePath();
		
		if (backupService.importarBackup(caminhoBackup)) {
			screen.exibirMensagem("Backup importado com sucesso!");
		} else {
			screen.exibirMensagemErro("Erro ao importar backup.");			
		}		
	}

}
