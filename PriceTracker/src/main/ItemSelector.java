package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

import structure.ResultDialog;
import structure.TableModel;

public class ItemSelector extends ResultDialog {

	JPanel contentPane = new JPanel();
	JTable itemListTable;
	JScrollPane tablePane;
	
	ArrayList<String> items = new ArrayList<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ItemSelector frame = new ItemSelector(null);
			}
		});
	}
	
	public ItemSelector(JFrame root) {
		setBounds(600, 400, 500, 500);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		TableModel model = new TableModel(10);
		
		itemListTable = new JTable(model);
		JScrollPane tablePane = new JScrollPane(itemListTable);
		itemListTable.setShowGrid(true);
		itemListTable.setGridColor(Color.BLACK);
		itemListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		itemListTable.setTableHeader(null);
		contentPane.add(tablePane, BorderLayout.CENTER);
		
		contentPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				setCellSize();
			}
		});
		setVisible(true);
	}
	
	
	@Override
	public Object getResult() {
		return items;
	}
	
	public JPanel createItem(String item) {
		JPanel itemPane = new JPanel();
		itemPane.setLayout(new GridLayout(1, 2));
		itemPane.add(new JLabel(item));
		itemPane.add(new JCheckBox());
		return itemPane;
	}
	
	public void setCellSize() {
		int chxwidth = 20;
		int width = contentPane.getWidth() - chxwidth - 13;
		Enumeration<TableColumn> cols = itemListTable.getColumnModel().getColumns();
		for(; cols.hasMoreElements();) {
			TableColumn tc = cols.nextElement();
			if(!cols.hasMoreElements()) {
				tc.setMaxWidth(chxwidth);
				tc.setMinWidth(chxwidth);
				continue;
			}
			tc.setMinWidth(width);
			tc.setMaxWidth(width);
		}
	}
}
