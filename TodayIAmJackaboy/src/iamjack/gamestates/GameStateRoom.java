package iamjack.gamestates;

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

		GameStateDrawHelper.drawRoom(g);
		
		jack.draw(g);

		if(canGame){
			g.drawImage(Images.game, 
					Window.scale(850),
					Window.scale(200) + (int)bobbing,
					Window.scale(64), Window.scale(64), null);
		}

		g.drawImage(Images.chair, Window.scale(824), Window.scale(260), (int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);
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
