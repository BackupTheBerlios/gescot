package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Messages;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaAyuda;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaTeclado;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarElementos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarNodos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarTramos;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;

public class BarraSeleccionar extends Barra {
	private static final long serialVersionUID = 1L;

	public BarraSeleccionar(Ventana ventana, MLSeleccionarTramos escuchaSeleccionTramos,MLSeleccionarElementos escuchaSeleccionNodosYTramos,			MLSeleccionarNodos escuchaSeleccion, EscuchaTeclado escuchaTeclado) 
	{
		ButtonGroup selecciones = new ButtonGroup();
		JToggleButton seleccion_nodo = (JToggleButton) this.añadirBoton(Messages.getString("BarraSeleccionar.0"), //$NON-NLS-1$
				Messages.getString("BarraSeleccionar.1"), Messages.getString("BarraSeleccionar.2"), //$NON-NLS-1$ //$NON-NLS-2$
				new AccionSobreMapa(escuchaSeleccion, ventana, escuchaTeclado,-1), true);
		seleccion_nodo.addKeyListener(escuchaTeclado);
		seleccion_nodo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraSeleccionar.3"), ventana)); //$NON-NLS-1$
		seleccion_nodo.setSelected(true);
		selecciones.add(seleccion_nodo);

		JToggleButton seleccion_tramo = (JToggleButton) this.añadirBoton(Messages.getString("BarraSeleccionar.4"), //$NON-NLS-1$
				Messages.getString("BarraSeleccionar.5"), Messages.getString("BarraSeleccionar.6"), //$NON-NLS-1$ //$NON-NLS-2$
				new AccionSobreMapa(escuchaSeleccionTramos, ventana,
						escuchaTeclado, -1), true);
		seleccion_tramo.addKeyListener(escuchaTeclado);
		seleccion_tramo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraSeleccionar.7"), ventana)); //$NON-NLS-1$
		selecciones.add(seleccion_tramo);

		JToggleButton seleccion_area = (JToggleButton) this.añadirBoton(
				Messages.getString("BarraSeleccionar.8"), //$NON-NLS-1$
				Messages.getString("BarraSeleccionar.9"), //$NON-NLS-1$
				Messages.getString("BarraSeleccionar.10"), new AccionSobreMapa( //$NON-NLS-1$
						escuchaSeleccionNodosYTramos, ventana, escuchaTeclado,-1), true);
		seleccion_area.addKeyListener(escuchaTeclado);
		seleccion_area.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraSeleccionar.11"), ventana)); //$NON-NLS-1$
		selecciones.add(seleccion_area);
	}
}
