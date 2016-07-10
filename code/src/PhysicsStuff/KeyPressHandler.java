package PhysicsStuff;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;


class KeyPressHandler implements KeyListener
{
	ShapeObj[] shapes; //Personal copy of reference variable

	//Constructor sets personal reference variable to passed one.
	public KeyPressHandler(ShapeObj[] array)
	{
		this.shapes = new ShapeObj[array.length]; //Not initialized until desired length is known
		for(int i = 0; i < shapes.length; i++)
		{
			this.shapes[i] = array[i];
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'w')
			moveJump();
		if(e.getKeyChar() == 'a')
			moveLeft();
		if(e.getKeyChar() == 'd')
			moveRight();
	}
	
	public void moveJump()
	{
		shapes[5].setY(895 - shapes[5].getYSize()); //Sets y outside of the PEngine's grab range, see line 92 of PEngine
		shapes[5].setMagY(-30); //Jump
		shapes[6].setRotationMag(-10); //Spin arm
		shapes[5].setRotationMag(-5); //Quickly rotates player
	}
	
	public void moveLeft()
	{
		shapes[5].setMagX(shapes[5].getMagX() - 2); //Sliding motion
	}
	
	public void moveRight()
	{
		shapes[5].setMagX(shapes[5].getMagX() + 2); //Sliding motion
	}
	


	@Override
	public void keyPressed(KeyEvent e) 
	{}


	@Override
	public void keyReleased(KeyEvent e) 
	{}
}
