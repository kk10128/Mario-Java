import java.util.ArrayList;
import java.util.Iterator;

//Kavya Kannan
//Assignment 5

class Model
{
	Mario mario; 
	Coin coin; 
	Brick brick;
	int x, y;
	int coinNum; 
	ArrayList<Sprite> sprites;

	Model()
	{
		sprites = new ArrayList <Sprite>(); // array list of sprites
		mario = new Mario(); 
		sprites.add(mario);  // adding mario sprite
		Json j = Json.load("map.json");
		this.unmarshal(j);		
	}
	
	public void update()
	{
		Iterator<Sprite> iter = sprites.iterator();
		while (iter.hasNext())
		{
			Sprite s = iter.next(); 
			s.update();
			if(s.isBrick())
			{
				if(marioColliding((Brick)s)) // if mario collides
				{
					mario.stopMario((Brick)s); // collision detection
					if(mario.stopMario((Brick)s) && s.isCoinBlock() == true) // if mario collides bottom of brick and hits a coin block
					{
						Brick b = ((Brick)s);
						b.releaseCoin();  // releases a coin
						break;
					}
				}	
			}
		}	
	}
	

	boolean marioColliding(Brick b) // detects colliding in 4 directions
	{
		if(mario.x + mario.w <= b.x)
			return false; 
		if(mario.x >= b.x + b.w)
			return false;
		if(mario.y + mario.h <= b.y)
			return false; 
		if(mario.y >= b.y + b.h)
		{
			return false; 
		}
		return true; 
	}
	
	
	Json marshal() // marshall method
    {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("bricks", tmpList);
        for(int i = 0; i < sprites.size(); i++)
        {
        	Sprite s = sprites.get(i); 
        	if(s.isBrick())
        	{
        		Brick b = (Brick)s; 
        		tmpList.add(b.marshal());
        	}
        }
        return ob;
    }
	
	public void unmarshal(Json ob) // unmarshall method
	{

        sprites = new ArrayList<Sprite>();
        sprites.add(mario);
        Json tmpList = ob.get("bricks");
        for(int i = 0; i < tmpList.size(); i++)
        {
        	//Brick b = new Brick(tmpList.get(i),this);
            sprites.add(new Brick(tmpList.get(i), this));
            
        }

	}
	
}