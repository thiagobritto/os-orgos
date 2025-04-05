package com.orgos.os.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

public class Tecnico {
	private int id;
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	private String especializacao;

	public Tecnico(int id, String nome, String cpf, String telefone, String email, String especializacao) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.email = email;
		this.especializacao = especializacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecializacao() {
		return especializacao;
	}

	public void setEspecializacao(String especializacao) {
		this.especializacao = especializacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, email, especializacao, id, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tecnico other = (Tecnico) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(email, other.email)
				&& Objects.equals(especializacao, other.especializacao) && id == other.id
				&& Objects.equals(nome, other.nome) && Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return nome;
	}

	public static class TableModelTecnico extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private final String[] COLUMNS = { "Código", "Nome", "CPF/CNPJ", "Telefone" };
		private List<Tecnico> listTecnico;

		public TableModelTecnico() {
			this.listTecnico = new ArrayList<>();
		}

		public TableModelTecnico(List<Tecnico> listTecnico) {
			this.listTecnico = listTecnico;
		}

		public List<Tecnico> getListTecnico() {
			return listTecnico;
		}

		public void setListTecnico(List<Tecnico> listTecnico) {
			this.listTecnico = listTecnico;
		}

		public void add(Tecnico tecnico) {
			listTecnico.add(tecnico);
			fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
		}

		public void remove(int rowIndex) {
			listTecnico.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}

		@Override
		public int getRowCount() {
			return listTecnico.size();
		}

		@Override
		public int getColumnCount() {
			return COLUMNS.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Tecnico tecnico = listTecnico.get(rowIndex);
			return switch (columnIndex) {
				case 0 -> tecnico.getId();
				case 1 -> tecnico.getNome();
				case 2 -> tecnico.getCpf();
				case 3 -> tecnico.getTelefone();
				default -> null;
			};
		}

		@Override
		public String getColumnName(int column) {
			return COLUMNS[column];
		}

	}

}
