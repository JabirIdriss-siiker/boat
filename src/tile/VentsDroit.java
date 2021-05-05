package tile;


import gfx.Assets;


public class VentsDroit extends Tile {

	public VentsDroit(int id) {
		super(Assets.ventDroit,id);
		
	}
	public boolean isDroit()
	{
		return true;
	}
}
