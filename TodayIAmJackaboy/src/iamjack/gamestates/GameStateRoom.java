package iamjack.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.KeyHandler;
import iamjack.engine.Window;
import iamjack.player.Jack;
import iamjack.resourceManager.Images;

public class GameStateRoom extends GameState {

	private Jack jack = new Jack();

	private double bobCounter;
	private double bobbing;

	private boolean canGame;

	public GameStateRoom(GameStateHandler gsh) {
		this.gsh = gsh;

	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		float scale = (float)Window.getWidth() / (float)Images.room.getWidth();
		float sizeX = Images.room.getWidth() * scale;
		float sizeY = Images.room.getHeight() * scale;

		g.drawImage(Images.room, 
				Window.getWidth()/2 - (int)sizeX/2,
				Window.getHeight()/2 - (int)sizeY/2, 
				(int)sizeX, (int)sizeY, null);

		jack.draw(g);

		if(canGame){
			g.drawImage(Images.game, 
					Window.scale(850),
					Window.scale(200) + (int)bobbing,
					Window.scale(64), Window.scale(64), null);
		}

		g.drawImage(Images.chair, Window.scale(824), Window.scale(260), (int)(64f*scale), (int)(64f*scale), null);
	}

	@Override
	public void update() {

		bobCounter += 0.025D;
		bobbing = Math.cos(bobCounter)*20;

		jack.update();

		if(jack.getPosX() > Window.scale(725))
			canGame = true;
		else
			canGame = false;

		if(canGame && KeyHandler.isPressed(KeyHandler.ENTER))
			gsh.changeGameState(GameStateHandler.GAME_GAMING);
	}
}
