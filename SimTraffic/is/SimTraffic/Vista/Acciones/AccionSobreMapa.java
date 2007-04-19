package is.SimTraffic.Vista.Acciones;

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

	public AccionSobreMapa(EscuchaRaton escucha, Ventana ventana, EscuchaTeclado escuchaTeclado, int boton) {
		this.escucha = escucha;
		this.ventana = ventana;
		this.escuchaTeclado = escuchaTeclado;
		this.boton = boton;
	}

	public void actionPerformed(ActionEvent arg0) {
		ventana.cambiarEscucha(escucha);
		escuchaTeclado.setEscuchaANotificar(escucha);
		escucha.activar();
		
		// Lo he tenido que comentar para las pruebas del 23/03/07 porque no iba bien.
		// Falta meter la imagen de la cruz, que no está subida al CVS
		String ImageStr="";
		if (boton != -1){
			//podemos cambiar los cursores si existen
			if (boton == 0){
				ImageStr = "añadir_nodo.png";
			}
			else if (boton == 1){
				ImageStr = "añadir_tramo.png";
			}			
			else if (boton == 2){
				ImageStr = "eliminar_nodo.png";
			}
			else if (boton == 3){
				ImageStr = "eliminar_tramo.png";
			}
			else if (boton == 4){
				ImageStr = "cruz.gif";
			}
			else if (boton == 5){
				ImageStr = "cruz.gif";
			}
			else if (boton == 6){
				ImageStr = "cruz.gif";
			}
			else if (boton == 7){
				ImageStr = "select.png";
			}			
			String direccion="is\\SimTraffic\\Vista\\Imagenes\\Cursores\\"+ImageStr;
			
			Cursor c = Toolkit.getDefaultToolkit().createCustomCursor( 
					  new ImageIcon( direccion).getImage(), 
					  new Point(15,12), "Cursor" );
			ventana.getPanel_mapa().setCursor(c);			
			
			
		}
		else {
			 
			Cursor c2;
			c2 = new Cursor(0);
			ventana.getPanel_mapa().setCursor(c2);		
		}

	}

}
