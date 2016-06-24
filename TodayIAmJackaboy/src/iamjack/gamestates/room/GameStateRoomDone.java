package iamjack.gamestates.room;

import java.awt.Color;
import java.awt.Font;
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
import iamjack.resourceManager.Sounds;

public class GameStateRoomDone extends GameState {

	private Jack jack = new Jack();

	private final Font text;

	public GameStateRoomDone(GameStateHandler gsh) {
		this.gsh = gsh;

		text = new Font("SquareFont", Font.PLAIN, Window.scale(35));

		jack.facingRight = false;
		jack.setPosX(Window.scale(800));

		Music.loop(Sounds.EVERYWHERE);
	}

	@Override
	public void draw(Graphics2D g) {

		GameStateDrawHelper.drawRoom(g);

		jack.draw(g);

		g.setFont(text);g.setColor(Color.white);

		String video = makeVideoText();

		String gj = "Good job Jack ! Today you made a";

		g.drawString(gj, Window.getWidth()/2 - g.getFontMetrics().stringWidth(gj)/2, Window.scale(70));
		g.drawString(video, Window.getWidth()/2 - g.getFontMetrics().stringWidth(video)/2, Window.scale(100));
		g.drawString("video !", Window.getWidth()/2 - g.getFontMetrics().stringWidth("video !")/2, Window.scale(130));

		float scale = GameStateDrawHelper.scale;

		g.drawImage(Images.chair, Window.scale(824), Window.scale(260), (int)(64f*scale), (int)(64f*scale), null);

		g.drawImage(Images.door, 0, Window.scale(160), (int)(64f*scale), (int)(64f*scale), null);

		g.setColor(Color.green.darker().darker().darker());
		g.setFont(text);

		if(!jack.isAnimated()){
			if(jack.getPosX() > Window.scale(400) && jack.getPosX() < Window.scale(600))
				jack.say("No time to sleep yet... I should go out and Exercise !", g);
			else if(jack.getPosX() > Window.scale(750))
				jack.say("Done for today !", g);
			else if(jack.getPosX() > Window.scale(150) && jack.getPosX() < Window.scale(250))
				jack.say("This is for looking at. Fan made, very Fancy stuff!", g);
		}
		
		for(Achievement a : Achievement.achievements.values())
			a.draw(g);
		
		if(PlayerData.daysPlayed > 0){
			g.setColor(Color.green.darker().darker());
			String money = "Boss Coin :" + PlayerData.money;
			g.drawString(money, 0, g.getFontMetrics().getHeight());
		}
	}

	@Override
	public void update() {

		jack.update();

		if(jack.getPosX() < -10){
			Music.stop("everywhere");
			gsh.changeGameState(GameStateHandler.GAME_LIVING);
		}

		for(Achievement a : Achievement.achievements.values())
			a.update();
	}

	private String makeVideoText(){

		String text = "-";
		int loudCounter = 0;
		int energyCounter = 0;
		int originCounter = 0;
		int laughCounter = 0;
		int rageCounter = 0;
		int withyCounter = 0;

		for(String s : PlayerData.videoOfTheDay){
			if(s.equals("Loud")){
				loudCounter++;continue;
			}else if(s.equals("Energetic")){
				energyCounter++;continue;
			}else if(s.equals("Original")){
				originCounter++;continue;
			}else if(s.equals("Funny")){
				laughCounter++;continue;
			}else if(s.equals("Raging")){
				rageCounter++;continue;
			}else if(s.equals("Withy")){
				withyCounter++;continue;
			}

			text += s + "";
		}

		String loud = "";
		switch (loudCounter){
		case 1:	loud = "Loud,"; break;
		case 2:	loud = "Loud,"; break;
		case 3:	loud = "Very Loud,"; break;
		case 4:	loud = "Very Loud,"; break;
		case 5:	loud = "Way Too Loud,"; break;
		case 6:	loud = "Obnoxiously Loud,"; break;
		case 7:	loud = "EARTHSHAKING,"; break;
		case 8:	loud = "DEAFENING !! AAAH MY EARS !!! "; Achievement.trigger("loud"); break;
		}

		String energy = "";
		switch (energyCounter) {
		case 1: energy = "Energetic,";break;
		case 2: energy = "Very Energetic,";break;
		case 3: energy = "1200V,";break;
		}

		String origin = "";
		switch(originCounter){
		case 1 : origin = "Original,";break;
		case 2 : origin = "Unique,";break;
		}

		String laugh = "";
		switch(laughCounter){
		case 1 : laugh = "Funny,";break;
		case 2 : laugh = "Comic,";break;
		case 3 : laugh = "Hilarious,";break;
		}

		String rage = "";
		switch(rageCounter){
		case 1 : rage = "Raging,";break;
		case 2 : rage = "Maddening,";break;
		}
		String with = "";
		switch(withyCounter){
		case 1 : with = "Withy";break;
		case 2 : with = "Positive";break;
		}

		String s = text + loud + energy + origin + laugh + rage + with +"-";

		if(s.equalsIgnoreCase("-very loud,energetic,original,positive-"))
			Achievement.trigger("erryday");
		
		return s;

	}
}
