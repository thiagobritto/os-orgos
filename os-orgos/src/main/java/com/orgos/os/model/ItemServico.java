package com.orgos.os.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ItemServico {

	private String descricao;
	private double quantidade;
	private double valor;

	public ItemServico(String descricao, double quantidade, double valor) {
		setDescricao(descricao);
		setQuantidade(quantidade);
		setValor(valor);
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public static class TableModelItemServico extends AbstractTableModel {

		private static final long serialVersionUID = 1L;
		private static final String[] COLUMNS = { "Descrição", "Quantidade", "Valor Unitario", "Valor Total" };
		private List<ItemServico> itensServico;

		public TableModelItemServico() {
			setItensServico(new ArrayList<>());
		}

		public TableModelItemServico(List<ItemServico> itensServico) {
			setItensServico(itensServico);
		}

		public List<ItemServico> getItensServico() {
			return itensServico;
		}

		public void setItensServico(List<ItemServico> itensServico) {
			this.itensServico = itensServico;
		}

		public void add(ItemServico itemServico) {
			itensServico.add(itemServico);
			fireTableRowsInserted(itensServico.size() - 1, itensServico.size() - 1);
		}

		public void remove(int rowIndex) {
			itensServico.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
		}

		// Methods
		@Override
		public int getRowCount() {
			return itensServico.size();
		}

		@Override
		public int getColumnCount() {
			return COLUMNS.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			ItemServico itemServico = itensServico.get(rowIndex);
			return switch (columnIndex) {
			case 0 -> itemServico.getDescricao();
			case 1 -> itemServico.getQuantidade();
			case 2 -> String.format("R$ %.2f", itemServico.getValor());
			case 3 -> String.format("R$ %.2f", itemServico.getValor() * itemServico.getQuantidade());
			default -> null;
			};
		}

		@Override
		public String getColumnName(int column) {
			return COLUMNS[column];
		}
	}
}
