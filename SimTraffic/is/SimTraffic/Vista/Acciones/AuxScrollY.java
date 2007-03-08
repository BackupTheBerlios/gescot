package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.PanelMapa;

import javax.swing.JScrollBar;

public class AuxScrollY implements Runnable {

	JScrollBar barra;

	PanelMapa panel;

	long tiempo;

	public AuxScrollY(JScrollBar barra, PanelMapa panel) {
		this.barra = barra;
		this.panel = panel;
	}

	public void run() {
		while (true) {
			panel.cambiaPosY((barra.getValue()-10)*10);
			tiempo = System.currentTimeMillis();
			while (System.currentTimeMillis() - tiempo < 200) {
			}
			;
		}
	}

}
