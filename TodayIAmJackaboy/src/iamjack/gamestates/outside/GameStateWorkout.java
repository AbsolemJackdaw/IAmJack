package iamjack.gamestates.outside;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import iamjack.engine.GameState;
import iamjack.engine.GameStateHandler;
import iamjack.engine.Window;
import iamjack.engine.input.KeyHandler;
import iamjack.engine.input.MouseHandler;
import iamjack.player.Jack;
import iamjack.player.PlayerData;
import iamjack.resourceManager.Images;

public class GameStateWorkout extends GameState{

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

	private int timer; //30 seconds * ticktime
	private int flagTimer;

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

		timer = (int)((30f + (float)PlayerData.exercised / 25f)*60f);
		flagTimer = timer * 4;

		jackbox = new Rectangle(0, 0, Window.scale(64), Window.scale(16));

		jack.setAnimated(true);
		jack.facingRight = true;
		jack.setSportsing(true);

	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(Color.black);
		g.fillRect(0, 0, Window.getWidth(), Window.getHeight());

		drawBackground(g);
		
		super.draw(g);
		
		jack.draw(g);

		for(EntityPickUps e : pickups)
			e.draw(g);

		g.setColor(Color.green.darker().darker());
		g.drawString(""+(timer/60), Window.scale(100), Window.scale(100));

		int scaled = (int)(64 * scale);
		g.drawImage(Images.flag, Window.scale(50) + (flagTimer), (int)drawHeight, scaled, scaled, null);
	}

	@Override
	public void update() {
		super.update();
		
		timer --;
		flagTimer -= 4;

		if(pickups.size() < 6)
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

		if(MouseHandler.mouseY > drawHeight && MouseHandler.mouseY < (drawHeight + scaledImgHeight - Window.scale(128))){ //128 is jacks size
			jack.setPosY(MouseHandler.mouseY);
		}

		jackbox = new Rectangle(0, 0, Window.scale(64), Window.scale(16));

		jackbox.setLocation((int)jack.getPosX(), (int)jack.getPosY()+Window.scale(110));

		KeyHandler.keyState[KeyHandler.RIGHT] = true;
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
				else if( e.getPosX() < -Window.scale(100)){
					pickups.remove(e);
					break;
				}
			}
		}

		if(timer <= 0){
			KeyHandler.keyState[KeyHandler.RIGHT] = false;
			PlayerData.exercised += 1;
			gsh.changeGameState(GameStateHandler.GAME_EXTERIOR_END);
		}
	}

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
			if (rand.nextInt(6) == 0)
				r = EntityPickUps.BILLY;

			e = new EntityPickUps(r,
					Window.getWidth() + rand.nextInt(1000),
					(Window.scale(64)+Window.getHeight() / 2 - (int)scaledImgHeight/2) + (rand.nextInt((int)scaledImgHeight-Window.scale(128))), 
					4d);
			pickups.add(e);
		}
	}
}
