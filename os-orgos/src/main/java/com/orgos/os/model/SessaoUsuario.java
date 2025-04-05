package com.orgos.os.model;

import com.orgos.os.util.Permissao;

public class SessaoUsuario {
	private static SessaoUsuario instancia;
	private Usuario usuarioLogado;
	
	private SessaoUsuario() {}
	
	public static SessaoUsuario getInstancia() {
		if (instancia == null) {
			instancia = new SessaoUsuario();
		}
		return instancia;
	}
	
	public boolean iniciada() {
		return !(getUsuarioLogado() == null);
	}
	
	public boolean temPermissao(Funcionalidade funcionalidade) {
		if (getUsuarioLogado() == null) {
			return false;
		}
		return Permissao.temPermissao(getUsuarioLogado(), funcionalidade);
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
}
