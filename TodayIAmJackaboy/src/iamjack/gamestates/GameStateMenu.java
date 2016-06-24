package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.buttons.ButtonMenu;
import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Sounds;

public class GameStateMenu extends GameState {

	private final Font titleFont ;
	private String titleHead = "Today,";
	private String title = "I Am Jackaboy";

	private ButtonMenu[] buttons;

	public GameStateMenu(GameStateHandler gsh) {
		this.gsh = gsh;

		titleFont = new Font("SquareFont", Font.PLAIN, Window.scale(100));

		buttons = new ButtonMenu[]{
				new ButtonMenu("Start", Window.getWidth()/2 , Window.getHeight()/2),
				new ButtonMenu("Achievements", Window.getWidth()/2, Window.getHeight()/2+Window.scale(64)),
				new ButtonMenu("Exit", Window.getWidth()/2, Window.getHeight()/2+Window.scale(64*2)),
		};

		Music.play(Sounds.HIGH5);

		if(!PlayerData.dontDoubleLoop){
			Music.loop(Sounds.QUEST);
			PlayerData.dontDoubleLoop = false;
		}
	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.setColor(Color.white);
		g.setFont(titleFont);
		int sizeXtop = g.getFontMetrics().stringWidth(titleHead);
		int sizeX = g.getFontMetrics().stringWidth(title);

		int sizeY = g.getFontMetrics().getHeight();

		g.drawString(titleHead, Window.getWidth()/2 - (sizeXtop/2), Window.getHeight()/2 - (sizeY/2)*2 );
		g.drawString(title, Window.getWidth()/2 - (sizeX/2), Window.getHeight()/2 + (sizeY/2)- (sizeY/2));

		for(ButtonMenu b : buttons)
			b.draw(g);

	}

	@Override
	public void update() {

		for(ButtonMenu b : buttons){
			b.update();
			if(MouseHandler.click && b.isLit())
				b.click(gsh);
		}	
	}
}
