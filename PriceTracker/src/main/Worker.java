package main;

import java.util.ArrayList;

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
		
		setBounds(10, searchArea.y + 100, searchArea.x - 20, 200);
		setVisible(true);
	}
	
	
	
	
	
	
	
	@Override
	public Object getResult() {
		return null;
	}

}
