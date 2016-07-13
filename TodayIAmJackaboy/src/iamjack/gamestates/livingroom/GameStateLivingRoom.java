package iamjack.gamestates.livingroom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import framework.GameStateHandler;
import framework.input.KeyHandler;
import framework.input.MouseHandler;
import framework.resourceLoaders.StreamMusic;
import framework.window.Window;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.main.GameStateHandlerJack;
import iamjack.main.GameStateJack;
import iamjack.player.Jack;
import iamjack.player.achievements.Achievement;
import iamjack.player.achievements.AchievementLoader;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateLivingRoom extends GameStateJack {

	private Jack jack = new Jack();

	private boolean canWorkout;

	private float alpha;
	
	/**boolean triggered after player has moved. allows for one time only intro logic*/
	private boolean once = false;

	private String text[] = new String[]{
			"Oh boy ! Reps !",
			"can't wait to get buff !",
			"The dev made this to encourage me exercising !",
			"Let's get my pot-o-gold's pumpin' !",
			"Meáchain trom do mo gunnaí álainn !",
			"Buff Boss from Bossatron, Here I come",
			"Let's make my arms the second huge thing about me",
	};
	private Random rand = new Random();
	private int randTextIndex=0;
	
	public GameStateLivingRoom(GameStateHandler gsh) {
		super(gsh);

		randTextIndex = rand.nextInt(text.length);

		alpha = 1f;

		StreamMusic.loopStream(Sounds.STREAM_ROOMMUSIC);

	}

	@Override
	public void update() {
		super.update();
		
		GameStateDrawHelper.updateBobbingImage();

		if(jack.isAnimated() && !once)
			once = true;
		
		if(jack.getPosX() > Window.getGameScale(750))
			canWorkout = true;
		else
			canWorkout = false;

		if(canWorkout && KeyHandler.isPressed(KeyHandler.ENTER)){
			gsh.changeGameState(GameStateHandlerJack.GAME_LIVING_BENCHGAME);
		}

		if(canWorkout && MouseHandler.clicked != null && MouseHandler.click){
			double x = MouseHandler.clicked.getX();
			double y = MouseHandler.clicked.getY();

			if(x >= Window.getGameScale(850) && x <= Window.getGameScale(914) && y >= Window.getGameScale(180) && y <= Window.getGameScale(290)){
				StreamMusic.endStream(Sounds.STREAM_ROOMMUSIC);
				gsh.changeGameState(GameStateHandlerJack.GAME_LIVING_BENCHGAME);
			}
		}

		if(jack.getPosX() < -10){
			StreamMusic.endStream(Sounds.STREAM_ROOMMUSIC);
			gsh.changeGameState(GameStateHandlerJack.GAME_ENDDAY);
		}

		jack.update();

		if(!jack.isAnimated())	
			if(jack.getPosX() > Window.getGameScale(310) && jack.getPosX() < Window.getGameScale(320))
				Achievement.trigger(AchievementLoader.seat);

		if(alpha > 0)
			alpha -= 0.0025f;

	}

	@Override
	public void draw(Graphics2D g) {
		
		GameStateDrawHelper.drawLivingRoom(g,0);
		
		if(canWorkout)
			GameStateDrawHelper.drawBobbingImg(g, Images.bubbleWorkout);

		jack.draw(g);

		g.drawImage(Images.livingroomChair,
				Window.getGameScale(280),
				Window.getGameScale(160),
				(int)(64f*GameStateDrawHelper.scale),(int)(64f*GameStateDrawHelper.scale), null);

		g.drawImage(Images.livingroomBenchPress,
				Window.getGameScale(732),
				Window.getGameScale(288),
				(int)(64f*GameStateDrawHelper.scale),(int)(32f*GameStateDrawHelper.scale), null);
		g.drawImage(Images.livingroomBenchPressWeight,
				Window.getGameScale(732),
				Window.getGameScale(288),
				(int)(64f*GameStateDrawHelper.scale),(int)(32f*GameStateDrawHelper.scale), null);

		g.drawImage(Images.door, 
				0,
				Window.getGameScale(160), 
				(int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		if(!jack.isAnimated()){
			if(jack.getPosX() > Window.getGameScale(600) && jack.getPosX() < Window.getGameScale(750))
				jack.say("This is a guy from Sonsor'Anarky. Drawn by me roommate !", g);
			if(!once)
				jack.say(text[randTextIndex], g);
		}
		
		GameStateDrawHelper.drawBossCoinCounter(g);
		GameStateDrawHelper.drawBicepsCounter(g);
		
		g.setColor(new Color(0f,0f,0f,alpha));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		super.draw(g);
	}
}
