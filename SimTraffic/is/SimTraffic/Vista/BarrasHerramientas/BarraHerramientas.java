package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionBarra;
import is.SimTraffic.Vista.Acciones.AccionBuscar;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaAyuda;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirLineaAutobus;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirVia;
import is.SimTraffic.Vista.EscuchasRaton.MLDesplazar;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLEscuchaItinerario;
import is.SimTraffic.Vista.EscuchasRaton.MLMover;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarNodos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarVia;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarYMover;

import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

public class BarraHerramientas extends Barra {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JToggleButton botonDesplazar;
	private JToggleButton botonSeleccionar;
	private JToggleButton botonA�adirNodo;
	private JToggleButton botonA�adirTramo;
	private JToggleButton botonEliminarNodo;
	private JToggleButton botonEliminarTramo;
	
	public BarraHerramientas(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		super(JToolBar.VERTICAL);
		JToggleButton boton;
		ButtonGroup grupoherramientas = new ButtonGroup();
		
		//Desplazar el mapa.
		botonDesplazar = (JToggleButton) a�adirBoton("mover.png", "mover2.png",
				"Desplazar el mapa", new AccionSobreMapa(new MLDesplazar(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 7), true);
		botonDesplazar.addActionListener(new AccionBarra(ventana, null));
		botonDesplazar.addMouseMotionListener(new EscuchaAyuda("Pulse el raton en el mapa y matengalo para desplazarlo.", ventana));
		botonDesplazar.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonDesplazar);

		//Seleccionar y Mover.
		botonSeleccionar = (JToggleButton) a�adirBoton("seleccionarYMover.png", "seleccionarYMover2.png",
				"Seleccionar y Mover", new AccionSobreMapa(new MLSeleccionarYMover(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 7), true);
		botonSeleccionar.addActionListener(new AccionBarra(ventana, null));
		botonSeleccionar.addMouseMotionListener(new EscuchaAyuda("Seleccione nodos y tramos y arr�strelos para moverlos por el mapa.", ventana));
		botonSeleccionar.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonSeleccionar);
		
		//Seleccionar Vias
		boton = (JToggleButton) a�adirBoton("seleccionarVia1.png", "seleccionarVia2.png",
				"Seleccionar Vias", new AccionSobreMapa(new MLSeleccionarVia(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 9), true);
		boton.addActionListener(new AccionBarra(ventana, null));
		boton.addMouseMotionListener(new EscuchaAyuda("Seleccione los tramos del mapa para identificar a qu� via pertenecen", ventana));
		boton.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(boton);
		
		
		
		//A partir de ahora no se utilizar�n los botones de seleccionar y mover (los dejo comentados de momento).
		
		/*boton = (JToggleButton) a�adirBoton("seleccionar-1.png", "seleccionar-2.png",
				"Seleccionar", new AccionSobreMapa(new MLSeleccionarNodos(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1), true);
		boton.addActionListener(new AccionBarra(ventana, ventana
				.getBarraSeleccionar()));
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse aqu� y seleccione uno de los tres botones superiores de selecci�n para elegir el tipo de la misma.", ventana));
		boton.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(boton);

		boton = (JToggleButton) a�adirBoton("mover1.png", "mover2.png", "Mover la selecci�n actual",
				new AccionSobreMapa(new MLMover(modelo, controlador, ventana
						.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1), true);
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse en los elementos seleccionados y arrastre el rat�n hasta que los elementos est�n en la posici�n deseada.", ventana));
		grupoherramientas.add(boton);*/
		
		botonA�adirNodo = (JToggleButton) a�adirBoton("a�adir_nodo.png", "a�adir_nodo2.png",
				"A�adir Nodo", new AccionSobreMapa(new MLA�adirNodo(modelo,
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 0), true);
		botonA�adirNodo.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearNodo()));
		botonA�adirNodo.addMouseMotionListener(new EscuchaAyuda("Pulse aqu� para a�adir un nuevo nodo.", ventana));
		grupoherramientas.add(botonA�adirNodo);
		
		// Aqu� tambi�n habr�a que a�adir el oyente de teclado al
		// boton (y en el resto de botones),
		// pero de momento no lo pongo por si encontramos una alternativa mejor.

		botonA�adirTramo = (JToggleButton) a�adirBoton("a�adir_tramo.png", "a�adir_tramo2.png",
				"A�adir Tramo", new AccionSobreMapa(new MLA�adirTramo(modelo,
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 1), true);
		botonA�adirTramo.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearTramo()));
		botonA�adirTramo.addMouseMotionListener(new EscuchaAyuda("Pulse aqu� para a�adir un nuevo tramo.", ventana));
		grupoherramientas.add(botonA�adirTramo);
		
		//Eliminar nodo.
		botonEliminarNodo = (JToggleButton) a�adirBoton("eliminar_nodo.png", "eliminar_nodo2.png", "Eliminar Nodo",
				new AccionSobreMapa(new MLEliminarNodo(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 2), true);
		botonEliminarNodo.addActionListener(new AccionBarra(ventana, null));
		botonEliminarNodo.addMouseMotionListener(new EscuchaAyuda("Pulse aqu� para eliminar un nodo.", ventana));
		grupoherramientas.add(botonEliminarNodo);

		//Eliminar tramo.
		botonEliminarTramo = (JToggleButton) a�adirBoton("eliminar_tramo.png", "eliminar_tramo2.png",
				"Eliminar Tramo", new AccionSobreMapa(new MLEliminarTramo(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 3), true);		
		botonEliminarTramo.addActionListener(new AccionBarra(ventana, null));
		botonEliminarTramo.addMouseMotionListener(new EscuchaAyuda("Pulse aqu� para eliminar un tramo.", ventana));
		grupoherramientas.add(botonEliminarTramo);

		// Aqui se a�adir�n los nuevos botones.
		boton = (JToggleButton) a�adirBoton("add_bus.PNG", "add_bus2.PNG", "A�adir linea de autobus",
				new AccionSobreMapa(new MLA�adirLineaAutobus(modelo,
						controlador, ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 4), true);
		boton.addActionListener(new AccionBarra(ventana, null));
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse aqu� para a�adir una nueva l�nea de autob�s.", ventana));
		grupoherramientas.add(boton);

		// A�adir Vias
		boton = (JToggleButton) a�adirBoton("a�adir_via.png", "a�adir_via2.png", "A�adir una via",
				new AccionSobreMapa(new MLA�adirVia(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 6), true);
		boton.addActionListener(new AccionBarra(ventana, null));
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse aqu� para a�adir una nueva v�a.", ventana));
		grupoherramientas.add(boton);
		
		//A�adir Buscar
		boton = (JToggleButton) a�adirBoton("buscar.PNG", "buscar2.PNG", "Buscar",
				new AccionBuscar(controlador,ventana.getPanel_mapa()), true);
		boton.addActionListener(new AccionBarra(ventana, null));
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse aqu� para buscar un elemento.", ventana));
		grupoherramientas.add(boton);
		
		//Mostrar itinerario entre 2 nodos
		boton = (JToggleButton) a�adirBoton("como_ir_a_icon1.GIF", "como_ir_a_icon2.GIF", "Mostrar itinerario entre 2 nodos",
				new AccionSobreMapa(new MLEscuchaItinerario(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 8), true);
		boton.addActionListener(new AccionBarra(ventana, null));
			//Faltar�a por ajustar la escucha de teclado y el numero (ahora 0) para el cursor.
		boton.addMouseMotionListener(new EscuchaAyuda("Mostrar itinerario entre 2 nodos", ventana));
		grupoherramientas.add(boton);
		
		
	}
	
	public JToggleButton getBotonA�adirNodo(){
		return this.botonA�adirNodo;
	}
	
	public JToggleButton getBotonA�adirTramo(){
		return this.botonA�adirTramo;
	}
	
	public JToggleButton getBotonSeleccionar(){
		return this.botonSeleccionar;
	}
	
	public JToggleButton getBotonEliminarTramo(){
		return this.botonEliminarTramo;
	}
	
	public JToggleButton getBotonEliminarNodo(){
		return this.botonEliminarNodo;
	}
}
