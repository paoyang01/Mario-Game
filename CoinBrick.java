import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Image;

class CoinBrick extends Sprite
{
	BufferedImage coinBrickImage = null;
	BufferedImage coinBrickBlankImage = null;
	int coinsRemaining;
	
	CoinBrick(int _x, int _y, int _w, int _h, Model m)
	{
		super(m);
		x = _x;
		y = _y;
		w = 50;
		h = 50;
		coinsRemaining = 5;
		horizontal_velocity = 0;
		vertical_velocity = 0;
		type = "CoinBrick";
		
		try
		{
			coinBrickImage = ImageIO.read(new File("coinblock.png"));
			coinBrickBlankImage = ImageIO.read(new File("coinblockblank.png"));
		}
		catch (Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
		
	boolean update()
	{
		return true;
	}
	
	void draw(Graphics g)
	{
		if(coinsRemaining == 0)
			g.drawImage(coinBrickBlankImage, x - model.scrollPosition, y, null);
		else
			g.drawImage(coinBrickImage, x - model.scrollPosition, y, null);
	}
	
	Json marshall()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("type", "CoinBrick");
		return ob;
	}
	
	CoinBrick(Json ob, Model m)
	{
		super(m);
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		type = (String)ob.getString("type");
	}
	
	boolean AmICoinBlock()
	{
		return true;
	}
	
	void popOutCoin(int _x, int _y, double vertical_vel, double horizontal_vel)
	{
		model.addCoin(_x, _y, vertical_vel, horizontal_vel);
	}

}