package state.states;

import components.MineField;
import components.TopBar;
import state.State;
import state.StateManager;
import utility.Settings;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class PlayState extends State
{
	private MineField mineField;
	private TopBar topBar;

	public PlayState(StateManager stateManager)
	{
		this.stateManager = stateManager;
		init();
	}

	public void init()
	{
		mineField = new MineField();
		mineField.setCoordinates(0, 26);

		topBar = new TopBar(mineField);
	}

	public void update()
	{
		mineField.update();
		topBar.update();
	}

	public void draw(Graphics2D g2d)
	{
		mineField.draw(g2d);
		topBar.draw(g2d);
	}

	public void keyPressed(int key)
	{
		topBar.keyPressed(key);

		if(key == KeyEvent.VK_ESCAPE)
		{
			stateManager.setState(StateManager.MENU_STATE);
		}
	}

	public void keyReleased(int key)
	{

	}

	public void mouseMoved(MouseEvent e)
	{

	}

	public void mousePressed(MouseEvent e)
	{
		mineField.mousePressed(e);
		topBar.mousePressed(e);
	}

	public void mouseReleased(MouseEvent e)
	{
		mineField.mouseReleased(e);
		topBar.mouseReleased(e);
	}

	public void mouseDragged(MouseEvent e)
	{
		mineField.mouseDragged(e);
	}
}
