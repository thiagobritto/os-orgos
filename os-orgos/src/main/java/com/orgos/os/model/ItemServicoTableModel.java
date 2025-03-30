package com.orgos.os.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ItemServicoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final String[] COLUMNS = { "ID", "DESCRIÇÃO", "VALOR R$" };
	private List<ItemServico> itensSevico;
	private int id;

	public ItemServicoTableModel() {
		itensSevico = new ArrayList<>();
		id = itensSevico.size() + 1;
	}

	public ItemServicoTableModel(List<ItemServico> itensSevico) {
		this.itensSevico = itensSevico;
		id = itensSevico.size() + 1;
	}

	@Override
	public int getRowCount() {
		return itensSevico.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ItemServico itemServico = itensSevico.get(rowIndex);
		return switch (columnIndex) {
			case 0 -> itemServico.getId();
			case 1 -> itemServico.getDescricao();
			case 2 -> new DecimalFormat("R$ ###,##0.00").format(itemServico.getCusto());
			default -> null;
		};
	}

	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}

	public ItemServico getItemServico(int rowIndex) {
		return itensSevico.get(rowIndex);
	}

	public List<ItemServico> getAllItemServico() {
		return itensSevico;
	}
	
	public int getId() {
		return id;
	}

	public void adicionarItem(ItemServico Item) {
		id++;
		itensSevico.add(Item);
		fireTableRowsInserted(itensSevico.size() - 1, itensSevico.size() - 1);
	}

	public void removerItem(int rowIndex) {
		itensSevico.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}

}
