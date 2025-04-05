package com.orgos.os.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.table.AbstractTableModel;

public class Cliente {
	private int id;
	private String nome;
	private String cpfCnpj;
	private String telefone;
	private String email;
	private String endereco;

	public Cliente(int id, String nome, String cpfCnpj, String telefone, String email, String endereco) {
		this.id = id;
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpfCnpj, email, endereco, id, nome, telefone);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpfCnpj, other.cpfCnpj) && Objects.equals(email, other.email)
				&& Objects.equals(endereco, other.endereco) && id == other.id && Objects.equals(nome, other.nome)
				&& Objects.equals(telefone, other.telefone);
	}

	@Override
	public String toString() {
		return nome;
	}

	public static class TableModelCliente extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private static final String[] COLUMNS = { "Código", "Nome", "CPF/CNPJ", "Telefone" };
		private List<Cliente> clientes;

		public TableModelCliente() {
			this.clientes = new ArrayList<>();
		}

		public TableModelCliente(List<Cliente> clientes) {
			this.clientes = clientes;
		}

		public List<Cliente> getClientes() {
			return clientes;
		}

		public void setClientes(List<Cliente> clientes) {
			this.clientes = clientes;
		}
		
		public void add(Cliente cliente) {
			clientes.add(cliente);
			fireTableRowsInserted(clientes.size() - 1, clientes.size() - 1);
		}
		
		public void remove(int rowIndex) {
			clientes.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}

		@Override
		public int getRowCount() {
			return clientes.size();
		}

		@Override
		public int getColumnCount() {
			return COLUMNS.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Cliente cliente = clientes.get(rowIndex);
			return switch (columnIndex) {
				case 0 -> cliente.getId();
				case 1 -> cliente.getNome();
				case 2 -> cliente.getCpfCnpj();
				case 3 -> cliente.getTelefone();
				default -> null;
			};

		}

		@Override
		public String getColumnName(int column) {
			return COLUMNS[column];
		}

	}

}
