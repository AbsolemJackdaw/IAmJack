package iamjack.gamestates.livingroom;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import framework.GameStateHandler;
import framework.input.MouseHandler;
import framework.resourceLoaders.Music;
import framework.resourceLoaders.StreamMusic;
import framework.window.Window;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.gamestates.outside.EntityPopoff;
import iamjack.main.GameStateHandlerJack;
import iamjack.main.GameStateJack;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateLivingRoomPlay extends GameStateJack {

	private Jack jack = new Jack();

	private double speakTimer = 0;

	private Random rand = new Random();

	private String[] info = new String []{
			"To get buff, mash the mouse like a boss !",
			"You can do a couple of reps a day.",
			"This will keep Jack healthy and fit.",
			"It will also give him more fans",
			"because being buff is boss as hell !",
			"The more you exercice, the more reps you can do"
	};

	private int counter = 0;
	private int index = 0;
	
	private int totalReps = 0;
	
	private int currentReps = 0;
	private int prevReps = 0;
	private boolean rep;
	private boolean prevRep;

	private ArrayList<EntityPopoff> popoffs = new ArrayList<EntityPopoff>();
	
	public GameStateLivingRoomPlay(GameStateHandler gsh) {
		super(gsh);

		jack.setPosX(Window.getGameScale(882));
		jack.setPosY(Window.getGameScale(256));

		jack.setBenchPressing(true);

		StreamMusic.loopStream(Sounds.STREAM_METALREPS);
		
		totalReps = (int) (5f + ((float)PlayerData.exercised / 10f)); //every 2 days of exercise, 1 rep is added
	}

	@Override
	public void update() {
		super.update();
		
		prevRep = rep;
		
		if(jack.canPress())
			rep = true;
		else
			rep = false;
		
		if(prevRep && rep == false)
			currentReps++;

		if(prevReps < currentReps){
			prevReps++;
			popoffs.add(new EntityPopoff(EntityPopoff.PLUS1BICEPS, (int)jack.getPosX(), (int)jack.getPosY()+ Window.getGameScale(64), 4d));
		}
		
		if(jack.repsDone() >= totalReps && jack.canPress()){
			PlayerData.exercised += totalReps;
			StreamMusic.endStream(Sounds.STREAM_METALREPS);
			gsh.changeGameState(GameStateHandlerJack.GAME_LIVING_END);
		}

		if(MouseHandler.clicked != null && MouseHandler.click){
			if(jack.canPress()){
				jack.pressArms();

				if(speakTimer <= 0){
					String song = Sounds.REPS+rand.nextInt(20);
					Music.play(song);

					int songlength = Music.getFrameLength(song);
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
		
		for(EntityPopoff pop : popoffs){
			pop.update(jack);
			if(pop.getLifetime() <= 0){
				popoffs.remove(pop);
				break;
			}
		}
	}

	@Override
	public void draw(Graphics2D g) {
		GameStateDrawHelper.drawLivingRoom(g,0);
		g.drawImage(Images.livingroomBenchPress,
				Window.getGameScale(732),
				Window.getGameScale(288),
				(int)(64f*GameStateDrawHelper.scale),(int)(32f*GameStateDrawHelper.scale), null);

		jack.draw(g);

		g.setComposite(AlphaComposite.SrcOver);

		g.drawImage(Images.livingRoomShade, 
				Window.getWidth()/2 - (int)(GameStateDrawHelper.sizeX/2f),
				Window.getHeight()/2 - (int)(GameStateDrawHelper.sizeY/2f), 
				(int)GameStateDrawHelper.sizeX, (int)GameStateDrawHelper.sizeY, null);

		if(!PlayerData.hasWorkedOut && index < info.length-1)
			jack.say(info[index], g);
		
		for(EntityPopoff pop : popoffs)
			pop.draw(g);
		
		super.draw(g);

	}
}
