package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import iamjack.engine.Window;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;

public class GameStateDrawHelper {

	public static float scale;
	public static float sizeX;
	public static float sizeY;
	private static Font font = new Font("SquareFont", Font.PLAIN, Window.scale(35));
	
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
	
	public static void drawExterior(Graphics2D g){

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		scale = (float)Window.getWidth() / (float)Images.exterior.getWidth();
		sizeX = Images.exterior.getWidth() * scale;
		sizeY = Images.exterior.getHeight() * scale;

		g.drawImage(Images.exterior, 
				Window.getWidth()/2 - (int)sizeX/2,
				Window.getHeight()/2 - (int)sizeY/2, 
				(int)sizeX, (int)sizeY, null);
	}
	
	public static void drawBossCoinCounter(Graphics2D g){
		if(PlayerData.daysPlayed > 1){
			g.setFont(font);
			g.setColor(Color.green.darker().darker());
			String money = "Boss Coin :" + PlayerData.money;
			g.drawString(money, 0, g.getFontMetrics().getHeight());
		}
	}
	
	private static double bobCounter;
	private static double bobbing;
	
	public static void drawBobbingImg(Graphics2D g, BufferedImage img){
		g.drawImage(img, 
				Window.scale(850),
				Window.scale(200) + (int)bobbing,
				Window.scale(64), Window.scale(64), null);
	}
	
	public static void updateBobbingImage(){
		bobCounter += 0.025D;
		bobbing = Math.cos(bobCounter)*20;

	}
}
