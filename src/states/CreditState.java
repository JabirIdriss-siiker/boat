/*
 * Class du menu credit
 */
package states;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Assets;
import project_java.Handler;
import tile.mondes.Monde;

public class CreditState extends State {

	public boolean joueur = false;
	protected BufferedImage texture = Assets.credit;
	
	public CreditState(Handler handler)
	{
		super(handler);
	}
	@Override
	public void tick() {
			//System.out.println(handler.getMouseManager().getMouseX());
		if(handler.getMouseManager().isClickGauche() && 
	       (handler.getMouseManager().getMouseX() > 700 && 
	    	handler.getMouseManager().getMouseX() < 850)&&
	       (handler.getMouseManager().getMouseY() > 570 && 
	    	handler.getMouseManager().getMouseY() < 670))
		{
			
			State.setState(handler.getGame().gameState);
			State.getState().setMonde(new Monde(handler,"World/monde.txt"));
			
		}
			
	}

	public boolean getJoueur() {
		return joueur;
	}
	@Override
	public void render(Graphics g) {
	
		g.drawImage(texture,  0, 0,handler.getWidth(),handler.getHeight(), null);
		
	}

	
}
