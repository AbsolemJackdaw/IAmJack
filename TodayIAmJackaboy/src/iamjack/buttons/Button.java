package iamjack.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import framework.GameStateHandler;
import framework.input.MouseHandler;
import framework.window.Window;
import iamjack.resourceManager.Images;

public class Button {

	protected double posX;
	protected double posY;
	protected final String name;

	protected Font text;

	private Rectangle box;

	protected boolean isLit;

	public Button(String name, int x, int y) {
		this.name = name;
		posX=x;
		posY=y;

		text = new Font("SquareFont", Font.PLAIN, Window.getGameScale(35));

		box = new Rectangle((int)posX - Window.getGameScale(64), (int)posY+Window.getGameScale(64/2), Window.getGameScale(128), Window.getGameScale(64));
	}

	public void draw(Graphics2D g){

		g.setColor(isLit ? Color.green.darker() : Color.white);
		g.setFont(text);

		int h = g.getFontMetrics().getHeight();
		int w = g.getFontMetrics().stringWidth(name);

		g.drawImage(isLit ? Images.buttonLit : Images.button , (int)posX- Window.getGameScale(64), (int)posY, Window.getGameScale(128), Window.getGameScale(128), null);

		g.drawString(name, 
				((int)posX - Window.getGameScale(64)) - w/2 + Window.getGameScale(64),
				(int)posY + h + Window.getGameScale(34) );

	}

	public void update(GameStateHandler gsh){
		if(getBox().contains(MouseHandler.mouseX , MouseHandler.mouseY))
			setLit(true);
		else
			setLit(false);
		
		if(MouseHandler.click && isLit())
			click(gsh);
	}

	public void click(GameStateHandler gsh){};

	public Rectangle getBox() {
		return box;
	}

	public void setLit(boolean b){
		isLit = b;
	}
	
	public boolean isLit() {
		return isLit;
	}

	public String getName() {
		return name;
	}
}
