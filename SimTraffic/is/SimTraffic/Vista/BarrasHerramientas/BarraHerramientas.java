package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionBarra;
import is.SimTraffic.Vista.Acciones.AccionBuscar;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirLineaAutobus;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirSemaforo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirVia;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLMover;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarNodos;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class BarraHerramientas extends Barra {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BarraHerramientas(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		super(JToolBar.VERTICAL);
		JButton boton;

		boton = añadirBoton("seleccionar-1.png", "seleccionar-2.png",
				"Seleccionar", new AccionSobreMapa(new MLSeleccionarNodos(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1));
		boton.addActionListener(new AccionBarra(ventana, ventana
				.getBarraSeleccionar()));
		boton.addKeyListener(ventana.getEscuchaTeclado());

		añadirBoton("mover1.png", "mover2.png", "Mover la selección actual",
				new AccionSobreMapa(new MLMover(modelo, controlador, ventana
						.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1));

		boton = añadirBoton("añadir_nodo.png", "añadir_nodo2.png",
				"Añadir Nodo", new AccionSobreMapa(new MLAñadirNodo(modelo,
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 0));
		boton.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearNodo()));
		// Aquí también habría que añadir el oyente de teclado al
		// boton (y en el resto de botones),
		// pero de momento no lo pongo por si encontramos una alternativa mejor.

		boton = añadirBoton("añadir_tramo.png", "añadir_tramo2.png",
				"Añadir Tramo", new AccionSobreMapa(new MLAñadirTramo(modelo,
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 1));
		boton.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearTramo()));

		añadirBoton("eliminar_nodo.png", "eliminar_nodo2.png", "Eliminar Nodo",
				new AccionSobreMapa(new MLEliminarNodo(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 2));

		añadirBoton("eliminar_tramo.png", "eliminar_tramo2.png",
				"Eliminar Tramo", new AccionSobreMapa(new MLEliminarTramo(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 3));

		// Aqui se añadirán los nuevos botones.
		añadirBoton("add_bus.PNG", "add_bus2.PNG", "Añadir linea de autobus",
				new AccionSobreMapa(new MLAñadirLineaAutobus(modelo,
						controlador, ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 4));

		// Botón añadir semaforos
		añadirBoton("semaforo1.png", "semaforo2.png", "Añadir semaforo",
				new AccionSobreMapa(new MLAñadirSemaforo(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 5));

		// Añadir Vias
		añadirBoton("añadir_via2.png", "añadir_via.png", "Añadir una via",
				new AccionSobreMapa(new MLAñadirVia(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 6));
		
		//Añadir Buscar
		añadirBoton("buscar.PNG", "buscar.PNG", "Buscar",
				new AccionBuscar(controlador,ventana.getPanel_mapa()));
	}

}
