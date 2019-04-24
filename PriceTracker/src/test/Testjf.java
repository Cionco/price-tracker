package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Testjf extends JFrame {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Testjf frame = new Testjf();
		 			frame.setVisible(true);
				} catch(Exception e) {e.printStackTrace();}
			}
		});
	}
	
	public Testjf() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0,0,0,0));
		contentPane.setLayout(new BorderLayout());

		JTextField txtSearch = new JTextField();
		contentPane.add(txtSearch, BorderLayout.CENTER);

		JButton btnSearch = new JButton("Suchen");
		btnSearch.addActionListener(e -> System.out.println("Button pressed"));
		contentPane.add(btnSearch, BorderLayout.EAST);

		JTextField txtPrice = new JTextField();
		txtPrice.setEnabled(false);
		txtPrice.setText("50,000");
		contentPane.add(txtPrice, BorderLayout.SOUTH);
		
		setContentPane(contentPane);
	}
}
