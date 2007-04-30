package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Ventana;
import is.SimTraffic.Vista.Acciones.AccionBarra;
import is.SimTraffic.Vista.Acciones.AccionBuscar;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaAyuda;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirLineaAutobus;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirVia;
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
	private JToggleButton botonAñadirNodo;
	private JToggleButton botonAñadirTramo;
	private JToggleButton botonEliminarNodo;
	private JToggleButton botonEliminarTramo;
	
	public BarraHerramientas(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		super(JToolBar.VERTICAL);
		JToggleButton boton;
		ButtonGroup grupoherramientas = new ButtonGroup();
		
		//Desplazar el mapa.
		botonDesplazar = (JToggleButton) añadirBoton("mover.png", "mover2.png",
				"Desplazar el mapa", new AccionSobreMapa(new MLDesplazar(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 7), true);
		botonDesplazar.addActionListener(new AccionBarra(ventana, null));
		botonDesplazar.addMouseMotionListener(new EscuchaAyuda("Pulse el raton en el mapa y matengalo para desplazarlo.", ventana));
		botonDesplazar.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonDesplazar);

		//Seleccionar y Mover.
		botonSeleccionar = (JToggleButton) añadirBoton("seleccionarYMover.png", "seleccionarYMover2.png",
				"Seleccionar y Mover", new AccionSobreMapa(new MLSeleccionarYMover(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 7), true);
		botonSeleccionar.addActionListener(new AccionBarra(ventana, null));
		botonSeleccionar.addMouseMotionListener(new EscuchaAyuda("Seleccione nodos y tramos y arrástrelos para moverlos por el mapa.", ventana));
		botonSeleccionar.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonSeleccionar);
		
		//Seleccionar Vias
		boton = (JToggleButton) añadirBoton("seleccionarVia1.png", "seleccionarVia2.png",
				"Seleccionar Vias", new AccionSobreMapa(new MLSeleccionarVia(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 9), true);
		boton.addActionListener(new AccionBarra(ventana, null));
		boton.addMouseMotionListener(new EscuchaAyuda("Seleccione los tramos del mapa para identificar a qué via pertenecen", ventana));
		boton.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(boton);
		
		
		
		//A partir de ahora no se utilizarán los botones de seleccionar y mover (los dejo comentados de momento).
		
		/*boton = (JToggleButton) añadirBoton("seleccionar-1.png", "seleccionar-2.png",
				"Seleccionar", new AccionSobreMapa(new MLSeleccionarNodos(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1), true);
		boton.addActionListener(new AccionBarra(ventana, ventana
				.getBarraSeleccionar()));
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse aquí y seleccione uno de los tres botones superiores de selección para elegir el tipo de la misma.", ventana));
		boton.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(boton);

		boton = (JToggleButton) añadirBoton("mover1.png", "mover2.png", "Mover la selección actual",
				new AccionSobreMapa(new MLMover(modelo, controlador, ventana
						.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), -1), true);
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse en los elementos seleccionados y arrastre el ratón hasta que los elementos estén en la posición deseada.", ventana));
		grupoherramientas.add(boton);*/
		
		botonAñadirNodo = (JToggleButton) añadirBoton("añadir_nodo.png", "añadir_nodo2.png",
				"Añadir Nodo", new AccionSobreMapa(new MLAñadirNodo(modelo,
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 0), true);
		botonAñadirNodo.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearNodo()));
		botonAñadirNodo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para añadir un nuevo nodo.", ventana));
		grupoherramientas.add(botonAñadirNodo);
		
		// Aquí también habría que añadir el oyente de teclado al
		// boton (y en el resto de botones),
		// pero de momento no lo pongo por si encontramos una alternativa mejor.

		botonAñadirTramo = (JToggleButton) añadirBoton("añadir_tramo.png", "añadir_tramo2.png",
				"Añadir Tramo", new AccionSobreMapa(new MLAñadirTramo(modelo,
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 1), true);
		botonAñadirTramo.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearTramo()));
		botonAñadirTramo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para añadir un nuevo tramo.", ventana));
		grupoherramientas.add(botonAñadirTramo);
		
		//Eliminar nodo.
		botonEliminarNodo = (JToggleButton) añadirBoton("eliminar_nodo.png", "eliminar_nodo2.png", "Eliminar Nodo",
				new AccionSobreMapa(new MLEliminarNodo(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 2), true);
		botonEliminarNodo.addActionListener(new AccionBarra(ventana, null));
		botonEliminarNodo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para eliminar un nodo.", ventana));
		grupoherramientas.add(botonEliminarNodo);

		//Eliminar tramo.
		botonEliminarTramo = (JToggleButton) añadirBoton("eliminar_tramo.png", "eliminar_tramo2.png",
				"Eliminar Tramo", new AccionSobreMapa(new MLEliminarTramo(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 3), true);		
		botonEliminarTramo.addActionListener(new AccionBarra(ventana, null));
		botonEliminarTramo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para eliminar un tramo.", ventana));
		grupoherramientas.add(botonEliminarTramo);

		// Aqui se añadirán los nuevos botones.
		boton = (JToggleButton) añadirBoton("add_bus.PNG", "add_bus2.PNG", "Añadir linea de autobus",
				new AccionSobreMapa(new MLAñadirLineaAutobus(modelo,
						controlador, ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 4), true);
		boton.addActionListener(new AccionBarra(ventana, null));
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para añadir una nueva línea de autobús.", ventana));
		grupoherramientas.add(boton);

		// Añadir Vias
		boton = (JToggleButton) añadirBoton("añadir_via.png", "añadir_via2.png", "Añadir una via",
				new AccionSobreMapa(new MLAñadirVia(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 6), true);
		boton.addActionListener(new AccionBarra(ventana, null));
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para añadir una nueva vía.", ventana));
		grupoherramientas.add(boton);
		
		//Añadir Buscar
		boton = (JToggleButton) añadirBoton("buscar.PNG", "buscar2.PNG", "Buscar",
				new AccionBuscar(controlador,ventana.getPanel_mapa()), true);
		boton.addActionListener(new AccionBarra(ventana, null));
		boton.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para buscar un elemento.", ventana));
		grupoherramientas.add(boton);
		
		//Mostrar itinerario entre 2 nodos
		boton = (JToggleButton) añadirBoton("como_ir_a_icon1.GIF", "como_ir_a_icon2.GIF", "Mostrar itinerario entre 2 nodos",
				new AccionSobreMapa(new MLEscuchaItinerario(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 8), true);
		boton.addActionListener(new AccionBarra(ventana, null));
			//Faltaría por ajustar la escucha de teclado y el numero (ahora 0) para el cursor.
		boton.addMouseMotionListener(new EscuchaAyuda("Mostrar itinerario entre 2 nodos", ventana));
		grupoherramientas.add(boton);
		
		
	}
	
	public JToggleButton getBotonAñadirNodo(){
		return this.botonAñadirNodo;
	}
	
	public JToggleButton getBotonAñadirTramo(){
		return this.botonAñadirTramo;
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
