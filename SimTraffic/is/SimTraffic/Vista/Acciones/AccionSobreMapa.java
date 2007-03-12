package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaTeclado;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	public AccionSobreMapa(EscuchaRaton escucha, Ventana ventana, EscuchaTeclado escuchaTeclado) {
		this.escucha = escucha;
		this.ventana = ventana;
		this.escuchaTeclado = escuchaTeclado;
	}

	public void actionPerformed(ActionEvent arg0) {
		ventana.cambiarEscucha(escucha);
		escuchaTeclado.setEscuchaANotificar(escucha);
		escucha.activar();
	}

}
