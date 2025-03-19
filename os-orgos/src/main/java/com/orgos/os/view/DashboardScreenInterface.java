package com.orgos.os.view;

public interface DashboardScreenInterface {
	void close();
	void exportarBackup();
	void importarBackup();
	void abrirTelaCadastroUsuario();
	void abrirTelaGerenciarUsuario();
	void exibirMensagem(String menssagem);
	void exibirMensagemErro(String menssagem);
}
