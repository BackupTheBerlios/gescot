package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Messages;
import is.SimTraffic.Vista.PanelMapa;

import javax.swing.JScrollBar;

/**
 * Esta clase extiende Thread y es utilizada para conseguir el desplazamiento
 * cuando se arrastra el scroll.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AuxScrollY extends Thread {

	JScrollBar barra;

	PanelMapa panel;

	long tiempo;

	private boolean termino = false;

	public AuxScrollY(JScrollBar barra, PanelMapa panel) {
		this.barra = barra;
		this.panel = panel;
	}

	public void terminar() {
		termino = true;
	}

	public void run() {
		while (true && !termino) {
			int valor = barra.getValue();
			valor -= 45;
			if (valor > -1 && valor <1)
				valor = 0;
			panel.cambiaPosY((valor));
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

}
