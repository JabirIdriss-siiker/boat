/*
 * Class generale des composant du monde 
 * dans cette classe on definie pour chaque composant un id 
 * pour pouvoir l'utiliser apres dans les class Monde
 * 
 */


package tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	//STATIC
	//on initialise les tableau de type tile pour stocker les different tile
	
	public static Tile[] tiles = new Tile[256];
	public static Tile[] tile = new Tile[256];
	//pour chaque Tile on donne un id 
	
	public static Tile merTile = new WaterTile(0);
	
	public static Tile stoneTile = new StoneTile(1);
	//vents
	public static Tile VentsGauche = new VentsGauche(2);
	public static Tile VentsDroit = new VentsDroit(3);
	public static Tile VentsNord = new VentsNord(4);
	public static Tile VentsSud = new VentsSud(5);
	
	public static Tile dirt = new dirt(6);
	public static Tile vague = new Vague(7);
	public static Tile courant = new Courant(8);
	public static Tile box = new chest(9);
	public static Tile pont = new Pont(10);
	public static Tile sable = new Sable(11);
	public static Tile mountains = new Mountains(12);
	
	//taille predefinie des tiles
	public static final int TILEWIDTH = 80, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	
	public Tile(BufferedImage texture, int id)
	{
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
		tile[id] = this;
		
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g, int x, int y)
	{
		g.drawImage(texture, x, y,TILEWIDTH,TILEHEIGHT, null);
	}
	//de base les methodes de collisions sont predefinie a false
	public boolean isSolid()
	{
		return false;
	}
	public boolean isDeath()
	{
		return false;
	}
	public boolean isDroit()
	{
		return false;
	}
	public boolean isGauche()
	{
		return false;
	}
	public boolean isNord()
	{
		return false;
	}
	public boolean isSud()
	{
		return false;
	}
	public boolean isVague()
	{
		return false;
	}
	public boolean isCourant()
	{
		return false;
	}
	public boolean isPont()
	{
		return false;
	}
	//grace a cette methode on peut recuperer l'id du Tile
	public int getId()
	{
		return id;
	}
}
