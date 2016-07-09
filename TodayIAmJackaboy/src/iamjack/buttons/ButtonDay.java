package iamjack.buttons;

import iamjack.engine.GameStateHandler;
import iamjack.engine.resources.Music;
import iamjack.engine.resources.StreamMusic;
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
			gsh.changeGameState(GameStateHandler.GAME_ROOM);
		}else{
			PlayerData.money += payOut();
			PlayerData.fans += PlayerData.daysPlayed/5;
			PlayerData.daysPlayed++;
			SaveManager.writePlayerData();
			gsh.changeGameState(GameStateHandler.GAME_QUIT);
		}
	}
	
	private int payOut(){
		return (int)(20f+(float)PlayerData.fans/15f + ((2f+(float)PlayerData.fans/10f)*((float)PlayerData.daysPlayed/5f)));
	}
}
