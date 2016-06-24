package iamjack.player;

import java.util.ArrayList;

import iamjack.engine.save.DataList;
import iamjack.engine.save.DataTag;
import iamjack.player.achievements.Achievement;

public class PlayerData {

	public static ArrayList<String> videoOfTheDay = new ArrayList<String>();
	
	public static int daysPlayed = 1;
	
	public static ArrayList<String> soundsPlayed = new ArrayList<String>();
	
	public static String currentlySaying = "";
	
	/**boolean triggered when credits roll. used to not loop the same song twice in a row*/
	public static boolean dontDoubleLoop;
	
	public static ArrayList<Achievement> achievements = new ArrayList<Achievement>();
	
	public static int money = 0;
	public static int fans = 0;
	
	public static boolean hasWorkedOut = false;
	
	public static void writeToSave(DataTag tag){
		System.out.println(daysPlayed);
		tag.writeInt("money", money);
		tag.writeInt("fans", fans);
		tag.writeInt("daysPlayed", daysPlayed);
		tag.writeBoolean("hasWorkedOut", hasWorkedOut);

		DataList list = new DataList();
		for(Achievement a : achievements){
			DataTag t = new DataTag();
			a.writeData(t);
			list.write(t);
		}
		tag.writeList("achievements", list);
	}
	
	public static void readData(DataTag tag){
		money = tag.readInt("money");
		fans = tag.readInt("fans");
		daysPlayed = tag.readInt("daysPlayed");
		hasWorkedOut = tag.readBoolean("hasWorkedOut");
		
		DataList list = tag.readList("achievements");
		
		for(int i = 0; i < list.data().length(); i++){
			DataTag dt = list.readArray(i);
			achievements.add(Achievement.readData(dt));
		}
	}
}
