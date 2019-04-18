package main;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Robot;
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
		items = new ArrayList<String>();
		items.add("Engelsfeder");
		items.add("Vollmondkristall");
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
		
		try {
			analyze();			
		} catch(AWTException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	private void analyze() throws AWTException, InterruptedException {
		Robot rob = new Robot();
		Point searchMid = new Point(searchArea.x + searchArea.width / 2, searchArea.y + searchArea.height / 2);
		Point buttonMid = new Point(searchButton.x + searchButton.width / 2, searchButton.y + searchButton.height / 2);		
		for(String item : items) {
			rob.mouseMove(searchMid.x, searchMid.y);
			System.out.println("Click");
			System.out.println("Input for " + item);
			Thread.sleep(500);
			rob.mouseMove(buttonMid.x, buttonMid.y);
			System.out.println("Click");
			System.out.println("Read data for " + item);
			Thread.sleep(500);
		}
	}
	
	
	
	
	@Override
	public Object getResult() {
		return null;
	}

}
