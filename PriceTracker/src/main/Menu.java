package main;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import structure.Rectangle;
import structure.ResultDialog;

public class Menu extends JFrame {

	private JPanel contentPane;
	private Rectangle searchField;
	private Rectangle searchButton;
	private Rectangle priceArea;
	
	public interface IStoreResult {
		public void storeResult(Object e);
	}

	public interface IOpenDialog {
		public ResultDialog openDialog();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(4, 0));
		setContentPane(contentPane);
		
		addButton("Set Search Field Area", e -> searchField = (Rectangle) e);
		addButton("Set Button Area", e -> searchButton = (Rectangle) e);
		addButton("Set Price Area", e -> priceArea = (Rectangle) e);
		addButton("Select Items", () -> new ItemSelector(this), e -> {});
		addButton("Start", () -> new Worker(null, searchField, searchButton, priceArea), e -> {});
	}
	
	private void addButton(String msg, IOpenDialog od, IStoreResult sr) {
		JButton newbtn = new JButton(msg);
		newbtn.addActionListener(e -> {
			ResultDialog rd = od.openDialog();			
			rd.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					sr.storeResult(rd.getResult());
					Menu.this.setVisible(true);
				}
			});
			Menu.this.setVisible(false);
		});
		contentPane.add(newbtn);
	}
	
	private void addButton(String msg, IStoreResult sr) {
		addButton(msg, () -> new SelectFrame(), sr);
	}
}
