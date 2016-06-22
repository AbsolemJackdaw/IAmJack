package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.resources.Music;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;

public class GameStateRoomDone extends GameState {

	private Jack jack = new Jack();

	private final Font text;

	public GameStateRoomDone(GameStateHandler gsh) {
		this.gsh = gsh;

		text = new Font("SquareFont", Font.PLAIN, Window.scale(35));

		jack.facingRight = false;
		jack.setPosX(Window.scale(800));

		Music.play("everywhere");
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
			if(jack.getPosX() > Window.scale(400) && jack.getPosX() < Window.scale(600)){
				String bed = "No time to sleep yet... I should go out and Exercise !";
				int x = g.getFontMetrics().stringWidth(bed);
				g.drawString(bed, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);
			}

			else if(jack.getPosX() > Window.scale(750)){
				String work = "Done for today !";
				int x = g.getFontMetrics().stringWidth(work);
				g.drawString(work, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);
			}
			
			else if(jack.getPosX() > Window.scale(150) && jack.getPosX() < Window.scale(250)){
				String shelf = "This is for looking at. Fan made, very Fancy stuff!";
				int x = g.getFontMetrics().stringWidth(shelf);
				g.drawString(shelf, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);

			}

		}
	}

	@Override
	public void update() {

		jack.update();

		if(jack.getPosX() < -10){
			PlayerData.daysPlayed++;
			gsh.changeGameState(GameStateHandler.GAME_ENDDAY);
		}

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
		case 7:	loud = "EARTHSHAKING"; break;
		case 8:	loud = "DEAFENING !! AAAH MY EARS !!! "; break;
		}

		String energy = "";
		switch (energyCounter) {
		case 1: energy = "Energetic,";break;
		case 2: energy = "Charged,";break;
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
		case 1 : with = "Withy,";break;
		case 2 : with = "Positive,";break;
		}
		
		return text + loud + energy + origin + laugh + rage + with +"-";

	}
}
