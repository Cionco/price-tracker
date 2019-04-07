package structure;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {

	private Object data[][];
	
	public TableModel(int rows) {
		data = new Object[rows][2];
	}
	
	public TableModel(ArrayList<String> items) {
		data = new Object[items.size()][2];
		
		for(int i = 0; i < items.size(); i++) {
			data[i][0] = items.get(i);
			data[i][1] = false;
		}
	}
	
	
	@Override
	public int getColumnCount() {
		return data[0].length;
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data[arg0][arg1];
	}
	
	public void flipCheck(int row) {
		data[row][1] = !(boolean) data[row][1];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data[rowIndex][columnIndex] = aValue;
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return colIndex == 1;
	}
	
	public Class getColumnClass(int colIndex) {
		switch(colIndex) {
		case 0: return String.class;
		default: return Boolean.class;
		}
	}
	
}
