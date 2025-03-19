package com.orgos.os.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OrdemServico {
	private int id;
	private String numeroOS;
	private LocalDate dataAbertura;
	private Cliente cliente;
	private Tecnico tecnico;
	private String descricaoProblema;
	private StatusOS status;
	private List<ItemServico> itens;

	public OrdemServico(int id, String numeroOS, LocalDate dataAbertura, Cliente cliente, Tecnico tecnico,
			String descricaoProblema, StatusOS status, List<ItemServico> itens) {
		super();
		this.id = id;
		this.numeroOS = numeroOS;
		this.dataAbertura = dataAbertura;
		this.cliente = cliente;
		this.tecnico = tecnico;
		this.descricaoProblema = descricaoProblema;
		this.status = status;
		this.itens = itens;
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

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
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

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public StatusOS getStatus() {
		return status;
	}

	public void setStatus(StatusOS status) {
		this.status = status;
	}

	public List<ItemServico> getItens() {
		return itens;
	}

	public void setItens(List<ItemServico> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, dataAbertura, descricaoProblema, id, itens, numeroOS, status, tecnico);
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
				&& Objects.equals(descricaoProblema, other.descricaoProblema) && id == other.id
				&& Objects.equals(itens, other.itens) && Objects.equals(numeroOS, other.numeroOS)
				&& status == other.status && Objects.equals(tecnico, other.tecnico);
	}

	@Override
	public String toString() {
		return "OrdemServico [id=" + id + ", numeroOS=" + numeroOS + ", dataAbertura=" + dataAbertura + ", cliente="
				+ cliente + ", tecnico=" + tecnico + ", descricaoProblema=" + descricaoProblema + ", status=" + status
				+ ", itens=" + itens + "]";
	}

}
