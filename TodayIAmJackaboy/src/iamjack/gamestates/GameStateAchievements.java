package iamjack.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import framework.GameStateHandler;
import framework.input.MouseHandler;
import framework.window.Window;
import iamjack.main.GameStateHandlerJack;
import iamjack.main.GameStateJack;
import iamjack.player.PlayerData;

public class GameStateAchievements extends GameStateJack {

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
			gsh.changeGameState(GameStateHandlerJack.MENU);
		}
	}
}
