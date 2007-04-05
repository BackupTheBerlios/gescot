package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;

import javax.swing.JScrollBar;

/**
 * Esta clase extiende Thread y es utilizada para conseguir el desplazamiento
 * cuando se arrastra el scroll.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AuxScrollX extends Thread {

	JScrollBar barra;

	PanelMapa panel;

	long tiempo;

	private boolean termino = false;

	public AuxScrollX(JScrollBar barra, PanelMapa panel) {
		this.barra = barra;
		this.panel = panel;
	}

	public void terminar() {
		termino = true;
	}

	@SuppressWarnings("static-access")
	public void run() {
		while (true && !termino) {
			int valor = barra.getValue();
			if (valor > 20)
				valor -= 10;
			if (valor > 9 && valor < 11)
				valor = 10;
			panel.cambiaPosX((valor - 10) * 8);
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
	}

}
