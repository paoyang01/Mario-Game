import java.util.Iterator;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Image;

class Mario extends Sprite
{	
	// Keep track of Mario's previous X and Y coordinates
	int previous_x;
	int previous_y;
	
	double vertical_velocity;
	
	// Track frames for jumping
	int frames_since_ground;
	
	int framesSinceHit;
	
	static Image[] mario_images;
	
	// Mario Constructor
	Mario(Model m)
	{
		super(m);
		x = 0;
		y = 250;
		w = 60;
		h = 95;
		frames_since_ground = 0;
		framesSinceHit = 0;
		horizontal_velocity = 0;
		type = "Mario";
		
		if(mario_images == null)
		{
			try
			{
				mario_images = new Image[5];
				mario_images[0] = ImageIO.read(new File("luigi1.png"));
				mario_images[1] = ImageIO.read(new File("luigi2.png"));
				mario_images[2] = ImageIO.read(new File("luigi3.png"));
				mario_images[3] = ImageIO.read(new File("luigi4.png"));
				mario_images[4] = ImageIO.read(new File("luigi5.png"));
			}
			catch (Exception e) 
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
		}
	}
	
	// Move Mario out of the bricks
	void moveOutOfBrick(int x1, int y1, int w1, int h1)
	{
		if(x + w >= x1 && previous_x + w <= x1) // Check left
		{
			x = x1 - w;
		}
		else if(x <= x1 + w1 && previous_x >= x1 + w1) // Check right
		{
			x = x1 + w1;
		}
		else if(y + h >= y1 && previous_y + h <= y1) // Check top
		{
			y = y1 - h;
			vertical_velocity = 0;
			frames_since_ground = 0; // Reset frames since Mario on top of brick
		}
		else if(y <= y1 + h1 && previous_y >= y1 + h1) // Check bottom
		{
			y = y1 + h1;
			vertical_velocity = 0;
		}
		else
		{
			System.out.println("Help Mario get out NOW!");
		}
		
	}
	
	
	// Mario update
	boolean update()
	{	
		model.scrollPosition = x - 234;  // Set scroll position equal to mario's x to make screen travel with mario
		
		// Gravity
		vertical_velocity += 3.8;
		y += vertical_velocity;
		
		// Stop when Mario on ground
		if (y > 625)
		{
			vertical_velocity = 0.0;
			y = 625;
			frames_since_ground = 0;  // Reset frames if Mario has been on ground or brick
		}
		
		// Collision or nah?
		for(int i = 0; i < model.sprite.size(); i++)
		{
			Sprite s = model.sprite.get(i);
			
			if(s.AmIBrick())
				if(doesBrickCollide(s.x, s.y, s.w, s.h, this.x, this.y, this.w, this.h))
					moveOutOfBrick(s.x, s.y, s.w, s.h);
			
			if(s.AmICoinBlock())
			{
				if(doesBrickCollide(s.x, s.y, s.w, s.h, this.x, this.y, this.w, this.h))
				{
					moveOutOfBrick(s.x, s.y, s.w, s.h);
					if(this.y >= s.y + s.h)
					{
						CoinBrick cb = (CoinBrick)s; // casting
						if(framesSinceHit > 5 && cb.coinsRemaining > 0)
						{
							cb.popOutCoin(s.x, s.y, s.vertical_velocity, s.horizontal_velocity);
							cb.coinsRemaining --;
							framesSinceHit = 0;
						}

					}
				}
			}
			
		}
		
		frames_since_ground++; // Update the frames since Mario was on something solid
		framesSinceHit++;
		return true;
	}
	
	void jump()
	{
		vertical_velocity = -28.8;
	}
	
	void rememberPreviousXYCoordinates()
	{
		previous_x = x;
		previous_y = y;
	}
	
	boolean AmICoinBlock()
	{
		return false;
	}
	
	void draw(Graphics g)
	{
		int marioFrame = Math.abs(x/20) % 5; // Used to improve the readability of program
		g.drawImage(mario_images[marioFrame], x - model.scrollPosition, y, null);
	}
	
	Json marshall()
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		ob.add("vertical_velocity", vertical_velocity);
		ob.add("type", "Mario");
		ob.add("frames_since_ground", frames_since_ground);
		ob.add("framesSinceHit", framesSinceHit);
		return ob;
	}
	
	Mario(Json ob, Model m)
	{
		super(m);
		x = (int)ob.getLong("x");
		y = (int)ob.getLong("y");
		w = (int)ob.getLong("w");
		h = (int)ob.getLong("h");
		vertical_velocity = (int)ob.getDouble("vertical_velocity");
		type = (String)ob.getString("type");
		frames_since_ground = (int)ob.getLong("frames_since_ground");
		framesSinceHit = (int)ob.getLong("framesSinceHit");
		try
			{
				mario_images = new Image[5];
				mario_images[0] = ImageIO.read(new File("mario1.png"));
				mario_images[1] = ImageIO.read(new File("mario2.png"));
				mario_images[2] = ImageIO.read(new File("mario3.png"));
				mario_images[3] = ImageIO.read(new File("mario4.png"));
				mario_images[4] = ImageIO.read(new File("mario5.png"));
			}
		catch (Exception e) 
			{
				e.printStackTrace(System.err);
				System.exit(1);
			}
	}


}
