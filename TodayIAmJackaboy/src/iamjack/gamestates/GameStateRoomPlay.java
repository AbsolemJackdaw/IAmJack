package iamjack.gamestates;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.MouseHandler;
import iamjack.engine.Window;
import iamjack.player.Jack;
import iamjack.resourceManager.Images;
import iamjack.video.ButtonGamePlay;

public class GameStateRoomPlay extends GameState {

	private int sitX ;
	private int sitY ;

	private Jack jack;

	private int stage = 0;

	private int[][] choices = new int[][]{
		{0},
		{1,2},
		{5,2,4},
		{2,3,6},
		{8,7,2},
		{2,4}
	};

	private String[] text = new String[]{
			"Play Game",
			"Intro",
			"Yell",
			"Be Funny",
			"Jack TM",
			"Be Nice",
			"Rage a Bit",
			"Energetic",
			"Be Scared"
	};

	private ButtonGamePlay[][] buttons = new ButtonGamePlay[6][3];

	public GameStateRoomPlay(GameStateHandler gsh) {
		this.gsh = gsh;

		jack = new Jack();
		sitX = Window.scale(820);
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
		
		jack.draw(g);

		g.drawImage(Images.chairLow, Window.scale(824), Window.scale(272), (int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		g.setComposite(AlphaComposite.SrcOver);
		
		g.drawImage(Images.roomShade, 
				Window.getWidth()/2 - (int)(GameStateDrawHelper.sizeX/2f),
				Window.getHeight()/2 - (int)(GameStateDrawHelper.sizeY/2f), 
				(int)GameStateDrawHelper.sizeX, (int)GameStateDrawHelper.sizeY, null);

		for(int i = 0; i < buttons[stage].length; i++){

			ButtonGamePlay b = buttons[stage][i];

			if(b != null){
				b.draw(g);
			}
		}
	}

	@Override
	public void update() {

		jack.update();

		for(ButtonGamePlay b : buttons[stage]){
			if(b != null)
				if(b.getBox().contains(MouseHandler.mouseX , MouseHandler.mouseY)){
					if(MouseHandler.click){
						b.click();
						stage++;
						if(!jack.isPlaying())
							jack.setPlaying(true);
					}
					b.isLit(true);
				}else{
					b.isLit(false);
				}
		}	

		if(stage >=	choices.length-1)
			gsh.changeGameState(GameStateHandler.GAME_DONEGAMING);
	}
}
