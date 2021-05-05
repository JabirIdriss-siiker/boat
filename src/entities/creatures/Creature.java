/*
 * Cette class permet de definir les collisons avec les differents 
 * Tile du jeu et les bases du gameplay des bateaux
 * 
 * 
 */


package entities.creatures;



import entities.Entity;

import project_java.Handler;
import states.ExiteState;
import states.MortState;
import states.State;
import tile.Tile;

public abstract class Creature extends Entity{
	
	public State exitState;
	public State mortState;
	
	//Definition des Bases du gameplay vitesse, vie, taille des images etc..
	public static final float DEFAULT_HEALTH = 300f;
	public static  float DEFAULT_VITESSE = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 80,
							DEFAULT_CREATURE_HEIGHT = 80;
	protected float vie;
	protected float Vitesse;
	protected float xMve,yMve;

	
	protected String dir = "";
	protected String hauteur = "";
	protected String Courant = "";
	protected float VitesseVents;

	
	public Creature(Handler handler,float x, float y,int width,int height) {
		super(handler,x, y,width,height);
		vie = DEFAULT_HEALTH;
		Vitesse = DEFAULT_VITESSE;	
		mortState = new MortState(handler);
		exitState = new ExiteState(handler); 
		
		}
	
	//methode pricipale pour le deplacement elle appelle tout les methodes qui gere le gameplay pour le premier bateau
	//impliquant aussi le premier monde 
	public void move()
	{

		
		dir = "Normal ";
		VitesseVents = 0;
		hauteur = "0";
		Courant="3.0";
		
		Vitesse = DEFAULT_VITESSE; 
		
		VentsGauche();
		VentsDroite();
		VentsNord();
		VentsSud();
		Vague();
		Courant();
		Pont();	
		Death();
		moveX();
		moveY();
	
	}
	
	//methode pricipale pour le deplacement du deuxieme bateau elle appelle tout les methodes qui gere le gameplay
	//dans le deuxieme monde 
	public void moveM2()
	{

		
		dir = "Normal ";
		VitesseVents = 0;
		hauteur = "0";
		Courant="3.0";
		
		Vitesse = DEFAULT_VITESSE; 
		
		VentsGaucheM2();
		VentsDroiteM2();
		VentsNordM2();
		VentsSudM2();
		VagueM2();
		CourantM2();
		PontM2();	
		DeathM2();
		moveXM2();
		moveYM2();
		
		
		
		
		
	}
	
	//collison avec les iles et les objets solide sur l'axe X
	public void moveX()
	{
		if (xMve > 0)
		{//dir droite
		
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   !collisionWithTile(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				x += xMve;
				
				
			}else 
			{
				x = tx * Tile.TILEWIDTH - limites.x - limites.width-1;
				
			}
		}
		
		
		else if (xMve < 0)
		{//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(!collisionWithTile(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   !collisionWithTile(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				
				x+=xMve;
				
			}else
			{
				x = tx * Tile.TILEWIDTH - limites.x + Tile.TILEWIDTH ;
			}
		}
		
	}
	
	//collision avec les iles et les objets solide sur l'axe Y
	public void moveY()
	{
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       !collisionWithTile((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
				
				y += yMve;
			}else
			{
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - limites.y;
			}
			
		}else if (yMve > 0 )
		{
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       !collisionWithTile((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		      && ty < handler.getMonde().getHeight())
			{
				
				
				
				y += yMve;
			}else 
			{
				y = ty * Tile.TILEHEIGHT - limites.y - limites.height - 1;
				
			}
			
		}
		
	}
	
	//collisions avec le vents venant de la droite 
	public void VentsDroite()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithTile2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				
				dir = "Est";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithTile2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				dir = "Est";
				Vitesse = 4.5f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
				
				
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
				
				dir = "Est";
				Vitesse = DEFAULT_VITESSE * 0.7f; ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
			
		}else if (yMve > 0 )
		{
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		      && ty < handler.getMonde().getHeight())
			{
				
				dir = "Est";
				Vitesse = DEFAULT_VITESSE * 0.7f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			
			}
		}
		if(xMve < 0 && (yMve > 0 || yMve<0))//le joueur se deplace en diagonal 
		{
			
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			if(collisionWithTile2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0 
			    && collisionWithTile2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
			       collisionWithTile2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
				      && ty < handler.getMonde().getHeight())
			{
				dir = "Est";
				Vitesse = 3.5f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
		}
		
		
	}
	//collisions avec le vents venant de la gauche meme idee que droite on inverse juste les vitesse 
	public void VentsGauche()
	{
		
		if (xMve > 0)
		{//dir droite
		
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			if(collisionWithTile3(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile3(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				
				
				dir = "Ouest";
				
				Vitesse = 4.5f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		
		else if (xMve < 0)
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithTile3(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile3(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				dir = "Ouest";
				Vitesse = Vitesse * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile3((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile3((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
				dir = "Ouest";
				Vitesse = DEFAULT_VITESSE * 0.7f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			
				
			}
		}
		else if (yMve > 0 )
		{
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile3((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile3((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		      && ty < handler.getMonde().getHeight())
			{
				
				dir = "Ouest";
				Vitesse = DEFAULT_VITESSE * 0.7f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
				
				
			}
			
		}
		if(xMve > 0 && (yMve > 0 || yMve<0))
		{
			//diagonale droite bas
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			if(collisionWithTile3(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile3(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0 
			    && collisionWithTile3((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
			       collisionWithTile3((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
				      && ty < handler.getMonde().getHeight())
			{
				Vitesse = 3.5f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		
	}
	
	//collisions avec le vents venant de Haut 
	public void VentsNord()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithTile4(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile4(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				dir = "Nord";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithTile4(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile4(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				dir = "Nord";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile4((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile4((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
				dir = "Nord";
				Vitesse = DEFAULT_VITESSE * 0.7f; ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithTile4((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
			       collisionWithTile4((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			      && ty < handler.getMonde().getHeight())
				{
					dir = "Nord";
					Vitesse = 4.5f;
					VitesseVents = DEFAULT_VITESSE * 0.7f;
				}
				
			}
		}
	//collisions avec le vents venant du bas 
	public void VentsSud()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithTile5(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile5(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				dir = "Sud";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
				
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithTile5(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile5(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				dir = "Sud";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile5((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile5((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
					dir = "Sud";
					Vitesse = 4.5f;
					VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithTile5((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
			       collisionWithTile5((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			      && ty < handler.getMonde().getHeight())
				{
					dir = "Sud";
					Vitesse = DEFAULT_VITESSE * 0.7f; ;
					VitesseVents = DEFAULT_VITESSE * 0.7f;
					
				}
				
			}
		}
	//collison avec les vagues
	public void Vague()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithVague(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
		       collisionWithVague(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				hauteur = "2";
				Vitesse = DEFAULT_VITESSE * 0.4f;
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithVague(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithVague(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				hauteur = "2";
				Vitesse = DEFAULT_VITESSE * 0.4f;

			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithVague((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
					collisionWithVague((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
					hauteur = "2";
					Vitesse = DEFAULT_VITESSE * 0.4f; ;
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithVague((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
						collisionWithVague((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			      && ty < handler.getMonde().getHeight())
				{
					hauteur = "2";
					Vitesse = DEFAULT_VITESSE * 0.4f; ;
					
					
				}
				
			}
		}
		
	//collison avec le courant 
	public void Courant()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithCourant(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
		       collisionWithCourant(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				Courant = "3.6";
				Vitesse = DEFAULT_VITESSE + DEFAULT_VITESSE * 0.2f; 
				 
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithCourant(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithCourant(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				Courant = "3.6";
				Vitesse = DEFAULT_VITESSE + DEFAULT_VITESSE * 0.2f; 

			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithCourant((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
					collisionWithCourant((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
					Courant = "3.6";
					Vitesse = DEFAULT_VITESSE + DEFAULT_VITESSE * 0.2f; 
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithCourant((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
						collisionWithCourant((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			      && ty < handler.getMonde().getHeight())
				{
					Courant = "3.6";
					Vitesse = DEFAULT_VITESSE + DEFAULT_VITESSE * 0.2f; 
					
					
				}
				
			}
		}
	
	//collision avec le port changeant ainsi la scene en ExiteState(victoire)
	public void Pont()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithPont(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
		       collisionWithPont(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) 
			  )
			{
				
				 State.setState(exitState);
				
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithPont(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
					collisionWithPont(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    )
			{
				
				State.setState(exitState);
				
				
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithPont((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
					collisionWithPont((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       )
			{
				
				State.setState(exitState);
				
					
				
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithPont((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
						collisionWithPont((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			     )
				{
					
					State.setState(exitState);
					
					
					
				}
				
			}
		}
	
	//collision avec les mountains qui diminue la vie si la vie ==0 on change la scene en MortState celle de la defaite
	public void Death()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithMountains(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
				collisionWithMountains(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) 
			  )
			{
				if((int) vie/100>2 && (int) vie/100 <=3) {
					 vie = vie - 0.7f;
				}
					
				if((int) vie/100>=1 && (int) vie/100 <=2)
					 {vie = vie - 0.7f;}
				if((int) vie/100<1)
				{
					 vie = vie - 0.7f;
				}
				if ((int) vie/100 == 0) {
					State.setState(mortState);
				}
					
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithMountains(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
					collisionWithMountains(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    )
			{
				
				if((int) vie/100>2 && (int) vie/100 <=3) {
					 vie = vie - 0.7f;
				}
					
				if((int) vie/100>=1 && (int) vie/100 <=2)
					 {vie = vie - 0.7f;}
				if((int) vie/100<1)
				{
					 vie = vie - 0.7f;
				}
				if ((int) vie/100 == 0) {
					State.setState(mortState);
				}
					
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithMountains((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
					collisionWithMountains((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       )
			{
				if((int) vie/100>2 && (int) vie/100 <=3) {
					 vie = vie - 0.7f;
				}
					
				if((int) vie/100>=1 && (int) vie/100 <=2)
					 {vie = vie - 0.7f;}
				if((int) vie/100<1)
				{
					 vie = vie - 0.7f;
				}
				if ((int) vie/100 == 0) {
					State.setState(mortState);
				}
				
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithMountains((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
						collisionWithMountains((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			     )
				{
					if((int) vie/100>2 && (int) vie/100 <=3) {
						 vie = vie - 0.7f;
					}
						
					if((int) vie/100>=1 && (int) vie/100 <=2)
						 {vie = vie - 0.7f;}
					if((int) vie/100<1)
					{
						 vie = vie - 0.7f;
					}
					if ((int) vie/100 == 0) {
						State.setState(mortState);
					}
					
				}
				
			}
		}
		
		/*
		 * ******************** MONDE 2 *****************************
		 * Meme comportement que les methodes qui gere le premier monde
		 */
	
	
	public void moveXM2()
	{
		if (xMve > 0)
		{//dir droite
		
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			if(!collisionWithTileM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   !collisionWithTileM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde2().getWidth())
			{
				x += xMve;
				
				
			}else 
			{
				x = tx * Tile.TILEWIDTH - limites.x - limites.width-1;
				
			}
		}
		
		
		else if (xMve < 0)
		{//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(!collisionWithTileM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   !collisionWithTileM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				
				x+=xMve;
				
			}else
			{
				x = tx * Tile.TILEWIDTH - limites.x + Tile.TILEWIDTH ;
			}
		}
		
	}
	
	
	public void moveYM2()
	{
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(!collisionWithTileM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       !collisionWithTileM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
				
				y += yMve;
			}else
			{
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - limites.y;
			}
			
		}else if (yMve > 0 )
		{
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			
			if(!collisionWithTileM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       !collisionWithTileM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		      && ty < handler.getMonde().getHeight())
			{
				
				
				
				y += yMve;
			}else 
			{
				y = ty * Tile.TILEHEIGHT - limites.y - limites.height - 1;
				
			}
			
		}
		
	}
	
	//collisions avec le vents venant de la droite 
	public void VentsDroiteM2()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithTile2M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile2M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				
				dir = "Est";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithTile2M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile2M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				dir = "Est";
				Vitesse = 4.5f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
				
				
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile2M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile2M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
				
				dir = "Est";
				Vitesse = DEFAULT_VITESSE * 0.7f; ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
			
		}else if (yMve > 0 )
		{
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile2M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile2M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		      && ty < handler.getMonde().getHeight())
			{
				
				dir = "Est";
				Vitesse = DEFAULT_VITESSE * 0.7f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			
			}
		}
		if(xMve < 0 && (yMve > 0 || yMve<0))//le joueur se deplace en diagonal 
		{
			
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			if(collisionWithTile2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0 
			    && collisionWithTile2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
			       collisionWithTile2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
				      && ty < handler.getMonde().getHeight())
			{
				dir = "Est";
				Vitesse = 3.5f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
		}
		
		
	}
	//collisions avec le vents venant de la gauche meme idee que droite on inverse juste les vitesse 
	public void VentsGaucheM2()
	{
		
		if (xMve > 0)
		{//dir droite
		
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			if(collisionWithTile3M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile3M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				
				
				dir = "Ouest";
				
				Vitesse = 4.5f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		
		else if (xMve < 0)
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithTile3M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile3M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				dir = "Ouest";
				Vitesse = Vitesse * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile3M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile3M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
				dir = "Ouest";
				Vitesse = DEFAULT_VITESSE * 0.7f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			
				
			}
		}
		else if (yMve > 0 )
		{
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile3M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile3M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		      && ty < handler.getMonde().getHeight())
			{
				
				dir = "Ouest";
				Vitesse = DEFAULT_VITESSE * 0.7f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
				
				
			}
			
		}
		if(xMve > 0 && (yMve > 0 || yMve<0))
		{
			//diagonale droite bas
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
			if(collisionWithTile3M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile3M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0 
			    && collisionWithTile3M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
			       collisionWithTile3M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
				      && ty < handler.getMonde().getHeight())
			{
				Vitesse = 3.5f ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		
	}
	
	//collisions avec le vents venant de Haut 
	public void VentsNordM2()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithTile4M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile4M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				dir = "Nord";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithTile4M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile4M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				dir = "Nord";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile4M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile4M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
				dir = "Nord";
				Vitesse = DEFAULT_VITESSE * 0.7f; ;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithTile4M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
			       collisionWithTile4M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			      && ty < handler.getMonde().getHeight())
				{
					dir = "Nord";
					Vitesse = 4.5f;
					VitesseVents = DEFAULT_VITESSE * 0.7f;
				}
				
			}
		}
	//collisions avec le vents venant du bas 
	public void VentsSudM2()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithTile5M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile5M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				dir = "Sud";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
				
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithTile5M2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithTile5M2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				dir = "Sud";
				Vitesse = DEFAULT_VITESSE * 0.7f;
				VitesseVents = DEFAULT_VITESSE * 0.7f;
				
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithTile5M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
		       collisionWithTile5M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
					dir = "Sud";
					Vitesse = 4.5f;
					VitesseVents = DEFAULT_VITESSE * 0.7f;
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithTile5M2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
			       collisionWithTile5M2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			      && ty < handler.getMonde().getHeight())
				{
					dir = "Sud";
					Vitesse = DEFAULT_VITESSE * 0.7f; ;
					VitesseVents = DEFAULT_VITESSE * 0.7f;
					
				}
				
			}
		}
	//collison avec les vagues
	public void VagueM2()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithVagueM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
		       collisionWithVagueM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				hauteur = "2";
				Vitesse = DEFAULT_VITESSE * 0.4f;
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithVagueM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithVagueM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				hauteur = "2";
				Vitesse = DEFAULT_VITESSE * 0.4f;

			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithVagueM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
					collisionWithVagueM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
					hauteur = "2";
					Vitesse = DEFAULT_VITESSE * 0.4f; ;
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithVagueM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
						collisionWithVagueM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			      && ty < handler.getMonde().getHeight())
				{
					hauteur = "2";
					Vitesse = DEFAULT_VITESSE * 0.4f; ;
					
					
				}
				
			}
		}
		
	//collison avec le courant 
	public void CourantM2()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithCourantM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
		       collisionWithCourantM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) &&
			   tx < handler.getMonde().getWidth())
			{
				Courant = "3.6";
				Vitesse = DEFAULT_VITESSE + DEFAULT_VITESSE * 0.2f; 
				 
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithCourantM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
			   collisionWithCourantM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    && tx > 0)
			{
				Courant = "3.6";
				Vitesse = DEFAULT_VITESSE + DEFAULT_VITESSE * 0.2f; 

			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithCourantM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
					collisionWithCourantM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       && ty > 0)
			{
					Courant = "3.6";
					Vitesse = DEFAULT_VITESSE + DEFAULT_VITESSE * 0.2f; 
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithCourantM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
						collisionWithCourantM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			      && ty < handler.getMonde().getHeight())
				{
					Courant = "3.6";
					Vitesse = DEFAULT_VITESSE + DEFAULT_VITESSE * 0.2f; 
					
					
				}
				
			}
		}
	//collision avec le port 
	public void PontM2()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithPontM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
		       collisionWithPontM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) 
			  )
			{
				
				 State.setState(exitState);
				
				
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithPontM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
					collisionWithPontM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    )
			{
				
				State.setState(exitState);
				
				
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithPontM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
					collisionWithPontM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       )
			{
				
				State.setState(exitState);
				
					
				
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithPontM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
						collisionWithPontM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			     )
				{
					
					State.setState(exitState);
					
					
					
				}
				
			}
		}
	//collision avec les mountains	
	public void DeathM2()
	{
		
		
		if (xMve > 0)//joueur se deplace vers la droite
		{//dir droite
			//x temp 
			int tx = (int) (x + xMve + limites.x + limites.width) / Tile.TILEWIDTH;
			
			
			if(collisionWithMountainsM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
				collisionWithMountainsM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT) 
			  )
			{
				if((int) vie/100>2 && (int) vie/100 <=3) {
					 vie = vie - 0.7f;
				}
					
				if((int) vie/100>=1 && (int) vie/100 <=2)
					 {vie = vie - 0.7f;}
				if((int) vie/100<1)
				{
					 vie = vie - 0.7f;
				}
				if ((int) vie/100 == 0) {
					State.setState(mortState);
				}
					
			}
		}
		
		
		else if (xMve < 0) // le joueur se deplace vers la gauche
		{
			//dir gauche
			int tx = (int) (x + xMve + limites.x ) / Tile.TILEWIDTH;
			
			if(collisionWithMountainsM2(tx, (int) (y + limites.y) / Tile.TILEHEIGHT) && 
					collisionWithMountainsM2(tx, (int) (y + limites.y + limites.height) / Tile.TILEHEIGHT)
			    )
			{
				
				if((int) vie/100>2 && (int) vie/100 <=3) {
					 vie = vie - 0.7f;
				}
					
				if((int) vie/100>=1 && (int) vie/100 <=2)
					 {vie = vie - 0.7f;}
				if((int) vie/100<1)
				{
					 vie = vie - 0.7f;
				}
				if ((int) vie/100 == 0) {
					State.setState(mortState);
				}
					
			}
		}
		
		if (yMve < 0)
		{
			int ty = (int) (y + yMve + limites.y ) / Tile.TILEHEIGHT;
			
			if(collisionWithMountainsM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
					collisionWithMountainsM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
		       )
			{
				if((int) vie/100>2 && (int) vie/100 <=3) {
					 vie = vie - 0.7f;
				}
					
				if((int) vie/100>=1 && (int) vie/100 <=2)
					 {vie = vie - 0.7f;}
				if((int) vie/100<1)
				{
					 vie = vie - 0.7f;
				}
				if ((int) vie/100 == 0) {
					State.setState(mortState);
				}
				
			}
		}
				
			else if (yMve > 0 )
			{
				int ty = (int) (y + yMve + limites.y+ limites.height  ) / Tile.TILEHEIGHT;
				
				if(collisionWithMountainsM2((int)(x + limites.x) / Tile.TILEWIDTH, ty)&&
						collisionWithMountainsM2((int)(x + limites.x + limites.width) / Tile.TILEWIDTH, ty)
			     )
				{
					if((int) vie/100>2 && (int) vie/100 <=3) {
						 vie = vie - 0.7f;
					}
						
					if((int) vie/100>=1 && (int) vie/100 <=2)
						 {vie = vie - 0.7f;}
					if((int) vie/100<1)
					{
						 vie = vie - 0.7f;
					}
					if ((int) vie/100 == 0) {
						State.setState(mortState);
					}
					
				}
				
			}
		}
		
	
	
							//--------------------------MONDE 1------------------//
	//Des methodes qui retourne l'etat du Tile du premier Monde
	//de base ils retournent faux dans la class Tile 
	//mais Vrai si dans la class de l'objet elle retourne Vrai
	
	
	//collision avec un element solide
	protected boolean collisionWithTile(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isSolid();
	}
	//collision avec un element de vents droit (ZONE vent droit)
	protected boolean collisionWithTile2(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isDroit();
	}
	//collision avec un element de vents gauche (ZONE vent gauche)
	protected boolean collisionWithTile3(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isGauche();
	}
	//collision avec un element de vents nord (ZONE vent Nord)
	protected boolean collisionWithTile4(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isNord();
	}
	//collision avec un element de vents sud (ZONE vent Sud)
	protected boolean collisionWithTile5(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isSud();
	}
	//Collision avec un element de vague
	protected boolean collisionWithVague(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isVague();
	}

	//Collision avec un element de courant
	protected boolean collisionWithCourant(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isCourant();
	}
	
	//Collision avec un element de pont(port)
	protected boolean collisionWithPont(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isPont();
	}
	
	//Collision avec un element de mountains
	protected boolean collisionWithMountains(int x, int y)
	{
		return handler.getMonde().getTile(x, y).isDeath();
	}
	
	
	
											//MONDE 2
	//Des methodes qui retourne l'etat du Tile du deuxieme Monde
	
	//Collision avec un element solide
	protected boolean collisionWithTileM2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isSolid();
	}
	
	//collision avec un element de vents droit (ZONE vent droit)
	protected boolean collisionWithTile2M2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isDroit();
	}
	
	//collision avec un element de vents gauche (ZONE vent gauche)
	protected boolean collisionWithTile3M2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isGauche();
	}
	
	//collision avec un element de vents nord (ZONE vent Nord)
	protected boolean collisionWithTile4M2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isNord();
	}
	
	//collision avec un element de vents sud (ZONE vent Sud)
	protected boolean collisionWithTile5M2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isSud();
	}
	
	//Collision avec un element de vague
	protected boolean collisionWithVagueM2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isVague();
	}
	
	//Collision avec un element de courant
	protected boolean collisionWithCourantM2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isCourant();
	}
	
	//Collision avec un element de pont(port)
	protected boolean collisionWithPontM2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isPont();
	}
	
	//Collision avec un element de mountains
	protected boolean collisionWithMountainsM2(int x, int y)
	{
		return handler.getMonde2().getTile(x, y).isDeath();
	}
	
	//Getters Setters
	public float getxMve() {
		return xMve;
	}
	public void setxMve(float xMve) {
		this.xMve = xMve;
	}
	public float getyMve() {
		return yMve;
	}
	public void setyMve(float yMve) {
		this.yMve = yMve;
	}

}

