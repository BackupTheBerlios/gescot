package is.SimTraffic.Vista.BarrasHerramientas;

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
		JToggleButton seleccion_nodo = (JToggleButton) this.añadirBoton("seleccionar-nodos.png",
				"seleccionar-nodos-2.png", "Seleccionar nodos",
				new AccionSobreMapa(escuchaSeleccion, ventana, escuchaTeclado,-1), true);
		seleccion_nodo.addKeyListener(escuchaTeclado);
		seleccion_nodo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para seleccionar nodos.", ventana));
		seleccion_nodo.setSelected(true);
		selecciones.add(seleccion_nodo);

		JToggleButton seleccion_tramo = (JToggleButton) this.añadirBoton("seleccionar-tramos.png",
				"seleccionar-tramos-2.png", "Seleccionar tramos",
				new AccionSobreMapa(escuchaSeleccionTramos, ventana,
						escuchaTeclado, -1), true);
		seleccion_tramo.addKeyListener(escuchaTeclado);
		seleccion_tramo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para seleccionar tramos.", ventana));
		selecciones.add(seleccion_tramo);

		JToggleButton seleccion_area = (JToggleButton) this.añadirBoton(
				"seleccionar-nodosytramos.png",
				"seleccionar-nodosytramos-2.png",
				"Seleccionar componentes dentro de área", new AccionSobreMapa(
						escuchaSeleccionNodosYTramos, ventana, escuchaTeclado,-1), true);
		seleccion_area.addKeyListener(escuchaTeclado);
		seleccion_area.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para seleccionar nodos y tramos.", ventana));
		selecciones.add(seleccion_area);
	}
}
