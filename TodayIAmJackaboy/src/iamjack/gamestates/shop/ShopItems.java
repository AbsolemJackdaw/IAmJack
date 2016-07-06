package iamjack.gamestates.shop;

import java.util.ArrayList;

import iamjack.buttons.ButtonShop;
import iamjack.resourceManager.Images;

public class ShopItems {

	public static ArrayList<ButtonShop> items = new ArrayList<ButtonShop>();

	public static final String workout = "Work-Out Clothes";
	public static final String tank = "Sam Tank";
	public static final String billyPotion = "Anti-Billy Potion";
	public static final String voices = "New Set Of Video Combo's";

	public static void load(){
		new ButtonShop(Images.clothes, 150, workout, 1);
		new ButtonShop(Images.tank, 350, tank, 4);
		new ButtonShop(Images.potion, 400, billyPotion, 7);
		new ButtonShop(Images.beer, 50, voices, 10);

	}
}
