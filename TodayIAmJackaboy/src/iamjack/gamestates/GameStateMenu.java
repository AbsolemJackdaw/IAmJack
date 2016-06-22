package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.player.PlayerData;
import iamjack.video.Button;

public class GameStateMenu extends GameState {

	private final Font titleFont ;
	private String titleHead = "Today,";
	private String title = "I Am Jackaboy";

	private Button[] buttons;

	public GameStateMenu(GameStateHandler gsh) {
		this.gsh = gsh;

		titleFont = new Font("SquareFont", Font.PLAIN, Window.scale(100));

		buttons = new Button[]{
				new Button("Start", Window.getWidth()/2 - Window.scale(64), Window.getHeight()/2),
				new Button("Achievements", Window.getWidth()/2- Window.scale(64), Window.getHeight()/2+Window.scale(64)),
				new Button("Exit", Window.getWidth()/2- Window.scale(64), Window.getHeight()/2+Window.scale(64*2)),
		};


		Music.play("highfive");
		if(!PlayerData.quitGame){
			Music.loop("quest");
			PlayerData.quitGame = false;
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

		for(Button b : buttons)
			b.draw(g);

	}

	@Override
	public void update() {

		for(int i = 0; i < buttons.length ; i++){
			Button b = buttons[i];

			if(b != null)
				if(b.getBox().contains(MouseHandler.mouseX , MouseHandler.mouseY)){
					if(MouseHandler.click){
						if(b.getName().equals("Start")){
							Music.stop("quest");
							gsh.changeGameState(GameStateHandler.GAME_ROOM);
						}else if(b.getName().equals("Exit")){

							System.exit(0);
						}
					}
					b.isLit(true);
				}else{
					b.isLit(false);
				}
		}	
	}
}
