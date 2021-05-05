/*
 * Scene qui charge le premier monde
 * 
 */


package states;

import java.awt.Graphics;
import project_java.Handler;



import tile.mondes.Monde;

public class GameState extends State {

	private Monde monde;

	
	public GameState(Handler handler)
	{
		super(handler);
		
		monde = new Monde(handler,"World/monde.txt");
		handler.setMonde(monde);
		
				
	}


	public Monde getMonde() {
		return monde;
	}


	public void setMonde(Monde monde) {
		this.monde = monde;
	}


	@Override
	public void tick() {
		
		monde.tick();
	
		
		
	
		
	}
	
	@Override
	public void render(Graphics g) {
	
		monde.render(g);
		
	
		
		
		
	}



	

	
}