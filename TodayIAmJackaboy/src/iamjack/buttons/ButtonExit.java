package iamjack.buttons;

import iamjack.engine.GameStateHandler;

public class ButtonExit extends Button {

	public ButtonExit(int x, int y) {
		super("Exit", x, y);
	}

	public void click(GameStateHandler gsh) {
		gsh.changeGameState(GameStateHandler.GAME_ENDDAY);
	}
}
