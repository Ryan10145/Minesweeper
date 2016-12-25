package main;

import javax.swing.*;

public class Game
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Minesweeper");
		frame.setContentPane(new GamePanel());
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
