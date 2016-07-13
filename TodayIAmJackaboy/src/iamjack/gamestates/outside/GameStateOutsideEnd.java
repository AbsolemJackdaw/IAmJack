package iamjack.gamestates.outside;

import java.awt.Graphics2D;

import framework.GameStateHandler;
import framework.resourceLoaders.StreamMusic;
import framework.window.Window;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.main.GameStateHandlerJack;
import iamjack.main.GameStateJack;
import iamjack.player.Jack;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateOutsideEnd extends GameStateJack {

	private Jack jack = new Jack();

	public GameStateOutsideEnd(GameStateHandler gsh) {
		super(gsh);
		jack.setSportsing(true);
		jack.setPosX(Window.getGameScale(750));
		jack.setPosY(Window.getHeight() / 2 - Window.getGameScale(15));
		jack.facingRight = false;
	}

	@Override
	public void draw(Graphics2D g) {
		GameStateDrawHelper.drawExterior(g,1);
		GameStateDrawHelper.drawBossCoinCounter(g);
		GameStateDrawHelper.drawBicepsCounter(g);

		jack.draw(g);
		g.drawImage(Images.exteriorDoor, Window.getGameScale(15), Window.getGameScale(148), (int)(64*GameStateDrawHelper.scale),  (int)(64*GameStateDrawHelper.scale), null);
		
		if(jack.getPosX() > Window.getGameScale(750))
			jack.say("Gosh I'm so tired right now !", g);
		
		super.draw(g);

	}

	@Override
	public void update() {
		super.update();
		jack.update();

		if(jack.getPosX() < Window.getGameScale(60)){
			StreamMusic.endStream(Sounds.STREAM_WORKOUT);
			gsh.changeGameState(GameStateHandlerJack.GAME_ENDDAY);
		}
	}
}
