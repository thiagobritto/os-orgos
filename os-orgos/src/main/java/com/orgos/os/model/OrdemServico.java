package com.orgos.os.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrdemServico {
	private int id;
	private String numeroOS;
	private LocalDateTime dataAbertura;
	private Type type;
	private Status status;
	private Cliente cliente;
	private Tecnico tecnico;
	private String equipamento;
	private String marca;
	private String servico;
	private String descricaoProblema;
	private String solucaoProblema;
	private double valorServico;
	private List<ItemServico> listItemServico;

	public OrdemServico(int id, String numeroOS, LocalDateTime dataAbertura, Type type, Status status, Cliente cliente,
			Tecnico tecnico, String equipamento, String marca, String servico, String descricaoProblema,
			String solucaoProblema, double valorServico, List<ItemServico> listItemServico) {
		this.id = id;
		this.numeroOS = numeroOS;
		this.dataAbertura = dataAbertura;
		this.type = type;
		this.status = status;
		this.cliente = cliente;
		this.tecnico = tecnico;
		this.equipamento = equipamento;
		this.marca = marca;
		this.servico = servico;
		this.descricaoProblema = descricaoProblema;
		this.solucaoProblema = solucaoProblema;
		this.valorServico = valorServico;
		this.listItemServico = listItemServico;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeroOS() {
		return numeroOS;
	}

	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public String getSolucaoProblema() {
		return solucaoProblema;
	}

	public void setSolucaoProblema(String solucaoProblema) {
		this.solucaoProblema = solucaoProblema;
	}

	public double getValorServico() {
		return valorServico;
	}

	public void setValorServico(double valorServico) {
		this.valorServico = valorServico;
	}

	public List<ItemServico> getListItemServico() {
		return listItemServico;
	}

	public void setListItemServico(List<ItemServico> listItemServico) {
		this.listItemServico = listItemServico;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataAbertura, descricaoProblema, equipamento, id, listItemServico, marca, numeroOS,
				servico, solucaoProblema, status, tecnico, type, valorServico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemServico other = (OrdemServico) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(dataAbertura, other.dataAbertura)
				&& Objects.equals(descricaoProblema, other.descricaoProblema)
				&& Objects.equals(equipamento, other.equipamento) && id == other.id
				&& Objects.equals(listItemServico, other.listItemServico) && Objects.equals(marca, other.marca)
				&& Objects.equals(numeroOS, other.numeroOS) && Objects.equals(servico, other.servico)
				&& Objects.equals(solucaoProblema, other.solucaoProblema) && status == other.status
				&& Objects.equals(tecnico, other.tecnico) && type == other.type
				&& Double.doubleToLongBits(valorServico) == Double.doubleToLongBits(other.valorServico);
	}

	@Override
	public String toString() {
		return "OrdemServico [id=" + id + ", numeroOS=" + numeroOS + ", dataAbertura=" + dataAbertura + ", type=" + type
				+ ", status=" + status + ", cliente=" + cliente + ", tecnico=" + tecnico + ", equipamento="
				+ equipamento + ", marca=" + marca + ", servico=" + servico + ", descricaoProblema=" + descricaoProblema
				+ ", solucaoProblema=" + solucaoProblema + ", valorServico=" + valorServico + ", listItemServico="
				+ listItemServico + "]";
	}

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
				return "Em andamento";
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
