package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaTeclado;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarElementos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarNodos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarTramos;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class BarraSeleccionar extends Barra {
	private static final long serialVersionUID = 1L;

	public BarraSeleccionar(Ventana ventana,
			MLSeleccionarTramos escuchaSeleccionTramos,
			MLSeleccionarElementos escuchaSeleccionNodosYTramos,
			MLSeleccionarNodos escuchaSeleccion, EscuchaTeclado escuchaTeclado) {
		JButton seleccion_nodo = this.a�adirBoton("seleccionar-nodos.png",
				"seleccionar-nodos-2.png", "Seleccionar nodos",
				new AccionSobreMapa(escuchaSeleccion, ventana, escuchaTeclado,
						-1));
		seleccion_nodo.addKeyListener(escuchaTeclado);

		JButton seleccion_tramo = this.a�adirBoton("seleccionar-tramos.png",
				"seleccionar-tramos-2.png", "Seleccionar tramos",
				new AccionSobreMapa(escuchaSeleccionTramos, ventana,
						escuchaTeclado, -1));
		seleccion_tramo.addKeyListener(escuchaTeclado);

		JButton seleccion_area = this.a�adirBoton(
				"seleccionar-nodosytramos.png",
				"seleccionar-nodosytramos-2.png",
				"Seleccionar componentes dentro de �rea", new AccionSobreMapa(
						escuchaSeleccionNodosYTramos, ventana, escuchaTeclado,
						-1));
		seleccion_area.addKeyListener(escuchaTeclado);
	}
}
