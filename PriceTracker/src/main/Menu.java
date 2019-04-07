package main;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import structure.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {

	private JPanel contentPane;
	private Rectangle searchField;
	private Rectangle searchButton;
	private Rectangle priceArea;
	
	public interface IStoreResult {
		public void storeResult(Object e);
	}

	/**
	 * Launch the application.
	 */
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
		
		
		
	}
	
	private void addButton(String msg, IStoreResult sr) {
		JButton newbtn = new JButton(msg);
		newbtn.addActionListener(e -> {
			SelectFrame sf =  new SelectFrame(this);			
			sf.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					sr.storeResult(sf.getResult());
					Menu.this.setVisible(true);
				}
			});
			Menu.this.setVisible(false);
		});
		contentPane.add(newbtn);
	}
}
