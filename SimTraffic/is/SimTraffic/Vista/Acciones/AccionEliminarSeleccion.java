package is.SimTraffic.Vista.Acciones;

import java.awt.event.ActionEvent;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Herramientas.HEliminarSeleccion;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * men� eliminar los elementos seleccionados.
 * <p>
 * <p>
 * Esta clase tomara el modelo, el controlador y el panel con la representaci�n
 * del mapa como par�metros en su construcutor y lo almacenar� para que cuando
 * se ejecute la acci�n poder llamar a la herramienta HEliminarSeleccion encargada
 * de eliminar la selecci�n de elementos del mapa.
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
		if (!modelo.getMapa().getSeleccion().esVacia()) {			
			panel_mapa.getMenuEmergenteActivo().setVisible(false);
			controlador.herramienta(new HEliminarSeleccion(
					modelo.getMapa().getSeleccion().getNodosSeleccionados(),
					modelo.getMapa().getSeleccion().getTramosSeleccionados()));
			modelo.getMapa().limpiaSeleccion();
			panel_mapa.sugerir(null);
			panel_mapa.recrearMapa();
			panel_mapa.repaint();
		}
	}
}
