package iamjack.video;

import iamjack.player.PlayerData;

public class ButtonGamePlay extends Button {

	String soundPath;

	public ButtonGamePlay(String name, int x, int y) {
		super(name, x, y);
	}
	
	@Override
	public void click(){
		if(!name.equals("Play Game"))
			PlayerData.videoOfTheDay.add(getVideonameFromString(name));
	}
	
	private String getVideonameFromString(String s){

		switch (s) {
		case "Yell":
			return "Loud";

		case "Be Funny":
			return "Withy";

		case "Jack TM":
			return "Original";

		case "Be Nice":
			return "Friendly";

		case "Rage a Bit":
			return "Raging";

		case "Energetic":
			return "Energetic";

		case "Be Scared":
			return "Scary";

		case "Intro":
			return "Regular";

		default:
			return "";
		}
	}
}
