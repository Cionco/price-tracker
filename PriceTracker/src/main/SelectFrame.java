package main;

import java.awt.Graphics;
import structure.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SelectFrame extends JDialog {

	private final JPanel contentPanel = new JPanel();

	private Rectangle result;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SelectFrame dialog = new SelectFrame(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SelectFrame(JFrame root) {
		setBounds(0, 0, 1920, 1080);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.addMouseListener(new MouseAdapter() {
    		class Point {
    			public int x;
    			public int y;
    			public Point(int x, int y) {
    				this.x = x;
    				this.y = y;
    			}
    		}
    		
    		Point start;
    		
    		@Override
    		public void mousePressed(MouseEvent e) {
    			Graphics g = contentPanel.getGraphics();
    			g.clearRect(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
    			start = new Point(e.getX(), e.getY());
    		}
    		
    		public void mouseReleased(MouseEvent e) {
    			Point end = new Point(e.getX(), e.getY());
    			Graphics g = contentPanel.getGraphics();
    			g.drawLine(start.x, start.y, end.x, start.y);
    			g.drawLine(start.x, end.y, end.x, end.y);
    			g.drawLine(start.x, start.y, start.x, end.y);
    			g.drawLine(end.x, start.y, end.x, end.y);
    			result = new Rectangle(start.x, start.y, end.x - start.x, end.y - start.y);
    			SelectFrame.this.dispose();
    		}    		
    	});
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public Rectangle getResult() {
		return result;
	}

}
