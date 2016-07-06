package iamjack.buttons;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.gamestates.shop.ShopItems;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.player.achievements.AchievementLoader;
import iamjack.resourceManager.Images;

public class ButtonShop extends Button {

	private BufferedImage img = null;
	private int price = 0;

	public ButtonShop(BufferedImage item, int price, String name, int offset) {
		super(name, 64, 32*offset);

		img = item;
		this.price = price;

		ShopItems.items.add(this);
	}

	@Override
	public void draw(Graphics2D g) {

		g.setFont(text);

		int h = g.getFontMetrics().getHeight();
		g.drawImage(isLit && canBuy() ? Images.buttonLit : Images.button , (int)posX- Window.scale(64), (int)posY, Window.scale(128), Window.scale(128), null);

		g.drawImage(img, (int)posX - Window.scale(40), (int)posY+ Window.scale(20), Window.scale(64), Window.scale(64), null);

		g.setColor(isLit && canBuy() ? Color.green.darker() : canBuy() ? Color.WHITE : Color.red.darker());

		g.drawString(itemName(), 
				(int)posX + Window.scale(64),
				(int)posY + h + Window.scale(35) );
	}

	@Override
	public void click(GameStateHandler gsh){
		if(canBuy()){
			PlayerData.itemsBought.add(getName());
			PlayerData.money -= price;

			if(getName().equals(ShopItems.tank)){
				PlayerData.fans += 50;
			}
		}
	}

	public boolean canBuy(){
		boolean basic = PlayerData.money >= price && !PlayerData.itemsBought.contains(name);

		if(getName().equals(ShopItems.voices)){
			basic = basic &&
					PlayerData.achievements.contains(Achievement.achievements.get(AchievementLoader.erryday))&&
					PlayerData.achievements.contains(Achievement.achievements.get(AchievementLoader.happy)) &&
					PlayerData.achievements.contains(Achievement.achievements.get(AchievementLoader.scary)) ;
		}

		return basic;
	}

	public String itemName(){

		String s = "";

		if(PlayerData.itemsBought.contains(this.name))
			s = "Already Owned";

		else{
			s = this.name + ": " + price + " BC";

			if(!canBuy() && getName().equals(ShopItems.voices))
				s= "'Every Day Video, 'Smile !' and 'Scary Games required'";
		}
		return s;
	}
}
