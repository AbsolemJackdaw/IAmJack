package iamjack.video;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import iamjack.engine.Window;
import iamjack.resourceManager.Images;

public class Button {

	protected double posX;
	protected double posY;
	protected final String name;

	private Font text;

	private Rectangle box;

	private boolean isLit;

	public Button(String name, int x, int y) {
		this.name = name;
		posX=x;
		posY=y;

		text = new Font("SquareFont", Font.PLAIN, Window.scale(35));

		box = new Rectangle(x, y+Window.scale(64/2), Window.scale(128), Window.scale(64));
	}

	public void draw(Graphics2D g){
		
		g.setColor(Color.white);
		g.setFont(text);

		int h = g.getFontMetrics().getHeight();
		int w = g.getFontMetrics().stringWidth(name);

		g.drawImage(isLit ? Images.buttonLit : Images.button , (int)posX, (int)posY, Window.scale(128), Window.scale(128), null);

//		g.draw(box);
		
		g.drawString(name, 
				(int)posX - w/2 + Window.scale(64),
				(int)posY + h + Window.scale(34) );

	}

	public void click(){
	}

	public Rectangle getBox() {
		return box;
	}

	public void isLit(boolean b){
		isLit = b;
	}

	public String getName() {
		return name;
	}
}
