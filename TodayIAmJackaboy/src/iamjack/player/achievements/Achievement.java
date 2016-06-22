package iamjack.player.achievements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import iamjack.engine.Window;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;

public class Achievement {

	protected final String name;
	protected final BufferedImage img;
	protected final String text;

	private int offset = 0;
	private boolean isTriggerd = false;

	//	public static ArrayList<Achievement> achievements = new ArrayList<Achievement>();
	public static HashMap<String, Achievement> achievements = new HashMap<String, Achievement>();

	public Achievement(String name, BufferedImage img, String text) {
		this.name = name;
		this.img = img;
		this.text = text;
		this.isTriggerd = false;

		offset = Window.getHeight();

		achievements.put(name, this);
	}

	public static void trigger(String name){

		Achievement a = achievements.get(name);
		if(a == null)
			System.out.println("no achievement for " + name);

		if(a != null)
			if(!PlayerData.achievements.contains(a)){
				PlayerData.achievements.add(a);
				a.setTriggerd(true);
				System.out.println("trigger");
			}
	}

	public void setTriggerd(boolean isTriggerd) {
		this.isTriggerd = isTriggerd;
	}

	public void draw(Graphics2D g){

		if(offset < Window.getHeight()){

			int y = Math.max(Window.getHeight()-64,offset);

			g.drawImage(Images.achievement, 0, y, 256, 64, null);
			g.drawImage(img, 2, y , 64, 64, null);

			g.setFont(new Font("SquareFont", Font.PLAIN, 25));
			g.setColor(Color.white);
			g.drawString(text, 75, y+40);
		}
	}

	public void drawInGui(Graphics2D g, int x, int y){

		BufferedImage icon = null;
		
		if(PlayerData.achievements.contains(this))
			icon = img;
		else
			icon = Images.locked;
		
		g.drawImage(icon, x, y , Window.scale(64), Window.scale(64), null);
		
		g.setFont(new Font("SquareFont", Font.PLAIN, Window.scale(25)));
		
		g.setColor(Color.white);
		g.drawString(text, x + Window.scale(75), y+Window.scale(32)+g.getFontMetrics().getHeight()/2);
	}
	
	public void update(){
		if(isTriggerd){
			offset--;
			if(offset <= Window.getHeight() - 128)
				isTriggerd = false;
		}else{
			if(offset < Window.getHeight())
				offset++;
		}
	}
}
