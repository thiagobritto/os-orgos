package com.orgos.os.util;

import com.orgos.os.model.Tecnico;

public class TecnicoSanitizer {

	public void sanitizar(Tecnico tecnico) {
		if (tecnico != null) {
			tecnico.setCpf(sanitizarCpfCnpj(tecnico.getCpf()));
			tecnico.setTelefone(sanitizarTelefone(tecnico.getTelefone()));
			tecnico.setEmail(sanitizarTexto(tecnico.getEmail()));
			tecnico.setEspecializacao(sanitizarTexto(tecnico.getEspecializacao()));			
		}
	}

	private String sanitizarCpfCnpj(String valor) {
		if (valor == null || valor.replaceAll("\\D", "").length() < 11) {
			return null;
		} else {
			return valor.trim();			
		}
	}
	
	private String sanitizarTelefone(String valor) {
		if (valor == null || valor.replaceAll("\\D", "").length() < 10) {
			return null;
		} else {
			return valor.trim();			
		}
	}
	
	private String sanitizarTexto(String valor) {
		if (valor == null || valor.trim().isEmpty()) {
			return null;
		} else {
			return valor.trim();			
		}
	}
	
}
