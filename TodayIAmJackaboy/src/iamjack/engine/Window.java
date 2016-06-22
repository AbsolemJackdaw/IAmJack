package iamjack.engine;

import java.awt.Dimension;
import java.awt.Toolkit;


public class Window {

	private static int screenWidth;
	private static int screenHeight;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Window(){
		screenWidth = 1024; //screenSize.width;
		screenHeight = screenWidth * 9 / 16;
	}
	
	public static int getWidth(){
		return screenWidth;
	}
	
	public static int getHeight(){
		return screenHeight;
	}
	
	public static double getScale(){
		
		return (double)screenWidth / 1024f;
	}
	
	public static int scale(double number){
		double f = number * getScale();
		double f2 = Math.ceil(f);
		
		return (int)f2;
	}
}
