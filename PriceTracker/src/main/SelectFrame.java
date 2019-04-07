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
		MouseAdapter m = new MouseAdapter() {
    		class Point {
    			public int x;
    			public int y;
    			public Point(int x, int y) {
    				this.x = x;
    				this.y = y;
    			}
    		}
    		
    		Point start, current;
    		
    		@Override
    		public void mousePressed(MouseEvent e) {
    			System.out.println("pressed " + e.getX() + " " + e.getY());
    			Graphics g = contentPanel.getGraphics();
//    			g.clearRect(0, 0, contentPanel.getWidth(), contentPanel.getHeight());
    			start = new Point(e.getX(), e.getY());
    		}
    		
    		public void mouseReleased(MouseEvent e) {
    			System.out.println("released " + e.getX() + " " + e.getY());
    			result = new Rectangle(start.x, start.y, current.x - start.x, current.y - start.y);
    			SelectFrame.this.dispose();
    		}   
    		
    		public void mouseDragged(MouseEvent e) {
    			System.out.println("released " + e.getX() + " " + e.getY());
    			Graphics g = contentPanel.getGraphics();
    			if(current != null) g.clearRect(Math.min(start.x, current.x), Math.min(start.y, current.y), Math.abs(current.x - start.x) + 1, Math.abs(current.y - start.y) + 1);
    			current = new Point(e.getX(), e.getY());
    			g.drawLine(start.x, start.y, current.x, start.y);
    			g.drawLine(start.x, current.y, current.x, current.y);
    			g.drawLine(start.x, start.y, start.x, current.y);
    			g.drawLine(current.x, start.y, current.x, current.y);
    		}
    	};
    	
    	contentPanel.addMouseListener(m);
    	contentPanel.addMouseMotionListener(m);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public Rectangle getResult() {
		return result;
	}

}
