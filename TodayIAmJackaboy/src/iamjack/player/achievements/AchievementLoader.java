package iamjack.player.achievements;

import iamjack.resourceManager.Images;

public class AchievementLoader {
	
	public static void load(){
		new Achievement("loud", Images.ear, "Bleeding Ears");
		new Achievement("room", Images.spyglass, "Discover Room");
		new Achievement("3daystreak", Images.three, "3 Day Streak");
		new Achievement("likeaboss", Images.sam, "Signature Yell");
		new Achievement("credits", Images.scroll, "Watch Credits");
		new Achievement("erryday", Images.yt, "Everyday Video");
		new Achievement("seat", Images.seat, "Take A Seat");

	}
}
