package PhysicsStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.security.SecureRandom;
import javax.swing.JFrame;

public class PEngine implements MouseListener
{
	private int mouseEventX;
	private int mouseEventY;
	private int whichObject = 0;
	private static int numberOfPoints = 0;
	private JFrame frame;
	
	public ShapeObj[] shapes;
	SecureRandom randomNumber = new SecureRandom();
	
	public PEngine(ShapeObj[] shapes, JFrame frame)
	{
		numberOfPoints = 0;
		this.frame = frame;
		this.frame.pack();
		this.shapes = new ShapeObj[shapes.length];
		for(int i = 0; i < shapes.length; i++)
		{
			this.shapes[i] = shapes[i];
			//System.out.printf("Pengine initial values: x: %d, y: %d\n", this.shapes[i].getX(), this.shapes[i].getY());
		}
	}
	
	
	
	//Mouse check functions
	@Override
	public void mouseClicked(MouseEvent e)
	{}
	public void mouseEntered(MouseEvent e)
	{}
	public void mouseExited(MouseEvent e)
	{}
	
	@Override
	public void mousePressed(MouseEvent e) {
		//System.out.printf("Mouse pressed at x:%d, y%d\n", e.getX(), e.getY());
		mouseEventX = e.getX();
		mouseEventY = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		//System.out.printf("Mouse released at x:%d, y%d", e.getX(), e.getY());
		shapes[whichObject].setX(30);
		shapes[whichObject].setY(818);
		
		shapes[whichObject].setMagX((mouseEventX - e.getX()) / 4); //Divide by four for less sensitivity
		shapes[whichObject].setMagY((mouseEventY - e.getY()) / 4); //Divide by four for less sensitivity
		
		//Cycling through which object to use when mouse is released
		if(whichObject < shapes.length - 3)
			whichObject++;
		else
			whichObject = 0;
	}
	
	//Barrier checks-----------------------------------
	public void barrierCollideCheck(int i, int time)
	{
			//Wall interaction handling
			if((shapes[i].getX() <= 0) || (shapes[i].getX() >= frame.getContentPane().getWidth() - shapes[i].getXSize()))//width
			{
				if(shapes[i].getMagX() != 0) //So a stationary ball doesn't bounce off a wall
					shapes[i].setMagX(shapes[i].getMagX() * -1); //Change direction (maintains velocity)
			}
			
			//Floor interaction handling
			if((shapes[i].getY() >= frame.getContentPane().getHeight() - shapes[i].getYSize()))
			{
				//shapes[i].setRotation(shapes[i].getRotation() + (-5 + randomNumber.nextInt(1)));
				if(shapes[i].getWeight() > 1) //Heavier objects put more force into the ground and do not bounce as far
					shapes[i].setMagY((shapes[i].getMagY()* -1) + 3); //(hitting the floor causes shape to loose velocity) (+3 if weight is greater than 1)
				else
					shapes[i].setMagY((shapes[i].getMagY()* -1) + 2); //hitting the floor causes shape to loose velocity, if weight is 0 or 1, +2.
				
				//This checks if gravity is pushing the object past the lower barrier, and resets it to the floor height, so it does not sink beneath the floor.
				if((shapes[i].getY() >= (frame.getContentPane().getHeight() - shapes[i].getYSize())) && (shapes[i].getMagY() >= 1)) //Does not escape ground fast enough
				{
					shapes[i].setRotation(0); //Stops object from rotating through the floor
					shapes[i].setRotationMag(0); //Stops rotation speed
					shapes[i].setY(frame.getContentPane().getHeight() - shapes[i].getYSize()); //Sets object to correct position
					shapes[i].setMagY(0);	//If it has lost magnitude and is on the floor, it should not have a y velocity
				}
			}
 
	}
	
	//Every frame interaction, gravity, etc.---------------
	public void moveObj(int i, int time)
	{
		shapes[i].setRotation(shapes[i].getRotation() + shapes[i].getRotationMag()); //Tells shape to rotate by its rotationMag
		
			shapes[i].setX(shapes[i].getX() + shapes[i].getMagX()); //Tells object to move its x by its x velocity
			shapes[i].setY(shapes[i].getY() + shapes[i].getMagY()); //Tells object to move its y by its y velocity
			
			//Gravity
			if(time % 2 == 0) //every two frames, send object downward
			{
				if(!(shapes[i].getMagY() > 15)) //terminal velocity is 15. Shape will not move farther than 15 pixels per frame
					shapes[i].setMagY(shapes[i].getMagY() + ((int)1 * shapes[i].getWeight())); //Change direction //Gravity
				
				//While object is air born, it will try to rotate it towards 90 degrees
				if(shapes[i].getRotation() < 90)
					shapes[i].setRotation(shapes[i].getRotation() + 1);
				if(shapes[i].getRotation() > 90)
					shapes[i].setRotation(shapes[i].getRotation() - 1);
			}
			
			//Friction / wind resistance
			if(time % 40 == 0) //Every 50 frames, decrease speed X speed in given direction
			{
				try
				{
					//Will divide magnitude x by the absolute value magnitude x, so it will subtract either 1, or -1
					shapes[i].setMagX((shapes[i].getMagX() - ((shapes[i].getMagX() / (Math.abs(shapes[i].getMagX()))))));
				}
				catch(ArithmeticException divideByZero)
				{
					//since if either magX or |magX| would both be zero, divide by zero is justified. set MagX to 0
					shapes[i].setMagX(0);
				}
			}
	}
	
	public void checkObjectCollide(int i, int time)
	{
		for(int j = 0; j < shapes.length; j++)
		{			
			if(((( ((shapes[i].getX() >= shapes[j].getX()) && (shapes[i].getX()) <= shapes[j].getX() + shapes[j].getXSize())) //shapes[i].x is between upper and lower x bounds of shape[j].x
					&& ((shapes[i].getY() >= shapes[j].getY()) && (shapes[i].getY() <= shapes[j].getY() + shapes[j].getYSize()))) && i != j)) //Same for y, and check and make sure object isnt colliding with itself.
			{
					collisionEvent(i, j, time); //i collided with j
			}
				
		}
		
	}
	
	//Does actual checking and changing of variables
	public void collisionEvent(int i, int j, int time)
	{
		if(time % 2 == 0)
		{
			if(!(((i == 6) && (j == 5)) || ((i == 5) && (j == 6)))) //Makes sure defender player object shapes[5] and it's arm shapes[6] never collide
			{
				int temp;
				//Effects the rotation based on where the effected object was struck.
				if((shapes[i].getY() * 2) > (shapes[j].getYSize() / 2)) 
				{
					shapes[j].setRotationMag(shapes[i].getMagX() * -1);
					shapes[i].setRotationMag(shapes[j].getMagX() * -1);
				}
				else if((shapes[i].getY() * 2) < (shapes[j].getYSize() / 2))
				{
					shapes[j].setRotationMag(shapes[i].getMagX());
					shapes[i].setRotationMag(shapes[j].getMagX());
				}
				
				//Switches both objects velocities for x and y. Bouncing off of each other.
				temp = shapes[j].getMagX();
				shapes[j].setMagX(shapes[i].getMagX());
				shapes[i].setMagX(temp);
				
				temp = shapes[j].getMagY();
				shapes[j].setMagY(shapes[i].getMagY());
				shapes[i].setMagY(temp);
			}
			else
			{
				
			}
		}
	}
	
	//Checks to see if an object is in the hoop or not at every frame.
	public void pointCheck(int i)
	{
		if((((shapes[i].getX() >= 1165) && (shapes[i].getX() <= 1225)) &&  //Coordinates of hoop
				((shapes[i].getY() >= 538) && (shapes[i].getY() <= 558)) && ((i != 5) && (i != 6)))) //Coordinates and check that the object is not defender player
		{
			System.out.println("One point!"); //Easy check documentation of each point to match with in game check.
			numberOfPoints++; //Increments static variable number of points
		}
	}
	
	
	//Executes appropriate methods from class--------------
	public void runEnvironment(int time)
	{
		//Calls all checks for each element
		for(int i = 0; i < shapes.length; i++)
		{
			barrierCollideCheck(i, time);
			checkObjectCollide(i, time);
			pointCheck(i);
			moveObj(i, time);
		}
	}
	
	//Since points are processed here, the get method should also be here.
	public static int getPointNum()
	{
		return numberOfPoints;
	}
}
