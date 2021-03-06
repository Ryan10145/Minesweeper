package state;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class State
{
	protected StateManager stateManager;

	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g2d);

	public abstract void keyPressed(int key);
	public abstract void keyReleased(int key);

	public abstract void mouseMoved(MouseEvent e);
	public abstract void mousePressed(MouseEvent e);
	public abstract void mouseReleased(MouseEvent e);
	public abstract void mouseDragged(MouseEvent e);
}
