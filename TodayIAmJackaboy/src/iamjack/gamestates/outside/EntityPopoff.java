package iamjack.gamestates.outside;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.engine.Window;
import iamjack.player.Jack;

public class EntityPopoff extends EntityPickUps {

	private int lifetime = 4*60;
	private int randSpud = rand.nextInt(2);

	private static Font font = new Font("SquareFont", Font.PLAIN, Window.scale(35));

	public static final int PLUS1BICEPS = 4;

	public EntityPopoff(int index, int startX, int startY, double moveSpeed) {
		super(index, startX, startY, moveSpeed);
	}

	@Override
	public void update(Jack jack) {
		posY -= moveSpeed/1.5d;
		
		lifetime--;
		
		if(lifetime < 0)
			lifetime = 0;
	}

	public int getLifetime() {
		return lifetime;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setFont(font);

		int a1 = 85 + ((int)(256f*((float)lifetime / (4f*60f))));
		int alpha = 255;

		if(a1 < 255)
			alpha = a1;
		
		if(alpha < 0)
			alpha = 0;

		if(imgIndex == BILLY)
			g.setColor(new Color(
					Color.red.darker().getRed(), 
					Color.red.darker().getGreen(), 
					Color.red.darker().getBlue(), 
					(alpha)));
		else
			g.setColor(new Color(
					Color.yellow.darker().getRed(), 
					Color.yellow.darker().getGreen(), 
					Color.yellow.darker().getBlue(), 
					(alpha)));

		g.drawString(getString(), (int)posX, (int)posY);
	}

	private String getString(){

		switch (imgIndex) {
		case BEER:
			return "Fans +";
		case CLOVER:
			return "BossCoin +";
		case SPUD:
			if(randSpud == 0)
				return "Fans ++";
			else
				return "BossCoin ++";
		case BILLY :
			return"BossCoins and Fans ----";

		case PLUS1BICEPS :
			return "+1 biceps";
		}
		return "error";
	}
}
