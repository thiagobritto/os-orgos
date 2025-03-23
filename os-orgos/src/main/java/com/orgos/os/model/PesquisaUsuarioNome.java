package com.orgos.os.model;

import java.util.List;

import com.orgos.os.service.UsuarioService;

public class PesquisaUsuarioNome implements PesquisaUsuario {
	
private UsuarioService usuarioService;
	
	public PesquisaUsuarioNome(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@Override
	public List<Usuario> buscar(String valor) {
		if (valor.isEmpty()) {
			return usuarioService.listarTodos();
		} else {
			return usuarioService.buscarTodosPorNome(valor);			
		}
	}
	
	@Override
	public String toString() {
		return "Nome";
	}

}
