package tile;

import gfx.Assets;

public class Sable extends Tile {

	public Sable(int id)  {
		super(Assets.sable, id);
		
	}
	@Override
	public boolean isPont()
	{
		return true;
	}
	
}
