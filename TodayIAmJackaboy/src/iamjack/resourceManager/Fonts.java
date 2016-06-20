package iamjack.resourceManager;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {

	public static void registerFont(){
		 GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		 InputStream stream = null;
			stream = Fonts.class.getClassLoader().getResourceAsStream("font/Square.ttf");
			
		try {
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, stream));
		} catch (IOException|FontFormatException e) {
		    e.printStackTrace();
		}
	}
}
