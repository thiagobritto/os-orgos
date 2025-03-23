package com.orgos.os.model;

import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TecnicoTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMNS = {"ID", "Nome", "CPF/CNPJ", "Telefone"};
private List<Tecnico> tecnicos;
	
	public TecnicoTableModel() {
		this.tecnicos = Collections.emptyList();
	}
	
	public TecnicoTableModel(List<Tecnico> tecnicos) {
		this.tecnicos = tecnicos;
	}
	
	public Tecnico getTecnico(int rowIndex) {
		return tecnicos.get(rowIndex);
	}
	@Override
	public int getRowCount() {
		return tecnicos.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Tecnico tecnico = tecnicos.get(rowIndex);
		if (columnIndex == 0)
			return tecnico.getId();
		if (columnIndex == 1)
			return tecnico.getNome();
		if (columnIndex == 2)
			return tecnico.getCpf();
		if (columnIndex == 3)
			return tecnico.getTelefone();
		return null;
	} 
	
	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}
}
