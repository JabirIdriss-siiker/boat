/*
 * Class de fin de jeu (Victoire)
 */
package states;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gfx.Assets;
import project_java.Handler;

public class ExiteState extends State {

	public boolean joueur = false;
	protected BufferedImage texture = Assets.exit;
	
	public ExiteState(Handler handler)
	{
		super(handler);
	}
	@Override
	public void tick() {
			
		if(handler.getMouseManager().isClickGauche() && 
			       (handler.getMouseManager().getMouseX() > 44 && 
			    	handler.getMouseManager().getMouseX() < 250)&&
			       (handler.getMouseManager().getMouseY() > 642 && 
			    	handler.getMouseManager().getMouseY() < 680))
				{
					State.setState(handler.getGame().menuState);
				}
		if(handler.getMouseManager().isClickGauche() && 
			       (handler.getMouseManager().getMouseX() > 567 && 
			    	handler.getMouseManager().getMouseX() < 866)&&
			       (handler.getMouseManager().getMouseY() > 643 && 
			    	handler.getMouseManager().getMouseY() < 681))
				{
					System.exit(0);
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
