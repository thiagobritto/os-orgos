package com.orgos.os.util;

public class OperacaoResultado {

	private boolean sucesso;
	private String mensagem;

	public OperacaoResultado(boolean sucesso, String mensagem) {
		this.sucesso = sucesso;
		this.mensagem = mensagem;
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

}
