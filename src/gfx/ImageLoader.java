/*
 * Cette classe permet d'ouvrir un fichier image que nous utilisons pour charger des textures
 */

package gfx;

import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	
	public static BufferedImage LoadImage(String path)
	{
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
