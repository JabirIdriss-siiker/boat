package tile;


import gfx.Assets;

public class Vague extends Tile{
	
	
	public Vague( int id) {
		super(Assets.vague, id);
		
	}
	
	
	public boolean isVague()
	{
		return true;
	}
}
