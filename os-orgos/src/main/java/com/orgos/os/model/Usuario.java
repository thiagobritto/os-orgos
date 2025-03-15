package com.orgos.os.model;

import java.util.List;

public class Usuario {

	private int id;
	private String username;
	private List<Permissao> permissoes;

	public Usuario(int id, String username, List<Permissao> permissoes) {
		super();
		this.id = id;
		this.username = username;
		this.permissoes = permissoes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", permissoes=" + permissoes + "]";
	}

}
