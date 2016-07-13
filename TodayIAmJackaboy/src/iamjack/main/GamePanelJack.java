package iamjack.main;

import framework.GamePanel;
import framework.GameStateHandler;

@SuppressWarnings("serial")
public class GamePanelJack extends GamePanel {

	public GamePanelJack() {
		super();
	}
	
	@Override
	public GameStateHandler getCustomGameStateHandler() {
		return new GameStateHandlerJack();
	}
}
