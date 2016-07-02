package iamjack.gamestates.outside;

import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.resources.Music;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateOutsideEnd extends GameState {

	private Jack jack = new Jack();

	public GameStateOutsideEnd(GameStateHandler gsh) {
		super(gsh);
		jack.setSportsing(true);
		jack.setPosX(Window.scale(750));
		jack.setPosY(Window.getHeight() / 2 - Window.scale(15));
		jack.facingRight = false;
	}

	@Override
	public void draw(Graphics2D g) {
		GameStateDrawHelper.drawExterior(g);
		super.draw(g);
		jack.draw(g);
		g.drawImage(Images.exteriorDoor, Window.scale(15), Window.scale(148), (int)(64*GameStateDrawHelper.scale),  (int)(64*GameStateDrawHelper.scale), null);
	}

	@Override
	public void update() {
		super.update();
		jack.update();

		if(jack.getPosX() < Window.scale(60)){
			Music.stop(Sounds.WORKOUT);
			gsh.changeGameState(GameStateHandler.GAME_ENDDAY);
		}
	}
}
