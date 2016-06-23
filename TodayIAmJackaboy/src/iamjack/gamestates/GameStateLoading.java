package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.SwingWorker;

import iamjack.engine.GamePanel;
import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.player.achievements.AchievementLoader;
import iamjack.resourceManager.Fonts;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateLoading extends GameState {

	private final Font title ;
	private final Font subTitle ;

	private float fadeAlpha = 1f;
	private boolean counting = false;
	private float textFade = 0f;

	private boolean resourcesLoaded = false;
	private int counter = 0;
	private int tipIndex = 0;

	private String tips[] = new String[]{
			"Did you know sounds take a long time to load ?",
			"Wasd and Zqsd are valid controls to move Jack.",
			"This game has over 100 audio files of Jack's voice !",
			"Interact by showing Jack the way with Sam.",
			"Did you know sounds take a long time to load ?",
			"Like really long ?",
			"It took me one day to code the base.",
			"and one day to find and edit all the sounds !",
			"And two more to tweak and finalize everything.",
			"This game was made in Java exclusively."
			
	};

	public GameStateLoading(GameStateHandler gsh) {
		this.gsh = gsh;

		load();
		Fonts.registerFont();
		title = new Font("SquareFont", Font.PLAIN, Window.scale(100));
		subTitle = new Font("SquareFont", Font.PLAIN, Window.scale(25));

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, GamePanel.W, GamePanel.H);

		g.setColor(Color.white);
		g.setFont(title);

		String theTitleTop = "Today,";
		String theTitle = "I am Jackaboy";

		int sizeXtop = g.getFontMetrics().stringWidth(theTitleTop);
		int sizeX = g.getFontMetrics().stringWidth(theTitle);

		int sizeY = g.getFontMetrics().getHeight();

		g.drawString(theTitleTop, Window.getWidth()/2 - (sizeXtop/2), Window.getHeight()/2 - (sizeY/2)*2 );
		g.drawString(theTitle, Window.getWidth()/2 - (sizeX/2), Window.getHeight()/2 + (sizeY/2)- (sizeY/2));

		if(resourcesLoaded){
//			g.setFont(subTitle);
//
//			String start = "Press Enter, or Click, to Start";
//			int startSizeX = g.getFontMetrics().stringWidth(start);
//			int startSizeY = g.getFontMetrics().getHeight();
//
//			g.setColor(new Color(1f,1f,1f,textFade));
//
//			g.drawString(start, 
//					Window.getWidth()/2 - startSizeX/2, 
//					Window.getHeight()/2 + sizeY + startSizeY);
//
//			g.drawImage(
//					Images.sam,
//					Window.getWidth()/2 - (startSizeX/2) - Window.scale(32), 
//					Window.getHeight()/2 + sizeY + Window.scale(5), 
//					Window.scale(32), Window.scale(32), null);
//
//			g.setColor(new Color(0f,0f,0f, 1f - textFade));
//			g.fillRect(Window.getWidth()/2 - (startSizeX/2) - Window.scale(32), 
//					Window.getHeight()/2 + sizeY + Window.scale(5), 
//					Window.scale(32), Window.scale(32));

		}else{
			g.setFont(subTitle);

			String start = "Loading";
			int startSizeX = g.getFontMetrics().stringWidth(start);
			int startSizeY = g.getFontMetrics().getHeight();

			g.setColor(new Color(1f,1f,1f,textFade));

			g.drawString(start, 
					Window.getWidth()/2 - startSizeX/2, 
					Window.getHeight()/2 + sizeY + startSizeY);
			
			g.setFont(subTitle);
			g.setColor(Color.green.darker().darker().darker());
			int startSizeX2 = g.getFontMetrics().stringWidth(tips[tipIndex]);
			g.drawString(tips[tipIndex], Window.getWidth()/2 - startSizeX2/2, Window.getHeight() - Window.scale(40));

		}
		
		g.setColor(new Color(0f,0f,0f, fadeAlpha));
		g.fillRect(0, 0, GamePanel.W, GamePanel.H);
	}

	@Override
	public void update() {

		counter++;

		if(counter % 360 == 0)
			tipIndex++;
		
		if(tipIndex >= tips.length)
			tipIndex = 0;
		

		if(fadeAlpha > 0)
			fadeAlpha -= 0.0025f;

		if(textFade >= 0.9F){
			counting = false;
		}
		if(textFade <= 0.1F){
			counting = true;
		}

		if(counting)
			textFade+=.005F;
		else
			textFade-=.005F;

		if(resourcesLoaded)
			gsh.changeGameState(GameStateHandler.MENU);
//			if(KeyHandler.isPressed(KeyHandler.ENTER) || MouseHandler.click){
//				MouseHandler.clicked = null;
//				gsh.changeGameState(GameStateHandler.GAME_ROOM);
//			}
	}

	private void load(){

		new SwingWorker<Integer, Void>() {

			@Override
			protected Integer doInBackground() throws Exception {

				Images.loadImages();
				Sounds.loadSounds();
				AchievementLoader.load();
				
				return null;
			}

			@Override
			protected void done() {
				super.done();
				resourcesLoaded = true;
				System.out.println("done loading rsrcs");
			}
		}.execute();
	}
}