package iamjack.resourceManager;

import org.json.JSONObject;

import framework.save.DataTag;
import framework.save.Save;
import iamjack.player.PlayerData;

public class SaveManager {

	
	public static void readPlayerData(){
		JSONObject data = Save.read("playerdata");
		if(data == null){
			System.out.println("no save file present");
			return;
		}
		PlayerData.readData(new DataTag(data));
	}
	
	public static void writePlayerData(){
		DataTag tag = new DataTag();
		PlayerData.writeToSave(tag);
		Save.write("playerdata", tag);
	}

}
