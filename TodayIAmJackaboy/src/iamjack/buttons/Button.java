package iamjack.buttons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
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

		text = new Font("SquareFont", Font.PLAIN, Window.scale(35));

		box = new Rectangle((int)posX - Window.scale(64), (int)posY+Window.scale(64/2), Window.scale(128), Window.scale(64));
	}

	public void draw(Graphics2D g){

		g.setColor(isLit ? Color.green.darker() : Color.white);
		g.setFont(text);

		int h = g.getFontMetrics().getHeight();
		int w = g.getFontMetrics().stringWidth(name);

		g.drawImage(isLit ? Images.buttonLit : Images.button , (int)posX- Window.scale(64), (int)posY, Window.scale(128), Window.scale(128), null);

		g.drawString(name, 
				((int)posX - Window.scale(64)) - w/2 + Window.scale(64),
				(int)posY + h + Window.scale(34) );

	}

	public void update(){
		if(getBox().contains(MouseHandler.mouseX , MouseHandler.mouseY))
			setLit(true);
		else
			setLit(false);
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
