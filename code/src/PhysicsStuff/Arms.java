package PhysicsStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Arms extends ShapeObj
{
	int x = 300;
	int y = 0;
	
	int xSize = 10;
	int ySize = 20;
	
	int weight = 2;
	int magX = 1;
	int magY = 1;
	int rotation = 90;
	int rotationMag = 0;
	Rectangle hitBox;
	ShapeObj bindObj; //Object that it will bind to, set by constructor
	
	//Object passed to is the object Arm will bind to
	public Arms(ShapeObj bindObj)
	{
		this.bindObj = bindObj;
	}
	
	//Setter and getters
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
	public int getXSize() 
	{
		return xSize;
	}

	@Override
	public void setXSize(int size) 
	{
		this.xSize = size;		
	}

	@Override
	public int getYSize() 
	{
		return ySize;
	}

	@Override
	public void setYSize(int size) 
	{	
		this.ySize = size;
	}
	
	@Override
	public int getRotation()
	{
		return rotation;
	}
	
	@Override
	public void setRotation(int x)
	{
		rotation = x;
	}

	@Override
	public int getRotationMag() 
	{
		return rotationMag;
	}

	@Override
	public void setRotationMag(int mag) 
	{
		this.rotationMag = mag;
	}
	
	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	
	//Arm's personal paint method
	@Override
	public void paintObj(Graphics g)
	{
		AffineTransform transform = new AffineTransform();
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setBackground(Color.BLACK);
		hitBox = new Rectangle(x, y, 40, 5);
		
		x = bindObj.getX() + 30; //Set to binded object's x + 30
		y = bindObj.getY() + 10; //Set to binded object's y + 10
		
		//Same as SHRectangle
		transform.rotate(Math.toRadians(rotation), x, y);
		Shape transformed = transform.createTransformedShape(hitBox);
		
		g2d.setColor(Color.black);
		g2d.fill(transformed);
	}
}
