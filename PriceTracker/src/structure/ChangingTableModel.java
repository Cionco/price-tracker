package structure;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;

public class ChangingTableModel extends AbstractTableModel {

	private ArrayList<Object[]> data;
	
	public ChangingTableModel(int rows) {
		data = new ArrayList<>(rows);
	}
	
	public ChangingTableModel(ArrayList<String> items) {
		data = new ArrayList<Object[]>();
		
		for(int i = 0; i < items.size(); i++) {
			data.add(i, new Object[2]);
			data.get(i)[0] = items.get(i);
			data.get(i)[1] = false;
		}
	}
	
	public void addRow(String item) {
		Object[] newItem = new Object[2];
		newItem[0] = item;
		newItem[1] = false;
		data.add(newItem);
	}
	
	@Override
	public int getColumnCount() {
		return data.get(0).length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		return data.get(arg0)[arg1];
	}
	
	public void flipCheck(int row) {
		data.get(row)[1] = !(boolean) data.get(row)[1];
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		data.get(rowIndex)[columnIndex] = aValue;
		
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
