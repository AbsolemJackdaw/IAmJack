package iamjack.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;

public class GameStateAchievements extends GameState {
	
	public GameStateAchievements(GameStateHandler gsh) {
		this.gsh = gsh;
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		int dex = 0;
		
		for(Achievement a : Achievement.achievements.values()){
			
			a.drawInGui(g, Window.scale(32), Window.scale(64) + Window.scale(64)*dex);
			
			dex ++;
		}
	}
	
	@Override
	public void update() {
		
		if(MouseHandler.click){
			PlayerData.dontDoubleLoop = true;
			gsh.changeGameState(GameStateHandler.MENU);
		}
	}
	
}
