package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.player.PlayerData;

public class GameStateQuit extends GameState {

	private String header ="Today, I Am Jackaboy";
	
	private double offset = 0;
	private Font headerFont;
	private Font creditFont;
	
	private String[] credits = new String[]{
			"","","",
			"Tribute to JackScepticEye",
			"and every hardworking youtuber out there",
			"",
			"You survived " + PlayerData.daysPlayed + " days",
			"They do this every day to keep you entertained.",
			"A sincere Thanks to all of them out there <3",
			"",
			"Sounds :",
			"Royalty Free background Music",
			"TechnoAxe",
			"",
			"Voice",
			"Courtesy of Sean McLoughlin",
			"",
			"Game Idea and Lead Dev. :",
			"Subaraki",
			"",
			"Art and Dev. :",
			"Axel 'TheBelgian' C.",
			"",
			"TroubleShooting and Testers",
			"raydeejay",
			"Hatsjer",
			"Chloé 'frenchFries' B.",
			""
			
	};
	
	public GameStateQuit(GameStateHandler gsh) {
		this.gsh = gsh;
		
		offset = Window.getHeight();
		headerFont = new Font("SquareFont", Font.PLAIN, Window.scale(80));
		creditFont = new Font("SquareFont", Font.PLAIN, Window.scale(40));
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		g.setColor(Color.green.darker().darker().darker());
		
		g.setFont(headerFont);
		int x = g.getFontMetrics().stringWidth(header);
		g.drawString(header, Window.getWidth()/2 - x/2, (int)offset);
		
		g.setFont(creditFont);
		
		for(int i = 0; i < credits.length; i++){
			String s = credits[i];
			int x2 = g.getFontMetrics().stringWidth(s);
			int y2 = g.getFontMetrics().getHeight() + Window.scale(2);
			g.drawString(s, Window.getWidth()/2 - x2/2, (int)offset + y2*i);
		}
	}
	
	@Override
	public void update() {
		
		offset -= 0.4D;
		
	}
}
