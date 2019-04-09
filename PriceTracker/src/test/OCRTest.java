package test;

import net.sourceforge.tess4j.*;
import java.io.File;

public class OCRTest {

	public static void main(String[] args) {
		File f = new File("D:\\Users\\nicol\\Desktop\\Unbenannt.png");
		ITesseract instance = new Tesseract();
		
		try {
			String result = instance.doOCR(f);
			System.out.println(result);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
