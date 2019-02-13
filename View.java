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

class View extends JPanel
{

	Model model;
	Image background;

	View(Controller c, Model m)
	{
		try
		{
			model = m;
			background = ImageIO.read(new File("background.png"));
		}
		catch (Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(background, -model.scrollPosition/2, 0, 1600, 800, null); // Draw background image
		for(int i = 0; i < model.sprite.size(); i++)
		{
			Sprite s = model.sprite.get(i);
			s.draw(g);
		}
	}
		
}
