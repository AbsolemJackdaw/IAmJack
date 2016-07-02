package iamjack.gamestates.livingroom;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.KeyHandler;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateLivingRoom extends GameState {

	private Jack jack = new Jack();

	private boolean canWorkout;

	private float alpha;
	
	/**boolean triggered after player has moved. allows for one time only intro logic*/
	private boolean once = false;

	public GameStateLivingRoom(GameStateHandler gsh) {
		super(gsh);

		alpha = 1f;

		Music.loop(Sounds.ROOMMUSIC);
	}

	@Override
	public void update() {
		super.update();
		
		GameStateDrawHelper.updateBobbingImage();

		if(jack.isAnimated() && !once)
			once = true;
		
		if(jack.getPosX() > Window.scale(750))
			canWorkout = true;
		else
			canWorkout = false;

		if(canWorkout && KeyHandler.isPressed(KeyHandler.ENTER)){
			gsh.changeGameState(GameStateHandler.GAME_LIVING_BENCHGAME);
		}

		if(canWorkout && MouseHandler.clicked != null && MouseHandler.click){
			double x = MouseHandler.clicked.getX();
			double y = MouseHandler.clicked.getY();

			if(x >= Window.scale(850) && x <= Window.scale(914) && y >= Window.scale(180) && y <= Window.scale(290)){
				gsh.changeGameState(GameStateHandler.GAME_LIVING_BENCHGAME);
				Music.stop(Sounds.ROOMMUSIC);
			}
		}

		if(jack.getPosX() < -10){
			Music.stop(Sounds.ROOMMUSIC);
			gsh.changeGameState(GameStateHandler.GAME_ENDDAY);
		}

		jack.update();

		if(!jack.isAnimated())	
			if(jack.getPosX() > Window.scale(310) && jack.getPosX() < Window.scale(320))
				Achievement.trigger("seat");

		if(alpha > 0)
			alpha -= 0.0025f;

	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
		
		GameStateDrawHelper.drawLivingRoom(g);

		if(canWorkout)
			GameStateDrawHelper.drawBobbingImg(g, Images.bubbleWorkout);

		jack.draw(g);

		g.drawImage(Images.livingroomChair,
				Window.scale(280),
				Window.scale(160),
				(int)(64f*GameStateDrawHelper.scale),(int)(64f*GameStateDrawHelper.scale), null);

		g.drawImage(Images.livingroomBenchPress,
				Window.scale(732),
				Window.scale(288),
				(int)(64f*GameStateDrawHelper.scale),(int)(32f*GameStateDrawHelper.scale), null);
		g.drawImage(Images.livingroomBenchPressWeight,
				Window.scale(732),
				Window.scale(288),
				(int)(64f*GameStateDrawHelper.scale),(int)(32f*GameStateDrawHelper.scale), null);

		g.drawImage(Images.door, 
				0,
				Window.scale(160), 
				(int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		if(!jack.isAnimated()){
			if(jack.getPosX() > Window.scale(600) && jack.getPosX() < Window.scale(750))
				jack.say("This is a guy from Sonsor'Anarky. Drawn by me roommate !", g);
			if(!once && PlayerData.daysPlayed < 1 && jack.getPosX() < Window.scale(75) )
				jack.say("Oh boy, workout !", g);
		}
		
		GameStateDrawHelper.drawBossCoinCounter(g);

		g.setColor(new Color(0f,0f,0f,alpha));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
	}
}
