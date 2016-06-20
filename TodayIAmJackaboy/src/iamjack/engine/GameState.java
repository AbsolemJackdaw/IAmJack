package iamjack.engine;

import java.awt.Graphics2D;

public abstract class GameState {

	protected GameStateHandler gsh;
	public void draw (Graphics2D g){}
	public void update(){}

}