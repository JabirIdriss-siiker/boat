package tile;

import gfx.Assets;

public class chest extends Tile {

	public chest(int id)  {
		super(Assets.box, id);
		
	}
	@Override
	public boolean isSolid()
	{
		return true;
	}
}
