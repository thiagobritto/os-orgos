package com.orgos.os.model;

public enum Funcionalidade {
	CADASTRAR_USUARIO("Cadastrar Usuários"),
	GERENCIAR_PERMISSOES("Gerenciar Permissões"),
	CONFIGURAR_SISTEMA("Configurar Sistema"),
	EXPORTAR_BACKUP("Exportar Backup"),
	IMPORTAR_BACKUP("Importar Backup"),
	VISUALIZAR_RELATORIOS("Visualizar Relatórios");
	
	private String descricao;
	
	private Funcionalidade(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
