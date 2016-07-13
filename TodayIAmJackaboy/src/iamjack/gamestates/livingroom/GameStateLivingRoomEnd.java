package iamjack.gamestates.livingroom;

import java.awt.Graphics2D;

import framework.GameStateHandler;
import framework.resourceLoaders.StreamMusic;
import framework.window.Window;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.main.GameStateHandlerJack;
import iamjack.main.GameStateJack;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.player.achievements.AchievementLoader;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateLivingRoomEnd extends GameStateJack {

	private Jack jack = new Jack();

	public GameStateLivingRoomEnd(GameStateHandler gsh) {
		super(gsh);

		jack.facingRight = false;
		jack.setPosX(Window.getGameScale(800));

		StreamMusic.loopStream(Sounds.STREAM_ROOMMUSIC);
	}

	@Override
	public void update() {
		super.update();

		jack.update();

		if(!jack.isAnimated())
			if(jack.getPosX() > Window.getGameScale(310) && jack.getPosX() < Window.getGameScale(320))
				Achievement.trigger(AchievementLoader.seat);

		if(jack.getPosX() < -10){
			StreamMusic.endStream(Sounds.STREAM_ROOMMUSIC);
			if(!PlayerData.hasWorkedOut)
				PlayerData.hasWorkedOut = true;
			gsh.changeGameState(GameStateHandlerJack.GAME_ENDDAY);
		}
	}

	@Override
	public void draw(Graphics2D g) {

		GameStateDrawHelper.drawLivingRoom(g,1);
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


		if(!jack.isAnimated()){
			if(jack.getPosX() > Window.getGameScale(600) && jack.getPosX() < Window.getGameScale(750))
				jack.say("This is a guy from Sonsor'Anarky. Drawn by me roommate !", g);

			if(jack.getPosX() > Window.getGameScale(750))
				jack.say("Workout got me tired...  to bed !", g);
		}

		g.drawImage(Images.door, 
				0,
				Window.getGameScale(160), 
				(int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		GameStateDrawHelper.drawBossCoinCounter(g);
		GameStateDrawHelper.drawBicepsCounter(g);
		
		super.draw(g);
	}
}
