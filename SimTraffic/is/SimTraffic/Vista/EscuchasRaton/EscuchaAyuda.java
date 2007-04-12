package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.Vista.Ventana;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class EscuchaAyuda implements MouseMotionListener 
{
	private Ventana ventana;
	private String ayuda;

	public EscuchaAyuda(String string, Ventana window) {
		ayuda = string;
		ventana = window;
	}

	public void mouseDragged(MouseEvent e) 
	{
	
	}

	public void mouseMoved(MouseEvent e) 
	{
		ventana.cambiarAyuda(this.ayuda);
	}

}
