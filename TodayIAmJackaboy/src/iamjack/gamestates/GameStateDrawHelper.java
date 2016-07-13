package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import framework.GameStateHandler;
import framework.input.MouseHandler;
import framework.window.Window;
import iamjack.buttons.ButtonDay;
import iamjack.gamestates.shop.ShopItems;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.resourceManager.Images;

public class GameStateDrawHelper {

	public static float scale;
	public static float sizeX;
	public static float sizeY;
	private static Font font = new Font("SquareFont", Font.PLAIN, Window.getGameScale(35));
	
	public GameStateDrawHelper() {
		BufferedImage img = Images.room; //standard size
		scale = (float)Window.getWidth() / (float)img.getWidth();
		sizeX = img.getWidth() * scale;
		sizeY = img.getHeight() * scale;
	}
	
	public static void drawRoom(Graphics2D g){

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.drawImage(Images.room, 
				Window.getWidth()/2 - (int)sizeX/2,
				Window.getHeight()/2 - (int)sizeY/2, 
				(int)sizeX, (int)sizeY, null);
		
		if(PlayerData.itemsBought.contains(ShopItems.tank))
			g.drawImage(Images.tank, Window.getGameScale(300), Window.getGameScale(200),(int)(54*scale), (int)(54*scale), null);
		
	}
	
	public static void drawLivingRoom(Graphics2D g, int daynight){

		BufferedImage img = daynight == 0 ? Images.livingroom : Images.livingroomNight;
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.drawImage(img, 
				Window.getWidth()/2 - (int)sizeX/2,
				Window.getHeight()/2 - (int)sizeY/2, 
				(int)sizeX, (int)sizeY, null);
	}
	
	public static void drawExterior(Graphics2D g, int daynight){

		BufferedImage img = daynight == 0 ? Images.exterior : Images.exteriorNight;

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.drawImage(img, 
				Window.getWidth()/2 - (int)sizeX/2,
				Window.getHeight()/2 - (int)sizeY/2, 
				(int)sizeX, (int)sizeY, null);
	}
	
	private static ButtonDay b = new ButtonDay("Quit Game", Window.getWidth() - Window.getGameScale(250), Window.getGameScale(25));
	
	public static void drawMenu(Graphics2D g){

		drawAchievements(g);
		
		b.draw(g);
	}
	
	public static void updateMenu(GameStateHandler gsh){
		b.update(gsh);
	}
	
	public static void drawAchievements(Graphics2D g){
		g.setColor(new Color(0, 0, 0, .5f));
		g.fillRect(Window.getGameScale(25), 0, Window.getWidth()-Window.getGameScale(50), Window.getHeight());
		
		int dex = 0;

		for(Achievement a : Achievement.achievements.values()){
			Point p = new Point((int)MouseHandler.mouseX, (int)MouseHandler.mouseY);
			
			Rectangle r = new Rectangle(
					Window.getGameScale(32), 
					(Window.getGameScale(64) + Window.getGameScale(70)*dex)+ MouseHandler.wheelY,
					Window.getGameScale(256), Window.getGameScale(64));
			
			if(r.contains(p))
				a.drawAid(g, Window.getGameScale(107), (Window.getGameScale(90) + Window.getGameScale(70)*dex) + MouseHandler.wheelY);

			a.drawInGui(g, Window.getGameScale(32), (Window.getGameScale(64) + Window.getGameScale(70)*dex) + MouseHandler.wheelY);
			dex ++;
		}
	}
	
	public static void drawBossCoinCounter(Graphics2D g){
		if(PlayerData.daysPlayed > 1){
			g.setFont(font);
			g.setColor(Color.green.darker().darker());
			
			String money = "Boss Coin :" + PlayerData.money;
			g.drawString(money, Window.getGameScale(2), g.getFontMetrics().getHeight());
			
			String fans = "Fans :" + PlayerData.fans;
			g.drawString(fans, Window.getWidth()-g.getFontMetrics().stringWidth(fans) - Window.getGameScale(2), g.getFontMetrics().getHeight());
			
		}
	}
	
	public static void drawBicepsCounter(Graphics2D g){
		if(PlayerData.daysPlayed > 1){
			g.setFont(font);
			g.setColor(Color.green.darker().darker());
			
			String biceps = "Biceps :" + PlayerData.exercised;
			g.drawString(biceps, Window.getWidth()-g.getFontMetrics().stringWidth(biceps) - Window.getGameScale(2), g.getFontMetrics().getHeight() * 2 + 10);
		}
	}
	
	private static double bobCounter;
	private static double bobbing;
	
	public static void drawBobbingImg(Graphics2D g, BufferedImage img){
		g.drawImage(img, 
				Window.getGameScale(850),
				Window.getGameScale(200) + (int)bobbing,
				Window.getGameScale(64), Window.getGameScale(64), null);
	}
	
	public static void updateBobbingImage(){
		bobCounter += 0.025D;
		bobbing = Math.cos(bobCounter)*20;
	}
}
