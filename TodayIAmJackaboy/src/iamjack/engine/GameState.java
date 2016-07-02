package iamjack.engine;

import java.awt.Graphics2D;

import iamjack.player.achievements.Achievement;

public abstract class GameState {

	public GameState(GameStateHandler gsh){this.gsh = gsh;};
	protected GameStateHandler gsh;
	
	public void draw (Graphics2D g){
		for(Achievement a : Achievement.achievements.values())
			a.draw(g);
	}
	
	public void update(){
		for(Achievement a : Achievement.achievements.values())
			a.update();
	}

}