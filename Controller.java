import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements MouseListener, KeyListener
{ 
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	int mouseClickX;
	int mouseClickY;
	int queuedSpaces;
	CoinBrick coinbrick;
	
	Controller(Model m)
	{
		model = m;
	}
	
	void setView(View v)
	{
		view = v;
	}
	
	public void mousePressed(MouseEvent e)
	{
		mouseClickX = e.getX();
		mouseClickY = e.getY();
	}
	
	public void mouseReleased(MouseEvent e) 
	{  
		int x1 = mouseClickX;
		int x2 = e.getX();
		int y1 = mouseClickY;
		int y2 = e.getY();
		int top = Math.min(y1,y2);
		int bottom = Math.max(y1,y2);
		int right = Math.max(x1,x2);
		int left = Math.min(x1,x2);
	
		// Draw brick
		model.addBrick(left + model.scrollPosition, top, right - left, bottom - top);
		
		// Draw coin block
		if(e.getButton() == e.BUTTON3)
			model.addCoinBrick(left + model.scrollPosition, top, right - left, bottom - top);
		
	}
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_S: model.save("map.json"); break;
			case KeyEvent.VK_L: model.load("map.json"); break;
			case KeyEvent.VK_SPACE: keySpace = true; queuedSpaces++; break;
			
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: keySpace = false; break;
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		
		model.mario.rememberPreviousXYCoordinates();
		if(keyRight) model.mario.x += 10;
		if(keyLeft) model.mario.x -= 10; 
		if(keySpace || queuedSpaces > 0)
		{
			if(model.mario.frames_since_ground < 5)
			{
				model.mario.vertical_velocity -= 12.34;
			}
		}
		queuedSpaces = 0;
	}
	
	
}
