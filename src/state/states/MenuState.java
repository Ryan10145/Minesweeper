package state.states;

import main.GamePanel;
import state.State;
import state.StateManager;
import utility.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MenuState extends State
{
	private String[] options;
	private int selection;

	private int optionHoverIndex;

	private int originalX;
	private int newX;

	private boolean isMoving;
	private int originalOption;

	public MenuState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		options = new String[] {"Play", "Setup Game", "Tutorial", "Exit"};
		selection = 0;

		optionHoverIndex = -1;

		originalX = (GamePanel.WIDTH - 32) / 2;
		newX = GamePanel.WIDTH;

		originalOption = -1;
	}

	public void update()
	{
		if(isMoving)
		{
			originalX -= 2;
			newX -= 2;

			if(originalX < newX)
			{
				if(originalX == -32)
				{
					isMoving = false;
					newX = (GamePanel.WIDTH - 32) / 2;
					originalX = GamePanel.WIDTH;

					originalOption = -1;
				}
			}
			else
			{
				if(newX == -32)
				{
					isMoving = false;
					originalX = (GamePanel.WIDTH - 32) / 2;
					newX = GamePanel.WIDTH;

					originalOption = -1;
				}
			}
		}
	}

	public void draw(Graphics2D g2d)
	{
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		g2d.setFont(new Font("Default", Font.PLAIN, 12));

		for(int i = 0; i < options.length; i++)
		{
			g2d.setColor(Color.WHITE);
			if(i == selection || i == optionHoverIndex) g2d.setColor(Color.DARK_GRAY);
			g2d.drawString(options[i], 8, 15 + 13 * i);
		}

		switch(selection)
		{
		case 0:
			g2d.drawImage(Images.Tiles.FLAG, (originalX > newX) ? originalX : newX,
					100, 32, 32, null);
			if(originalOption == -1)
				g2d.drawImage(Images.Tiles.FLAG, (originalX < newX) ? originalX : newX,
						100, 32, 32, null);
			break;

		case 1:
			g2d.drawImage(Images.Tiles.Numbers.EIGHT, (originalX > newX) ? originalX : newX,
					100, 32, 32, null);
			if(originalOption == -1)
				g2d.drawImage(Images.Tiles.Numbers.EIGHT, (originalX < newX) ? originalX : newX,
						100, 32, 32, null);
			break;

		case 2:
			g2d.drawImage(Images.Tiles.QUESTION, (originalX > newX) ? originalX : newX,
					100, 32, 32, null);
			if(originalOption == -1)
				g2d.drawImage(Images.Tiles.QUESTION, (originalX < newX) ? originalX : newX,
						100, 32, 32, null);
			break;

		case 3:
			g2d.drawImage(Images.Tiles.MINE_TRIGGERED, (originalX > newX) ? originalX : newX,
					100, 32, 32, null);
			if(originalOption == -1)
				g2d.drawImage(Images.Tiles.MINE_TRIGGERED, (originalX < newX) ? originalX : newX,
						100, 32, 32, null);
			break;
		}

		switch(originalOption)
		{
		case 0:
			g2d.drawImage(Images.Tiles.FLAG, (originalX < newX) ? originalX : newX,
					100, 32, 32, null);
			break;

		case 1:
			g2d.drawImage(Images.Tiles.Numbers.EIGHT, (originalX < newX) ? originalX : newX,
					100, 32, 32, null);
			break;

		case 2:
			g2d.drawImage(Images.Tiles.QUESTION, (originalX < newX) ? originalX : newX,
					100, 32, 32, null);
			break;

		case 3:
			g2d.drawImage(Images.Tiles.MINE_TRIGGERED, (originalX < newX) ? originalX : newX,
					100, 32, 32, null);
			break;
		}
	}

	public void keyPressed(int key)
	{
		int previousSelection = selection;

		switch(key)
		{
		case KeyEvent.VK_UP:
			if(originalOption == -1) originalOption = selection;

			if(selection > 0) selection--;

			if(previousSelection != selection) isMoving = true;
			break;

		case KeyEvent.VK_DOWN:
			if(originalOption == -1) originalOption = selection;

			if(selection < options.length - 1) selection++;

			if(previousSelection != selection) isMoving = true;
			break;

		case KeyEvent.VK_ENTER:
			switch(selection)
			{
			case 0: //Play
				stateManager.setState(StateManager.PLAY_STATE);
				break;
			case 1: //Options
				stateManager.setState(StateManager.OPTION_STATE);
				break;
			case 2: //Help
				stateManager.setState(StateManager.TUTORIAL_STATE);
				break;
			case 3: //Exit
				System.exit(0);
				break;
			}
			break;
		}
	}

	public void keyReleased(int key)
	{

	}

	public void mouseMoved(MouseEvent e)
	{
		Point mouse = new Point((int) e.getPoint().getX() / GamePanel.SCALE,
				(int) e.getPoint().getY() / GamePanel.SCALE);

		for(int i = 0; i < options.length; i++)
		{
			Rectangle optionRectangle = new Rectangle(8, 13 * i, 100, 20);

			if(optionRectangle.contains(mouse))
			{
				optionHoverIndex = i;

				if(optionHoverIndex != selection) isMoving = true;
				if(originalOption == -1 && originalOption != selection) originalOption = selection;

				selection = optionHoverIndex;
				break;
			}
			optionHoverIndex = -1;
		}
	}

	public void mousePressed(MouseEvent e)
	{
		switch(optionHoverIndex)
		{
		case 0: //Play
			stateManager.setState(StateManager.PLAY_STATE);
			//Check if the values are changed, and if they are, create a new window.
			break;
		case 1: //Options
			stateManager.setState(StateManager.OPTION_STATE);
			break;
		case 2: //Tutorial
			stateManager.setState(StateManager.TUTORIAL_STATE);
			break;
		case 3: //Exit
			System.exit(0);
			break;
		}
	}

	public void mouseReleased(MouseEvent e)
	{

	}
	public void mouseDragged(MouseEvent e)
	{

	}
}
