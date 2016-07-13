package iamjack.gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import framework.GameStateHandler;
import framework.gamestate.LoadState;
import framework.window.Window;
import iamjack.gamestates.shop.ShopItems;
import iamjack.main.GameStateHandlerJack;
import iamjack.player.achievements.AchievementLoader;
import iamjack.resourceManager.Fonts;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.SaveManager;
import iamjack.resourceManager.Sounds;

public class GameStateLoading extends LoadState {

	private final Font title ;
	private final Font subTitle ;

	private float fadeAlpha = 1f;
	private boolean counting = false;
	private float textFade = 0f;

	private int tipIndex = 0;

	private String tips[] = new String[]{
			"Did you know sounds take a long time to load ?",
			"This game has over 125 audio files of Jack's voice !",
			"Interact by showing Jack the way with Sam.",
			"Fans determine part of your salary",
			"Hold escape in game for a handy menu",
			"Did you know sounds take a long time to load ?",
			"Like really long ?",
			"It took me one day to code the base.",
			"and one day to find and edit all the sounds !",
			"And two weaks to tweak and finalize everything.",
			"This game was made in Java exclusively.",
	};

	public GameStateLoading(GameStateHandler gsh) {
		super(gsh);

		Fonts.registerFont();
		title = new Font("SquareFont", Font.PLAIN, Window.getGameScale(100));
		subTitle = new Font("SquareFont", Font.PLAIN, Window.getGameScale(25));

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		g.setColor(Color.white);
		g.setFont(title);

		String theTitleTop = "Today,";
		String theTitle = "I am Jackaboy";

		int sizeXtop = g.getFontMetrics().stringWidth(theTitleTop);
		int sizeX = g.getFontMetrics().stringWidth(theTitle);

		int sizeY = g.getFontMetrics().getHeight();

		g.drawString(theTitleTop, Window.getWidth()/2 - (sizeXtop/2), Window.getHeight()/2 - (sizeY/2)*2 );
		g.drawString(theTitle, Window.getWidth()/2 - (sizeX/2), Window.getHeight()/2 + (sizeY/2)- (sizeY/2));

		if(!isDoneLoadingResources()){

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
			g.drawString(tips[tipIndex], Window.getWidth()/2 - startSizeX2/2, Window.getHeight() - Window.getGameScale(40));

		}

		g.setColor(new Color(0f,0f,0f, fadeAlpha));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
	}

	@Override
	public void update() {
		super.update();
		
		if(getPassedTime() % 240 == 0)
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

		if(isDoneLoadingResources()){
			gsh.changeGameState(GameStateHandlerJack.MENU);
		}
	}

	@Override
	protected void loadResources() {

		Images.loadImages();
		Sounds.loadSounds();

		AchievementLoader.load();
		ShopItems.load();

		SaveManager.readPlayerData();

		//initialize scale for room drawing
		new GameStateDrawHelper();

	}
}
