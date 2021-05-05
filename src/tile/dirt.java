package tile;

import gfx.Assets;

public class dirt extends Tile {

	public dirt(int id)  {
		super(Assets.dirt, id);
		
	}
	@Override
	public boolean isSolid()
	{
		return true;
	}
}
