package iamjack.player.achievements;

import iamjack.resourceManager.Images;

public class AchievementLoader {
	
	public static void load(){
		new Achievement("loud", Images.ear, "Bleeding Ears").setAid("Make a video that makes your fan's ears bleed");
		new Achievement("room", Images.spyglass, "Discover Room").setAid("Investigate your room");
		new Achievement("3daystreak", Images.three, "3 Day Streak").setAid("Play 3 days");
		new Achievement("likeaboss", Images.sam, "Signature Yell").setAid("Get a 'Like a Boss' yell in one of your videos.");
		new Achievement("credits", Images.scroll, "Watch Credits").setAid("It's the who, how and why of the game !");
		new Achievement("erryday", Images.yt, "Everyday Video").setAid("yell, get energetic, laugh and be yourself in a video");
		new Achievement("seat", Images.seat, "Take A Seat").setAid("try to sit down in one of the seats in the livingroom");
	}
}
