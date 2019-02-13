import java.awt.Graphics;

abstract class Sprite
{	
	int x;
	int y;
	int w;
	int h;
	Model model;
	double vertical_velocity;
	double horizontal_velocity;
	
	String type;
	
	Sprite(Model m)
	{
		model = m;
	}
	
	abstract boolean update();
	
	abstract void draw(Graphics g);
	
	abstract Json marshall();
	
	boolean AmIBrick()
	{
		return false;
	}
	
	abstract boolean AmICoinBlock();
	
	boolean doesBrickCollide(int _x, int _y, int _w, int _h, int _x2, int _y2, int _w2, int _h2)
	{
		if(_x2 + _w2 <= _x) return false;
		if(_x2 >= _x + _w) return false;
		if(_y2 + _h2 <= _y) return false;
		if(_y2 >= _y + _h) return false;
		return true;
	}
}
