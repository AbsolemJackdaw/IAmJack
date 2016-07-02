package iamjack.buttons;

import iamjack.engine.GameStateHandler;
import iamjack.engine.resources.Music;

public class ButtonExit extends Button {

	public ButtonExit(int x, int y) {
		super("Exit", x, y);
	}

	public void click(GameStateHandler gsh) {
		Music.stopAll();
		gsh.changeGameState(GameStateHandler.GAME_ENDDAY);
	}
}
