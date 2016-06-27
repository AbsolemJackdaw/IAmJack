package iamjack.gamestates.room;

import java.awt.Color;
import java.awt.Graphics2D;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.KeyHandler;
import iamjack.engine.input.MouseHandler;
import iamjack.engine.resources.Music;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateRoom extends GameState {

	private Jack jack = new Jack();

	private double bobCounter;
	private double bobbing;

	private boolean canGame;

	private float alpha = 1f;

	private boolean[] discoverRoom = new boolean[5];

	/**boolean triggered after player has moved. allows for one time only intro logic*/
	private boolean once = false;

	public GameStateRoom(GameStateHandler gsh) {
		super(gsh);
		Music.loop(Sounds.ROOMMUSIC);
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

			else if(once == false){
				if(PlayerData.daysPlayed == 1)
					jack.say("Hi, I'm Jack ! Welcome to mah Crib.",g);
				else if(once == false && PlayerData.daysPlayed == 2)
					jack.say("Hey, I made some Boss Coin from yesterday's video !",g);
				else if(once == false && PlayerData.daysPlayed == 3){
					jack.say("Ah, A beautiful new day to do the Youtube !",g);
					Achievement.trigger("3daystreak");
				}else if(once == false && PlayerData.daysPlayed == 4){
					jack.say("Where's the funky music coming from though ?",g);
				}
				else if(once == false && PlayerData.daysPlayed == 5)
					jack.say("Beer, Clovers and More Beer Lads!",g);
				else if(once == false && PlayerData.daysPlayed == 6)
					jack.say("Just Had IceCream BreakFast. Life is awesome!",g);
				else if(once == false && PlayerData.daysPlayed == 7)
					jack.say("Be more positive!",g);
			}
		}

		GameStateDrawHelper.drawBossCoinCounter(g);

		for(Achievement a : Achievement.achievements.values())
			a.draw(g);

		g.setColor(new Color(0f,0f,0f,alpha));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
	}

	@Override
	public void update() {

		if(jack.isAnimated() && !once)
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

		for(Achievement a : Achievement.achievements.values())
			a.update();

		int i = 0;
		for(boolean b : discoverRoom)
			if(b == true)
				i++;

		if(i == 5)
			Achievement.trigger("room");
	}
}
