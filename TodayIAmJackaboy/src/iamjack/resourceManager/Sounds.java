package iamjack.resourceManager;

import iamjack.engine.resources.Music;

public class Sounds {

	public static final String METAL = "metal"; 
	public static final String QUEST = "quest"; 
	public static final String ROOMMUSIC = "roomMusic"; 
	public static final String EVERYWHERE = "everywhere"; 
	public static final String METALREPS = "metalreps"; 
	
	public static final String HIGH5 = "high5"; 

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
	public static final String SHOP = "shop"; 

	public static void loadSounds(){
		Music.init();

		loadMusic("/sounds/WeasleyOldWeaselOrchestraComedyRoyaltyFreeMusicByTechnoaxe.mp3", ROOMMUSIC);
		loadMusic("/sounds/BasicReaperShrapnelRockMetalRoyaltyFreeMusic.mp3", METAL);
		loadMusic("/sounds/TeknoAXEsRoyaltyFreeMusicRoyaltyFreeMusic198ImEverywhereDubstepFunkstepTechnoFeaturingFarisha2.mp3", EVERYWHERE);
		loadMusic("/sounds/NewChiptuneQuests8bitEightBitRoyaltyFreeMusic.mp3",QUEST);
		loadMusic("/sounds/TeknoAXEsRoyaltyFreeMusicRoyaltyFreeCheapHairMetalRockSong.mp3", METALREPS);
		loadMusic("/sounds/SpaceDockDancePartyElectroHouseRoyaltyFreeMusic.mp3", SHOP);
		
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

		for(int i = 0; i < 12; i++)
			loadMusic("/sounds/scared/scared"+i+".mp3", SCARED+i);

		for(int i = 0; i < 19; i++)
			loadMusic("/sounds/tm/tm"+i+".mp3", TM+i);

		for(int i = 0; i < 14; i++)
			loadMusic("/sounds/yell/yell"+i+".mp3", YELL+i);

		for(int i = 0; i < 8; i++)
			loadMusic("/sounds/intro/intro"+i+".mp3", INTRO+i);

		for(int i = 0; i < 5; i++)
			loadMusic("/sounds/outro/outro"+i+".mp3", OUTRO+i);

		loadMusic("/sounds/high_five.mp3", HIGH5);

		for(int i = 0; i < 20; i++)
			loadMusic("/sounds/reps/reps"+i+".mp3", REPS+i);
	}

	private static void loadMusic(String path, String name){
		Music.load(path, name);
	}
}
