package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

public class gui extends JFrame {

	private JPanel contentPane;
	private JLabel pic;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public gui() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		JPanel bottom = new JPanel();
		bottom.setBorder(new EmptyBorder(0, 0, 0, 0));
		bottom.setLayout(new FlowLayout());
		contentPane.add(bottom, BorderLayout.SOUTH);
		//File input_file = new File("/res/maxresdefault.png"); 
//        BufferedImage image = new BufferedImage(1280, 720, 
//                                BufferedImage.TYPE_INT_ARGB); 

         // Reading input file 

    	final BufferedImage image = ImageIO.read(this.getClass().getResource("/res/nosbasar.jpg"));
    	final BufferedImage image_backup = ImageIO.read(this.getClass().getResource("/res/nosbasar.jpg"));
    	pic = new JLabel(new ImageIcon(image));
    	pic.addMouseListener(new MouseAdapter() {
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
    			int hWastedSpace = (pic.getWidth() - image.getWidth()) / 2;
    			int vWastedSpace = (pic.getHeight() - image.getHeight()) / 2;
    			
    			start = new Point(e.getX() - hWastedSpace, e.getY() - vWastedSpace);
    			System.out.println(start.x + " " + start.y);
    		}
    		
    		public void mouseReleased(MouseEvent e) {
    			int hWastedSpace = (pic.getWidth() - image.getWidth()) / 2;
    			int vWastedSpace = (pic.getHeight() - image.getHeight()) / 2;
    			
    			Point end = new Point(e.getX() - hWastedSpace, e.getY() - vWastedSpace);
    			for(int x = start.x; x < end.x; x++) {
    				image.setRGB(x, start.y, 0xffff0000);
    				image.setRGB(x, Math.min(end.y, image.getHeight()), 0xffff0000);
    			}
    			for(int y = start.y; y < end.y; y++) {
    				image.setRGB(start.x, y, 0xffff0000);
    				image.setRGB(end.x, y, 0xffff0000);
    			}
    			gui.this.revalidate();
    			gui.this.repaint();
    		}
    		
    	});
		contentPane.add(pic, BorderLayout.CENTER);
		
		
		JSlider border = new JSlider(JSlider.HORIZONTAL, 1, 4000, 1);
		bottom.add(border, BorderLayout.SOUTH);
		border.addChangeListener(e -> {
			if(border.getValue() == 0) return;
			for(int i = 0; i < image.getWidth() - 1; i++) 
				for(int j = 0; j < image.getHeight() - 1; j++) {
					/*ArrayList<Integer> is = new ArrayList<>();
					for(int k = 1; k < border.getValue(); k++)
						is.add(image_backup.getRGB(i + k, j));
					for(int k = 1; k < border.getValue(); k++)
						is.add(image_backup.getRGB(i, j + k));
					Integer[] Ia = new Integer[is.size()];
					is.toArray(Ia);
					int[] ia = new int[Ia.length];
					for(int k = 0; k < ia.length; k++) ia[k] = Ia[k];
					image.setRGB(i, j, alg(ia));*/
					//Similar_1
					image.setRGB(i, j, alg(image_backup.getRGB(i, j), image_backup.getRGB(i + 1, j), image_backup.getRGB(i, j+1), border.getValue()));
					image.setRGB(i, j, alg(image_backup.getRGB(i, j)));
				}

			gui.this.revalidate();
			gui.this.repaint();
		});
		
		JButton btn_apply_alg = new JButton("Apply");
		btn_apply_alg.addActionListener(e -> {
			for(int i = 0; i < image.getWidth() - 1; i++) 
				for(int j = 0; j < image.getHeight() - 1; j++)
					image.setRGB(i, j, alg(image_backup.getRGB(i, j), image_backup.getRGB(i + 1, j), image_backup.getRGB(i, j+1), border.getValue()));

			gui.this.revalidate();
			gui.this.repaint();
		});
		bottom.add(btn_apply_alg);
		
		
		JButton btn_revert = new JButton("Revert");
		btn_revert.addActionListener(e -> {
			for(int x = 0; x < image.getWidth(); x++)
				for(int y = 0; y < image.getHeight(); y++)
					image.setRGB(x, y, image_backup.getRGB(x, y));
			gui.this.revalidate();
			gui.this.repaint();
		});
		bottom.add(btn_revert);

		JButton btn_greyscale = new JButton("Black/White");
		btn_greyscale.addActionListener(e -> {
			for(int i = 0; i < image.getWidth(); i++)
				for(int j = 0; j < image.getHeight(); j++) {
					int rgb = image.getRGB(i, j);
					int r = rgb >> 16 & 0xff;
					int g = rgb >> 8 & 0xff;
					int b = rgb & 0xff;
					int avg = (r + g + b) / 3;
					int argb = (avg << 16)+ (avg << 8) + avg;
					argb |= 0xff << 24;
					//System.out.printf("%x, %x, %x, %x, %x, %x\n", rgb, r, g, b, avg, argb);
					image.setRGB(i, j, argb);
					image_backup.setRGB(i, j, argb);
				}
			gui.this.revalidate();
			gui.this.repaint();
			
		});
        contentPane.add(btn_greyscale, BorderLayout.NORTH);
	}
	
	private int alg(int... colors) {
		return similar_1(colors);
	}
	
	int line(int...colors) {
		ArrayList<RGB> cs = new ArrayList<>();
		for(int i = 0; i < colors.length - 1; i++) 
			cs.add(new RGB(colors[i]));
		
		RGB base = cs.remove(0);
		int size = cs.size() / 2;
		for(int i = 0; i < size; i++) {
			if(sub(base, cs.get(i)) > 0) return 0;
		}
		for(int i = size; i < cs.size(); i++)
			if(sub(base, cs.get(i)) > 0) return 0;
		return 0xff000000;
	}
	
	private int similar_1(int... colors) {
		RGB c0 = new RGB(colors[0])
		,	c1 = new RGB(colors[1])
		,	c2 = new RGB(colors[2]);

		int sub = sub(c0, c1);
		int sub1 = sub(c0, c2);
		sub = (sub + sub1) / 2;
		if(sub < colors[3]) sub = 0x00;
		else sub = 0xffffffff;
		return new RGB((byte) sub).color;
	}
	
	private int getAlpha(int... colors) {
		System.out.println(colors[0]>>24 & 0xff);
		return colors[0];
	}
	
	private int black_and_white(int... colors) {
		RGB c = new RGB(colors[0]);
		int avg = (c.r + c.g + c.b) / 3;
		int argb = (avg << 16)+ (avg << 8) + avg;
		argb |= 0xff << 24;
		return argb;
	}
	
	private class RGB {
		public int r;
		public int g;
		public int b;
		
		public int color;
		
		public RGB(int color) {
			r = color >> 16 & 0xff;
			g = color >> 8 & 0xff;
			b = color & 0xff;
			this.color = color;
		}
		
		public RGB(byte r, byte g, byte b) {
			this.r = r;
			this.g = g;
			this.b = b;
			this.color = (0xff << 24) | (r << 16) | (g << 8) | b;
		}
		
		public RGB(byte greyscale) {
			this(greyscale, greyscale, greyscale);
		}
	}
	
	int sub(RGB c, RGB c1) {
		double res = (1 / 3.0 * (Math.pow(c.r - c1.r, 2) + Math.pow(c.g - c1.g, 2) + Math.pow(c.b - c1.b, 2)));
		//System.out.println(Math.pow(c.r - c1.r, 2) + " " + Math.pow(c.g - c1.g, 2) + " " + Math.pow(c.b - c1.b, 2) + " " + res);
		//if(res > 0) System.out.println(res);
		return (int) res;
	}
}
