package com.orgos.os.model;

public class Usuario {

	private int id;
	private String username;

	public Usuario() {
		super();
	}
	
	public Usuario(int id, String username) {
		super();
		this.id = id;
		this.username = username;
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

	@Override
	public String toString() {
		return "UsuarioModel [id=" + id + ", username=" + username + "]";
	}
	
}
