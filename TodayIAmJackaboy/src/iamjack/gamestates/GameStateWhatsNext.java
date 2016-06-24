package iamjack.gamestates;

import java.awt.Graphics2D;

import iamjack.buttons.ButtonPick;
import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;;

public class GameStateWhatsNext extends GameState {

	private ButtonPick[] buttons;

	public GameStateWhatsNext(GameStateHandler gsh) {
		this.gsh = gsh;

		buttons = new ButtonPick[]{
				new ButtonPick("Exercise", Window.getWidth()/2, Window.getHeight()/2),
				new ButtonPick("Shop", Window.getWidth()/2, Window.getHeight()/2),
				new ButtonPick("End Day", Window.getWidth()/2, Window.getHeight()/2),
		};
	}

	@Override
	public void update(){
		for(ButtonPick b : buttons){
			b.update();
			if(MouseHandler.click && b.isLit())
				b.click(gsh);
		}
	}

	@Override
	public void draw(Graphics2D g) {

		for(ButtonPick b : buttons)
			b.draw(g);
	}
}
