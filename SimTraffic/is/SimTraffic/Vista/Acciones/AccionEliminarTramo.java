package is.SimTraffic.Vista.Acciones;

import java.awt.event.ActionEvent;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarTramo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;



/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * menú eliminar un tramo del mapa.
 * <p>
 * <p>
 * Esta clase tomara el modelo, el controlador y el panel con la representación
 * del mapa como parámetros en su construcutor y lo almacenará para que cuando
 * se ejecute la acción poder llamar a la herramienta HEliminarTramo encargada de
 * eliminar efectivametne el traomo del mapa.
 * 
 * @author Grupo ISTrafico
 * 
 */

public class AccionEliminarTramo extends AccionesPopUpMenu  {


		public AccionEliminarTramo(IModelo modelo, IControlador controlador,
				PanelMapa panel_mapa) {
			super(modelo, controlador, panel_mapa);
		}

		public void actionPerformed(ActionEvent arg0) {
			Tramo seleccionado = buscarTramo(panel_mapa.getPosEx(), panel_mapa
					.getPosEy());
			if (seleccionado != null)
			{
				panel_mapa.getMenuEmergenteActivo().setVisible(false);
				controlador.herramienta(new HEliminarTramo(seleccionado));			
				Tramo tramoSeleccion = modelo.getMapa().getSeleccion().existeTramo(seleccionado);
				if (tramoSeleccion!=null) {
					for (int i=0; i<modelo.getMapa().getSeleccion().getTramosSeleccionados().size(); i++) {
						if (modelo.getMapa().getSeleccion().getTramosSeleccionados().get(i).equals(tramoSeleccion)) 
							modelo.getMapa().getSeleccion().getTramosSeleccionados().remove(i);
					}
				}			
				panel_mapa.sugerir(null);
				panel_mapa.recrearMapa();
				panel_mapa.repaint();
			}	
				
			
		}

	}
