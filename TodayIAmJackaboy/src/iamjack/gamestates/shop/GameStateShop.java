package iamjack.gamestates.shop;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.buttons.ButtonExit;
import iamjack.buttons.ButtonShop;
import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;

public class GameStateShop extends GameState {

	private ButtonShop[] buttons;

	private ButtonExit exit = new ButtonExit(Window.getWidth() - Window.scale(75), 0 );

	public GameStateShop(GameStateHandler gsh) {
		super(gsh);
		this.gsh = gsh;
		
		buttons = new ButtonShop[ShopItems.items.size()];
		
		for(int i = 0 ; i < ShopItems.items.size(); i++)
		buttons[i] = ShopItems.items.get(i);
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.setColor(Color.green.darker());
		g.drawString("Graphicsless Shop", Window.getWidth()/2 - g.getFontMetrics().stringWidth("Graphicsless Shop")/2, Window.scale(64));

		for(ButtonShop b : buttons)
			b.draw(g);

		exit.draw(g);
	}

	@Override
	public void update() {
		for(ButtonShop b : buttons)
			b.update();

		exit.update();
	}
}
