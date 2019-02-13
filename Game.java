import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	// Member Variables
	Model model;
	Controller controller;
	View view;
	
	public Game()
	{
		// Initialize the member variables
		model = new Model();
		controller = new Controller(model);
		view = new View(controller,model);
		
		// Setup the window
		this.setTitle("Turtle attack!");
		this.setSize(1000, 800);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		// Input devices
		view.addMouseListener(controller);
		this.addKeyListener(controller);
	}
	
	public void run()
	{
		
		while(true) 
		{
			controller.update();
			model.update();
			view.repaint(); //Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); //Updates Screen

			
			//Go to sleep for 40 milliseconds
			try
			{
				Thread.sleep(40);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}
		
	}

	
	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

}
