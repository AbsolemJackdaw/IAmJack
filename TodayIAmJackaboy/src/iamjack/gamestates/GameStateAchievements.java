package iamjack.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.player.PlayerData;

public class GameStateAchievements extends GameState {

	public GameStateAchievements(GameStateHandler gsh) {
		super(gsh);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		GameStateDrawHelper.drawAchievements(g);
	}

	@Override
	public void update() {

		if(MouseHandler.click){
			PlayerData.dontDoubleLoop = true;
			MouseHandler.wheelY = 0;
			gsh.changeGameState(GameStateHandler.MENU);
		}
	}
}
