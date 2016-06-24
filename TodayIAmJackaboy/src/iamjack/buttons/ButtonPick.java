package iamjack.buttons;

import iamjack.engine.GameStateHandler;
import iamjack.engine.resources.Music;
import iamjack.resourceManager.Sounds;

public class ButtonPick extends Button{

	public ButtonPick(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void click(GameStateHandler gsh) {
		Music.stop(Sounds.EVERYWHERE);
		switch (getName()) {
		case "Exercise":gsh.changeGameState(GameStateHandler.GAME_LIVING);break;
		case "Shop": break;
		case "End Day":gsh.changeGameState(GameStateHandler.GAME_ENDDAY); break;

		default:
			break;
		}
	}
}
