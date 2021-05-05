/*
 * Class qui permet de choisir un bateau
 * 
 */


package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Assets;
import project_java.Handler;
import tile.mondes.Monde;


public class ChoixBateau extends State {

	public boolean joueur = false;
	protected BufferedImage texture = Assets.choixBateau;
	
	public ChoixBateau(Handler handler)
	{
		super(handler);
	}
	@Override
	public void tick() {
		//si le joueur clique sur un bateau on charge sa scene 
		if(handler.getMouseManager().isClickGauche() && 
			       (handler.getMouseManager().getMouseX() > 262 && 
			    	handler.getMouseManager().getMouseX() < 503)&&
			       (handler.getMouseManager().getMouseY() > 238 && 
			    	handler.getMouseManager().getMouseY() < 401))
				{
					State.setState(handler.getGame().mondeState);
					State.getState().setMonde(new Monde(handler,"World/monde2.txt"));
				}
		
		if(handler.getMouseManager().isClickGauche() && 
			       (handler.getMouseManager().getMouseX() > 765 && 
			    	handler.getMouseManager().getMouseX() < 989)&&
			       (handler.getMouseManager().getMouseY() > 242 && 
			    	handler.getMouseManager().getMouseY() < 396))
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
		g.setColor(Color.red);
	
		g.drawImage(texture,  0, 0,handler.getWidth(),handler.getHeight(), null);
	
		
		
	}


	
}
