package iamjack.gamestates.shop;

import java.awt.Color;
import java.awt.Graphics2D;

import framework.GameStateHandler;
import framework.resourceLoaders.StreamMusic;
import framework.window.Window;
import iamjack.buttons.ButtonExit;
import iamjack.buttons.ButtonShop;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.main.GameStateJack;
import iamjack.resourceManager.Sounds;

public class GameStateShop extends GameStateJack {

	private ButtonShop[] buttons;

	private ButtonExit exit = new ButtonExit(Window.getWidth() - Window.getGameScale(75), Window.getGameScale(25) );

	public GameStateShop(GameStateHandler gsh) {
		super(gsh);
		this.gsh = gsh;
		
		buttons = new ButtonShop[ShopItems.items.size()];
		
		for(int i = 0 ; i < ShopItems.items.size(); i++)
		buttons[i] = ShopItems.items.get(i);
		
		StreamMusic.loopStream(Sounds.STREAM_SHOP);
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.setColor(Color.green.darker());
		g.drawString("Graphicsless Shop", Window.getWidth()/2 - g.getFontMetrics().stringWidth("Graphicsless Shop")/2, Window.getGameScale(64));

		for(ButtonShop b : buttons)
			b.draw(g);

		exit.draw(g);
		
		GameStateDrawHelper.drawBossCoinCounter(g);
		
		super.draw(g);

	}

	@Override
	public void update() {
		super.update();
		
		for(ButtonShop b : buttons)
			b.update(gsh);

		exit.update(gsh);

	}
}
