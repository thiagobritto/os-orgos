package com.orgos.os.util;

public class OperacaoResultado {

	private boolean sucesso;
	private String mensagem;

	public OperacaoResultado(boolean sucesso, String mensagem) {
		this.sucesso = sucesso;
		this.mensagem = mensagem;
	}
	
	public static OperacaoResultado sucesso(String mensagem) {
		return new OperacaoResultado(true, mensagem);
	}

	public static OperacaoResultado erro(String mensagem) {
		return new OperacaoResultado(false, mensagem);
	}

	public boolean isSucesso() {
		return sucesso;
	}

	public String getMensagem() {
		return mensagem;
	}

}
