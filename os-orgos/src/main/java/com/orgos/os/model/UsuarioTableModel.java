package com.orgos.os.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class UsuarioTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMNS = {"Código", "Nome"};
	private List<Usuario> usuarios;
	
	public UsuarioTableModel(List<Usuario> usuarios) {
		super();
		this.usuarios = usuarios;
	}
	
	public Usuario getValue(int rowIndex) {
		return usuarios.get(rowIndex);
	}

	@Override
	public int getRowCount() {
		return usuarios.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuario usuario = usuarios.get(rowIndex);
		if (columnIndex == 0) {
			return usuario.getId();
		}
		if (columnIndex == 1) {
			return usuario.getUsername();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}

}
