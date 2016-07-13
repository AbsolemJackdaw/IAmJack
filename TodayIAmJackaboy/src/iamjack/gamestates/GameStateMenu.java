package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import framework.GameStateHandler;
import framework.resourceLoaders.Music;
import framework.resourceLoaders.StreamMusic;
import framework.window.Window;
import iamjack.buttons.ButtonMenu;
import iamjack.main.GameStateJack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Sounds;

public class GameStateMenu extends GameStateJack {

	private final Font titleFont ;
	private String titleHead = "Today,";
	private String title = "I Am Jackaboy";

	private ButtonMenu[] buttons;

	public GameStateMenu(GameStateHandler gsh) {
		super(gsh);

		titleFont = new Font("SquareFont", Font.PLAIN, Window.getGameScale(100));

		buttons = new ButtonMenu[]{
				new ButtonMenu("Start", Window.getWidth()/2 , Window.getHeight()/2),
				new ButtonMenu("Achievements", Window.getWidth()/2, Window.getHeight()/2+Window.getGameScale(64)),
				new ButtonMenu("Exit", Window.getWidth()/2, Window.getHeight()/2+Window.getGameScale(64*2)),
		};

		if(!PlayerData.dontDoubleLoop){
			StreamMusic.loopStream(Sounds.STREAM_QUEST);
			PlayerData.dontDoubleLoop = false;
		}		
		Music.play(Sounds.HIGH5);

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
		super.update();

		for(ButtonMenu b : buttons)
			b.update(gsh);
	}
}
