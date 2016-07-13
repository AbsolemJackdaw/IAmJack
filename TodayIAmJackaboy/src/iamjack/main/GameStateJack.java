package iamjack.main;

import java.awt.Graphics2D;

import framework.GameState;
import framework.GameStateHandler;
import framework.input.KeyHandler;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.achievements.Achievement;

public class GameStateJack extends GameState{

	public GameStateJack(GameStateHandler gsh) {
		super(gsh);
	}
	
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
