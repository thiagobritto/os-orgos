package com.orgos.os.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.orgos.os.service.BackupService;

public class BackupController {

	private BackupService backupService;
	private String caminhoDestino;
	private String caminhoBackup;

	public BackupController() {
		this.backupService = new BackupService();
	}

	// Método para exportar o backup
	public boolean exportarBackup(File diretorio) {
		// Gera o nome do arquivo com data e hora
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String nomeArquivo = "os-orgos_" + timeStamp + ".db";
        File arquivoBackup = new File(diretorio, nomeArquivo);
        caminhoDestino = arquivoBackup.getAbsolutePath();
        
		return backupService.exportarBackup(caminhoDestino);
	}

	// Método para importar o backup
	public boolean importarBackup(File arquivoBackup) {
		caminhoBackup = arquivoBackup.getAbsolutePath();
		return backupService.importarBackup(caminhoBackup);
	}

	public String getCaminhoDestino() {
		return caminhoDestino;
	}

	public String getCaminhoBackup() {
		return caminhoBackup;
	}
	
}
