package iamjack.gamestates.outside;

import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateOutside extends GameState {

	private Jack jack = new Jack();

	private boolean canSport;

	public GameStateOutside(GameStateHandler gsh) {
		super(gsh);
		
		jack.setSportsing(true);
		jack.setPosY(Window.getHeight() / 2 - Window.scale(15));
		jack.setPosX(Window.scale(125));
		Music.loop(Sounds.WORKOUT);
	}

	@Override
	public void draw(Graphics2D g) {
		GameStateDrawHelper.drawExterior(g);
		super.draw(g);

		jack.draw(g);

		if(canSport)
			GameStateDrawHelper.drawBobbingImg(g, Images.bubbleArrow);
	}

	@Override
	public void update() {
		super.update();

		if(jack.getPosX() > Window.scale(800))
			canSport = true;
		else
			canSport = false;

		if(canSport)
			GameStateDrawHelper.updateBobbingImage();
		
		if(canSport && MouseHandler.clicked != null && MouseHandler.click){
			double x = MouseHandler.clicked.getX();
			double y = MouseHandler.clicked.getY();
			
			if(x >= Window.scale(850) && x <= Window.scale(914) && y >= Window.scale(180) && y <= Window.scale(290)){
				//Music.stop(Sounds.ROOMMUSIC);
				gsh.changeGameState(GameStateHandler.GAME_WORKOUT);
			}
		}

		jack.update();
	}
}
