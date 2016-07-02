package iamjack.resourceManager;

import java.awt.image.BufferedImage;

import iamjack.engine.resources.ImageLoader;

public class Images {

	public static BufferedImage sam;

	public static BufferedImage[] jackWalk = new BufferedImage[2];
	public static BufferedImage[] jackKeyBoard = new BufferedImage[2];
	public static BufferedImage[] jackTalk = new BufferedImage[5];
	public static BufferedImage[] jackpressFace = new BufferedImage[5];
	public static BufferedImage[] jackSport = new BufferedImage[2];

	public static BufferedImage jack;
	public static BufferedImage jackSit;
	public static BufferedImage jackPress;
	public static BufferedImage jackPressArms;
	public static BufferedImage jackSportsing;

	public static BufferedImage bubbleGame;
	public static BufferedImage bubbleWorkout;
	public static BufferedImage bubbleArrow;

	public static BufferedImage chair;
	public static BufferedImage chairLow;	
	public static BufferedImage roomShade;
	public static BufferedImage door;
	public static BufferedImage room;
	
	public static BufferedImage sky;
	public static BufferedImage street;
	public static BufferedImage exterior;
	public static BufferedImage exteriorDoor;

	public static BufferedImage livingroom;
	public static BufferedImage livingRoomShade;
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

	public static BufferedImage clothes;
	public static BufferedImage spud;
	public static BufferedImage clover;
	public static BufferedImage beer;
	public static BufferedImage flag;
	public static BufferedImage billy;


	//not really necesairy, but allows for more control over the loadtime
	public static void loadImages(){

		room = ImageLoader.loadSprite("/img/room/jacksroom.png");
		chair = ImageLoader.loadSprite("/img/room/chair.png");
		chairLow = ImageLoader.loadSprite("/img/room/chairLow.png");
		roomShade = ImageLoader.loadSprite("/img/room/jacksroomshadow.png");
		door = ImageLoader.loadSprite("/img/room/door.png");
		livingRoomShade = ImageLoader.loadSprite("/img/room/livingroomshade.png");
		
		sky = ImageLoader.loadSprite("/img/street/sky.png");
		street = ImageLoader.loadSprite("/img/street/street.png");
		exterior = ImageLoader.loadSprite("/img/street/house.png");
		exteriorDoor = ImageLoader.loadSprite("/img/street/outsideDoor.png");

		sam = ImageLoader.loadSprite("/img/sam.png");
		bubbleGame = ImageLoader.loadSprite("/img/game.png");
		bubbleWorkout = ImageLoader.loadSprite("/img/workout.png");
		bubbleArrow = ImageLoader.loadSprite("/img/arrow.png");

		jack = ImageLoader.loadSprite("/img/jack/jack.png");
		jackSit = ImageLoader.loadSprite("/img/jack/jackSit.png");
		jackPress = ImageLoader.loadSprite("/img/jack/jackpress2.png");
		jackPressArms = ImageLoader.loadSprite("/img/jack/jackpress.png");
		jackSportsing = ImageLoader.loadSprite("/img/jack/jackrun.png");

		button = ImageLoader.loadSprite("/img/button.png");
		buttonLit = ImageLoader.loadSprite("/img/buttonLit.png");

		ImageLoader.loadImages(jackWalk, "/img/jack/jackwalk");
		ImageLoader.loadImages(jackKeyBoard, "/img/jack/jackSitHit");
		ImageLoader.loadImages(jackTalk, "/img/jack/jackTalk");
		ImageLoader.loadImages(jackpressFace, "/img/jack/jackpressface");
		ImageLoader.loadImages(jackSport, "/img/jack/jackrun");

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

		clothes = ImageLoader.loadSprite("/img/workoutclothes.png");
		spud = ImageLoader.loadSprite("/img/street/spud.png");
		clover = ImageLoader.loadSprite("/img/street/clover.png");
		beer = ImageLoader.loadSprite("/img/street/beer.png");
		flag = ImageLoader.loadSprite("/img/street/flag.png");
		billy = ImageLoader.loadSprite("/img/street/billy.png");

	}
}
