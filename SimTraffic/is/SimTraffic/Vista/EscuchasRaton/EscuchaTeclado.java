package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Clase que implementa KeyListener.
 * Se encarga de escuchar si se ha pulsado una tecla.
 * @author Grupo ISTrafico
 *
 */public class EscuchaTeclado implements KeyListener {

	
	/**
	 * Panel del mapa
	 */
	PanelMapa panel;
	
	/**
	 * Escucha sobre la cual se van a realizar notificaciones. 
	 */
	EscuchaRaton escuchaANotificar;
	
	/**
	 * Constructora de la Escucha de teclado. Se inicializan los valores
	 * del panel y de la escucha
	 * @param panel
	 * @param escuchaANotificar
	 */
	public EscuchaTeclado(PanelMapa panel, EscuchaRaton escuchaANotificar){
		this.panel = panel;
		this.escuchaANotificar = escuchaANotificar;
		panel.setFocusable(true);
	}
	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		escuchaANotificar.notificar(arg0.getKeyCode());

	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		escuchaANotificar.desnotificar();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public EscuchaRaton getEscuchaANotificar() {
		return escuchaANotificar;
	}

	public void setEscuchaANotificar(EscuchaRaton escuchaANotificar) {
		this.escuchaANotificar = escuchaANotificar;
	}

}
