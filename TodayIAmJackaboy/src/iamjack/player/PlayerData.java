package iamjack.player;

import java.util.ArrayList;

import iamjack.player.achievements.Achievement;

public class PlayerData {

	public static ArrayList<String> videoOfTheDay = new ArrayList<String>();
	
	public static int daysPlayed = 0;
	
	public static ArrayList<String> soundsPlayed = new ArrayList<String>();
	
	public static String currentlySaying = "";
	
	/**boolean triggered when credits roll. used to not loop the same song twice in a row*/
	public static boolean dontDoubleLoop;
	
	public static ArrayList<Achievement> achievements = new ArrayList<Achievement>();

}
