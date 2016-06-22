package iamjack.resourceManager;

import java.awt.image.BufferedImage;

import iamjack.engine.resources.ImageLoader;

public class Images {

	public static BufferedImage room;
	public static BufferedImage sam;
	
	public static BufferedImage[] jackWalk = new BufferedImage[2];
	public static BufferedImage[] jackKeyBoard = new BufferedImage[2];
	public static BufferedImage[] jackTalk = new BufferedImage[5];

	public static BufferedImage jack;
	public static BufferedImage game;
	public static BufferedImage chair;
	public static BufferedImage chairLow;
	public static BufferedImage jackSit;
	public static BufferedImage roomShade;
	public static BufferedImage button;
	public static BufferedImage buttonLit;
	public static BufferedImage door;
	
	public static BufferedImage achievement;
	public static BufferedImage ear;
	public static BufferedImage spyglass;
	public static BufferedImage three;
	public static BufferedImage locked;
	public static BufferedImage scroll;
	public static BufferedImage yt;

	//not really necesairy, but allows for more control over the loadtime
	public static void loadImages(){
		
		room = ImageLoader.loadSprite("/img/jacksroom.png");
		chair = ImageLoader.loadSprite("/img/chair.png");
		chairLow = ImageLoader.loadSprite("/img/chairLow.png");
		roomShade = ImageLoader.loadSprite("/img/jacksroomshadow.png");
		door = ImageLoader.loadSprite("/img/door.png");

		sam = ImageLoader.loadSprite("/img/sam.png");
		game = ImageLoader.loadSprite("/img/game.png");

		jack = ImageLoader.loadSprite("/img/jack.png");
		jackSit = ImageLoader.loadSprite("/img/jackSit.png");
		
		button = ImageLoader.loadSprite("/img/button.png");
		buttonLit = ImageLoader.loadSprite("/img/buttonLit.png");
		
		ImageLoader.loadImages(jackWalk, "/img/jackwalk");
		ImageLoader.loadImages(jackKeyBoard, "/img/jackSitHit");
		ImageLoader.loadImages(jackTalk, "/img/jackTalk");
		
		
		achievement = ImageLoader.loadSprite("/img/achievement/achievement.png");
		ear = ImageLoader.loadSprite("/img/achievement/deaf.png");
		spyglass = ImageLoader.loadSprite("/img/achievement/spyglass.png");
		three = ImageLoader.loadSprite("/img/achievement/three.png");
		locked = ImageLoader.loadSprite("/img/achievement/locked.png");
		scroll = ImageLoader.loadSprite("/img/achievement/scroll.png");
		yt = ImageLoader.loadSprite("/img/achievement/yt.png");

	}
}
