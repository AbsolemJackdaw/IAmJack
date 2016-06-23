package iamjack.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.engine.Window;
import iamjack.resourceManager.Images;

public class GameStateDrawHelper {

	public static float scale;
	public static float sizeX;
	public static float sizeY;
	
	public static void drawRoom(Graphics2D g){

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		scale = (float)Window.getWidth() / (float)Images.room.getWidth();
		sizeX = Images.room.getWidth() * scale;
		sizeY = Images.room.getHeight() * scale;

		g.drawImage(Images.room, 
				Window.getWidth()/2 - (int)sizeX/2,
				Window.getHeight()/2 - (int)sizeY/2, 
				(int)sizeX, (int)sizeY, null);
	}
	
	public static void drawLivingRoom(Graphics2D g){

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		scale = (float)Window.getWidth() / (float)Images.livingroom.getWidth();
		sizeX = Images.livingroom.getWidth() * scale;
		sizeY = Images.livingroom.getHeight() * scale;

		g.drawImage(Images.livingroom, 
				Window.getWidth()/2 - (int)sizeX/2,
				Window.getHeight()/2 - (int)sizeY/2, 
				(int)sizeX, (int)sizeY, null);
	}
}
