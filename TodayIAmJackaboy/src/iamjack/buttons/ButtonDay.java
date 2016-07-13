package iamjack.buttons;

import framework.GameStateHandler;
import framework.resourceLoaders.Music;
import framework.resourceLoaders.StreamMusic;
import iamjack.main.GameStateHandlerJack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.SaveManager;

public class ButtonDay extends Button {

	public ButtonDay(String name, int x, int y) {
		super(name, x, y);
	}

	@Override
	public void click(GameStateHandler gsh) {
		Music.stopAll();
		StreamMusic.stopAll();
		
		if(getName().equals("Next Day")){
			PlayerData.money += payOut();
			PlayerData.fans += PlayerData.daysPlayed/5;
			PlayerData.daysPlayed++;
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandlerJack.GAME_ROOM);
		}else{
			PlayerData.money += payOut();
			PlayerData.fans += PlayerData.daysPlayed/5;
			PlayerData.daysPlayed++;
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandlerJack.GAME_QUIT);
		}
	}
	
	private int payOut(){
		return (int)(20f+(float)PlayerData.fans/15f + ((2f+(float)PlayerData.fans/10f)*((float)PlayerData.daysPlayed/5f)));
	}
}
