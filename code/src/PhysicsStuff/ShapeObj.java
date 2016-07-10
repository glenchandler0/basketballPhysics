package PhysicsStuff;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.security.SecureRandom;

@SuppressWarnings("serial")
public abstract class ShapeObj{
	
	private int x = 3;
	private int y = 3;
	private int weight = 0;
	private int magX = 3;
	private int magY = 3;
	private int rotation;
	private int rotationMag = 0;
	
	Rectangle hitBox;
	
	//abstract getter and setters
	public abstract Rectangle getHitBox();
	
	public abstract int getX();
	
	public abstract void setX(int x);
	
	public abstract int getY();
	
	public abstract void setY(int y);
	
	public abstract int getXSize();
	public abstract void setXSize(int size);
	
	public abstract int getYSize();
	public abstract void setYSize(int size);
	
	public abstract int getMagX();
	
	public abstract void setMagX(int magX);
	
	public abstract int getMagY();
	
	public abstract void setMagY(int magY);
	
	public abstract int getRotation();
	
	public abstract void setRotation(int rotation);
	
	public abstract int getWeight();
	
	public abstract void setWeight(int weight);
	
	public abstract int getRotationMag();
	
	public abstract void setRotationMag(int mag);
	
	
	//Non abstract paint obj (repaint) called from painting class defaults to circle
	public void paintObj(Graphics g) 
	{
		//super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		System.out.println("Shape paint reached"); //This should not happen since every object has its own paintObj
		//move();
		g2d.fillOval(x, y, 30, 30); //Defaults to drawing oval of radius 15
	}

	
}
