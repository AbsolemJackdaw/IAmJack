package iamjack.resourceManager;

import iamjack.engine.resources.Music;

public class Sounds {

	public static void loadSounds(){
		Music.init();

		loadMusic("/sounds/WeasleyOldWeaselOrchestraComedyRoyaltyFreeMusicByTechnoaxe.mp3", "backgroundmusic");
		loadMusic("/sounds/BasicReaperShrapnelRockMetalRoyaltyFreeMusic.mp3", "metal");
		loadMusic("/sounds/TeknoAXEsRoyaltyFreeMusicRoyaltyFreeMusic198ImEverywhereDubstepFunkstepTechnoFeaturingFarisha2.mp3", "everywhere");
		loadMusic("/sounds/NewChiptuneQuests8bitEightBitRoyaltyFreeMusic.mp3","quest");

		for(int i = 1; i < 5; i++)
			loadMusic("/sounds/step_0"+i+".mp3", "step"+i);

		for(int i = 0; i < 14; i++)
			loadMusic("/sounds/energy/energy"+i+".mp3", "energy"+i);

		for(int i = 0; i < 13; i++)
			loadMusic("/sounds/funny/funny"+i+".mp3", "funny"+i);

		for(int i = 0; i < 6; i++)
			loadMusic("/sounds/laugh/laugh"+i+".mp3", "laugh"+i);

		for(int i = 0; i < 15; i++)
			loadMusic("/sounds/rage/rage"+i+".mp3", "rage"+i);

		for(int i = 0; i < 8; i++)
			loadMusic("/sounds/scared/scared"+i+".mp3", "scared"+i);

		for(int i = 0; i < 19; i++)
			loadMusic("/sounds/tm/tm"+i+".mp3", "tm"+i);

		for(int i = 0; i < 14; i++)
			loadMusic("/sounds/yell/yell"+i+".mp3", "yell"+i);

		for(int i = 0; i < 7; i++)
			loadMusic("/sounds/intro/intro"+i+".mp3", "intro"+i);

		for(int i = 0; i < 5; i++)
			loadMusic("/sounds/outro/outro"+i+".mp3", "outro"+i);

		loadMusic("/sounds/high_five.mp3", "highfive");

		for(int i = 0; i < 19; i++)
			loadMusic("/sounds/reps/reps"+i+".mp3", "reps"+i);
	}

	private static void loadMusic(String path, String name){
		Music.load(path, name);
	}
}
