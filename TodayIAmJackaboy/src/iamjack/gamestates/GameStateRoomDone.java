package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;

public class GameStateRoomDone extends GameState {

	private Jack jack = new Jack();

	private Font text;

	public GameStateRoomDone(GameStateHandler gsh) {
		this.gsh = gsh;

		text = new Font("SquareFont", Font.PLAIN, Window.scale(35));

		jack.facingRight = false;
		jack.setPosX(Window.scale(750));

	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		float scale = (float)Window.getWidth() / (float)Images.room.getWidth();
		float sizeX = Images.room.getWidth() * scale;
		float sizeY = Images.room.getHeight() * scale;

		g.drawImage(Images.room, 
				Window.getWidth()/2 - (int)sizeX/2,
				Window.getHeight()/2 - (int)sizeY/2, 
				(int)sizeX, (int)sizeY, null);

		jack.draw(g);

		g.setFont(text);g.setColor(Color.white);

		String video = makeVideoText();

		String gj = "Good job Jack ! Today you made a";

		g.drawString(gj, Window.getWidth()/2 - g.getFontMetrics().stringWidth(gj)/2, Window.scale(70));
		g.drawString(video + "video !", Window.getWidth()/2 - g.getFontMetrics().stringWidth(video +"video !")/2, Window.scale(100));

		g.drawImage(Images.chair, Window.scale(824), Window.scale(260), (int)(64f*scale), (int)(64f*scale), null);

		g.drawImage(Images.door, 0, Window.scale(160), (int)(64f*scale), (int)(64f*scale), null);
	}

	@Override
	public void update() {

		jack.update();

		if(jack.getPosX() < -95){
			PlayerData.daysPlayed++;
			gsh.changeGameState(GameStateHandler.GAME_END);
		}

	}

	private String makeVideoText(){

		String text = "";
		int loudCounter = 0;

		for(String s : PlayerData.videoMade){
			if(s.equals("Loud")){
				loudCounter++;
				continue;
			}

			text += s + " ";
		}

		String loud = "";
		switch (loudCounter){
		case 1:	loud = "Loud "; break;
		case 2:	loud = "Very Loud "; break;
		case 3:	loud = "Obnoxiously Loud "; break;
		case 4: loud = "DEAFENING !! AAAH MY EARS !!! "; break;
		}

		return text + loud;

	}
}
