package iamjack.buttons;

import iamjack.engine.GameStateHandler;
import iamjack.engine.resources.Music;
import iamjack.resourceManager.SaveManager;
import iamjack.resourceManager.Sounds;

public class ButtonPick extends Button{

	public ButtonPick(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void click(GameStateHandler gsh) {
		Music.stop(Sounds.EVERYWHERE);
		switch (getName()) {
		case "Exercise":
			//augments money and day count when you exit the living room
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandler.GAME_LIVING);break;
		case "Go Jog":
			gsh.changeGameState(GameStateHandler.GAME_EXTERIOR);
			break;
		case "Shop": 
			gsh.changeGameState(GameStateHandler.GAME_SHOP);
			break;
		case "End Day":
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandler.GAME_ENDDAY);
			break;

		default:
			break;
		}
	}
}
