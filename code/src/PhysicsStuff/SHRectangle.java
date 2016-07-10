package PhysicsStuff;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SHRectangle extends ShapeObj{
	private int x = 300;
	private int y = 0;
	
	private int xSize;
	private int ySize;
	
	private int weight = 2;
	private int magX = 1;
	private int magY = 1;
	private int rotation = 0;
	private int rotationMag = 1;
	Rectangle hitBox;
	
	//Constructor sets size and position of rectangle
	public SHRectangle(int x, int y, int size1, int size2)
	{
		xSize = size1;
		ySize = size2;
		this.x = x;
		this.y = y;
		//System.out.printf("Ball created: x: %d, y: %d|", x, y);
	}
	
	//Getter and setters
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
		return xSize;
	}

	@Override
	public void setXSize(int xSize) 
	{
		this.xSize = xSize;
	}
	
	@Override
	public int getYSize()
	{
		return ySize;
	}
	
	@Override
	public void setYSize(int ySize)
	{
		this.ySize = ySize;
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
	
	//Rectangle's personal paint method
	@Override
	public void paintObj(Graphics g)
	{
		AffineTransform transform = new AffineTransform();
		Graphics2D g2d = (Graphics2D) g.create();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		hitBox = new Rectangle(x, y, xSize, ySize);
		g2d.setColor(Color.blue);
		/*//Cube
		g2d.drawRect(x, y, xSize, ySize);
		g2d.drawRect(x + (xSize / 5), y + (ySize / 5), xSize, ySize);
		g2d.drawLine(x, y, x + (xSize / 5), y + (ySize /5));
		g2d.drawLine(x + xSize, y, x + (xSize / 5) + xSize, y + (ySize / 5));
		g2d.drawLine(x, y + ySize, x + (xSize / 5), y + (ySize / 5) + ySize);
		g2d.drawLine(x + xSize, y + ySize, x + (xSize / 5) + xSize, y + (ySize / 5) + ySize);
		*/
		
		transform.rotate(Math.toRadians(rotation), x + (xSize / 2), y + (ySize / 2)); //Rotates at center of rectangle, at rotation degrees
		Shape transformed = transform.createTransformedShape(hitBox); //Creates transformation returns new shape, which can then be filled (angle)
		
		g2d.fill(transformed); //Fills new rectangle
		
	}
}
