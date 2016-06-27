package iamjack.buttons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.gamestates.shop.ShopItems;
import iamjack.player.PlayerData;

public class ButtonShop extends Button {

	private BufferedImage img = null;
	private int price = 0;

	public ButtonShop(BufferedImage item, int price, String name, int x, int y) {
		super(name, x, y);

		img = item;
		this.price = price;
		
		ShopItems.items.add(this);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(isLit ? Color.green.darker() : Color.white);
		g.setFont(text);

		int h = g.getFontMetrics().getHeight();
		int w = g.getFontMetrics().stringWidth(name);
		
		g.drawImage(img, (int)posX- Window.scale(64), (int)posY+ Window.scale(32), Window.scale(64), Window.scale(64), null);
		
		g.setColor(canBuy() ? Color.WHITE : Color.red.darker().darker());
		g.drawString(name + ": " + price + " BC", 
				((int)posX),
				(int)posY + h + Window.scale(34) );
		
		g.draw(getBox());

	}

	@Override
	public void click(GameStateHandler gsh){
		if(canBuy()){
			PlayerData.itemsBought.add(getName());
			PlayerData.money -= price;
		}
	}
	
	public boolean canBuy(){
		return PlayerData.money >= price && !PlayerData.itemsBought.contains(name);
	}
}
