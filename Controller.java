import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener
{
	View view;
	Model model;
	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace; 
	boolean keyS;
	boolean keyL;
	boolean edit; 
	Brick b;

	Controller(Model m)
	{
		model = m; 
		model.unmarshal(Json.load("map.json"));
		edit = false; 
	}
	
	void setView(View v)
	{
		view = v;
	}
	
	public void mousePressed(MouseEvent e) // when mouse is pressed gets x and y
	{
		
		if(edit == true)
			b = new Brick(e.getX() + model.mario.x - model.mario.marioScreenLocation, e.getY(), model);
		
	}

	public void mouseReleased(MouseEvent e) // when mouse is released
	{    
		if(edit) 
		{
			b.endBrick(e.getX() + model.mario.x - model.mario.marioScreenLocation, e.getY());
			model.sprites.add(b); 
		}
	}
		
	public void mouseEntered(MouseEvent e) 
	{   
		
	}
	public void mouseExited(MouseEvent e) 
	{   
		
	}
	public void mouseClicked(MouseEvent e)
	{    
		//if(e.getY() < 100)
		//{
		//	System.out.println("break here");
		//}
	}
	
	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; 
			break;
			case KeyEvent.VK_LEFT: keyLeft = true; 
			break;
			case KeyEvent.VK_UP: keyUp = true; 
			break;
			case KeyEvent.VK_DOWN: keyDown = true; 
			break;
			case KeyEvent.VK_SPACE: keySpace = true; 
			break;
			case KeyEvent.VK_S: keyS = true; 
			break;
			case KeyEvent.VK_L: keyL = true; 
			break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; 
			break;
			case KeyEvent.VK_LEFT: keyLeft = false; 
			break;
			case KeyEvent.VK_UP: keyUp = false; 
			break;
			case KeyEvent.VK_DOWN: keyDown = false; 
			break;
			case KeyEvent.VK_SPACE: keySpace = false; 
			break;
			case KeyEvent.VK_S: keyS = false; 
			{ 
				model.marshal().save("map.json");
				break;
			}
			
			case KeyEvent.VK_L: keyL = false; 
			{ 
				model.unmarshal(Json.load("map.json"));
				break;
			}
		}
			char c = e.getKeyChar(); 
			if(c == 's' || c == 'S')
			{
				model.marshal().save("map.json");
			}
			if(c == 'l' || c == 'L')
			{
				Json j = Json.load("map.json"); 
				model.unmarshal(j); 
			}
			if(c == 'q' || c == 'Q')
			{
				System.exit(0); 
			}
			if(c == 'e' || c == 'E')
			{
				edit = !edit; 
			}
	}
	
	public void keyTyped(KeyEvent e)
	{
		
	}

	void update() // updates the scrolling screen
	{
		model.mario.lastDest(); // mario previous destination
		if(keyRight) // if right key
		{
			model.mario.x += 4;
			model.mario.updateImage();
		}
		if(keyLeft) // if left key
		{
			model.mario.x -= 4;
			model.mario.updateImage();
		}
		if(keySpace) // if space key
		{
			if(model.mario.spaceFrame < 5)
			{
				model.mario.vert_velocity -= 7.1;
				model.mario.y += model.mario.vert_velocity; 
				
			}
		}
		
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
