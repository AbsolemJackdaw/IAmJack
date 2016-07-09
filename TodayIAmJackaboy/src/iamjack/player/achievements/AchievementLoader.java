package iamjack.player.achievements;

import iamjack.resourceManager.Images;

public class AchievementLoader {
	
	public static String loud = "loud";
	public static String room = "room";
	public static String threedaystreak = "3daystreak";
	public static String tendaystreak = "10daystreak";
	public static String likeaboss = "likeaboss";
	public static String credits = "credits";
	public static String erryday = "erryday";
	public static String seat = "seat";
	public static String happy = "happy";
	public static String billy = "billy";
	public static String coins = "coins";
	public static String scary = "scary";
	public static String rage = "rage";

	public static void load(){
		new Achievement(loud, Images.ear, "Bleeding Ears").setAid("Make a video that makes your fan's ears bleed");
		new Achievement(room, Images.spyglass, "Discover Room").setAid("Investigate your room");
		new Achievement(threedaystreak, Images.three, "3 Day Streak").setAid("Play 3 days");
		new Achievement(tendaystreak, Images.ten, "10 Days Played").setAid("Play 10 days");
		new Achievement(likeaboss, Images.sam, "Signature Yell").setAid("Get a 'Like a Boss' yell in one of your videos.");
		new Achievement(credits, Images.scroll, "Watch Credits").setAid("It's the who, how and why of the game !");
		new Achievement(erryday, Images.yt, "Everyday Video").setAid("Get very loud, energetic, funny and be yourself in a video");
		new Achievement(seat, Images.seat, "Take A Seat").setAid("try to sit down in one of the seats in the livingroom");
		new Achievement(happy, Images.samSmile, "Smile !").setAid("Laugh and Be Funny a lot in one of your videos !");
		new Achievement(billy, Images.billyAchievement, "SCREW YOU BILLY!").setAid("Run into a Billy while working out.");
		new Achievement(coins, Images.bosscoin, "Makin' a Livin'").setAid("Earn 1500 BC !");
		new Achievement(scary, Images.scary, "Scary Games").setAid("get scared and rage, get comic and loud about it");
		new Achievement(rage, Images.rage, "AAAAAAAAARG !!").setAid("Rage games... many rage, such energy, wow");

	}
}
