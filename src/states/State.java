/*
 * 
 * Class abstarct qui s'occupe des Scene
 */

package states;

import java.awt.Graphics;
import project_java.Handler;
import tile.mondes.Monde;


public abstract class State 
{
	
	private static State currentState = null;
	private Monde monde;
	
	
	public static void setState(State state)
	{
		currentState = state;
	}
	public static State getState()
	{
		return currentState;
	}
	
	protected Handler handler;
	
	public State(Handler handler)
	{
		this.handler = handler;
	}
	
	public Monde getMonde() {
		return monde;
	}


	public void setMonde(Monde monde) {
		this.monde = monde;
	}
	
	

	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public boolean getJoueur() {
		
		return false;
	}
	
}
