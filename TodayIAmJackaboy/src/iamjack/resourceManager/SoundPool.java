package iamjack.resourceManager;

import java.util.Random;

import iamjack.engine.resources.Music;
import iamjack.player.PlayerData;

public class SoundPool {

	private static Random rand = new Random();

	public static void playEnergyVoice(){
			Music.play(findNewVoice("energy", 14));
	}

	public static void playFunnyVoice(){
		Music.play(findNewVoice("funny", 14));
	}

	public static void playLaughVoice(){
		Music.play(findNewVoice("laugh", 6));
	}

	public static void playRageVoice(){
		Music.play(findNewVoice("rage", 15));
	}

	public static void playScaredVoice(){
		Music.play(findNewVoice("scared", 8));
	}

	public static void playTradeMarkVoice(){
		Music.play(findNewVoice("tm", 19));
	}

	public static void playYellVoice(){
		Music.play(findNewVoice("yell", 14));
	}

	public static void playIntroVoice(){
		Music.play(findNewVoice("intro", 6));
	}
	
	public static void playOutroVoice(){
		Music.play(findNewVoice("outro", 5));
	}
	
	private static String findNewVoice(String type, int max){
		
		int i = rand.nextInt(max);
		String song = type+i;
		
		int loops = 0;
		//as long as the song exists, try to find a new one
		while(PlayerData.soundsPlayed.contains(song)){
			i = rand.nextInt(max);
			song = type+i;
			loops++;
			if(loops > 18)
				break;
		}
		
		if(loops > 18)
			song = type + rand.nextInt(6); //all categories have at least 6 sounds 
		
		System.out.println("Found unique voice " + song + ". Playing...");
		
		//loop is done here, so add song to list
		PlayerData.soundsPlayed.add(song);
		PlayerData.currentlySaying = song;
		
		return song;
		
	}
}
