package iamjack.gamestates.outside;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import framework.GameStateHandler;
import framework.input.KeyHandler;
import framework.input.MouseHandler;
import framework.resourceLoaders.Music;
import framework.window.Window;
import iamjack.gamestates.GameStateDrawHelper;
import iamjack.gamestates.shop.ShopItems;
import iamjack.main.GameStateHandlerJack;
import iamjack.main.GameStateJack;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;
import iamjack.resourceManager.Sounds;

public class GameStateWorkout extends GameStateJack{

	private BufferedImage sky = null;
	private BufferedImage street = null;

	private double posXSky, posYSky, posXStreet, posYStreet;
	private double vecX, vecY;

	private double scale;
	private double scaledImgWidth;
	private double scaledImgHeight;

	private Jack jack = new Jack();

	private double drawHeight;
	private double drawWidth;

	private Rectangle jackbox;

	private ArrayList<EntityPickUps> pickups = new ArrayList<EntityPickUps>();

	private final Random rand = new Random();

	private int timer;
	private int flagPositionX;
	private boolean playedCheerOnce;

	private float alphaFinish = 0f;

	public GameStateWorkout(GameStateHandler gsh) {
		super(gsh);

		sky = Images.sky;
		street = Images.street;
		scale = (double)Window.getWidth() / (double)street.getWidth();
		//street and sky are concidered to have same dimensions.
		scaledImgWidth = Images.sky.getWidth()*scale;
		scaledImgHeight = Images.sky.getHeight()*scale;

		drawHeight = Window.getHeight()/2 - scaledImgHeight/2;
		drawWidth = Window.getWidth()/2 - scaledImgWidth/2;

		timer = (int)((32f + (float)PlayerData.exercised / 25f)*60f); //32 seconds * ticktime and a bonus
		flagPositionX = (timer * 4) - ((2*60)*4); // same as timer, but leaves out the last 2 seconds after the finish

		jackbox = new Rectangle(0, 0, Window.getGameScale(64), Window.getGameScale(16));

		jack.setAnimated(true);
		jack.facingRight = true;
		jack.setSportsing(true);

	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		drawBackground(g);
		GameStateDrawHelper.drawBossCoinCounter(g);

		for(EntityPickUps e : pickups)
			if(jack.getPosY() >= e.posY + Window.getGameScale(32) && e.imgIndex == 3)
				e.draw(g);
			else 
				e.draw(g);

		int scaled = (int)(64 * scale);
		g.drawImage(Images.flag, Window.getGameScale(50) + (flagPositionX), (int)drawHeight, scaled, scaled, null);

		jack.draw(g);
		
		//double drawing for front and back apparition of billy
		for(EntityPickUps e : pickups)
			if(jack.getPosY() + Window.getGameScale(128) < e.posY + Window.getGameScale(32) && e.imgIndex == 3)
				e.draw(g);

		g.setColor(Color.green.darker().darker());
		g.drawString(""+(timer/60), Window.getGameScale(100), Window.getGameScale(100));

		g.setColor(new Color(0, 0, 0, alphaFinish));
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());
		
		super.draw(g);

	}

	@Override
	public void update() {
		super.update();

		timer --;
		flagPositionX -= 4;

		//5 seconds left
		if(flagPositionX <= jack.getPosX() && !playedCheerOnce){
			Music.play(Sounds.CHEER);
			playedCheerOnce = true;
			pickups.add(new EntityPopoff(EntityPopoff.PLUS1BICEPS, (int)jack.getPosX(), (int)jack.getPosY(), 3.5d));
		}

		if(flagPositionX < jack.getPosX())
			alphaFinish += (1f/(2.5f*60f)); 

		if(alphaFinish >= 1f)
			alphaFinish = 1f;

		if(pickups.size() < 6 && flagPositionX > Window.getWidth()) 
			//renew pickups if there are less then 6 pickups and the flag is off screen
			generatePickUps();

		vecX = 4;
		vecY = 0.0;

		posXSky += vecX*2;
		posYSky += vecY;
		posXStreet += vecX;
		posYStreet += vecY;

		if(posXSky >= Window.getWidth())
			posXSky = 0;

		if(posXStreet >= Window.getWidth())
			posXStreet = 0;

		if(MouseHandler.mouseY > drawHeight && MouseHandler.mouseY < (drawHeight + scaledImgHeight - Window.getGameScale(128))) //128 is jacks size
			jack.setPosY(MouseHandler.mouseY);
		//queue failsaifs : if mouse moved to fast out of the update region, set pos to max
		else if(MouseHandler.mouseY < drawHeight)
			jack.setPosY(drawHeight);
		else if(MouseHandler.mouseY > (drawHeight + scaledImgHeight - Window.getGameScale(128)))
			jack.setPosY(drawHeight + scaledImgHeight - Window.getGameScale(128));

		jackbox.setLocation((int)jack.getPosX(), (int)jack.getPosY()+Window.getGameScale(110));

		//queue right to trigger walk animation
		KeyHandler.keyState[KeyHandler.RIGHT] = true;
		//set jack back to initial position, or he will effectively run to the right
		jack.setPosX(200);

		jack.update();

		for(EntityPickUps e : pickups){
			if(e instanceof EntityPopoff){

				((EntityPopoff)e).update(jack);

				if(((EntityPopoff)e).getLifetime() <= 0){
					pickups.remove(e);
					break;
				}
			}
			else{
				e.update(jack);
				if(jackbox.intersects(e.getBox())){
					e.pickUp();
					pickups.remove(e);
					pickups.add(new EntityPopoff(e.imgIndex, (int)jack.getPosX(), (int)jack.getPosY(), 4d));
					break;
				}
				else if( e.getPosX() < -Window.getGameScale(100)){
					pickups.remove(e);
					break;
				}
			}
		}

		if(timer <= 0){
			KeyHandler.keyState[KeyHandler.RIGHT] = false;
			PlayerData.exercised += 1;
			gsh.changeGameState(GameStateHandlerJack.GAME_EXTERIOR_END);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////

	private void drawBackground(Graphics2D g){

		g.drawImage(sky, 
				(int)(drawWidth - posXSky), (int)(drawHeight + posYSky), 
				(int)scaledImgWidth, (int)scaledImgHeight, null);
		g.drawImage(sky, 
				(int)(drawWidth - posXSky) + Window.getWidth(), (int)(drawHeight + posYSky),
				(int)scaledImgWidth, (int)scaledImgHeight, null);

		g.drawImage(street, 
				(int)(drawWidth - posXStreet), (int)(drawHeight + posYStreet), 
				(int)scaledImgWidth, (int)scaledImgHeight, null);
		g.drawImage(street, 
				(int)(drawWidth - posXStreet) + Window.getWidth(), (int)(drawHeight + posYStreet),
				(int)scaledImgWidth, (int)scaledImgHeight,null);
	}

	private void generatePickUps(){

		int listSize = pickups.size();
		int loop = 6 - listSize;
		if(loop == 0)
			return;

		EntityPickUps e = null;
		for(int i = 0; i < loop; i++){

			int r = rand.nextInt(3);
			if (rand.nextInt(PlayerData.itemsBought.contains(ShopItems.billyPotion) ? 20 : 6) == 0)
				r = EntityPickUps.BILLY;

			int x = Window.getWidth() + rand.nextInt(1000);

			if(x > flagPositionX)
				continue;//dont set pickups after flag

			e = new EntityPickUps(r,
					x,
					(Window.getGameScale(64)+Window.getHeight() / 2 - (int)scaledImgHeight/2) + (rand.nextInt((int)scaledImgHeight-Window.getGameScale(128))), 
					4d);
			pickups.add(e);
		}
	}
}
