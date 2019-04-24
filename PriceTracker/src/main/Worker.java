package main;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import structure.Rectangle;
import structure.ResultDialog;

public class Worker extends ResultDialog {
	
	final ArrayList<String> items;
	final Rectangle searchArea;
	final Rectangle searchButton;
	final Rectangle priceArea;
	
	private volatile JLabel lblImage;
	
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
		
		lblImage = new JLabel();
		lblImage.setBackground(Color.BLACK);
		contentPane.add(lblImage, BorderLayout.CENTER);
		
		JTextField txtResult = new JTextField();
		txtResult.setEnabled(false);
		contentPane.add(txtResult, BorderLayout.SOUTH);
		
		
		setBounds(10, searchArea.y + 100, searchArea.x - 20, 200);
		setVisible(true);
			
		new Screenshotter().execute();
		
		try {
			analyze();
//			screenshot.run();
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
			rob.mousePress(InputEvent.BUTTON1_MASK);
			rob.mouseRelease(InputEvent.BUTTON1_MASK);
			System.out.println("Input for " + item);
			Thread.sleep(500);
			rob.mouseMove(buttonMid.x, buttonMid.y);
			System.out.println("Click");
			System.out.println("Read data for " + item);
			Thread.sleep(500);
		}
	}
	
	private synchronized void setLabelIcon(ImageIcon icon) {
		lblImage.setIcon(icon);
	}
	
	@Override
	public Object getResult() {
		return null;
	}

	private class Screenshotter extends SwingWorker<Void, BufferedImage> {

		@Override
		protected Void doInBackground() throws Exception {
			Robot screenshotter = new Robot();
			BufferedImage result;
			while(true) {
				Point p = MouseInfo.getPointerInfo().getLocation();
				int width = lblImage.getWidth();
				int height = lblImage.getHeight();
				result = screenshotter.createScreenCapture(new java.awt.Rectangle(p.x - width / 2, p.y - height / 2, lblImage.getWidth(), lblImage.getHeight()));
				publish(result);
				Thread.sleep(50);
			}
		}

		@Override
		protected void process(List<BufferedImage> chunks) {
			lblImage.setIcon(new ImageIcon(chunks.get(chunks.size() - 1)));
			super.process(chunks);
		}

		@Override
		protected void done() {
			// TODO Auto-generated method stub
			super.done();
		}
		
	}
	
}
