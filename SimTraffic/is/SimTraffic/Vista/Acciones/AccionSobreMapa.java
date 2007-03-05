package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que extiende ActionListener para los botones que activan un esucha del raton.<p>
 * Esta clase toma en su constructor como parámetros un escucha que es el que pondra en
 * el panel del mapa y una ventana donde se encuentra este.<br>
 * 
 * @author Grupo ISTrafico
 *
 */
public class AccionSobreMapa implements ActionListener {
	
	EscuchaRaton escucha;
	Ventana ventana;
	
	public AccionSobreMapa(EscuchaRaton escucha, Ventana ventana) {
		this.escucha = escucha;
		this.ventana = ventana;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		ventana.cambiarEscucha(escucha);
		escucha.activar();
	}

}
