/*
 * Cette class gere les animations
 */

package gfx;

import java.awt.image.BufferedImage;

public class Animation {

	
	private int speed, index;
	private long LastTime,Timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames)
	{
		this.speed = speed;
		this.frames = frames;
		index = 0;
		LastTime = System.currentTimeMillis();
		Timer = 0;
	}
	// a chaque tick(nombre de seconde precis) on affiche une nouvelle image du tableau
	public void tick()
	{
		Timer += System.currentTimeMillis() - LastTime;
		LastTime = System.currentTimeMillis();
		
		if (Timer > speed )
		{
			index++;
			Timer = 0;
			if (index >=frames.length)
			{
				index = 0;
			}
		}
	}
	
	public BufferedImage getCurrentFrame()
	{
		return frames[index];
	}
}
