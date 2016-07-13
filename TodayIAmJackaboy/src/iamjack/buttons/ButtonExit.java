package iamjack.buttons;

import framework.GameStateHandler;
import framework.resourceLoaders.Music;
import iamjack.main.GameStateHandlerJack;

public class ButtonExit extends Button {

	public ButtonExit(int x, int y) {
		super("Exit", x, y);
	}

	public void click(GameStateHandler gsh) {
		Music.stopAll();
		gsh.changeGameState(GameStateHandlerJack.GAME_ENDDAY);
	}
}
