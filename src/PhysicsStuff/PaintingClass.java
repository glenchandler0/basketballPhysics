package PhysicsStuff;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.security.SecureRandom;



@SuppressWarnings("serial")
public class PaintingClass extends JPanel {

	public ShapeObj[] shapes; //Amount will be to type shape
	private int counter = 0; //Counter separate from gameRun counter, but increments simultaneously
	private String stringCounter;
	private String pointCounter;
	private Font font = new Font("", Font.BOLD, 25);
	
	public PaintingClass(ShapeObj[] shapes) //Constructor takes passed object array to use
	{
		this.shapes = new ShapeObj[shapes.length];
		for(int i = 0; i < shapes.length; i++)
		{
			this.shapes[i] = shapes[i];
			//System.out.printf("Shapes initial values: x: %d, y: %d\n", this.shapes[i].getX(), this.shapes[i].getY());
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //This clears frame
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		//Painting
		auxillaryPainting(g); //Paints background stuff, no properties involved
		for(int i = 0; i < shapes.length; i++)
		{
			shapes[i].paintObj(g); //Calls each individual paint method gives each method its own Graphics g as a pass
		}
	}
	
	public void auxillaryPainting(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setBackground(Color.BLACK);
		
		//Gets text ready to be printed on scoreboard
		g2d.setFont(font);
		stringCounter = ("Shot Clock: " + Integer.toString((6000 - (counter)) / 100)); //Allows for count down as counter gets larger
		pointCounter = ("Number of points: " + Integer.toString(PEngine.getPointNum())); //Static number of points from PEngine where it is incremented
		
		//Score board
		g2d.setColor(Color.black);
		g2d.fillRect(525, 5, 275, 55);
		
		//Numbers on score board
		g2d.setColor(Color.red);
		g2d.drawString(stringCounter, 565, 30);
		g2d.drawString(pointCounter, 550, 50);
		
		//BasketBall hoop and pole
		g2d.setColor(Color.black);
		g2d.drawOval(1160, 550, 65, 10);
		g2d.fillRect(1225, 550, 10, 400);
		
		//Shooter drawn as background
		g2d.setColor(Color.yellow);
		g2d.fillRect(30, 818, 60, 100);
		g2d.setColor(Color.black);
		counter++;
	}
}