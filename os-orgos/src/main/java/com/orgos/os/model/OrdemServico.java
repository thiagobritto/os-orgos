package com.orgos.os.model;

public class OrdemServico {
//	private int id;
//	private String numeroOS;
//	private Timestamp dataAbertura;
//	private Cliente cliente;
//	private Tecnico tecnico;
//	private String descricaoProblema;
//	private StatusOS status;
//	private List<Pecas> pecas;

	/**
	 * ENUM Type
	 */
	public static enum Type {
		ORDEM_SERVICO(1) {
			@Override
			public String toString() {
				return "Ordem de Serviço";
			}
		},
		ORCAMENTO(2) {
			@Override
			public String toString() {
				return "Orçamento";
			}
		};

		private int id;

		private Type(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public static Type valueOf(int id) {
			for (Type type : Type.values()) {
				if (type.getId() == id) {
					return type;
				}
			}
			return null;
		}
	}

	/**
	 * ENUM Status
	 */
	public static enum Status {
		ABERTA(1) {
			@Override
			public String toString() {
				return "Aberta";
			}
		},
		EM_ANDAMENTO(2) {
			@Override
			public String toString() {
				return "Em Andamento";
			}
		},
		CONCLUIDA(3) {
			@Override
			public String toString() {
				return "Concluida";
			}
		},
		CANCELADA(4) {
			@Override
			public String toString() {
				return "Cancelada";
			}
		};

		private int id;

		private Status(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public static Status valueOf(int id) {
			for (Status status : Status.values()) {
				if (status.getId() == id) {
					return status;
				}
			}
			return null;
		}
	}
}
