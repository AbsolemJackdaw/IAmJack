package iamjack.buttons;

import framework.GameStateHandler;
import framework.resourceLoaders.StreamMusic;
import iamjack.main.GameStateHandlerJack;
import iamjack.resourceManager.SaveManager;
import iamjack.resourceManager.Sounds;

public class ButtonPick extends Button{

	public ButtonPick(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void click(GameStateHandler gsh) {
		StreamMusic.endStream(Sounds.STREAM_EVERYWHERE);
		
		switch (getName()) {
		case "Exercise":
			//augments money and day count when you exit the living room
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandlerJack.GAME_LIVING);break;
		case "Go Jog":
			gsh.changeGameState(GameStateHandlerJack.GAME_EXTERIOR);
			break;
		case "Shop": 
			gsh.changeGameState(GameStateHandlerJack.GAME_SHOP);
			break;
		case "End Day":
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandlerJack.GAME_ENDDAY);
			break;

		default:
			break;
		}
	}
}
