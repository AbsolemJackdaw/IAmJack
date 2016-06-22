package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.KeyHandler;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;

public class GameStateRoom extends GameState {

	private Jack jack = new Jack();

	private double bobCounter;
	private double bobbing;

	private boolean canGame;

	private float alpha = 1f;

	private final Font subTitle ;

	/**boolean triggered after player has moved. allows for one time only intro logic*/
	private boolean once = false;

	public GameStateRoom(GameStateHandler gsh) {
		this.gsh = gsh;

		Music.play("backgroundmusic");
		subTitle = new Font("SquareFont", Font.PLAIN, Window.scale(35));
	}

	@Override
	public void draw(Graphics2D g) {

		GameStateDrawHelper.drawRoom(g);

		jack.draw(g);

		if(canGame){
			g.drawImage(Images.game, 
					Window.scale(850),
					Window.scale(200) + (int)bobbing,
					Window.scale(64), Window.scale(64), null);
		}

		g.drawImage(Images.chair, Window.scale(824), Window.scale(260), (int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		g.setColor(Color.green.darker().darker().darker());
		g.setFont(subTitle);

		if(!jack.isAnimated()){
			if(jack.getPosX() < Window.scale(20)){
				String nope = "Nope, nothing to see here !";
				int x = g.getFontMetrics().stringWidth(nope);
				g.drawString(nope, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);

			}
			else if(jack.getPosX() > Window.scale(150) && jack.getPosX() < Window.scale(250)){
				String shelf = "Dis ma jack-Shelf! Full of Awesome!";
				int x = g.getFontMetrics().stringWidth(shelf);
				g.drawString(shelf, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);

			}
			else if(jack.getPosX() > Window.scale(400) && jack.getPosX() < Window.scale(600)){
				String bed = "Bed's gathering dust... SLEEP IS FOR THE WEAK !";
				int x = g.getFontMetrics().stringWidth(bed);
				g.drawString(bed, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);

			}
			else if(jack.getPosX() > Window.scale(700) && jack.getPosX() < Window.scale(750)){
				String work = "This is my Beautiful Bonzaï !";
				int x = g.getFontMetrics().stringWidth(work);
				g.drawString(work, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);
			}
			else if(jack.getPosX() > Window.scale(800)){
				String work = "ooh boy ! work ! :D";
				int x = g.getFontMetrics().stringWidth(work);
				g.drawString(work, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);
			}

			else if(once == false && PlayerData.daysPlayed == 0){
				String work = "Hi, I'm Jack ! Welcome to mah Crib.";
				int x = g.getFontMetrics().stringWidth(work);
				g.drawString(work, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);
			}
			else if(once == false && PlayerData.daysPlayed == 1){
				String work = "Ah, A beautiful new day to do the Youtube !";
				int x = g.getFontMetrics().stringWidth(work);
				g.drawString(work, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);
			}
			else if(once == false && PlayerData.daysPlayed == 2){
				String work = "Where's the funky music coming from though ?";
				int x = g.getFontMetrics().stringWidth(work);
				g.drawString(work, Window.getWidth()/2 - x/2, Window.getHeight()/2 + Window.getHeight()/3);
			}
		}

		g.setColor(new Color(0f,0f,0f,alpha));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
	}

	@Override
	public void update() {

		if(jack.isAnimated())
			once = true;

		if(alpha > 0)
			alpha -= 0.0025f;

		bobCounter += 0.025D;
		bobbing = Math.cos(bobCounter)*20;


		if(jack.getPosX() > Window.scale(800))
			canGame = true;
		else
			canGame = false;

		if(canGame && KeyHandler.isPressed(KeyHandler.ENTER)){
			Music.stop("backgroundmusic");
			gsh.changeGameState(GameStateHandler.GAME_ROOM_VIDEO);
		}

		//g.drawRect(850, 180, 64, 110);

		if(canGame && MouseHandler.clicked != null && MouseHandler.click){
			double x = MouseHandler.clicked.getX();
			double y = MouseHandler.clicked.getY();
			System.out.println(x + " " + y);

			if(x >= Window.scale(850) && x <= Window.scale(914) && y >= Window.scale(180) && y <= Window.scale(290)){
				Music.stop("backgroundmusic");
				gsh.changeGameState(GameStateHandler.GAME_ROOM_VIDEO);
			}
		}
		jack.update();
	}
}
