package tile;


import gfx.Assets;


public class VentsSud extends Tile {

	public VentsSud(int id) {
		super(Assets.ventSud,id);
		
	}
	
	public boolean isSud()
	{
		return true;
	}
}
