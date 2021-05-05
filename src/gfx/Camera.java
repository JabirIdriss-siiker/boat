/*
 * Cette class gere le comportement de la camera 
 */

package gfx;

import entities.Entity;

import project_java.Handler;
import tile.Tile;

public class Camera {

	private float xOffset, yOffset;
	private Handler handler;
	
	public Camera(Handler handler,float xOffset, float yOffset)
	{
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	
	//cette methode permet de centrer la camera sur le premier bateau
	public void centrerSurJoueur(Entity e)
	{
		xOffset = e.getX() - handler.getWidth()/2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight()/2 + e.getHeight() / 2;
		checkBlank();
		
	}
	//cette methode permet de centrer la camera sur le deuxieme bateau
	public void centrerSurJoueur2(Entity e)
	{
		xOffset = e.getX() - handler.getWidth()/2 + e.getWidth() / 2;
		yOffset = e.getY() - handler.getHeight()/2 + e.getHeight() / 2;
		checkBlank2();
		
	}
	//cette methode permet de placer la camera 
	public void move(Handler handler,float xAmt, float yAmt )
	{
		this.handler = handler;
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlank();
		checkBlank2();
	}
	//cette methode permet de ne pas afficher l'espace vide de la fenetre
	public void checkBlank()
	{
		if (xOffset < 0 )
		{
			xOffset = 0;
			
		}else if(xOffset > handler.getMonde().getWidth() * Tile.TILEWIDTH - handler.getWidth())
			xOffset = handler.getMonde().getWidth() * Tile.TILEWIDTH - handler.getWidth();
			
		if(yOffset < 0 )
		{
			yOffset = 0;
		}else if(yOffset > handler.getMonde().getHeight() * Tile.TILEHEIGHT - handler.getHeight())
			yOffset =  handler.getMonde().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		
			
	}
	public void checkBlank2()
	{
		if (xOffset < 0 )
		{
			xOffset = 0;
			
		}else if(xOffset > handler.getMonde2().getWidth() * Tile.TILEWIDTH - handler.getWidth())
			xOffset = handler.getMonde2().getWidth() * Tile.TILEWIDTH - handler.getWidth();
			
		if(yOffset < 0 )
		{
			yOffset = 0;
		}else if(yOffset > handler.getMonde2().getHeight() * Tile.TILEHEIGHT - handler.getHeight())
			yOffset =  handler.getMonde2().getHeight() * Tile.TILEHEIGHT - handler.getHeight();
		
			
	}
	
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
}
