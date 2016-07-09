package iamjack.gamestates.livingroom;

import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.resources.StreamMusic;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.player.achievements.AchievementLoader;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateLivingRoomEnd extends GameState {

	private Jack jack = new Jack();

	public GameStateLivingRoomEnd(GameStateHandler gsh) {
		super(gsh);

		jack.facingRight = false;
		jack.setPosX(Window.scale(800));

		StreamMusic.loopStream(Sounds.STREAM_ROOMMUSIC);
	}

	@Override
	public void update() {
		super.update();

		jack.update();

		if(!jack.isAnimated())
			if(jack.getPosX() > Window.scale(310) && jack.getPosX() < Window.scale(320))
				Achievement.trigger(AchievementLoader.seat);

		if(jack.getPosX() < -10){
			StreamMusic.endStream(Sounds.STREAM_ROOMMUSIC);
			if(!PlayerData.hasWorkedOut)
				PlayerData.hasWorkedOut = true;
			gsh.changeGameState(GameStateHandler.GAME_ENDDAY);
		}
	}

	@Override
	public void draw(Graphics2D g) {

		GameStateDrawHelper.drawLivingRoom(g,1);
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


		if(!jack.isAnimated()){
			if(jack.getPosX() > Window.scale(600) && jack.getPosX() < Window.scale(750))
				jack.say("This is a guy from Sonsor'Anarky. Drawn by me roommate !", g);

			if(jack.getPosX() > Window.scale(750))
				jack.say("Workout got me tired...  to bed !", g);
		}

		g.drawImage(Images.door, 
				0,
				Window.scale(160), 
				(int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		GameStateDrawHelper.drawBossCoinCounter(g);
		GameStateDrawHelper.drawBicepsCounter(g);
		
		super.draw(g);
	}
}
