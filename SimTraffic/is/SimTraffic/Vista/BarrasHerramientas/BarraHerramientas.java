package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
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
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarImagen;

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
	private JToggleButton botonSeleccionarVias;
	private JToggleButton botonSeleccionarImagenes;
	private JToggleButton botonAñadirNodo;
	private JToggleButton botonAñadirTramo;
	private JToggleButton botonAñadirVia;
	private JToggleButton botonAñadirBus;
	private JToggleButton botonEliminarNodo;
	private JToggleButton botonEliminarTramo;
	private JToggleButton botonBuscarElem;
	
	public BarraHerramientas(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		super(JToolBar.VERTICAL);
		JToggleButton boton;
		ButtonGroup grupoherramientas = new ButtonGroup();
		
		//Desplazar el mapa.
		botonDesplazar = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.0"), Messages.getString("BarraHerramientas.1"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.2"), new AccionSobreMapa(new MLDesplazar( //$NON-NLS-1$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 10), true);
		botonDesplazar.addActionListener(new AccionBarra(ventana, null));
		botonDesplazar.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.3"), ventana)); //$NON-NLS-1$
		botonDesplazar.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonDesplazar);

		//Seleccionar y Mover.
		botonSeleccionar = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.4"), Messages.getString("BarraHerramientas.5"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.6"), new AccionSobreMapa(new MLSeleccionarYMover( //$NON-NLS-1$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 7), true);
		botonSeleccionar.addActionListener(new AccionBarra(ventana, null));
		botonSeleccionar.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.7"), ventana)); //$NON-NLS-1$
		botonSeleccionar.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonSeleccionar);
		
		//Seleccionar Vias
		botonSeleccionarVias = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.8"), Messages.getString("BarraHerramientas.9"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.10"), new AccionSobreMapa(new MLSeleccionarVia( //$NON-NLS-1$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 9), true);
		botonSeleccionarVias.addActionListener(new AccionBarra(ventana, null));
		botonSeleccionarVias.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.11"), ventana)); //$NON-NLS-1$
		botonSeleccionarVias.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonSeleccionarVias);
		
		//Seleccionar Imagenes y redimensionarlas
		botonSeleccionarImagenes = (JToggleButton)añadirBoton(Messages.getString("BarraHerramientas.12"), Messages.getString("BarraHerramientas.13"),Messages.getString("BarraHerramientas.14"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLSeleccionarImagen(
				modelo, controlador, ventana.getPanel_mapa(),ventana), ventana,
				ventana.getEscuchaTeclado(), 11), true);
		botonSeleccionarImagenes.addActionListener(new AccionBarra(ventana,ventana.getBarraRedimensionarImagen()));
		botonSeleccionarImagenes.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.15"), ventana)); //$NON-NLS-1$
		botonSeleccionarImagenes.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonSeleccionarImagenes);
		
		
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
		
		botonAñadirNodo = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.16"), Messages.getString("BarraHerramientas.17"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.18"), new AccionSobreMapa(new MLAñadirNodo(modelo, //$NON-NLS-1$
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 0), true);
		botonAñadirNodo.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearNodo()));
		botonAñadirNodo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.19"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonAñadirNodo);
		
		// Aquí también habría que añadir el oyente de teclado al
		// boton (y en el resto de botones),
		// pero de momento no lo pongo por si encontramos una alternativa mejor.

		botonAñadirTramo = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.20"), Messages.getString("BarraHerramientas.21"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.22"), new AccionSobreMapa(new MLAñadirTramo(modelo, //$NON-NLS-1$
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 1), true);
		botonAñadirTramo.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearTramo()));
		botonAñadirTramo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.23"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonAñadirTramo);
		
		// Añadir Vias
		botonAñadirVia = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.24"), Messages.getString("BarraHerramientas.25"), Messages.getString("BarraHerramientas.26"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLAñadirVia(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 6), true);
		botonAñadirVia.addActionListener(new AccionBarra(ventana, null));
		botonAñadirVia.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.27"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonAñadirVia);
		
		//Eliminar nodo.
		botonEliminarNodo = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.28"), Messages.getString("BarraHerramientas.29"), Messages.getString("BarraHerramientas.30"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLEliminarNodo(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 2), true);
		botonEliminarNodo.addActionListener(new AccionBarra(ventana, null));
		botonEliminarNodo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.31"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonEliminarNodo);

		//Eliminar tramo.
		botonEliminarTramo = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.32"), Messages.getString("BarraHerramientas.33"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.34"), new AccionSobreMapa(new MLEliminarTramo( //$NON-NLS-1$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 3), true);		
		botonEliminarTramo.addActionListener(new AccionBarra(ventana, null));
		botonEliminarTramo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.35"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonEliminarTramo);

		// Aqui se añadirán los nuevos botones.
		botonAñadirBus = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.36"), Messages.getString("BarraHerramientas.37"), Messages.getString("BarraHerramientas.38"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLAñadirLineaAutobus(modelo,
						controlador, ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 4), true);
		botonAñadirBus.addActionListener(new AccionBarra(ventana, null));
		botonAñadirBus.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.39"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonAñadirBus);
		
		//Añadir Buscar
		botonBuscarElem = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.40"), Messages.getString("BarraHerramientas.41"), Messages.getString("BarraHerramientas.42"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionBuscar(controlador,ventana.getPanel_mapa()), true);
		botonBuscarElem.addActionListener(new AccionBarra(ventana, null));
		botonBuscarElem.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.43"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonBuscarElem);
		
		//Mostrar itinerario entre 2 nodos
		boton = (JToggleButton) añadirBoton(Messages.getString("BarraHerramientas.44"), Messages.getString("BarraHerramientas.45"), Messages.getString("BarraHerramientas.46"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLEscuchaItinerario(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 8), true);
		boton.addActionListener(new AccionBarra(ventana, null));
			//Faltaría por ajustar la escucha de teclado y el numero (ahora 0) para el cursor.
		boton.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.47"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(boton);
		
		
	}
	
	public JToggleButton getBotonDesplazar(){
		return this.botonDesplazar;
	}
	
	public JToggleButton getBotonSeleccionar(){
		return this.botonSeleccionar;
	}
	
	public JToggleButton getBotonSeleccionarVias(){
		return this.botonSeleccionarVias;
	}
	
	public JToggleButton getBotonAñadirNodo(){
		return this.botonAñadirNodo;
	}
	
	public JToggleButton getBotonAñadirTramo(){
		return this.botonAñadirTramo;
	}
	
	public JToggleButton getBotonAñadirVia(){
		return this.botonAñadirVia;
	}
	
	public JToggleButton getBotonAñadirBus(){
		return this.botonAñadirBus;
	}
	
	public JToggleButton getBotonEliminarTramo(){
		return this.botonEliminarTramo;
	}
	
	public JToggleButton getBotonEliminarNodo(){
		return this.botonEliminarNodo;
	}

	public JToggleButton getBotonSeleccionarImagenes() {
		return botonSeleccionarImagenes;
	}

	public void setBotonSeleccionarImagenes(JToggleButton botonSeleccionarImagenes) {
		this.botonSeleccionarImagenes = botonSeleccionarImagenes;
	}
}
