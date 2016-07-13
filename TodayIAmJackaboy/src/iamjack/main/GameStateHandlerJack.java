package iamjack.main;

import java.awt.Graphics2D;

import framework.GameStateHandler;
import iamjack.gamestates.GameStateAchievements;
import iamjack.gamestates.GameStateEndDay;
import iamjack.gamestates.GameStateLoading;
import iamjack.gamestates.GameStateMenu;
import iamjack.gamestates.GameStateQuit;
import iamjack.gamestates.GameStateWhatsNext;
import iamjack.gamestates.livingroom.GameStateLivingRoom;
import iamjack.gamestates.livingroom.GameStateLivingRoomEnd;
import iamjack.gamestates.livingroom.GameStateLivingRoomPlay;
import iamjack.gamestates.outside.GameStateOutside;
import iamjack.gamestates.outside.GameStateOutsideEnd;
import iamjack.gamestates.outside.GameStateWorkout;
import iamjack.gamestates.room.GameStateRoom;
import iamjack.gamestates.room.GameStateRoomEnd;
import iamjack.gamestates.room.GameStateRoomPlay;
import iamjack.gamestates.shop.GameStateShop;

public class GameStateHandlerJack extends GameStateHandler{

	public static final int LOAD = 0;

	public static final int GAME_ROOM = 1;
	public static final int GAME_ROOM_VIDEO = 2;
	public static final int GAME_ROOM_VIDEO_DONE = 3;

	public static final int GAME_ENDDAY = 4;
	public static final int GAME_QUIT = 5;
	public static final int MENU = 6;
	public static final int ACHIEVS = 7;
	public static final int GAME_WHERETO = 11;
	public static final int GAME_SHOP = 12;

	public static final int GAME_LIVING = 8;
	public static final int GAME_LIVING_BENCHGAME = 9;
	public static final int GAME_LIVING_END = 10;

	public static final int GAME_WORKOUT = 13;
	public static final int GAME_EXTERIOR = 14;
	public static final int GAME_EXTERIOR_END = 15;

	public GameStateHandlerJack() {
		super();

		addGameState(GameStateLoading.class, LOAD);

		addGameState(GameStateRoom.class, GAME_ROOM);
		addGameState(GameStateRoomPlay.class, GAME_ROOM_VIDEO);
		addGameState(GameStateRoomEnd.class, GAME_ROOM_VIDEO_DONE);

		addGameState(GameStateEndDay.class, GAME_ENDDAY);
		addGameState(GameStateQuit.class, GAME_QUIT);
		addGameState(GameStateMenu.class, MENU);
		addGameState(GameStateAchievements.class, ACHIEVS);
		addGameState(GameStateWhatsNext.class, GAME_WHERETO);
		addGameState(GameStateShop.class, GAME_SHOP);

		addGameState(GameStateLivingRoom.class, GAME_LIVING);
		addGameState(GameStateLivingRoomPlay.class, GAME_LIVING_BENCHGAME);
		addGameState(GameStateLivingRoomEnd.class, GAME_LIVING_END);

		addGameState(GameStateWorkout.class, GAME_WORKOUT);
		addGameState(GameStateOutside.class, GAME_EXTERIOR);
		addGameState(GameStateOutsideEnd.class, GAME_EXTERIOR_END);

		changeGameState(LOAD);
	}

	@Override
	public void draw(Graphics2D g) {
		super.draw(g);
	}
}