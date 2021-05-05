/*
 * Une class pour gerer les input du clavier en implementant l'interface KeyListener 
 * du package event de la classe keyEvent du toolkit AWT
 */

package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean haut , bas, gauche, droite;
	
	public KeyManager()
	{
		keys = new boolean[256];
	}
	//cette methode permet de remplir le tableau keys avec les touche appuie 
	public void tick()
	{
		haut = keys[KeyEvent.VK_Z];
		bas = keys[KeyEvent.VK_S];
		gauche = keys[KeyEvent.VK_Q];
		droite = keys[KeyEvent.VK_D];
	}
	//reecriture des methodes de l'interfaces
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	
		
	}
}
