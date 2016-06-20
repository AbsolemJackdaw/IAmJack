package iamjack.engine;

import java.awt.MouseInfo;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

	public static double mouseX = 0;
	public static double mouseY = 0;

	public static boolean click;
	private static boolean prevClick;

	public static void update(){
		double x = GamePanel.instance.getLocationOnScreen().getX();
		double y = GamePanel.instance.getLocationOnScreen().getY();

		double xm = MouseInfo.getPointerInfo().getLocation().getX();
		double ym = MouseInfo.getPointerInfo().getLocation().getY();

		mouseX = xm-x;
		mouseY = ym-y;

		if(click && !prevClick)
			click = false ; // prevClick = true;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		click = true;
	}
}
