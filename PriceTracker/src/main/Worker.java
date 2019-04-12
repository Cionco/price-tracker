package main;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import structure.Rectangle;
import structure.ResultDialog;

public class Worker extends ResultDialog {
	
	final ArrayList<String> items;
	final Rectangle searchArea;
	final Rectangle searchButton;
	final Rectangle priceArea;
	
	public Worker(ArrayList<String> items, Rectangle searchArea, Rectangle searchButton, Rectangle priceArea) {
		this.items = items;
		this.searchArea = searchArea;
		this.searchButton = searchButton;
		this.priceArea = priceArea;
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new GridLayout(1, 2));
		setContentPane(contentPane);
		
		JTextField txtStep = new JTextField();
		contentPane.add(txtStep);
		
		JTextField txtResult = new JTextField();
		contentPane.add(txtResult);
		
		
		setBounds(10, searchArea.y + 100, searchArea.x - 20, 200);
		setVisible(true);
	}
	
	
	
	
	
	
	
	@Override
	public Object getResult() {
		return null;
	}

}
