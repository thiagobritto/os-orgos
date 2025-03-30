package com.orgos.os.model;

public enum StatusOS {
	ABERTA(1) {
		@Override
		public String toString() {
			return "Aberta";
		}
	}, // Ordem de Serviço recém-criada
	EM_ANDAMENTO(2) {
		@Override
		public String toString() {
			return "Em Andamento";
		}
	}, // Sendo executada
	CONCLUIDA(3) {
		@Override
		public String toString() {
			return "Concluida";
		}
	}, // Finalizada com sucesso
	CANCELADA(4) {
		@Override
		public String toString() {
			return "Cancelada";
		}
	}; // Cancelada pelo cliente
	
	private int id;
	
	private StatusOS(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public static StatusOS valueOf(int id) {
		for(StatusOS statusOs : StatusOS.values()) {
			if (statusOs.getId() == id) {
				return statusOs;
			}
		}
		return null;
	}
}
