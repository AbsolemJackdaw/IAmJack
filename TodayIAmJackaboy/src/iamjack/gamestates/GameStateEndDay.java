package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.MouseHandler;
import iamjack.engine.Window;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;
import iamjack.video.Button;

public class GameStateEndDay extends GameState{

	private float alpha = 0f;

	private Button button1 = new Button(
			"Play Another Day", 
			Window.getWidth()/2-Window.scale(64),
			Window.scale(300));

	private Button button2 = new Button(
			"End Gaming", 
			Window.getWidth()/2-Window.scale(64),
			Window.scale(375));

	private Font text;

	public GameStateEndDay(GameStateHandler gsh) {
		this.gsh = gsh;

		PlayerData.videoMade.clear();

		text = new Font("SquareFont", Font.PLAIN, Window.scale(100));

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

		g.setColor(new Color(0f, 0f, 0f, Math.min(1, alpha)));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.setColor(Color.green.darker());
		g.setFont(text);

		String yt = "You have Youtube'd";
		String day = ""+PlayerData.daysPlayed;
		String days = "Days";

		g.drawString(yt, Window.getWidth()/2 - g.getFontMetrics().stringWidth(yt)/2, 100);
		g.drawString(day, Window.getWidth()/2 - g.getFontMetrics().stringWidth(day)/2, 200);
		g.drawString(days, Window.getWidth()/2 - g.getFontMetrics().stringWidth(days)/2, 300);

		button1.draw(g);
		button2.draw(g);

	}

	@Override
	public void update() {

		if(alpha < 1f)
			alpha += 0.025f;
		else
			alpha = 1f;

		for(int i = 0; i < 2 ; i++){
			Button b = i == 0 ? button1 : button2;

			if(b != null)
				if(b.getBox().contains(MouseHandler.mouseX , MouseHandler.mouseY)){
					if(MouseHandler.click){
						if(b.getName().equals("Play Another Day")){
							gsh.changeGameState(GameStateHandler.GAME);
						}else{
							//queue end
						}
					}
					b.isLit(true);
				}else{
					b.isLit(false);
				}
		}	
	}
}
