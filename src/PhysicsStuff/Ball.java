package PhysicsStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import java.security.SecureRandom;

@SuppressWarnings("serial")
public class Ball extends ShapeObj{
	//SecureRandom randomInt = new SecureRandom();
	
	private int x = 300;
	private int y = 50;
	
	private int radius = 15;
	private int rotation = 0;
	private int rotationMag = 0;
	private int weight = 1;
	private int magX = 1;
	private int magY = 1;
	
	Rectangle hitBox;
	
	//Constructor just takes x and y position, radius is constant
	public Ball(int x, int y)
	{
		this.x = x;
		this.y = y;
		
		//Sets magnitude to get the objects going
		this.magX = 1;
		this.magY = -10;
	}
	
	//Overridden setter and getters
	@Override
	public Rectangle getHitBox()
	{
		return hitBox;
	}
	
	@Override
	public int getX()
	{
		return x;
	}
	
	@Override
	public void setX(int x)
	{
		this.x = x;
	}
	
	@Override
	public int getY()
	{
		return y;
	}
	
	@Override 
	public void setY(int y)
	{
		this.y = y;
	}
	
	@Override
	public int getXSize()
	{
		return radius * 2;
	}
	
	@Override
	public void setXSize(int diameter)
	{
		radius = diameter / 2;
	}
	
	@Override
	public int getYSize()
	{
		return radius * 2;
	}
	
	@Override
	public void setYSize(int diameter)
	{
		radius = diameter / 2;
	}
	
	@Override 
	public int getMagX()
	{
		return magX;
	}
	
	@Override 
	public void setMagX(int magX)
	{
		this.magX = magX;
	}
	
	@Override 
	public int getMagY()
	{
		return magY;
	}
	
	@Override 
	public void setMagY(int magY)
	{
		this.magY = magY;
	}
	
	@Override
	public int getRotation()
	{
		return rotation;
	}

	@Override
	public void setRotation(int rotation) 
	{
		this.rotation = rotation;
	}

	@Override
	public int getRotationMag() 
	{
		return rotationMag;
	}

	@Override
	public void setRotationMag(int mag)
	{
		rotationMag = mag;
		
	}
	
	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	//Circle's custom paintObj
	@Override
	public void paintObj(Graphics g)
	{
		AffineTransform transform = new AffineTransform();
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		//More for polymorphism purposes
		hitBox = new Rectangle(x, y, radius * 2, radius * 2);
		
		transform.rotate(Math.toRadians(rotation), radius, radius);
		Shape transformed = transform.createTransformedShape(hitBox);
		
		g2d.setColor(Color.orange);
		//Doesn't implement spun transformed of type shape, because circles don't really spin visibly in this case
		g2d.fillOval(x, y, radius * 2, radius * 2); 
	}
}

