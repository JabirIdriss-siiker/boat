/*
 * Class de fin de jeu lors d'une defaite
 */
package states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Assets;
import project_java.Handler;

public class MortState extends State {

	public boolean joueur = false;
	protected BufferedImage texture = Assets.mort;
	
	public MortState(Handler handler)
	{
		super(handler);
	}
	
	@Override
	public void tick() {
		//On teste si le bouton gauche de la souris a ete clicker dans un intervalle conçus pour le "bouton" rejouer
		//si c'est vrai on change la scene a MenuState(scene du menu du jeu)
		if(handler.getMouseManager().isClickGauche() && 
			       (handler.getMouseManager().getMouseX() > 80 && 
			    	handler.getMouseManager().getMouseX() < 370)&&
			       (handler.getMouseManager().getMouseY() > 600 && 
			    	handler.getMouseManager().getMouseY() < 650))
				{
					State.setState(handler.getGame().menuState);
				}
		//sinon si le click se fait sur le button "quitter" on ferme le jeu
		if(handler.getMouseManager().isClickGauche() && 
			       (handler.getMouseManager().getMouseX() > 672 && 
			    	handler.getMouseManager().getMouseX() < 942)&&
			       (handler.getMouseManager().getMouseY() > 600 && 
			    	handler.getMouseManager().getMouseY() < 650))
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
