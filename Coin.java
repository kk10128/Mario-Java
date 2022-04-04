import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

class Coin extends Sprite
{
	static BufferedImage image = null; 
	Model model;
	int coinLimit; 
	Random r; 
	double hor_velocity; 
	double vert_velocity;
	
	
	public Coin(Model m, int x, int y) 
	{
		this.x = x; 
		this.y = y; 
		w = 75; 
		h = 75; 
		r = new Random(); 
        hor_velocity = r.nextInt(5) - 1;  // adds random horizontal velocity
        //vert_velocity = r.nextInt(20) - 1; 
		model = m;
		loadImage();
	}
	
	
	void loadImage() // loads coin image
	{ 
		if (image == null)
			image = View.loadImage("coin.png");	 
	}
	
	boolean update() // updates coin velocity
	{
		vert_velocity += 2;
		y += vert_velocity;
		x += hor_velocity; 

		return true; 
	}

	@Override
	boolean isCoin()
	{
		return true; 
	}
	
	
	void draw(Graphics g) // draws coin image
	{
		g.drawImage(image, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
	}
}