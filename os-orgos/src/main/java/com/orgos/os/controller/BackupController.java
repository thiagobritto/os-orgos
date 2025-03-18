package com.orgos.os.controller;

import com.orgos.os.service.BackupService;

public class BackupController {

	private BackupService backupService;

	public BackupController() {
		this.backupService = new BackupService();
	}

	// Método para exportar o backup
	public boolean exportarBackup(String caminhoDestino) {
		return backupService.exportarBackup(caminhoDestino);
	}

	// Método para importar o backup
	public boolean importarBackup(String caminhoBackup) {
		return backupService.importarBackup(caminhoBackup);
	}

}
