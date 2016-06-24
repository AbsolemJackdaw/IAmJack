package iamjack.gamestates.livingroom;

import java.awt.Graphics2D;
import java.util.Random;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateLivingRoomPlay extends GameState {

	private Jack jack = new Jack();

	private double speakTimer = 0;

	private Random rand = new Random();

	private String[] info = new String []{
			"To get buff, mash the mouse like a boss !",
			"You can do up to 5 reps a day.",
			"This will keep Jack healthy and fit.",
			"It will also give him more fans",
			"because being buff is boss as hell !",
			"",
			"",
			"",
			"Offcourse, you don't have to do reps.",
			"you can leave the living room before doing reps.",
			""
	};

	private int counter = 0;
	private int index = 0;

	public GameStateLivingRoomPlay(GameStateHandler gsh) {
		this.gsh = gsh;

		jack.setPosX(Window.scale(850));
		jack.setPosY(Window.scale(256));

		jack.setBenchPressing(true);
		
		Music.loop(Sounds.METALREPS);
	}

	@Override
	public void update() {

		if(jack.repsDone() >= 5 && jack.canPress()){
			Music.stop(Sounds.METALREPS);
			gsh.changeGameState(GameStateHandler.GAME_LIVING_END);
		}
		if(MouseHandler.clicked != null && MouseHandler.click){
			if(jack.canPress()){
				jack.pressArms();

				if(speakTimer <= 0){
					String song = Sounds.REPS+rand.nextInt(19);
					Music.play(song);

					int songlength = Music.getFrames(song);
					float secs = (float)songlength/Music.getFrameRate(song);
					double frames = ((double)secs) * 60d;
					speakTimer = Math.floor(frames); 
				}
			}
		}

		jack.update();

		if(!PlayerData.hasWorkedOut){
			counter++;
			if(index < info.length)
				if(counter % 180 == 0)
					index++;
		}

		if(speakTimer > 0)
			speakTimer --;
		
		for(Achievement a : Achievement.achievements.values())
			a.update();
	}

	@Override
	public void draw(Graphics2D g) {
		GameStateDrawHelper.drawLivingRoom(g);

		g.drawImage(Images.livingroomBenchPress,
				Window.scale(732),
				Window.scale(288),
				(int)(64f*GameStateDrawHelper.scale),(int)(32f*GameStateDrawHelper.scale), null);

		jack.draw(g);

		if(!PlayerData.hasWorkedOut && index < info.length-1)
			jack.say(info[index], g);
		
		for(Achievement a : Achievement.achievements.values())
			a.draw(g);
	}
}
