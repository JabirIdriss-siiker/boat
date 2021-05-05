/*
 * Class du monde elle permet de lire fichier .txt et stocker ces carcataires dans des variables et dans un tableau de type Tile
 * et les afficher plus tard 
 * 
 */

package tile.mondes;

import java.awt.Graphics;


import entities.EntityManager;
import entities.creatures.Bateau;
import project_java.Handler;
import project_java.utils.Utils;
import tile.Tile;



public class Monde {
	private int width, height;
	private int spawnX = 0, spawnY = 0;
	private int[][] tiles;
	private Handler handler;
	
	//Entity
	
	private EntityManager entityManager;
	
	
	public Monde(Handler handler,String path)
	{
		this.handler = handler;
		
		entityManager = new EntityManager(handler, new Bateau(handler,32,32));
		
		
		LoadMonde(path);
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	//on tick le jouer 
	public void tick()
	{
		entityManager.tick();
		
	}
	
	//on dessine le monde de le canvas 
	public void render(Graphics g)
	{
		// ces variables sert a afficher le rendu d'une maniere a que juste ce qu'il ya dans le visuel du joueur
		//c'est a dire dans le cadre de la camera soit dessiner et eviter de surchager le buffer 
		
		int iStart = (int) Math.max(0, handler.getCamera().getxOffset() / Tile.TILEWIDTH);
		int iEnd = (int) Math.min(width, (handler.getCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH +1);
		int jStart = (int) Math.max(0, handler.getCamera().getyOffset() / Tile.TILEHEIGHT);
		int jEnd = (int) Math.min(height, (handler.getCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1);
		
		for(int j = jStart; j < jEnd;j++)
		{
			for(int i = iStart; i < iEnd;i++)
			{
				getTile(i, j).render(g, (int)(i * Tile.TILEWIDTH - handler.getCamera().getxOffset()), 
											(int)(j * Tile.TILEHEIGHT - handler.getCamera().getyOffset()));
				
			}
		}
		
		entityManager.render(g);
		
		
	}
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	//on remplie le tableau avec les id des tiles a partir du fichier
	//si un numero n'est pas definie on returne l'image du la mer pour eviter
	//de laisser des carreaux blancs dans le canvas et ainsi eviter des problemes lors de l'excution
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x>= width || y>=height)
			return  Tile.merTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if (t == null)
			return Tile.merTile;
		return t;
	}
	
	
	//Grace a cette methode on load le fichier .txt et on stockes ces composant dans le bon tableau et les variables
	private void LoadMonde(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		width  = Utils.parsInt(tokens[0]);
		height = Utils.parsInt(tokens[1]);
		spawnX = Utils.parsInt(tokens[2]);
		spawnY = Utils.parsInt(tokens[3]);
		
		
		tiles = new int[width][height];
		
		for(int y = 0; y < height;y++)
		{
			for(int x = 0; x < width;x++)
			{
				tiles[x][y] = Utils.parsInt(tokens[(x + y * width)+4]);
			}
		}
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
