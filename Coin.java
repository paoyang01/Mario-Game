import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Image;
import java.util.Random;

class Coin extends Sprite
{
	BufferedImage coin = null;
	double horizontal_velocity;
	
	static Random r = new Random();
	
	Coin(int _x, int _y, double vertical_vel, Model m, double horizontal_vel)
	{
		super(m);
		x = _x;
		y = _y;
		vertical_velocity = vertical_vel; 
		horizontal_velocity = horizontal_vel;
		type = "Coin";
		
		try
		{
			coin = ImageIO.read(new File("coin.png"));
		}
		catch (Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	boolean update()
	{
		if(y > 1000)
		{
			return false;
		}
		
		vertical_velocity = -20.4;
		y -= vertical_velocity;
		
		horizontal_velocity = r.nextDouble() * 24 - 12;
		x -= horizontal_velocity;
		return true;
			
	}

	void draw(Graphics g)
	{
		g.drawImage(coin, x - model.scrollPosition, y - 100, null);
	}
	
	boolean AmIBrick()
	{
		return false;
	}

	Json marshall() 
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("type", "Coin");
		return ob;
	}
	
	Coin(Json ob, Model m)
	{
		super(m);
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		type = (String)ob.getString("type");
	}

	boolean AmICoinBlock() 
	{
		return false;
	}
	

}