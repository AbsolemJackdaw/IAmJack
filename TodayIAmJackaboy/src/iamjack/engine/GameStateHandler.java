package iamjack.engine;


import java.awt.Graphics2D;

import iamjack.engine.input.MouseHandler;
import iamjack.gamestates.GameStateAchievements;
import iamjack.gamestates.GameStateEndDay;
import iamjack.gamestates.GameStateLoading;
import iamjack.gamestates.GameStateMenu;
import iamjack.gamestates.GameStateQuit;
import iamjack.gamestates.GameStateWhatsNext;
import iamjack.gamestates.livingroom.GameStateLivingRoom;
import iamjack.gamestates.livingroom.GameStateLivingRoomEnd;
import iamjack.gamestates.livingroom.GameStateLivingRoomPlay;
import iamjack.gamestates.room.GameStateRoom;
import iamjack.gamestates.room.GameStateRoomDone;
import iamjack.gamestates.room.GameStateRoomPlay;

public class GameStateHandler {

	public int currentGameState;

	public static final int LOAD = 0;
	public static final int GAME_ROOM = 1;
	public static final int GAME_ROOM_VIDEO = 2;
	public static final int GAME_ROOM_VIDEO_DONE = 3;
	public static final int GAME_ENDDAY = 4;
	public static final int GAME_QUIT = 5;
	public static final int MENU = 6;
	public static final int ACHIEVS = 7;
	public static final int GAME_LIVING = 8;
	public static final int GAME_LIVING_BENCHGAME = 9;
	public static final int GAME_LIVING_END = 10;
	public static final int GAME_WHERETO = 11;

	public static final GameState[] states = new GameState[20];

	public GameStateHandler() {
		currentGameState = LOAD;
		loadState(LOAD);
	}

	public int getCurrentGameState() {
		return currentGameState;
	}


	public void draw(Graphics2D g) {
		states[currentGameState].draw(g);
	}

	public void update() {
		states[currentGameState].update();
	}

	public void changeGameState(int state) {
		MouseHandler.clicked = null;
		
		unloadState(currentGameState);
		currentGameState = state;
		loadState(state);
	}

	private void unloadState(int state){
		states[state] = null;
	}

	private void loadState(int state){
		switch(state){
		case LOAD:
			states[state] = new GameStateLoading(this);
			break;
		case GAME_ROOM:
			states[state] = new GameStateRoom(this);
			break;
		case GAME_ROOM_VIDEO:
			states[state] = new GameStateRoomPlay(this);
			break;
		case GAME_ROOM_VIDEO_DONE:
			states[state] = new GameStateRoomDone(this);
			break;
		case GAME_ENDDAY:
			states[state] = new GameStateEndDay(this);
			break;
		case GAME_QUIT:
			states[state] = new GameStateQuit(this);
			break;
		case MENU:
			states[state] = new GameStateMenu(this);
			break;
		case ACHIEVS:
			states[state] = new GameStateAchievements(this);
			break;
		case GAME_LIVING:
			states[state] = new GameStateLivingRoom(this);
			break;
		case GAME_LIVING_BENCHGAME:
			states[state] = new GameStateLivingRoomPlay(this);
			break;
		case GAME_LIVING_END:
			states[state] = new GameStateLivingRoomEnd(this);
			break;
		case GAME_WHERETO:
			states[state] = new GameStateWhatsNext(this);
			break;
		}
	}
}
