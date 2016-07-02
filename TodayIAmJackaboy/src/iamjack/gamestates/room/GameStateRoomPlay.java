package iamjack.gamestates.room;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import iamjack.buttons.ButtonGamePlay;
import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateRoomPlay extends GameState {

	private int sitX ;
	private int sitY ;

	private Jack jack;

	private int stage = 0;

	private boolean buttonClicked = false;

	private double speakTimer = 0;

	private int[][] choices = new int[][]{
		{0},
		{1,2},
		{3,5,2},
		{7,2,8},
		{2,6,5},
		{4,3,2},
		{5,2,7},
		{2,6,8},
		{4,3,2},
		{9}
	};

	private String[] text = new String[]{
			"Play Game",
			"Intro",
			"Yell",
			"Be Funny",
			"Jack TM",
			"Laugh",
			"Rage",
			"Energetic",
			"Be Scared",
			"Outro"
	};

	private ButtonGamePlay[][] buttons = new ButtonGamePlay[10][3];

	public GameStateRoomPlay(GameStateHandler gsh) {
		super(gsh);
		
		Music.loop(Sounds.METAL);

		jack = new Jack();
		sitX = Window.scale(852);
		sitY = Window.scale(240);

		jack.setPosX(sitX);
		jack.setPosY(sitY);

		jack.setSitting(true);
		jack.setAnimated(true);

		for(int j = 0; j < choices.length; j++)
			for(int i = 0; i < choices[j].length; i++){
				buttons[j][i] = new ButtonGamePlay(
						text[choices[j][i]],
						Window.scale(550),
						Window.scale(150) + (Window.scale(75) * i));
			}
	}

	@Override
	public void draw(Graphics2D g) {

		GameStateDrawHelper.drawRoom(g);
		super.draw(g);
		
		jack.draw(g);

		g.drawImage(Images.chairLow, Window.scale(824), Window.scale(272), (int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		g.setComposite(AlphaComposite.SrcOver);

		g.drawImage(Images.roomShade, 
				Window.getWidth()/2 - (int)(GameStateDrawHelper.sizeX/2f),
				Window.getHeight()/2 - (int)(GameStateDrawHelper.sizeY/2f), 
				(int)GameStateDrawHelper.sizeX, (int)GameStateDrawHelper.sizeY, null);

		if(stage < buttons.length)
			for(int i = 0; i < buttons[stage].length; i++){

				ButtonGamePlay b = buttons[stage][i];

				if(b != null)
					b.draw(g);
				
			}
	}

	@Override
	public void update() {
		super.update();
		
		jack.update();

		if(speakTimer <= 0 && stage < buttons.length)
			for(ButtonGamePlay b : buttons[stage]){
				if(b!=null){
					b.update(gsh);
					if(MouseHandler.click && b.isLit()){
						buttonClicked = true;
						stage++;
						//sets jack mashing his keyboard after first button is clicked
						if(!jack.isPlaying())
							jack.setPlaying(true);
					}
				}
			}	

		if(buttonClicked){
			buttonClicked = false;
			if(PlayerData.currentlySaying.length() > 0){
				int songlength = Music.getFrames(PlayerData.currentlySaying);
				float secs = (float)songlength/Music.getFrameRate(PlayerData.currentlySaying);
				double frames = ((double)secs) * 60d;
				speakTimer = Math.floor(frames); 
				jack.setTalking(true);
			}
		}

		if(speakTimer > 0)
			speakTimer --;
		else
			jack.setTalking(false);

		if(stage ==	choices.length && speakTimer <= 120d)
			Music.stop(Sounds.METAL);
		if(stage >=	choices.length && speakTimer <= 0d)
			gsh.changeGameState(GameStateHandler.GAME_ROOM_VIDEO_DONE);
	}
}
