package iamjack.gamestates.shop;

import java.util.ArrayList;

import iamjack.buttons.ButtonShop;
import iamjack.engine.Window;
import iamjack.resourceManager.Images;

public class ShopItems {

	public static ArrayList<ButtonShop> items = new ArrayList<ButtonShop>();
	
	public ShopItems() {
	}

	public static void load(){
		new ButtonShop(Images.workout, 200, "Work-Out Clothes", Window.scale(64), Window.scale(32));
	}
}
