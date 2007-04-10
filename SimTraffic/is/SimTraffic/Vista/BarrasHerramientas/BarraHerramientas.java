package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionBarra;
import is.SimTraffic.Vista.Acciones.AccionBuscar;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirLineaAutobus;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirSemaforo;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirVia;
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

		boton = a�adirBoton("seleccionar-1.png", "seleccionar-2.png",
				"Seleccionar", new AccionSobreMapa(new MLSeleccionarNodos(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1));
		boton.addActionListener(new AccionBarra(ventana, ventana
				.getBarraSeleccionar()));
		boton.addKeyListener(ventana.getEscuchaTeclado());

		a�adirBoton("mover1.png", "mover2.png", "Mover la selecci�n actual",
				new AccionSobreMapa(new MLMover(modelo, controlador, ventana
						.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1));

		boton = a�adirBoton("a�adir_nodo.png", "a�adir_nodo2.png",
				"A�adir Nodo", new AccionSobreMapa(new MLA�adirNodo(modelo,
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 0));
		boton.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearNodo()));
		// Aqu� tambi�n habr�a que a�adir el oyente de teclado al
		// boton (y en el resto de botones),
		// pero de momento no lo pongo por si encontramos una alternativa mejor.

		boton = a�adirBoton("a�adir_tramo.png", "a�adir_tramo2.png",
				"A�adir Tramo", new AccionSobreMapa(new MLA�adirTramo(modelo,
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 1));
		boton.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearTramo()));

		a�adirBoton("eliminar_nodo.png", "eliminar_nodo2.png", "Eliminar Nodo",
				new AccionSobreMapa(new MLEliminarNodo(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 2));

		a�adirBoton("eliminar_tramo.png", "eliminar_tramo2.png",
				"Eliminar Tramo", new AccionSobreMapa(new MLEliminarTramo(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 3));

		// Aqui se a�adir�n los nuevos botones.
		a�adirBoton("add_bus.PNG", "add_bus2.PNG", "A�adir linea de autobus",
				new AccionSobreMapa(new MLA�adirLineaAutobus(modelo,
						controlador, ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 4));

		// Bot�n a�adir semaforos
		a�adirBoton("semaforo1.png", "semaforo2.png", "A�adir semaforo",
				new AccionSobreMapa(new MLA�adirSemaforo(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 5));

		// A�adir Vias
		a�adirBoton("a�adir_via2.png", "a�adir_via.png", "A�adir una via",
				new AccionSobreMapa(new MLA�adirVia(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 6));
		
		//A�adir Buscar
		a�adirBoton("buscar.PNG", "buscar.PNG", "Buscar",
				new AccionBuscar(controlador,ventana.getPanel_mapa()));
	}

}
