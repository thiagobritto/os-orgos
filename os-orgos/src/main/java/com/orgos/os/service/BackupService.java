package com.orgos.os.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class BackupService {

	private final String SOURSE = "BANCO/os-orgos.db";
	
	// Método para exportar o banco de dados
    public boolean exportarBackup(String caminhoDestino) {
    	try {        	
        	Files.copy(Path.of(SOURSE), Path.of(caminhoDestino), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para importar o banco de dados
    public boolean importarBackup(String caminhoBackup) {
    	File arquivoBackup = new File(caminhoBackup);

        // Valida o arquivo antes de importar
        if (!isSQLiteFileValid(arquivoBackup)) {
            return false;
        }
        
        try {
        	Files.copy(arquivoBackup.toPath(), Path.of(SOURSE), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Método para validar se o arquivo é um banco de dados SQLite válido
    private boolean isSQLiteFileValid(File arquivo) {
        // Verifica se o arquivo existe e não está vazio
        if (!arquivo.exists() || arquivo.length() == 0) {
            return false;
        }

        // Verifica se o arquivo começa com o cabeçalho do SQLite
        try {
            byte[] header = Files.readAllBytes(arquivo.toPath());
            String headerStr = new String(header, 0, 16); // Os primeiros 16 bytes contêm o cabeçalho do SQLite
            return headerStr.startsWith("SQLite format 3");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
