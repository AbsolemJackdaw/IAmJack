package iamjack.buttons;

import framework.GameStateHandler;
import framework.resourceLoaders.StreamMusic;
import iamjack.main.GameStateHandlerJack;
import iamjack.resourceManager.Sounds;

public class ButtonMenu extends Button {

	public ButtonMenu(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void click(GameStateHandler gsh) {
		if(getName().equals("Start")){
			
			StreamMusic.endStream(Sounds.STREAM_QUEST);
			
			gsh.changeGameState(GameStateHandlerJack.GAME_ROOM);
		}else if(getName().equals("Achievements"))
			gsh.changeGameState(GameStateHandlerJack.ACHIEVS);
		else if(getName().equals("Exit"))
			System.exit(0);
	}

}
