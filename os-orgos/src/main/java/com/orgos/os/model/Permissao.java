package com.orgos.os.model;

public class Permissao {
	
	private int id;
    private int usuarioId;
    private Funcionalidade funcionalidade;

    public Permissao(int id, int usuarioId, Funcionalidade funcionalidade) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.funcionalidade = funcionalidade;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	@Override
	public String toString() {
		return "Permissao [id=" + id + ", usuarioId=" + usuarioId + ", funcionalidade=" + funcionalidade + "]";
	}
    
}

// admin123 = $2a$10$7n1PioVKVKDS6oXQcXOy0.2aUJqJ9JKho1VEOUKWmK7G2xQhppZTy
