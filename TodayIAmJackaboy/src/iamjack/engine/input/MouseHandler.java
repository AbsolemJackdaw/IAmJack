package iamjack.engine.input;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import iamjack.engine.GamePanel;

public class MouseHandler extends MouseAdapter {

	public static double mouseX = 0;
	public static double mouseY = 0;

	public static boolean click;
	private static boolean prevClick;
	
	public static Point clicked = null;

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
		clicked = e.getPoint();
		
	}
}
