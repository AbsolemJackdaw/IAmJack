package iamjack.resourceManager;

import iamjack.engine.resources.Music;

public class Sounds {
	//Music for stream
	public static final String STREAM_QUEST = "/sounds/NewChiptuneQuests8bitEightBitRoyaltyFreeMusic.mp3";
	public static final String STREAM_METAL = "/sounds/BasicReaperShrapnelRockMetalRoyaltyFreeMusic.mp3"; 
	public static final String STREAM_ROOMMUSIC = "/sounds/WeasleyOldWeaselOrchestraComedyRoyaltyFreeMusicByTechnoaxe.mp3"; 
	public static final String STREAM_EVERYWHERE = "/sounds/TeknoAXEsRoyaltyFreeMusicRoyaltyFreeMusic198ImEverywhereDubstepFunkstepTechnoFeaturingFarisha2.mp3"; 
	public static final String STREAM_METALREPS = "/sounds/TeknoAXEsRoyaltyFreeMusicRoyaltyFreeCheapHairMetalRockSong.mp3"; 
	public static final String STREAM_WORKOUT = "/sounds/TeknoAXEsRoyaltyFreeMusicBackground34TraditionalCelticFestivitiesWorldFestive.mp3"; 
	public static final String STREAM_SHOP = "/sounds/SpaceDockDancePartyElectroHouseRoyaltyFreeMusic.mp3";
	
	//Music loaded by clips
	public static final String HIGH5 = "high5"; 
	public static final String screwyoubilly = "screwyoubilly"; 

	public static final String WALK = "step"; 
	public static final String ENERGY = "energy"; 
	public static final String LAUGH = "laugh"; 
	public static final String FUNNY = "funny"; 
	public static final String RAGE = "rage"; 
	public static final String SCARED = "scared"; 
	public static final String TM = "tm"; 
	public static final String YELL = "yell"; 
	public static final String INTRO = "intro"; 
	public static final String OUTRO = "outro"; 
	public static final String REPS = "reps"; 
	public static final String CHEER = "cheer"; 

	public static void loadSounds(){

		for(int i = 1; i < 5; i++)
			loadMusic("/sounds/step_0"+i+".mp3", WALK+(i-1));

		for(int i = 0; i < 19; i++)
			loadMusic("/sounds/energy/energy"+i+".mp3", ENERGY+i);

		for(int i = 0; i < 17; i++)
			loadMusic("/sounds/funny/funny"+i+".mp3", FUNNY+i);

		for(int i = 0; i < 11; i++)
			loadMusic("/sounds/laugh/laugh"+i+".mp3", LAUGH+i);

		for(int i = 0; i < 17; i++)
			loadMusic("/sounds/rage/rage"+i+".mp3", RAGE+i);

		for(int i = 0; i < 13; i++)
			loadMusic("/sounds/scared/scared"+i+".mp3", SCARED+i);

		for(int i = 0; i < 19; i++)
			loadMusic("/sounds/tm/tm"+i+".mp3", TM+i);

		for(int i = 0; i < 14; i++)
			loadMusic("/sounds/yell/yell"+i+".mp3", YELL+i);

		for(int i = 0; i < 8; i++)
			loadMusic("/sounds/intro/intro"+i+".mp3", INTRO+i);

		for(int i = 0; i < 5; i++)
			loadMusic("/sounds/outro/outro"+i+".mp3", OUTRO+i);

		for(int i = 0; i < 20; i++)
			loadMusic("/sounds/reps/reps"+i+".mp3", REPS+i);

		loadMusic("/sounds/childrencheer.mp3", CHEER);
		loadMusic("/sounds/high_five.mp3", HIGH5);

		loadMusic("/sounds/billy/screwyoubilly1.mp3", screwyoubilly);

	}

	private static void loadMusic(String path, String name){
		Music.load(path, name);
	}
}
