package iamjack.gamestates.outside;

import java.awt.Graphics2D;
import java.util.Random;

import framework.GameStateHandler;
import framework.input.MouseHandler;
import framework.resourceLoaders.StreamMusic;
import framework.window.Window;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.main.GameStateHandlerJack;
import iamjack.main.GameStateJack;
import iamjack.player.Jack;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateOutside extends GameStateJack {

	private Jack jack = new Jack();

	private boolean canSport;

	private String text[] = new String[]{
			"Beautiful day to work out !",
			"*sniffs hard* SMELLS LIKE IRELAND !",
			"I feel like planting spuds",
			"Athlone is beautiful this time of year !",
			"It's so hot outside, I wish it would rain again",
			"I could get me a beer on a day like this",
	};
	private Random rand = new Random();
	private int randTextIndex=0;

	/**boolean triggered after player has moved. allows for one time only intro logic*/
	private boolean once = false;

	public GameStateOutside(GameStateHandler gsh) {
		super(gsh);

		randTextIndex = rand.nextInt(text.length);

		jack.setSportsing(true);
		jack.setPosY(Window.getHeight() / 2 - Window.getGameScale(15));
		jack.setPosX(Window.getGameScale(125));
		StreamMusic.loopStream(Sounds.STREAM_WORKOUT);
	}

	@Override
	public void draw(Graphics2D g) {
		GameStateDrawHelper.drawExterior(g,0);
	
		GameStateDrawHelper.drawBossCoinCounter(g);
		GameStateDrawHelper.drawBicepsCounter(g);

		jack.draw(g);

		if(canSport)
			GameStateDrawHelper.drawBobbingImg(g, Images.bubbleArrow);

		if(once == false)
			jack.say(text[randTextIndex], g);
		super.draw(g);

	}

	@Override
	public void update() {
		super.update();

		if(jack.isAnimated() && !once)
			once = true;

		if(jack.getPosX() > Window.getGameScale(800))
			canSport = true;
		else
			canSport = false;

		if(canSport)
			GameStateDrawHelper.updateBobbingImage();

		if(canSport && MouseHandler.clicked != null && MouseHandler.click){
			double x = MouseHandler.clicked.getX();
			double y = MouseHandler.clicked.getY();

			if(x >= Window.getGameScale(850) && x <= Window.getGameScale(914) && y >= Window.getGameScale(180) && y <= Window.getGameScale(290)){
				//Music.stop(Sounds.ROOMMUSIC);
				gsh.changeGameState(GameStateHandlerJack.GAME_WORKOUT);
			}
		}

		jack.update();
	}
}
