/*
 * Une class pour gerer les input de la souris en implementant les interefaces  
 * MouseListener, MouseMotionListener de la classes event du toolkit AWT
 */

package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	
	private boolean ClickGauche;
	private int mouseX, mouseY;
	
	
	
	
	//Getters 
	
	public boolean isClickGauche()
	{
		return ClickGauche;
	}
	
	public int getMouseX()
	{
		return mouseX;
	}
	public int getMouseY()
	{
		return mouseY;
	}
	
	//On reecrie les methodes des interefaces
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			ClickGauche = true;
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
		{
			ClickGauche = false;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
