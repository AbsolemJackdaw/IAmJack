package iamjack.buttons;

import iamjack.engine.GameStateHandler;
import iamjack.engine.resources.Music;
import iamjack.player.PlayerData;
import iamjack.resourceManager.SaveManager;
import iamjack.resourceManager.Sounds;

public class ButtonDay extends Button {

	public ButtonDay(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void click(GameStateHandler gsh) {
		Music.stop(Sounds.EVERYWHERE);
		
		if(getName().equals("Next Day")){
			PlayerData.money += 20+PlayerData.fans/4 + ((2+PlayerData.fans/2)*PlayerData.daysPlayed);
			PlayerData.daysPlayed++;
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandler.GAME_ROOM);
		}else{
			PlayerData.money += 20+PlayerData.fans/4 + ((2+PlayerData.fans/2)*PlayerData.daysPlayed);
			PlayerData.daysPlayed++;
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandler.GAME_QUIT);
		}
	}
}