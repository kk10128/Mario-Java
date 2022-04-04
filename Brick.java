import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Brick extends Sprite
{

	static BufferedImage image = null; 
	static BufferedImage coinBlockImage = null; 

	Model model; 
	Random r; 
	boolean CoinBlock = false; 
	int numCoinsReleased = 5;


	public Brick(int x1, int y1, Model m) 
	{
		x = x1;
		y = y1;
		w = 10;
		h = 10;
		loadImage();
		model = m; 
		r = new Random(); 
		randomCoinBlock(); 
	}


	void loadImage() // loads brick and coin block image in
	{ 
		if (image == null)
		{
			image = View.loadImage("brick.png");
			
		}
		if (coinBlockImage == null)
		{
			coinBlockImage = View.loadImage("coinblock.png"); 
		}
	}
	
	void randomCoinBlock() // randomizes coin blocks
	{
		int random = r.nextInt(2) + 1;
		if(random == 1)
		{
			CoinBlock = true;
		}
	}
	 
	// unmarshaling
	public Brick(Json ob, Model m) 
	{
		x = (int) ob.getLong("x");
		y = (int) ob.getLong("y");
		w = (int) ob.getLong("w");
		h = (int) ob.getLong("h");
		loadImage();
		model = m;
		r = new Random(); 
		randomCoinBlock(); 
	}
	
	public void endBrick(int x2, int y2) 
	{
		w = Math.abs(x2 - x);
		h = Math.abs(y2 - y);
		if(x2 < x)
		{
			x = x2; 
		}
		if(y2 < y)
		{
			y = y2; 
		}
	}
	
	@Override
	public boolean update()
	{
		return true; 
	}
	
	@Override
	boolean isBrick()
	{
		return true;
	}
	 
	@Override
	boolean isCoinBlock()
	{
		return CoinBlock; 
	}
	
	void releaseCoin() // releases coins with limit from coin block
	{
		Coin coin = new Coin(model, (x+w/2), y);
        model.sprites.add(coin); 
		numCoinsReleased--;

		if(numCoinsReleased == 0)
		{                   
			CoinBlock = false; 
		}	
	}
	
	Json marshal() // marshalls
	{
		Json ob = Json.newObject();
		ob.add("x", x);
		ob.add("y", y);
		ob.add("w", w);
		ob.add("h", h);
		loadImage();
		return ob;
	}

	void draw(Graphics g) // draws coin blocks and bricks image
	{
		if(CoinBlock == false)
		{
			g.drawImage(image, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
		}
		else if (CoinBlock == true)
		{
			g.drawImage(coinBlockImage, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);

		}
	}

}
