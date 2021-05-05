package tile;

import gfx.Assets;

public class Pont extends Tile {

	public Pont(int id)  {
		super(Assets.pont, id);
		
	}
	@Override
	public boolean isPont()
	{
		return true;
	}
	
}
