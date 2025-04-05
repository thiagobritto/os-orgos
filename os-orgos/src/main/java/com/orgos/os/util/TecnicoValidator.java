package com.orgos.os.util;

import com.orgos.os.model.Tecnico;

public class TecnicoValidator {

	public void validar(Tecnico tecnico) {
		if (tecnico.getNome() == null || tecnico.getNome().trim().isBlank()) {
			throw new IllegalArgumentException("O nome do técnico é obrigatorio.");
		}
	}
	
}
