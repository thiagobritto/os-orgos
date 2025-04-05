package com.orgos.os.util;

import com.orgos.os.model.Cliente;

public class ClienteValidator {

	public void validar(Cliente cliente) {
		if (cliente.getNome() == null || cliente.getNome().trim().isBlank()) {
			throw new IllegalArgumentException("O nome do cliente é obrigatorio.");
		}
	}
	
}
