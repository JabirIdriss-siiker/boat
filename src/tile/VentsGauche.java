package tile;

import gfx.Assets;

public class VentsGauche extends Tile {

	public VentsGauche(int id)  {
		super(Assets.ventGauche, id);
		
	}
	@Override
	public boolean isGauche()
	{
		return true;
	}
}
