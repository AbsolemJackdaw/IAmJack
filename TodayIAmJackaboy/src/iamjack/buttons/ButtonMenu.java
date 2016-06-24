package iamjack.buttons;

import iamjack.engine.GameStateHandler;
import iamjack.engine.resources.Music;
import iamjack.resourceManager.Sounds;

public class ButtonMenu extends Button {

	public ButtonMenu(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void click(GameStateHandler gsh) {
		if(getName().equals("Start")){
			Music.stop(Sounds.QUEST);
			gsh.changeGameState(GameStateHandler.GAME_ROOM);
		}else if(getName().equals("Achievements"))
			gsh.changeGameState(GameStateHandler.ACHIEVS);
		else if(getName().equals("Exit"))
			System.exit(0);
	}

}
