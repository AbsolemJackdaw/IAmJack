package iamjack.gamestates.outside;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import iamjack.engine.Window;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;

public class EntityPickUps {

	protected double posX, posY;
	protected int imgIndex;
	protected double moveSpeed;
	private Rectangle box;

	protected static final int BEER = 0;
	protected static final int CLOVER = 1;
	protected static final int SPUD = 2;
	protected static final int BILLY= 3;

	protected static final Random rand = new Random();
	private int billyTimer = 0;

	public EntityPickUps(int index, int startX, int startY, double moveSpeed) {

		imgIndex = index;
		posX = startX;
		posY = startY;
		this.moveSpeed = moveSpeed;
		box = new Rectangle(startX, startY, Window.scale(64), Window.scale(64));

	}

	public void update(Jack jack){

		posX -= moveSpeed;
		box.x = (int)posX;

		if(imgIndex == BILLY){
			billyTimer++;
			if(billyTimer % 2 == 0){
				if(posY  > jack.getPosY()+ Window.scale(70))
					posY--;
				else 
					posY++;
			}
		}
	}

	public void draw(Graphics2D g){
		g.drawImage(getImg(), (int)posX, (int)posY, Window.scale(64), Window.scale(64), null);
	}

	private BufferedImage getImg(){
		switch (imgIndex) {
		case BEER:
			return Images.beer;
		case CLOVER:
			return Images.clover;
		case SPUD:
			return Images.spud;
		case BILLY:
			return Images.billy;
		}
		return null;
	}

	public void pickUp(){
		switch (imgIndex) {
		case BEER: 
			PlayerData.fans += rand.nextInt(2) + 1;
			break;
		case CLOVER: 
			PlayerData.money += rand.nextInt(4) + 1; 
			break;
		case SPUD: 
			if(rand.nextInt(2) == 0)  
				PlayerData.fans += rand.nextInt(4) + 1;
			else 
				PlayerData.money += rand.nextInt(8) + 1; 
			break;
		case BILLY :
			PlayerData.money -= rand.nextInt(17)+3;
			PlayerData.fans -= rand.nextInt(17)+3;
			break;
		}
	}

	public Rectangle getBox() {
		return box;
	}

	public double getPosX() {
		return posX;
	}
}
