package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.EsuchasRaton.EscuchaRaton;
import is.SimTraffic.Vista.EsuchasRaton.MLAñadirNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
