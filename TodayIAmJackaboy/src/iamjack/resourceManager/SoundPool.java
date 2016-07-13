package iamjack.resourceManager;

import java.util.Random;

import framework.resourceLoaders.Music;
import iamjack.player.PlayerData;
import iamjack.player.achievements.Achievement;
import iamjack.player.achievements.AchievementLoader;

public class SoundPool {

	private static Random rand = new Random();

	public static void playEnergyVoice(){
			Music.play(findNewVoice(Sounds.ENERGY, 19));
	}

	public static void playFunnyVoice(){
		Music.play(findNewVoice(Sounds.FUNNY, 17));
	}

	public static void playLaughVoice(){
		Music.play(findNewVoice(Sounds.LAUGH, 11));
	}

	public static void playRageVoice(){
		Music.play(findNewVoice(Sounds.RAGE, 17));
	}

	public static void playScaredVoice(){
		Music.play(findNewVoice(Sounds.SCARED, 13));
	}

	public static void playTradeMarkVoice(){
		Music.play(findNewVoice(Sounds.TM, 19));
	}

	public static void playYellVoice(){
		Music.play(findNewVoice(Sounds.YELL, 14));
	}

	public static void playIntroVoice(){
		Music.play(findNewVoice(Sounds.INTRO, 8));
	}
	
	public static void playOutroVoice(){
		Music.play(findNewVoice(Sounds.OUTRO, 5));
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
			if(loops > 70) //entire size + marging
				break;
		}
		
		System.out.println(loops + " loops to find song" );
		
		if(loops > 70)
			song = type + rand.nextInt(6); //all categories have at least 6 sounds 
		
		System.out.println("Found unique voice " + song + ". Playing...");
		
		//loop is done here, so add song to list
		PlayerData.soundsPlayed.add(song);
		PlayerData.currentlySaying = song;
		
		if(song.equals("tm11") || song.equals("tm13"))
			Achievement.trigger(AchievementLoader.likeaboss);
		
		return song;
	}
}
