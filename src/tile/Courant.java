package tile;


import gfx.Assets;

public class Courant extends Tile{
	
	
	public Courant( int id) {
		super(Assets.courant, id);
		
	}
	
	
	public boolean isCourant()
	{
		return true;
	}
}
