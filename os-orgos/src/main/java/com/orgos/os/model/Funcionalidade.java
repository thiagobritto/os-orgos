package com.orgos.os.model;

public enum Funcionalidade {
	GERENCIAR_USUARIO("Gerenciar Usuários"),
	CONFIGURAR_SISTEMA("Configurar Sistema"),
	VISUALIZAR_RELATORIOS("Visualizar Relatórios");
	
	private String descricao;
	
	private Funcionalidade(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
