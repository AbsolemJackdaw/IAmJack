package iamjack.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import framework.GameStateHandler;
import framework.window.Window;
import iamjack.buttons.ButtonPick;
import iamjack.gamestates.shop.ShopItems;
import iamjack.main.GameStateJack;
import iamjack.player.PlayerData;;

public class GameStateWhatsNext extends GameStateJack {

	private ButtonPick[] buttons;

	public GameStateWhatsNext(GameStateHandler gsh) {
		super(gsh);

		if(PlayerData.itemsBought.contains(ShopItems.workout) && PlayerData.daysPlayed > 1){
			buttons = new ButtonPick[]{
					new ButtonPick("Exercise", Window.getWidth()/2, Window.getHeight()/2 - Window.getGameScale(68*2)),
					new ButtonPick("Go Jog", Window.getWidth()/2, Window.getHeight()/2 - Window.getGameScale(68)),
					new ButtonPick("Shop", Window.getWidth()/2, Window.getHeight()/2),
					new ButtonPick("End Day", Window.getWidth()/2, Window.getHeight()/2 + Window.getGameScale(68)) ,
			};
		}
		
		else if(PlayerData.daysPlayed > 1){
			buttons = new ButtonPick[]{
					new ButtonPick("Exercise", Window.getWidth()/2, Window.getHeight()/2 - Window.getGameScale(68*2)),
					new ButtonPick("Shop", Window.getWidth()/2, Window.getHeight()/2 - Window.getGameScale(68)),
					new ButtonPick("End Day", Window.getWidth()/2, Window.getHeight()/2) ,
			};
		}
		else{
			buttons = new ButtonPick[]{
					new ButtonPick("Exercise", Window.getWidth()/2, Window.getHeight()/2 - Window.getGameScale(68)),
					new ButtonPick("End Day", Window.getWidth()/2, Window.getHeight()/2) ,
			};
		}
	}

	@Override
	public void update(){
		super.update();
		
		for(ButtonPick b : buttons)
			b.update(gsh);
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
	
		for(ButtonPick b : buttons)
			b.draw(g);
		
		super.draw(g);
	}
}