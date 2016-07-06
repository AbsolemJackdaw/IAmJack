package iamjack.gamestates.room;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.KeyHandler;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.gamestates.shop.ShopItems;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.player.achievements.AchievementLoader;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateRoom extends GameState {

	private Jack jack = new Jack();

	private boolean canGame;

	private float alpha = 1f;

	private boolean[] discoverRoom = new boolean[5];
	
	private String[] text = new String[]{
			"Hi, I'm Jack ! Welcome to mah Crib.",
			"Hey, I made some Boss Coin from yesterday's video !",
			
			"Good mic, clean audio, nice video !",
			"Good nightrest makes up for a good day !",
			"Ah, A beautiful new day to do the Youtube !",
			"Where's the funky music coming from though ?",
			"Beer, Clovers and More Beer Lads!",
			"Just Had IceCream BreakFast. Life is awesome!",
			"Be more positive!",
			"I Feel ready for another day !",
			"I believe in you !",
			"Sometimes, I miss Gizmo...",
			"Wake up, and show the world who's boss !",
			"*random yelp*",
			"I should make a 'drawing your tweets' vid !",
			"Hmmm...ice creeeeaaaam <3",
			"I should make a video where I eat cake. Yummie !",
	};

	/**boolean triggered after player has moved. allows for one time only intro logic*/
	private boolean once = false;

	private int dayTextIndex = 0;
	private Random rand = new Random();
	
	public GameStateRoom(GameStateHandler gsh) {
		super(gsh);
		Music.loop(Sounds.ROOMMUSIC);
		dayTextIndex = rand.nextInt(text.length-2)+2;
	}

	@Override
	public void draw(Graphics2D g) {

		GameStateDrawHelper.drawRoom(g);
		
		jack.draw(g);

		if(canGame)
			GameStateDrawHelper.drawBobbingImg(g, Images.bubbleGame);

		g.drawImage(Images.chair, Window.scale(824), Window.scale(260), (int)(64f*GameStateDrawHelper.scale), (int)(64f*GameStateDrawHelper.scale), null);

		if(!jack.isAnimated()){
			if(jack.getPosX() < Window.scale(20)){
				jack.say("Nope, nothing to see here !", g);
				discoverRoom[0] = true;
			}
			else if(jack.getPosX() > Window.scale(150) && jack.getPosX() < Window.scale(250)){
				jack.say("Dis ma jack-Shelf! Full of Awesome!",g);
				discoverRoom[1] = true;
			}
			else if(jack.getPosX() > Window.scale(400) && jack.getPosX() < Window.scale(600)){
				jack.say("Bed's gathering dust... SLEEP IS FOR THE WEAK !",g);
				discoverRoom[2] = true;
			}
			else if(jack.getPosX() > Window.scale(700) && jack.getPosX() < Window.scale(750)){
				jack.say("This is my Beautiful Bonzaï !",g);
				discoverRoom[3] = true;
			}
			else if(jack.getPosX() > Window.scale(800)){
				jack.say("ooh boy ! work ! :D",g);
				discoverRoom[4] = true;
			}
			
			else if(PlayerData.itemsBought.contains(ShopItems.tank) &&
					jack.getPosX() > Window.scale(200) && 
					jack.getPosX() < Window.scale(400)){
				jack.say("Our Sam, given birth by my fans <3",g);
				discoverRoom[4] = true;
			}

			else if(once == false){
				if(PlayerData.daysPlayed <=2)
					jack.say(text[PlayerData.daysPlayed-1], g);
				else
					jack.say(text[dayTextIndex], g);

			}
		}

		GameStateDrawHelper.drawBossCoinCounter(g);

		if(KeyHandler.isHeld(KeyHandler.ESCAPE))
			GameStateDrawHelper.drawMenu(g);
		
		g.setColor(new Color(0f,0f,0f,alpha));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		super.draw(g);
	}

	@Override
	public void update() {
		super.update();
	
		if(PlayerData.daysPlayed == 3)
		Achievement.trigger(AchievementLoader.threedaystreak);
		if(PlayerData.daysPlayed == 10)
			Achievement.trigger(AchievementLoader.tendaystreak);
		if(PlayerData.money >= 1500)
			Achievement.trigger(AchievementLoader.coins);
		
		if(jack.isAnimated() && !once)
			once = true;

		if(alpha > 0)
			alpha -= 0.0025f;

		GameStateDrawHelper.updateBobbingImage();

		if(jack.getPosX() > Window.scale(800))
			canGame = true;
		else
			canGame = false;

		if(canGame && KeyHandler.isPressed(KeyHandler.ENTER)){
			Music.stop(Sounds.ROOMMUSIC);
			gsh.changeGameState(GameStateHandler.GAME_ROOM_VIDEO);
		}

		if(canGame && MouseHandler.clicked != null && MouseHandler.click){
			double x = MouseHandler.clicked.getX();
			double y = MouseHandler.clicked.getY();

			if(x >= Window.scale(850) && x <= Window.scale(914) && y >= Window.scale(180) && y <= Window.scale(290)){
				Music.stop(Sounds.ROOMMUSIC);
				gsh.changeGameState(GameStateHandler.GAME_ROOM_VIDEO);
			}
		}
		jack.update();


		int i = 0;
		for(boolean b : discoverRoom)
			if(b == true)
				i++;

		if(i == 5)
			Achievement.trigger(AchievementLoader.room);
	}
}
