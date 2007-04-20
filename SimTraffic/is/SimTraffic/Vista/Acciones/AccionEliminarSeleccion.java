package is.SimTraffic.Vista.Acciones;

import java.awt.event.ActionEvent;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.PanelMapa;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * men� eliminar un nodo del mapa.
 * <p>
 * <p>
 * Esta clase tomara el modelo, el controlador y el panel con la representaci�n
 * del mapa como par�metros en su construcutor y lo almacenar� para que cuando
 * se ejecute la acci�n poder llamar a la herramienta HEliminarNodo encargada
 * eliminar efectivametne el nodo del mapa.
 * 
 * @author Grupo ISTrafico
 * 
 */

public class AccionEliminarSeleccion extends AccionesPopUpMenu {


	public AccionEliminarSeleccion(IModelo modelo, IControlador controlador,
			PanelMapa panel_mapa) {
		super(modelo, controlador, panel_mapa);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (modelo.getMapa().getSeleccion().esVacia()) {
			//panel_mapa.getMenuEmergente().setVisible(false);
			//controlador.herramienta(new HEliminarSeleccion());
			panel_mapa.recrearMapa();
			panel_mapa.repaint();
		}
	}

}
