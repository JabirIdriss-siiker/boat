/*
 * Class du Menu elle herite La classe State 
 * 
 */

package states;


import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Assets;
import project_java.Handler;
import tile.mondes.Monde;

public class MenuState extends State {

	public boolean joueur = false;
	
	protected BufferedImage texture = Assets.menu;
	
	public MenuState(Handler handler)
	{
		super(handler);
		
	}
	
	//Dans cette methode on simule le fonctionement d'un boutton 
	@Override
	public void tick() {
			//On teste si le bouton gauche de la souris a ete clicker dans un intervalle conçus pour le "bouton" jouer
			//si c'est vrai on change la scene a gameState(scene par defaut du jeu)on chargeant un nouveau monde 
		if(handler.getMouseManager().isClickGauche() && 
	       (handler.getMouseManager().getMouseX() > 385 && 
	    	handler.getMouseManager().getMouseX() < 823)&&
	       (handler.getMouseManager().getMouseY() > 350 && 
	    	handler.getMouseManager().getMouseY() < 450))
		{
			
			State.setState(handler.getGame().gameState);
			State.getState().setMonde(new Monde(handler,"World/monde.txt"));
			
		}
		//si on clique sur la zone de credit on charge la scene du credit
		else if (handler.getMouseManager().isClickGauche() && 
	       (handler.getMouseManager().getMouseX() > 380 && 
	    	handler.getMouseManager().getMouseX() < 830)&&
	       (handler.getMouseManager().getMouseY() > 530 && 
	    	handler.getMouseManager().getMouseY() < 630))
		{
			
			 
			State.setState(handler.getGame().creditState);
			
			
		}
		//si on clique sur la zone de choix de bateau on charge cette scene
		else if(handler.getMouseManager().isClickGauche() && 
	       (handler.getMouseManager().getMouseX() > 1088 && 
	    	handler.getMouseManager().getMouseX() < 1350)&&
	       (handler.getMouseManager().getMouseY() > 262 && 
	    	handler.getMouseManager().getMouseY() < 332))
		{
			
			State.setState(handler.getGame().ChoixBateau);
			
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
