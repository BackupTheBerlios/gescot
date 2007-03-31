package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Grupo ISTrafico
 *
 */public class EscuchaTeclado implements KeyListener {

	PanelMapa panel;
	
	/**
	 * Escucha sobre la cual se van a realizar notificaciones. 
	 */
	EscuchaRaton escuchaANotificar;
	
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
