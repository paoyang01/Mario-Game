import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;



class Model
{
	ArrayList<Sprite> sprite;
	int scrollPosition;
	Mario mario;
	
	Model()
	{
		sprite = new ArrayList<Sprite>();
		mario = new Mario(this);
		//load("map.json");
		
		//Hard Coded Bricks and Coin Bricks.
		
		// Coinbrick dclaration
		CoinBrick a = new CoinBrick(500, 550, 50, 50, this);
		CoinBrick b = new CoinBrick(600, 500, 50, 50, this);
		CoinBrick line1 = new CoinBrick(1300, 500, 50, 50, this);
		CoinBrick line2 = new CoinBrick(1400, 500, 50, 50, this);
		CoinBrick line3 = new CoinBrick(1500, 500, 50, 50, this);
		CoinBrick lowcoinbrick = new CoinBrick(650, 575, 50, 50, this);
		CoinBrick highcoinbrick = new CoinBrick(775, 400, 50, 50, this);
	
		// Staircase bricks declaration
		Brick f = new Brick(350, 650, 50, 50, this);
		Brick g = new Brick(400, 600, 50, 50, this);
		Brick h = new Brick(450, 550, 50, 50, this);
		
		// Line of bricks declaration
		Brick c = new Brick(500, 500, 50, 50, this);
		Brick d = new Brick(700, 500, 50, 50, this);
		Brick e = new Brick(900, 500, 50, 50, this);
		Brick i = new Brick(1100, 500, 190, 50, this);
		Brick j = new Brick(1575, 500, 190, 50, this);
		
		// Line bricks
		sprite.add(c);
		sprite.add(d);
		sprite.add(e);
		sprite.add(i);
		sprite.add(j);
		
		// Staircase bricks
		sprite.add(f);
		sprite.add(g);
		sprite.add(h);
		
		// Coinbricks
		sprite.add(a);
		sprite.add(b);
		sprite.add(line1);
		sprite.add(line2);
		sprite.add(line3);
		sprite.add(lowcoinbrick);
		sprite.add(highcoinbrick);
		
		// Add Mario
		sprite.add(mario);
	}
		
	void addBrick(int x, int y, int w, int h)
	{
		Sprite s = new Brick(x, y, w, h, this);
		sprite.add(s);
	}
	
	void addCoinBrick(int x1, int y1, int w1, int h1)
	{
		Sprite s = new CoinBrick(x1, y1, w1, h1, this);
		sprite.add(s);
	}
	
	void addCoin(int _x, int _y, double vertical_vel, double horizontal_vel)
	{
		Sprite s = new Coin(_x, _y, vertical_vel, this, horizontal_vel);
		sprite.add(s);
	}
	
	void unmarshall(Json ob)
	{
		sprite.clear(); // Throw away any bricks already in file
		Json json_sprite = ob.get("sprite");
		for (int i = 0; i < json_sprite.size(); i++)
		{
			Sprite s;
			Json j = json_sprite.get(i);
			String str = j.getString("type");
			if(str.equals("Mario"))
			{
				s = new Mario(j, this);
				sprite.add(s);
			}
			else if(str.equals("Brick"))
			{
				s = new Brick(j, this);
				sprite.add(s);
			}
			else if(str.equals("CoinBrick"))
			{
					s = new CoinBrick(j, this);
					sprite.add(s);
			}
			else
			{
				s = new Coin(j, this);
				sprite.add(s);
			}
		}
	}
	
	Json marshall()
	{
		Json ob = Json.newObject();
		Json json_sprite = Json.newList();
		ob.add("sprite", json_sprite);
		for (int i = 0; i < sprite.size(); i++)
		{
			Sprite s = sprite.get(i);
			Json j = s.marshall();
			json_sprite.add(j);
		}
		return ob;	
	}
	
	void save(String filename)
	{
		Json ob = marshall();
		ob.save(filename);
	}
	
	void load(String filename)
	{
		Json ob = Json.load(filename);
		unmarshall(ob);
	}
	
	// Model update
	void update()
	{
		for(int i = 0; i < sprite.size(); i++)
		{
			Sprite s = sprite.get(i);
			boolean coinDelete = s.update();
			if(!coinDelete)
			{
				System.out.println("coin removed");
				sprite.remove(i);
				i--;
			}		
		}
	}

}