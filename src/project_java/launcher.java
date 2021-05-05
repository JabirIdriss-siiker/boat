/************************************************************************************************
 *
 * Cette class comporte la methode main c'est grace a cette qu'on peut excuter notre jeu 
 * et afficher le monde et elle creer un objet de type Game (class Game) creeat ainsi une fentre avec comme titre TOUR DU MONDE de taille
 * 1280x720
 * on appelle la methode start() de cette class qui lance la methode run()
 * [Elle change la valeur de runing en true]
 * 
 ************************************************************************************************
 * 
 */

package project_java;



public class launcher {
	public static void main(String[] args)
	{
		Game game = new Game("Tour du Monde",1280,720);
		game.start();
	}
}
