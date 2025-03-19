package com.orgos.os.model;

import java.util.Objects;

public class ItemServico {
	private int id;
	private String descricao;
	private double custo;

	public ItemServico(int id, String descricao, double custo) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.custo = custo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getCusto() {
		return custo;
	}

	public void setCusto(double custo) {
		this.custo = custo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(custo, descricao, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemServico other = (ItemServico) obj;
		return Double.doubleToLongBits(custo) == Double.doubleToLongBits(other.custo)
				&& Objects.equals(descricao, other.descricao) && id == other.id;
	}

	@Override
	public String toString() {
		return "ItemServico [id=" + id + ", descricao=" + descricao + ", custo=" + custo + "]";
	}

}
