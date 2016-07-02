package iamjack.gamestates.outside;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.player.Jack;

public class EntityPopoff extends EntityPickUps {

	private int lifetime = 3*60;
	private int randSpud = rand.nextInt(2);
	
	public EntityPopoff(int index, int startX, int startY, double moveSpeed) {
		super(index, startX, startY, moveSpeed);

	}

	@Override
	public void update(Jack jack) {
		posY -= moveSpeed/1.5d;
		lifetime--;
	}

	public int getLifetime() {
		return lifetime;
	}
	
	@Override
	public void draw(Graphics2D g) {
		if(imgIndex == BILLY)
			g.setColor(Color.red.darker());
		else
			g.setColor(Color.yellow.darker());
		
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
		}
		return "error";
	}
}
