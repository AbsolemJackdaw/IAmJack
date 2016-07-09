package iamjack.gamestates.outside;

import java.awt.Graphics2D;
import java.util.Random;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.StreamMusic;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateOutside extends GameState {

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
		jack.setPosY(Window.getHeight() / 2 - Window.scale(15));
		jack.setPosX(Window.scale(125));
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
