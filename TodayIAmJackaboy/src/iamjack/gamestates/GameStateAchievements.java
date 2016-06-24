package iamjack.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

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
			Point p = new Point((int)MouseHandler.mouseX, (int)MouseHandler.mouseY);
			
			Rectangle r = new Rectangle(
					Window.scale(32), 
					(Window.scale(64) + Window.scale(70)*dex)+ MouseHandler.wheelY,
					Window.scale(64), Window.scale(64));
			
			if(r.contains(p))
				a.drawAid(g, Window.scale(107), (Window.scale(90) + Window.scale(70)*dex) + MouseHandler.wheelY);

			a.drawInGui(g, Window.scale(32), (Window.scale(64) + Window.scale(70)*dex) + MouseHandler.wheelY);
			dex ++;
		}
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
