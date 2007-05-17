package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Messages;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaTeclado;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;

/**
 * Clase que extiende ActionListener para los botones que activan un esucha del
 * raton.
 * <p>
 * Esta clase toma en su constructor como parámetros un escucha que es el que
 * pondra en el panel del mapa y una ventana donde se encuentra este.<br>
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionSobreMapa implements ActionListener {

	EscuchaRaton escucha;
	EscuchaTeclado escuchaTeclado;

	Ventana ventana;
	int boton;
	ClassLoader cl;

	public AccionSobreMapa(EscuchaRaton escucha, Ventana ventana, EscuchaTeclado escuchaTeclado, int boton) {
		this.escucha = escucha;
		this.ventana = ventana;
		this.escuchaTeclado = escuchaTeclado;
		this.boton = boton;
		cl = this.getClass().getClassLoader();
	}

	public void actionPerformed(ActionEvent arg0) {
		ventana.cambiarEscucha(escucha);
		escuchaTeclado.setEscuchaANotificar(escucha);
		escucha.activar();
		
		// Lo he tenido que comentar para las pruebas del 23/03/07 porque no iba bien.
		// Falta meter la imagen de la cruz, que no está subida al CVS
		String ImageStr=Messages.getString("AccionSobreMapa.0"); //$NON-NLS-1$
		if (boton != -1){
			//podemos cambiar los cursores si existen
			if (boton == 0){
				ImageStr = Messages.getString("AccionSobreMapa.1"); //$NON-NLS-1$
			}
			else if (boton == 1){
				ImageStr = Messages.getString("AccionSobreMapa.2"); //$NON-NLS-1$
			}			
			else if (boton == 2){
				ImageStr = Messages.getString("AccionSobreMapa.3"); //$NON-NLS-1$
			}
			else if (boton == 3){
				ImageStr = Messages.getString("AccionSobreMapa.4"); //$NON-NLS-1$
			}
			else if (boton == 4){
				ImageStr = Messages.getString("AccionSobreMapa.5"); //$NON-NLS-1$
			}
			else if (boton == 5){
				ImageStr = Messages.getString("AccionSobreMapa.6"); //$NON-NLS-1$
			}
			else if (boton == 6){
				ImageStr = Messages.getString("AccionSobreMapa.7"); //$NON-NLS-1$
			}
			else if (boton == 7){
				ImageStr = Messages.getString("AccionSobreMapa.8"); //$NON-NLS-1$
			}
			else if (boton == 8){
				ImageStr = Messages.getString("AccionSobreMapa.9"); //$NON-NLS-1$
			}
			else if (boton == 9){
				ImageStr = Messages.getString("AccionSobreMapa.10"); //$NON-NLS-1$
			}
			else if (boton == 10){
				ImageStr = Messages.getString("AccionSobreMapa.11"); //$NON-NLS-1$
			}
			else if (boton == 11){
				ImageStr = Messages.getString("AccionSobreMapa.12"); //$NON-NLS-1$
			}

			//String direccion="is\\SimTraffic\\Vista\\Imagenes\\Cursores\\"+ImageStr;
			ImageIcon imagen = new ImageIcon(cl
					.getResource(Messages.getString("AccionSobreMapa.13") + ImageStr)); //$NON-NLS-1$
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor( 
					  imagen.getImage(), 
					  new Point(15,12), Messages.getString("AccionSobreMapa.14") ); //$NON-NLS-1$
			ventana.getPanel_mapa().setCursor(c);					
		}
		else {			 
			Cursor c2;
			c2 = new Cursor(0);
			ventana.getPanel_mapa().setCursor(c2);		
		}

	}

}
