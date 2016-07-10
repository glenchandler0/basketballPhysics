package PhysicsStuff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.security.SecureRandom;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
class GameRun extends JPanel{
	static ShapeObj[] shapes = new ShapeObj[7]; //Array of different shapes (polymorphism)
	
	public GameRun() throws InterruptedException //Constructor
	{
		JFrame frame = new JFrame("BASKETBALL MASH 2014"); //New frame for game
		
		//Shapes initialized to different object
		shapes[0] = new Ball(50, 150);
		shapes[1] = new Ball(150, 200);
		shapes[2] = new Ball(250, 360);
		shapes[3] = new Ball(400, 560);
		shapes[4] = new SHRectangle(10, 20, 30, 40);
		shapes[5] = new SHRectangle(700, 21, 60, 100);
		shapes[6] = new Arms(shapes[5]);
		
		//objects constructed for game environment
		KeyPressHandler butHandler = new KeyPressHandler(shapes); //Handles key presses during game
		PaintingClass painting = new PaintingClass(shapes); //Painting Class object initialized. Will paint everything in loop
		PEngine environment = new PEngine(shapes, frame); //Will process events, and change x and y positions for paint painting
		
		//Frame setup
		frame.pack(); //packs frame
		frame.addKeyListener(butHandler); //Gives the frame a listener for keys
		frame.addMouseListener(environment); //Gives the frame a listener that is in pEngine (since mouse listener changes object members)
		frame.add(painting); //Adds the painting object so that all the painting is put onto this frame
		frame.setSize(1250, 950); 
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Will exit when program is closed
		
		//Game Loop-----
		int counter = 0; //Used for timer, frame counter, and more
		
		while (true) { //Loops until counter == 6000 (roughly 60 seconds)
			environment.runEnvironment(counter); //Calls the run environment with the frame number(counter)
			painting.repaint(); //Calls the repaint of painting (which will call each object's repaint also)
			Thread.sleep(10); //Waits 10 milliseconds (for realism)
			
			if(counter == 6000) //if it has been 6000 frames (roughly 60 seconds) infinite loop breaks
				break;
			counter++; //increment counter(also frame counter)
		}
		
		frame.getContentPane().removeAll(); //Will remove everything from frame
		frame.setVisible(false); //Sets frame to invisible
		frame.dispose(); //Disposes of frame
		Menu.frame.dispose(); //Also disposes of static menu frame
		Menu.frame.getContentPane().removeAll(); //Removes all from menu frame
		Menu newMenu = new Menu(); //Re initializes menu class to re open menu (new values)
	}
	
}