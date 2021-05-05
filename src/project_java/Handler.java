/* Cette classe nous permet d'avoir accees a plusieeur composant
 * du jeu sans devoir les appeler separement et pouvoir les manipuler facilement 
 * son constructeur prends un parametre de type jeu (dans la boucle principal du jeu il ya tout les componsant   
 */


package project_java;

import gfx.Camera;
import input.KeyManager;
import input.MouseManager;

import tile.mondes.Monde;
import tile.mondes.Monde2;

public class Handler {
private Game game;
private Monde monde;
private Monde2 monde2;


	public Handler(Game game)
	{
		this.game = game;
		
	}
	
	
	public Camera getCamera()
	{
		return game.getCamera();
	}
	
	public KeyManager getKeyManager()
	{
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager()
	{
		return game.getMouseManager();
	}
	public int getWidth()
	{
		return game.getWidth();
	}
	
	public int getHeight()
	{
		return game.getHeight();
	}
	
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Monde getMonde() {
		return monde;
	}

	public void setMonde(Monde monde) {
		this.monde = monde;
	}
	


	public Monde2 getMonde2() {
		return monde2;
	}


	public void setMonde2(Monde2 monde2) {
		this.monde2 = monde2;
	}
	
	
}
