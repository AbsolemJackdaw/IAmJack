package iamjack.player;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import iamjack.engine.KeyHandler;
import iamjack.engine.Window;
import iamjack.resourceManager.Images;

public class Jack {

	private double posX;
	private double posY;

	public boolean facingRight = true;
	private boolean animated = false;

	private int counter = 0;
	private int animIndexWalking = 0;
	private int animIndexKeyboard = 0;

	private boolean isSitting;
	private boolean isPlaying;

	private BufferedImage[] animation ;
	private BufferedImage[] animationPlaying ;

	private double speed = Window.scale(5D);

	public Jack() {

		posX = Window.scale(50);
		posY = Window.getHeight() / 2;

		animation = new BufferedImage[]{
				Images.jack,
				Images.jackWalk[0],
				Images.jack,
				Images.jackWalk[1]
		};

		animationPlaying = new BufferedImage[]{
				Images.jackKeyBoard[0],
				Images.jackKeyBoard[1],
		};
	}

	public void draw(Graphics2D g){

		if(isSitting)
			if(isPlaying)
				g.drawImage(animationPlaying[animIndexKeyboard], (int)posX, (int)posY, Window.scale(128), Window.scale(128), null);
			else
				g.drawImage(Images.jackSit, (int)posX, (int)posY, Window.scale(128), Window.scale(128), null);
		else
			if(facingRight)
				if(animated)
					g.drawImage(animation[animIndexWalking], (int)posX, (int)posY, Window.scale(128), Window.scale(128), null);
				else
					g.drawImage(Images.jack, (int)posX, (int)posY, Window.scale(128), Window.scale(128), null);
			else
				if(animated)
					g.drawImage(animation[animIndexWalking], (int)posX+Window.scale(128), (int)posY, -Window.scale(128), Window.scale(128), null);
				else
					g.drawImage(Images.jack, (int)posX+Window.scale(128), (int)posY, -Window.scale(128), Window.scale(128), null);



	}

	public void update(){

		if(!isSitting)
			doMovement();

		if(animated){
			counter++;
		}else{
			counter = 0;
		}

		if(counter % 2 == 0){
			animIndexWalking++;

			if(animIndexWalking >= animation.length -1)
				animIndexWalking = 0;
		}

		if(counter % 5 == 0){
			animIndexKeyboard++;

			if(animIndexKeyboard >= animationPlaying.length -1)
				animIndexKeyboard = 0;
		}

	}

	private void doMovement(){
		if(KeyHandler.keyState[KeyHandler.RIGHT] && posX < Window.getWidth() - Window.scale(200)){
			posX += speed;
			facingRight = true;
			animated = true;
		}

		if(KeyHandler.keyState[KeyHandler.LEFT] && posX > - 100){
			posX -= speed;
			facingRight = false;
			animated = true;
		}

		if(!KeyHandler.keyState[KeyHandler.RIGHT] && !KeyHandler.keyState[KeyHandler.LEFT]){
			animated = false;
		}
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public void setSitting(boolean isSitting) {
		this.isSitting = isSitting;
	}

	public boolean isSitting() {
		return isSitting;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
}
