package com.orgos.os.model;

import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMNS = {"ID", "Nome", "CPF/CNPJ", "Telefone"};
	private List<Cliente> clientes;
	
	public ClienteTableModel() {
		this.clientes = Collections.emptyList();
	}
	
	public ClienteTableModel(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public Cliente getCliente(int rowIndex) {
		return clientes.get(rowIndex);
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
		if (columnIndex == 0)
			return cliente.getId();
		if (columnIndex == 1)
			return cliente.getNome();
		if (columnIndex == 2)
			return cliente.getCpfCnpj();
		if (columnIndex == 3)
			return cliente.getTelefone();
		return null;
	} 
	
	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
	
}
