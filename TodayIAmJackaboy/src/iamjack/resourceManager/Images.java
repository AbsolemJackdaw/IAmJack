package iamjack.resourceManager;

import java.awt.image.BufferedImage;

import iamjack.engine.resources.ImageLoader;

public class Images {

	public static BufferedImage sam;

	public static BufferedImage[] jackWalk = new BufferedImage[2];
	public static BufferedImage[] jackKeyBoard = new BufferedImage[2];
	public static BufferedImage[] jackTalk = new BufferedImage[5];
	public static BufferedImage[] jackpressFace = new BufferedImage[5];

	public static BufferedImage jack;
	public static BufferedImage jackSit;
	public static BufferedImage jackPress;
	public static BufferedImage jackPressArms;

	public static BufferedImage game;
	public static BufferedImage workout;

	public static BufferedImage chair;
	public static BufferedImage chairLow;	
	public static BufferedImage roomShade;
	public static BufferedImage door;
	public static BufferedImage room;

	public static BufferedImage livingroom;
	public static BufferedImage livingroomDoor;
	public static BufferedImage livingroomChair;
	public static BufferedImage livingroomBenchPress;
	public static BufferedImage livingroomBenchPressWeight;

	public static BufferedImage button;
	public static BufferedImage buttonLit;

	public static BufferedImage achievement;
	public static BufferedImage ear;
	public static BufferedImage spyglass;
	public static BufferedImage three;
	public static BufferedImage locked;
	public static BufferedImage scroll;
	public static BufferedImage yt;
	public static BufferedImage seat;
	public static BufferedImage samSmile;

	//not really necesairy, but allows for more control over the loadtime
	public static void loadImages(){

		room = ImageLoader.loadSprite("/img/room/jacksroom.png");
		chair = ImageLoader.loadSprite("/img/room/chair.png");
		chairLow = ImageLoader.loadSprite("/img/room/chairLow.png");
		roomShade = ImageLoader.loadSprite("/img/room/jacksroomshadow.png");
		door = ImageLoader.loadSprite("/img/room/door.png");

		sam = ImageLoader.loadSprite("/img/sam.png");
		game = ImageLoader.loadSprite("/img/game.png");
		workout = ImageLoader.loadSprite("/img/workout.png");

		jack = ImageLoader.loadSprite("/img/jack/jack.png");
		jackSit = ImageLoader.loadSprite("/img/jack/jackSit.png");
		jackPress = ImageLoader.loadSprite("/img/jack/jackpress2.png");
		jackPressArms = ImageLoader.loadSprite("/img/jack/jackpress.png");

		button = ImageLoader.loadSprite("/img/button.png");
		buttonLit = ImageLoader.loadSprite("/img/buttonLit.png");

		ImageLoader.loadImages(jackWalk, "/img/jack/jackwalk");
		ImageLoader.loadImages(jackKeyBoard, "/img/jack/jackSitHit");
		ImageLoader.loadImages(jackTalk, "/img/jack/jackTalk");
		ImageLoader.loadImages(jackpressFace, "/img/jack/jackpressface");

		achievement = ImageLoader.loadSprite("/img/achievement/achievement.png");
		ear = ImageLoader.loadSprite("/img/achievement/deaf.png");
		spyglass = ImageLoader.loadSprite("/img/achievement/spyglass.png");
		three = ImageLoader.loadSprite("/img/achievement/three.png");
		locked = ImageLoader.loadSprite("/img/achievement/locked.png");
		scroll = ImageLoader.loadSprite("/img/achievement/scroll.png");
		yt = ImageLoader.loadSprite("/img/achievement/yt.png");
		seat = ImageLoader.loadSprite("/img/achievement/seat.png");
		samSmile = ImageLoader.loadSprite("/img/achievement/samsmile.png");

		livingroom = ImageLoader.loadSprite("/img/room/livingroom.png");
		livingroomBenchPress = ImageLoader.loadSprite("/img/room/livingroomBenchPress.png");
		livingroomBenchPressWeight = ImageLoader.loadSprite("/img/room/livingroomBenchPressWeight.png");
		livingroomChair = ImageLoader.loadSprite("/img/room/livingroomchair.png");
		livingroomDoor = ImageLoader.loadSprite("/img/room/livingroomdoor.png");

	}
}
