package iamjack.engine;

import javax.swing.JFrame;

public class Main{

	public static GamePanel gamepanel;

	public static void main(String[] args) {

		new Window();

		JFrame frame = new JFrame("Labyrinth");

		gamepanel = new GamePanel();
		frame.setContentPane(gamepanel);

		//set size before relative location, or it wont be centered
		frame.setResizable(false);

		frame.requestFocusInWindow();

//		//fullscreen without borders
//		frame.setUndecorated(true);
//		//fullscreen
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
