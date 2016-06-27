package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.KeyHandler;
import iamjack.engine.resources.Music;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.resourceManager.Sounds;

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
			"You managed to Youtube " + (PlayerData.daysPlayed-1) + " days...",
			"But they do this every day to keep you entertained.",
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
			"Ideas and Testers",
			"raydeejay",
			"Hatsjer",
			"Chloé 'FrenchFries' B.",
			"",
			"",
			"",
			"Game Completion",
			""
			
	};
	
	public GameStateQuit(GameStateHandler gsh) {
		super(gsh);
		
		offset = Window.getHeight();
		headerFont = new Font("SquareFont", Font.PLAIN, Window.scale(80));
		creditFont = new Font("SquareFont", Font.PLAIN, Window.scale(40));
		Music.loop(Sounds.QUEST);
		PlayerData.dontDoubleLoop = true;
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		g.setColor(Color.WHITE);
		
		g.setFont(headerFont);
		int x = g.getFontMetrics().stringWidth(header);
		g.drawString(header, Window.getWidth()/2 - x/2, (int)offset);
		g.setColor(Color.green.darker().darker().darker());
		g.setFont(creditFont);
		
		for(int i = 0; i < credits.length; i++){
			String s = credits[i];
			int x2 = g.getFontMetrics().stringWidth(s);
			int y2 = g.getFontMetrics().getHeight() + Window.scale(2);
			g.drawString(s, Window.getWidth()/2 - x2/2, (int)offset + y2*i);
		}
		for(Achievement a : Achievement.achievements.values())
			a.draw(g);
	}
	
	@Override
	public void update() {
		
		float percentPart = 100f/(float)Achievement.achievements.size();
		float rest = (float)Achievement.achievements.size() - (float)PlayerData.achievements.size();
		float percent = 100f - (rest*percentPart);
		
		credits[credits.length-1] = percent+"%";
		offset -= 0.8D;
		
		if(offset < -Window.scale(1500) || KeyHandler.isPressed(KeyHandler.ENTER)){
			PlayerData.soundsPlayed.clear();
			gsh.changeGameState(GameStateHandler.MENU);
		}
		
		if(offset < -Window.scale(1100)){
			Achievement.trigger("credits");
		}
		for(Achievement a : Achievement.achievements.values())
			a.update();
	}
}
