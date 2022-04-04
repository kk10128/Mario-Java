import java.awt.Graphics;
import java.awt.image.BufferedImage;

class Mario extends Sprite
{
	
	int lastX, lastY; 	
	float vert_velocity;
	int marImageNum; 
	int spaceFrame;
	static BufferedImage[] mario_images = null;
	int marioScreenLocation;

	
	public Mario() 
	{
		x = 0; 
		y = 0; 
		w = 60; 
		h = 95; 
		
		marioScreenLocation = 200;
		marImageNum = 0; 
		spaceFrame = 0; 
		vert_velocity = 0;
		
		if (mario_images == null) // loads mario images
		{
			mario_images = new BufferedImage[5];
			mario_images[0] = View.loadImage("mario1.png");
			mario_images[1] = View.loadImage("mario2.png");
			mario_images[2] = View.loadImage("mario3.png");
			mario_images[3] = View.loadImage("mario4.png");
			mario_images[4] = View.loadImage("mario5.png");
		}	
	}
	
	void updateImage() 
	{
		marImageNum++;
		if(marImageNum > 4)
			marImageNum = 0; 
	}
	
	@Override
	public boolean update() 
	{

		if(y >= 310)
		{
			vert_velocity = 0;  
			y = 310;
			spaceFrame = 0;
		}
		else if (y < 310)
		{ 
			vert_velocity += 2;
			y += vert_velocity;
		}
		spaceFrame++;
		return true; 
	}
	
	void lastDest()
	{
		lastX = x;
		lastY = y; 
	}
	
	boolean stopMario(Brick b)
	{
		if(x + w >= b.x && lastX + w <= b.x) // left collision
		{
            x = b.x - w;
		}
		if (x <= b.x + b.w && lastX >= b.x + b.w) //right collision 
        {
            x = b.x + b.w;
        }
        if (y + h >= b.y && lastY + h <= b.y)  //top collision 
        {
            y = b.y - h;
            spaceFrame = 0; 
            vert_velocity = 2;
        }
        if (y <= b.y + b.h && lastY >= b.y + b.h)  //bottom collision
        {
            y = b.y + b.h;
            vert_velocity = 0;
            return true; 
        }
        return false; 
	}
	
	
	void drawYourself(Graphics g)
	{
		g.drawImage(mario_images[marImageNum], marioScreenLocation, y, null);
	}
	
	@Override
	void draw(Graphics g)
	{
		g.drawImage(mario_images[marImageNum], marioScreenLocation, y, null);
	}
	
	@Override
	boolean isMario()
	{
		return true; 
	}

}
