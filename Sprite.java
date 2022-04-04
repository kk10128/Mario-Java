import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Sprite 
{
	public Sprite()
	{
		
	}
	
	int x, y, w, h; 
	abstract boolean update(); 
	abstract void draw(Graphics g); 

	boolean isBrick()
	{
		return false; 
	}

	boolean isMario()
	{
		return false; 
	}

	boolean isCoinBlock()
	{
		return false; 
	}

	boolean isCoin()
	{
		return false; 
	}

}
