package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.ActionEvent;

/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * menú eliminar un nodo del mapa.
 * <p>
 * <p>
 * Esta clase tomara el modelo, el controlador y el panel con la representación
 * del mapa como parámetros en su construcutor y lo almacenará para que cuando
 * se ejecute la acción poder llamar a la herramienta HEliminarNodo encargada
 * eliminar efectivametne el nodo del mapa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionEliminarNodo extends AccionesPopUpMenu {

	public AccionEliminarNodo(IModelo modelo, IControlador controlador,
			PanelMapa panel_mapa) {
		super(modelo, controlador, panel_mapa);
	}

	public void actionPerformed(ActionEvent arg0) {
		Nodo seleccionado = buscarNodo(panel_mapa.getPosEx(), panel_mapa
				.getPosEy());
		if (seleccionado != null) {
			panel_mapa.getMenuEmergenteActivo().setVisible(false);
			controlador.herramienta(new HEliminarNodo(seleccionado));
			Nodo nodoSeleccion = modelo.getMapa().getSeleccion().existeNodo(seleccionado);
			if (nodoSeleccion!=null) {
				for (int i=0; i<modelo.getMapa().getSeleccion().getNodosSeleccionados().size(); i++) {
					if (modelo.getMapa().getSeleccion().getNodosSeleccionados().get(i).equals(nodoSeleccion)) 
						modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(i);
				}
			}
			panel_mapa.sugerir(null);
			panel_mapa.recrearMapa();
			panel_mapa.repaint();
		}
	}

}
