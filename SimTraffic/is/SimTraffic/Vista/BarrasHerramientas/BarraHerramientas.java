package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
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
	private JToggleButton botonA�adirNodo;
	private JToggleButton botonA�adirTramo;
	private JToggleButton botonA�adirVia;
	private JToggleButton botonA�adirBus;
	private JToggleButton botonEliminarNodo;
	private JToggleButton botonEliminarTramo;
	private JToggleButton botonBuscarElem;
	
	public BarraHerramientas(IControlador controlador, IModelo modelo,
			Ventana ventana) {
		super(JToolBar.VERTICAL);
		JToggleButton boton;
		ButtonGroup grupoherramientas = new ButtonGroup();
		
		//Desplazar el mapa.
		botonDesplazar = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.0"), Messages.getString("BarraHerramientas.1"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.2"), new AccionSobreMapa(new MLDesplazar( //$NON-NLS-1$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 10), true);
		botonDesplazar.addActionListener(new AccionBarra(ventana, null));
		botonDesplazar.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.3"), ventana)); //$NON-NLS-1$
		botonDesplazar.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonDesplazar);

		//Seleccionar y Mover.
		botonSeleccionar = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.4"), Messages.getString("BarraHerramientas.5"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.6"), new AccionSobreMapa(new MLSeleccionarYMover( //$NON-NLS-1$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 7), true);
		botonSeleccionar.addActionListener(new AccionBarra(ventana, null));
		botonSeleccionar.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.7"), ventana)); //$NON-NLS-1$
		botonSeleccionar.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonSeleccionar);
		
		//Seleccionar Vias
		botonSeleccionarVias = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.8"), Messages.getString("BarraHerramientas.9"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.10"), new AccionSobreMapa(new MLSeleccionarVia( //$NON-NLS-1$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 9), true);
		botonSeleccionarVias.addActionListener(new AccionBarra(ventana, null));
		botonSeleccionarVias.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.11"), ventana)); //$NON-NLS-1$
		botonSeleccionarVias.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonSeleccionarVias);
		
		//Seleccionar Imagenes y redimensionarlas
		botonSeleccionarImagenes = (JToggleButton)a�adirBoton(Messages.getString("BarraHerramientas.12"), Messages.getString("BarraHerramientas.13"),Messages.getString("BarraHerramientas.14"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLSeleccionarImagen(
				modelo, controlador, ventana.getPanel_mapa(),ventana), ventana,
				ventana.getEscuchaTeclado(), 11), true);
		botonSeleccionarImagenes.addActionListener(new AccionBarra(ventana,ventana.getBarraRedimensionarImagen()));
		botonSeleccionarImagenes.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.15"), ventana)); //$NON-NLS-1$
		botonSeleccionarImagenes.addKeyListener(ventana.getEscuchaTeclado());
		grupoherramientas.add(botonSeleccionarImagenes);
		
		
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
		
		botonA�adirNodo = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.16"), Messages.getString("BarraHerramientas.17"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.18"), new AccionSobreMapa(new MLA�adirNodo(modelo, //$NON-NLS-1$
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 0), true);
		botonA�adirNodo.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearNodo()));
		botonA�adirNodo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.19"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonA�adirNodo);
		
		// Aqu� tambi�n habr�a que a�adir el oyente de teclado al
		// boton (y en el resto de botones),
		// pero de momento no lo pongo por si encontramos una alternativa mejor.

		botonA�adirTramo = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.20"), Messages.getString("BarraHerramientas.21"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.22"), new AccionSobreMapa(new MLA�adirTramo(modelo, //$NON-NLS-1$
						controlador, ventana.getPanel_mapa(), ventana),
						ventana, ventana.getEscuchaTeclado(), 1), true);
		botonA�adirTramo.addActionListener(new AccionBarra(ventana, ventana
				.getBarraCrearTramo()));
		botonA�adirTramo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.23"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonA�adirTramo);
		
		// A�adir Vias
		botonA�adirVia = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.24"), Messages.getString("BarraHerramientas.25"), Messages.getString("BarraHerramientas.26"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLA�adirVia(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 6), true);
		botonA�adirVia.addActionListener(new AccionBarra(ventana, null));
		botonA�adirVia.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.27"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonA�adirVia);
		
		//Eliminar nodo.
		botonEliminarNodo = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.28"), Messages.getString("BarraHerramientas.29"), Messages.getString("BarraHerramientas.30"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLEliminarNodo(modelo, controlador,
						ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 2), true);
		botonEliminarNodo.addActionListener(new AccionBarra(ventana, null));
		botonEliminarNodo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.31"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonEliminarNodo);

		//Eliminar tramo.
		botonEliminarTramo = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.32"), Messages.getString("BarraHerramientas.33"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("BarraHerramientas.34"), new AccionSobreMapa(new MLEliminarTramo( //$NON-NLS-1$
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 3), true);		
		botonEliminarTramo.addActionListener(new AccionBarra(ventana, null));
		botonEliminarTramo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.35"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonEliminarTramo);

		// Aqui se a�adir�n los nuevos botones.
		botonA�adirBus = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.36"), Messages.getString("BarraHerramientas.37"), Messages.getString("BarraHerramientas.38"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLA�adirLineaAutobus(modelo,
						controlador, ventana.getPanel_mapa()), ventana, ventana
						.getEscuchaTeclado(), 4), true);
		botonA�adirBus.addActionListener(new AccionBarra(ventana, null));
		botonA�adirBus.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.39"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonA�adirBus);
		
		//A�adir Buscar
		botonBuscarElem = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.40"), Messages.getString("BarraHerramientas.41"), Messages.getString("BarraHerramientas.42"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionBuscar(controlador,ventana.getPanel_mapa()), true);
		botonBuscarElem.addActionListener(new AccionBarra(ventana, null));
		botonBuscarElem.addMouseMotionListener(new EscuchaAyuda(Messages.getString("BarraHerramientas.43"), ventana)); //$NON-NLS-1$
		grupoherramientas.add(botonBuscarElem);
		
		//Mostrar itinerario entre 2 nodos
		boton = (JToggleButton) a�adirBoton(Messages.getString("BarraHerramientas.44"), Messages.getString("BarraHerramientas.45"), Messages.getString("BarraHerramientas.46"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new AccionSobreMapa(new MLEscuchaItinerario(
						modelo, controlador, ventana.getPanel_mapa()), ventana,
						ventana.getEscuchaTeclado(), 8), true);
		boton.addActionListener(new AccionBarra(ventana, null));
			//Faltar�a por ajustar la escucha de teclado y el numero (ahora 0) para el cursor.
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
	
	public JToggleButton getBotonA�adirNodo(){
		return this.botonA�adirNodo;
	}
	
	public JToggleButton getBotonA�adirTramo(){
		return this.botonA�adirTramo;
	}
	
	public JToggleButton getBotonA�adirVia(){
		return this.botonA�adirVia;
	}
	
	public JToggleButton getBotonA�adirBus(){
		return this.botonA�adirBus;
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
