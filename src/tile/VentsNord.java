package tile;


import gfx.Assets;


public class VentsNord extends Tile {

	public VentsNord(int id) {
		super(Assets.ventNord,id);
		
	}
	
	public boolean isNord()
	{
		return true;
	}
}
