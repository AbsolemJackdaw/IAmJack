package iamjack.gamestates.livingroom;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.resources.Music;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.resourceManager.Images;

public class GameStateLivingRoomEnd extends GameState {

	private Jack jack = new Jack();

	public GameStateLivingRoomEnd(GameStateHandler gsh) {
		this.gsh = gsh;
		
		jack.facingRight = false;
		jack.setPosX(Window.scale(800));
	}

	@Override
	public void update() {

		jack.update();
		
		for(Achievement a : Achievement.achievements.values())
			a.update();
		
		if(jack.getPosX() < -10){
			PlayerData.daysPlayed++;
			Music.stop("backgroundmusic");
			gsh.changeGameState(GameStateHandler.GAME_ENDDAY);
		}
	}

	@Override
	public void draw(Graphics2D g) {

		GameStateDrawHelper.drawLivingRoom(g);
		
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
			if(jack.getPosX() > Window.scale(600) && jack.getPosX() < Window.scale(750)){
				jack.say("This is a guy from Sonsor'Anarky. Drawn by me roommate !", g);
			}
			
			if(jack.getPosX() > 310 && jack.getPosX() < 320)
				Achievement.trigger("seat");
			
		}
		
		g.drawImage(Images.door, 
				0,
				Window.scale(160), 
				(int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		if(PlayerData.daysPlayed > 0){
			g.setColor(Color.green.darker().darker());
			String money = "Boss Coin :" + PlayerData.money;
			g.drawString(money, 0, g.getFontMetrics().getHeight());
		}
		
		for(Achievement a : Achievement.achievements.values())
			a.draw(g);
	}
}
