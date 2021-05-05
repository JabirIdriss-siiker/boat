package tile;

import gfx.Assets;

public class Mountains extends Tile {

	public Mountains(int id)  {
		super(Assets.mountains, id);
		
	}
	@Override
	public boolean isDeath()
	{
		return true;
	}
}
