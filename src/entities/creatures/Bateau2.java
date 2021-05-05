/*
 * Class du deuxieme Bateau qui herite de Creature pour pouvoir 
 * avoir acces aux methode du gameplay
 * 
 */


package entities.creatures;


import java.awt.Color;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gfx.Animation;
import gfx.Assets;
import project_java.Handler;


public class Bateau2 extends Creature{
	
	//Animations
	
	private Animation animBas,animHaut,animGauche,animDroite;
	
	//On definie une font 
	private static Font sanSerifFont = new Font("SanSerif", Font.BOLD, 11);
	
	public Bateau2(Handler handler,float x, float y) {
		super(handler,x, y,Creature.DEFAULT_CREATURE_WIDTH,Creature.DEFAULT_CREATURE_HEIGHT);
		//les bordures du rectangle du collision
		limites.x = 10;
		limites.y = 10;
		limites.width = 70;
		limites.height = 50;
		
		//Les animations
		animHaut = new Animation(1000, Assets.bateau1_haut);
		animBas = new Animation(1000, Assets.bateau1_bas);
		animGauche = new Animation(1000, Assets.bateau1_gauche);
		animDroite = new Animation(1000, Assets.bateau1_droite);
		
	
	}
	//on appelle les methodes qui doivent s'appeler dans la boucle du jeu les images, le gameplay etc
	@Override
	public void tick() {
		animHaut.tick();
		animBas.tick();
		animGauche.tick();
		animDroite.tick();
		
		getInput();
		moveM2();
		
		handler.getCamera().centrerSurJoueur2(this);
	}
	
	//on lie chaque touche a un comportement 
	private void getInput()
	{
		yMve = 0;
		xMve = 0;
		
		if(handler.getKeyManager().haut)
			yMve = -(Vitesse*1.4f);
		if(handler.getKeyManager().bas)
			yMve = (Vitesse*1.4f);
		if(handler.getKeyManager().gauche)
			xMve = -(Vitesse*1.4f);
		if(handler.getKeyManager().droite)
			xMve = (Vitesse*1.4f);
		
	}
	//on render les images dans le champs qu'on voit et aussi les informations du jeu
	@Override
	public void render(Graphics g) {
		
			g.drawImage(getCurrentAnimationFrame(),(int) (x - handler.getCamera().getxOffset()),
					(int) (y-handler.getCamera().getyOffset()),width,height, null);
			g.setFont(sanSerifFont);
			
		
		    g.drawString("Direction vents : " + String.valueOf(dir), 400,100 );
		    g.drawString("Vitesse vents : " + String.valueOf(VitesseVents)+" noeud", 400,120 );
		    
		    g.drawString("Vitesse bateau : " + String.valueOf(String.format("%.1f", Vitesse*1.4f))+" noeud", 200,100 );
		    g.drawString("Hauteur Vague : " + String.valueOf(hauteur)+" m", 200,120 );
		    g.drawString("Vitesse courant : " + String.valueOf(Courant)+" noeud", 200,140 );
		    
		    if(vie >= 200)
		    {
		    	g.setColor(Color.green);
		    }
		    	
	    	if(vie <200)
		    {
		    	g.setColor(Color.red);
		    }
		
	    	g.drawString("Vie : " + String.valueOf( (int) (vie/100)), 600,100 );
		
		
	}
	
	//on affiche les images (animations) pour chaque deplacement 
	private BufferedImage getCurrentAnimationFrame()
	{
		if (yMve < 0)
		{
			return animHaut.getCurrentFrame();
		}  
		else if(yMve > 0)
		{
			return animBas.getCurrentFrame();
		}
		 if ( xMve < 0 )
		{
			return animGauche.getCurrentFrame();
		}else 
			return animDroite.getCurrentFrame();
		
		
		
		
		
		
	}

	
}
