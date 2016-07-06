package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.buttons.Button;
import iamjack.buttons.ButtonDay;
import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.player.PlayerData;

public class GameStateEndDay extends GameState{

	private float alpha = 0f;

	private ButtonDay button1 = new ButtonDay(
			"Next Day", 
			Window.getWidth()/2,
			Window.scale(300));

	private ButtonDay button2 = new ButtonDay(
			"End Gaming", 
			Window.getWidth()/2,
			Window.scale(375));

	private Font text;

	public GameStateEndDay(GameStateHandler gsh) {
		super(gsh);

		PlayerData.videoOfTheDay.clear();
		PlayerData.currentlySaying="";

		//2 videos worth of sound
		if(PlayerData.soundsPlayed.size() > 63)
			PlayerData.soundsPlayed.clear();

		text = new Font("SquareFont", Font.PLAIN, Window.scale(100));
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(new Color(0f, 0f, 0f, Math.min(1, alpha)));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.setColor(Color.green.darker());
		g.setFont(text);

		String yt = "You have Youtube'd";
		String day = ""+PlayerData.daysPlayed;
		String days = "Days";

		g.drawString(yt, Window.getWidth()/2 - g.getFontMetrics().stringWidth(yt)/2, Window.scale(100));
		g.drawString(day, Window.getWidth()/2 - g.getFontMetrics().stringWidth(day)/2, Window.scale(100) + g.getFontMetrics().getHeight()/2+ g.getFontMetrics().getHeight()/4);
		g.drawString(days, Window.getWidth()/2 - g.getFontMetrics().stringWidth(days)/2, Window.scale(100)+ g.getFontMetrics().getHeight()+ g.getFontMetrics().getHeight()/2);

		button1.draw(g);
		button2.draw(g);

		super.draw(g);
	}

	@Override
	public void update() {
		super.update();
		
		if(alpha < 1f)
			alpha += 0.025f;
		else
			alpha = 1f;

		for(int i = 0; i < 2 ; i++){
			Button b = i == 0 ? button1 : button2;
			b.update(gsh);
		}	
	}
}
