package iamjack.gamestates.livingroom;

import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;

public class GameStateLivingRoomPlay extends GameState {

	private Jack jack = new Jack();

	private String[] info = new String []{
			"To get buff, mash the mouse like a boss !",
			"You can do up to 5 reps a day.",
			"This will keep Jack healthy and fit.",
			"It will also give him more fans",
			"because being buff is boss as hell !",
	};

	private int counter = 0;
	private int index = 0;

	public GameStateLivingRoomPlay(GameStateHandler gsh) {
		this.gsh = gsh;

		jack.setPosX(850);
		jack.setPosY(256);

		jack.setBenchPressing(true);
	}

	@Override
	public void update() {

		if(jack.repsDone() >= 5 && jack.canPress()){
			gsh.changeGameState(GameStateHandler.GAME_LIVING_END);
		}
		
		if(MouseHandler.clicked != null && MouseHandler.click){
			if(jack.canPress())
				jack.pressArms();
		}

		jack.update();

		if(!PlayerData.hasWorkedOut){
			counter++;
			if(index < info.length)
				if(counter % 180 == 0)
					index++;
		}
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
	}
}
