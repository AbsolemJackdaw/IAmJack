package iamjack.buttons;

import framework.GameStateHandler;
import iamjack.player.PlayerData;
import iamjack.resourceManager.SoundPool;

public class ButtonGamePlay extends Button {

	String soundPath;

	public ButtonGamePlay(String name, int x, int y) {
		super(name, x, y);
	}
	
	@Override
	public void click(GameStateHandler gsh){
		if(!name.equals("Play Game")){
			PlayerData.videoOfTheDay.add(getVideonameFromString(name));
			playSoundFromName(name);
		}
	}
	
	private String getVideonameFromString(String s){

		switch (s) {
		case "Yell":
			return "Loud";

		case "Funny":
			return "Withy";

		case "Jack TM":
			return "Original";

		case "Laugh":
			return "Funny";

		case "Rage":
			return "Raging";

		case "Energy":
			return "Energetic";

		case "Scared":
			return "Scary,";

		default:
			return "";
		}
	}
	
	private void playSoundFromName(String s){

		switch (s) {
		case "Yell": SoundPool.playYellVoice(); break;
		case "Funny": SoundPool.playFunnyVoice(); break;
		case "Jack TM": SoundPool.playTradeMarkVoice();break;
		case "Laugh": SoundPool.playLaughVoice(); break;
		case "Rage": SoundPool.playRageVoice(); break;
		case "Energy": SoundPool.playEnergyVoice();break;
		case "Scared": SoundPool.playScaredVoice();break;
		case "Intro": SoundPool.playIntroVoice();break;
		case "Outro": SoundPool.playOutroVoice();break;

		}
	}
}
