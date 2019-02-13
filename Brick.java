import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Image;

class Brick extends Sprite
{
	Brick(int _x, int _y, int _w, int _h, Model m) // Don't use the same letter as member variables for parameters
	{
		super(m);
		model = m;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		horizontal_velocity = 0;
		vertical_velocity = 0;
		type = "Brick";
	}
	
	
	Brick(Json ob, Model m)
	{
		super(m);
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		type = (String)ob.getString("type");
	}
	
	Json marshall()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("type", "Brick");
		return ob;
	}
	
	boolean update()
	{
		return true;
	}
	
	void draw(Graphics g)
	{
		g.setColor(new Color(153, 102, 0));
		g.fillRect(x - model.scrollPosition, y, w, h);
		g.drawRect(x - model.scrollPosition, y, w, h);
	}
	
	boolean AmIBrick()
	{
		return true;
	}
	
	boolean AmICoinBlock()
	{
		return false;
	}
	
	
}