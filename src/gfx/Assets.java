/*
 * Grace a cette class on definie les textures grce au images qu'on load a l'aide de la methode LoadImage de la calsse ImageLoader
 */

package gfx;
import java.awt.image.BufferedImage;

public class Assets {
	
	
	// mettre toutes les images qu'on se sert ici
	public static BufferedImage mere,stone,dirt,ventDroit,ventGauche,ventNord,
				ventSud,vague,courant,box,menu,credit,exit,pont, sable,mountains,choixBateau,mort; 
	public static BufferedImage[] bateau_haut,bateau_bas,bateau_droite,bateau_gauche;
	public static BufferedImage[] bateau1_haut,bateau1_bas,bateau1_droite,bateau1_gauche;
	public static BufferedImage courant1;
	
	public static void init() {
		
		//textures
		
		menu = ImageLoader.LoadImage("/textures/menu.png");
		credit = ImageLoader.LoadImage("/textures/credit.png");
		exit = ImageLoader.LoadImage("/textures/finale.png");
		choixBateau = ImageLoader.LoadImage("/textures/choixBateau.png");
		mort = ImageLoader.LoadImage("/textures/mort.png");
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/wave.png"));
		
		
		vague = sheet.crop(400, 313, 58, 52);
		
		courant= sheet.crop(581, 419, 59, 14);
		
		//bateau
		
		SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/shipsheet.png"));
		
		
		bateau_haut = new BufferedImage[3];
		bateau_bas = new BufferedImage[3];
		bateau_gauche = new BufferedImage[3];
		bateau_droite = new BufferedImage[3];
		
		bateau_haut[0] = sheet2.crop(0,159*3,159,159);
		bateau_haut[1] = sheet2.crop(159,159*3,159,159);
		bateau_haut[2] = sheet2.crop(159*2,159*3,159,159);
		
		
		
		bateau_bas[0] =  sheet2.crop(0,0,159,159);
		bateau_bas[1] =  sheet2.crop(159,0,159,159);
		bateau_bas[2] =  sheet2.crop(159*2,0,159,159);
		
		bateau_gauche[0] = sheet2.crop(0,159,159,159);
		bateau_gauche[1] = sheet2.crop(159,159,159,159);
		bateau_gauche[2] = sheet2.crop(159*2,159,159,159);
		
		bateau_droite[0] = sheet2.crop(0,159*2,159,159);
		bateau_droite[1] = sheet2.crop(159,159*2,159,159);
		bateau_droite[2] = sheet2.crop(159*2,159*2,159,159);
		
		SpriteSheet sheet11 = new SpriteSheet(ImageLoader.LoadImage("/textures/shipsheet2.png"));
		
		
		bateau1_haut = new BufferedImage[3];
		bateau1_bas = new BufferedImage[3];
		bateau1_gauche = new BufferedImage[3];
		bateau1_droite = new BufferedImage[3];
		
		bateau1_haut[0] = sheet11.crop(0,159*3,159,159);
		bateau1_haut[1] = sheet11.crop(159,159*3,159,159);
		bateau1_haut[2] = sheet11.crop(159*2,159*3,159,159);
		
		
		
		bateau1_bas[0] =  sheet11.crop(0,0,159,159);
		bateau1_bas[1] =  sheet11.crop(159,0,159,159);
		bateau1_bas[2] =  sheet11.crop(159*2,0,159,159);
		
		bateau1_gauche[0] = sheet11.crop(0,159,159,159);
		bateau1_gauche[1] = sheet11.crop(159,159,159,159);
		bateau1_gauche[2] = sheet11.crop(159*2,159,159,159);
		
		bateau1_droite[0] = sheet11.crop(0,159*2,159,159);
		bateau1_droite[1] = sheet11.crop(159,159*2,159,159);
		bateau1_droite[2] = sheet11.crop(159*2,159*2,159,159);
		
		SpriteSheet sheet6 = new SpriteSheet(ImageLoader.LoadImage("/textures/watergrass.png"));
		mere = sheet6.crop(0,32*5,32,32);
		stone = sheet6.crop(0,0,32,32 );
		SpriteSheet sheet5 = new SpriteSheet(ImageLoader.LoadImage("/textures/grass.png"));
		dirt = sheet5.crop(0,32*5,32,32);
		SpriteSheet sheet4 = new SpriteSheet(ImageLoader.LoadImage("/textures/vent.png"));
		ventGauche = sheet4.crop(0,0,64,64);
		ventDroit = sheet4.crop(64,0,64,64);
		ventNord = sheet4.crop(0,64,64,64);
		ventSud = sheet4.crop(64,64,64,64);
		
		SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/chests.png"));
		box = sheet3.crop(0, 0, 32, 32);
		
		
		
		SpriteSheet sheet8 = new SpriteSheet(ImageLoader.LoadImage("/textures/bridge.png"));
		
		pont = sheet8.crop(10, 0, 53, 78);
		
		SpriteSheet sheet9 = new SpriteSheet(ImageLoader.LoadImage("/textures/sable.png"));
		sable= sheet9.crop(0, 0, 78, 78);
		
		SpriteSheet sheet10 = new SpriteSheet(ImageLoader.LoadImage("/textures/montains.png"));
		mountains = sheet10.crop(0, 230, 32, 33);
		
		
		
		
		
		
	}
}