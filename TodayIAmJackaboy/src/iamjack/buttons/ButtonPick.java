package iamjack.buttons;

import iamjack.engine.GameStateHandler;
import iamjack.engine.resources.StreamMusic;
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
