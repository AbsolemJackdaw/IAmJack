package iamjack.gamestates;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.KeyHandler;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.player.Jack;
import iamjack.resourceManager.Images;

public class GameStateRoom extends GameState {

	private Jack jack = new Jack();

	private double bobCounter;
	private double bobbing;

	private boolean canGame;
	
	private float alpha = 1f;
	
	public GameStateRoom(GameStateHandler gsh) {
		this.gsh = gsh;

		Music.play("backgroundmusic");
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
		
		g.setColor(new Color(0f,0f,0f,alpha));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
	}

	@Override
	public void update() {
		
		if(alpha > 0)
			alpha -= 0.0025f;
		
		bobCounter += 0.025D;
		bobbing = Math.cos(bobCounter)*20;


		if(jack.getPosX() > Window.scale(725))
			canGame = true;
		else
			canGame = false;

		if(canGame && KeyHandler.isPressed(KeyHandler.ENTER)){
			Music.stop("backgroundmusic");
			gsh.changeGameState(GameStateHandler.GAME_GAMING);
		}
		
		//g.drawRect(850, 180, 64, 110);
		
		if(canGame && MouseHandler.clicked != null && MouseHandler.click){
			double x = MouseHandler.clicked.getX();
			double y = MouseHandler.clicked.getY();
			System.out.println(x + " " + y);
			
			if(x >= 850 && x <= 850+64 && y >= 180 && y <= 180+110){
				Music.stop("backgroundmusic");
				gsh.changeGameState(GameStateHandler.GAME_GAMING);
			}
		}
		jack.update();
	}
}
