/*
 * 
 * Scene qui cahrge le deuxieme monde et qui herite de la class State
 */

package states;

import java.awt.Graphics;
import project_java.Handler;

import tile.mondes.Monde2;

public class GameState2 extends State {

	private Monde2 monde2;

	
	public GameState2(Handler handler)
	{
		super(handler);
		monde2 = new Monde2(handler,"World/monde2.txt");
		handler.setMonde2(monde2);
		
				
	}

	public Monde2 getMonde2() {
		return monde2;
	}


	public void setMonde2(Monde2 monde) {
		this.monde2 = monde;
	}
	
	@Override
	public void tick() {
		monde2.tick();
		
	
		
	}

	@Override
	public void render(Graphics g) {
		monde2.render(g);
	
	}
	
	

	
}