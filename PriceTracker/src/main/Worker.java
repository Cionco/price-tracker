package main;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		JTextField txtStep = new JTextField();
		txtStep.setEnabled(false);
		contentPane.add(txtStep, BorderLayout.NORTH);
		
		JLabel lblImage = new JLabel();
		contentPane.add(lblImage, BorderLayout.CENTER);
		
		JTextField txtResult = new JTextField();
		txtResult.setEnabled(false);
		contentPane.add(txtResult, BorderLayout.SOUTH);
		
		
		setBounds(10, searchArea.y + 100, searchArea.x - 20, 200);
		setVisible(true);
	}
	
	
	
	
	
	
	
	@Override
	public Object getResult() {
		return null;
	}

}
