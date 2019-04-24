package structure;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class AdvancedRobot extends Robot {

	public AdvancedRobot() throws AWTException {
		super();
	}

	public void mouseGlide(int x1, int y1, int x2, int y2, int t, int n) {
	    try {
	        double dx = (x2 - x1) / ((double) n);
	        double dy = (y2 - y1) / ((double) n);
	        double dt = t / ((double) n);
	        for (int step = 1; step <= n; step++) {
	            Thread.sleep((int) dt);
	            mouseMove((int) (x1 + dx * step), (int) (y1 + dy * step));
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}
	
	public void leftClick() {
		mousePress(InputEvent.BUTTON1_MASK);
		mouseRelease(InputEvent.BUTTON1_MASK);
	}
	
	public void doubleLeftClick() {
		leftClick();
		leftClick();
	}
}
