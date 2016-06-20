package iamjack.engine;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javafx.embed.swing.JFXPanel;

@SuppressWarnings("serial")
public class GamePanel extends JFXPanel implements Runnable, KeyListener{

	public static int W = Window.getWidth();
	public static int H = Window.getHeight();

	// game thread
	private Thread thread;
	private boolean running;

	// image to get graphics from
	protected BufferedImage image;
	protected Graphics2D g;

	private GameStateHandler ghs;

	public static GamePanel instance;

	public GamePanel() {
		instance = this;

		setPreferredSize(new Dimension(W, H));
		setFocusable(true);
		requestFocus();

		System.out.println("GamePanel : Initializing game");

	}

	@Override
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			addMouseListener(new MouseHandler());
			thread.start();
		}
	}

	public void keyPressed(KeyEvent key) {
		KeyHandler.keySet(key.getKeyCode(), true);
	}

	public void keyReleased(KeyEvent key) {
		KeyHandler.keySet(key.getKeyCode(), false);
	}

	public void keyTyped(KeyEvent key) {
	}

	@Override
	public void run() {
		init();
		runComplexLoop();
	}

	//finish drawing cycle
	private void drawToScreen() {
		if(this.hasFocus()) {
			Graphics g2 = getGraphics();
			g2.drawImage(image, 0, 0, W, H, null);
			g2.dispose();
		}
	}

	//start Drawing cycle
	protected void draw() {
		if(this.hasFocus()) 
			ghs.draw(g);
	}

	protected void update() {
		if(this.hasFocus()) {
			ghs.update();
			KeyHandler.update();
			MouseHandler.update();
		}
	}

	private void init() {

		System.out.println("launching...");

		image = new BufferedImage(W, H, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		running = true;

		ghs = new GameStateHandler();
	}

	private void runComplexLoop() {
		//Best Update System I found on the net !
		//http://entropyinteractive.com/2011/02/game-engine-design-the-game-loop/
		//thanksx1000 to this dude, as well as cuddos

		// convert the time to seconds
		double lastTime = (double)System.nanoTime() / 1000000000.0;
		double maxTimeDiff = 0.5;
		int skippedFrames = 1;
		int maxSkippedFrames = 5;
		double targetUpdates = 1.0/60.0;

		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while(running)
		{
			// convert the time to seconds
			double currTime = (double)System.nanoTime() / 1000000000.0;

			if((currTime - lastTime) > maxTimeDiff)
				lastTime = currTime;

			if(currTime >= lastTime){

				// assign the time for the next update
				lastTime += targetUpdates;
				update();
				updates++;

				if((currTime < lastTime) || (skippedFrames > maxSkippedFrames)){

					draw();
					drawToScreen();
					skippedFrames = 1;
					frames++;
				}
				else
					skippedFrames++;
			}else{
				// calculate the time to sleep
				int sleepTime = (int)(1000.0 * (lastTime - currTime));

				// sanity check
				if(sleepTime > 0)
				{
					// sleep until the next update
					try{
						Thread.sleep(sleepTime);
					}catch(InterruptedException e){
					}
				}
			}

			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;

			}
		}
	}
}
