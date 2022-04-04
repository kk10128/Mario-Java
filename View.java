//Kavya Kannan
//Assignment 5

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	static BufferedImage[] mario_images = null;

	Model model;
	BufferedImage backgroundImage;
 

	View(Controller c, Model m)
	{
		c.setView(this);
		model = m; 
		backgroundImage = loadImage("background.png"); 
	}
	
	static BufferedImage loadImage(String filename)
	{
		BufferedImage im = null; 
		try
		{
			im = ImageIO.read(new File(filename));
		}
		catch(Exception e) 
		{
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im; 
	}
	
	
	public void paintComponent(Graphics g) // adds background picture and draws bricks and sprites
	{
		
		for(int i = 0; i < 5; i++)
		{
			g.drawImage(backgroundImage, (i*400) - (model.mario.x - model.mario.marioScreenLocation) /25, 0, 
					this.getWidth(), this.getHeight(), null); 
			
		}
		
		for(int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i); 
			s.draw(g);
		}
	}
	
}
