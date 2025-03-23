package com.orgos.os.model;

import java.util.Collections;
import java.util.List;

import com.orgos.os.service.UsuarioService;

public class PesquisaUsuarioId implements PesquisaUsuario {

	private UsuarioService usuarioService;
	
	public PesquisaUsuarioId(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@Override
	public List<Usuario> buscar(String valor) {
		try {
			int usuarioId = Integer.parseInt(valor);			
			Usuario usuario = usuarioService.buscarPorId(usuarioId);
			return List.of(usuario);
		} catch (NumberFormatException e) {
			return Collections.emptyList();
		}
	}
	
	@Override
	public String toString() {
		return "Código";
	}

}
