package iamjack.gamestates;

import java.awt.Color;
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
				new ButtonPick("Exercise", Window.getWidth()/2, Window.getHeight()/2 - Window.scale(68)),
//				new ButtonPick("Shop", Window.getWidth()/2, Window.getHeight()/2 - Window.scale(68)),
				new ButtonPick("End Day", Window.getWidth()/2, Window.getHeight()/2) ,
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

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		for(ButtonPick b : buttons)
			b.draw(g);
	}
}
