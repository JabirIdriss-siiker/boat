/*
 * Class du monde2 elle permet de lire fichier .txt et stocker ces carcataires dans des variables et dans un tableau de type Tile
 * et les afficher plus tard elle a le meme comportement que la class Monde
 * 
 */

package tile.mondes;

import java.awt.Graphics;


import entities.EntityManager;

import entities.creatures.Bateau2;
import project_java.Handler;
import project_java.utils.Utils;
import tile.Tile;




public class Monde2 {
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tile;
	private Handler handler;
	
	//Entities
	
	private EntityManager entityManager;
	
	
	public Monde2(Handler handler,String path)
	{
		this.handler = handler;
		
		entityManager = new EntityManager(handler, new Bateau2(handler,32,32));
		
		LoadMonde(path);
		entityManager.getPlayer2().setX(spawnX);
		entityManager.getPlayer2().setY(spawnY);
	}
	
	public void tick()
	{
		entityManager.tick();
		
	}
	
	public void render(Graphics g)
	{
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

	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x>= width || y>=height)
			return  Tile.merTile;
		
		Tile t = Tile.tile[tile[x][y]];
		
		if (t == null)
			return Tile.merTile;
		return t;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void LoadMonde(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		
		width  = Utils.parsInt(tokens[0]);
		
		height = Utils.parsInt(tokens[1]);
		spawnX = Utils.parsInt(tokens[2]);
		spawnY = Utils.parsInt(tokens[3]);
		
		
		tile = new int[width][height];
		
		for(int y = 0; y < height;y++)
		{
			for(int x = 0; x < width;x++)
			{
				tile[x][y] = Utils.parsInt(tokens[(x + y * width)+4]);
			}
		}
	}

}
