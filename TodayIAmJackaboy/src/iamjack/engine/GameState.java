package iamjack.engine;

import java.awt.Graphics2D;

import iamjack.engine.input.KeyHandler;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.achievements.Achievement;

public abstract class GameState {

	public GameState(GameStateHandler gsh){
		System.out.println("initiating a game state");
		this.gsh = gsh;
		};
		
	protected GameStateHandler gsh;

	public void draw (Graphics2D g){
		for(Achievement a : Achievement.achievements.values())
			a.draw(g);
		
		if(KeyHandler.isHeld(KeyHandler.ESCAPE))
			GameStateDrawHelper.drawMenu(g);
	}

	public void update(){
		for(Achievement a : Achievement.achievements.values())
			a.update();
		
		if(KeyHandler.isHeld(KeyHandler.ESCAPE))
			GameStateDrawHelper.updateMenu(gsh);
	}

}