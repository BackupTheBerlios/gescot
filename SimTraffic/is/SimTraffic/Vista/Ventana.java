package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Acciones.AccionBarra;
import is.SimTraffic.Vista.Acciones.AccionBuscar;
import is.SimTraffic.Vista.Acciones.AccionCambiarRep;
import is.SimTraffic.Vista.Acciones.AccionCargar;
import is.SimTraffic.Vista.Acciones.AccionCargarImagen;
import is.SimTraffic.Vista.Acciones.AccionComenzarSimulacion;
import is.SimTraffic.Vista.Acciones.AccionCopiar;
import is.SimTraffic.Vista.Acciones.AccionCortar;
import is.SimTraffic.Vista.Acciones.AccionDescargar;
import is.SimTraffic.Vista.Acciones.AccionDeshacer;
import is.SimTraffic.Vista.Acciones.AccionDetenerSimulacion;
import is.SimTraffic.Vista.Acciones.AccionEliminarNodo;
import is.SimTraffic.Vista.Acciones.AccionEliminarSeleccion;
import is.SimTraffic.Vista.Acciones.AccionEliminarTramo;
import is.SimTraffic.Vista.Acciones.AccionGuardar;
import is.SimTraffic.Vista.Acciones.AccionIdioma;
import is.SimTraffic.Vista.Acciones.AccionImprimir;
import is.SimTraffic.Vista.Acciones.AccionNuevo;
import is.SimTraffic.Vista.Acciones.AccionPausarSimulacion;
import is.SimTraffic.Vista.Acciones.AccionPropiedadesNodo;
import is.SimTraffic.Vista.Acciones.AccionPropiedadesTramo;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.Acciones.AccionVerLineasBus;
import is.SimTraffic.Vista.Acciones.AccionVerNodos;
import is.SimTraffic.Vista.Acciones.AccionVerTramos;
import is.SimTraffic.Vista.Acciones.AccionVerVias;
import is.SimTraffic.Vista.Acciones.AccionZoom;
import is.SimTraffic.Vista.BarrasHerramientas.BarraCrearNodo;
import is.SimTraffic.Vista.BarrasHerramientas.BarraHerramientas;
import is.SimTraffic.Vista.BarrasHerramientas.BarraRedimensionarImagen;
import is.SimTraffic.Vista.BarrasHerramientas.BarraSeleccionar;
import is.SimTraffic.Vista.BarrasHerramientas.BarraSuperior;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaAyuda;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaTeclado;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirLineaAutobus;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirVia;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLEscuchaItinerario;
import is.SimTraffic.Vista.EscuchasRaton.MLEscuchaSiempre;
import is.SimTraffic.Vista.EscuchasRaton.MLMapaBDerecho;
import is.SimTraffic.Vista.EscuchasRaton.MLMover;
import is.SimTraffic.Vista.EscuchasRaton.MLPegar;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionaNodoBDerecho;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionaTramoBDerecho;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarElementos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarYMover;
import is.SimTraffic.Vista.Representaciones.Representacion;
import is.SimTraffic.Vista.Representaciones.RepresentacionAvanzada;
import is.SimTraffic.Vista.Representaciones.RepresentacionSimple;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.button.SubstanceButtonShaper;
import org.jvnet.substance.skin.AutumnSkin;
import org.jvnet.substance.skin.BusinessBlueSteelSkin;
import org.jvnet.substance.skin.ChallengerDeepSkin;
import org.jvnet.substance.skin.CremeSkin;
import org.jvnet.substance.skin.EmeraldDuskSkin;
import org.jvnet.substance.skin.FieldOfWheatSkin;
import org.jvnet.substance.skin.FindingNemoSkin;
import org.jvnet.substance.skin.MagmaSkin;
import org.jvnet.substance.skin.MangoSkin;
import org.jvnet.substance.skin.OfficeSilver2007Skin;
import org.jvnet.substance.skin.RavenSkin;
import org.jvnet.substance.skin.SaharaSkin;
import org.jvnet.substance.skin.StreetlightsSkin;
import org.jvnet.substance.skin.SubstanceSkin;
import org.jvnet.substance.watermark.SubstanceBinaryWatermark;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;
import org.jvnet.substance.watermark.SubstanceCopperplateEngravingWatermark;
import org.jvnet.substance.watermark.SubstanceCrosshatchWatermark;
import org.jvnet.substance.watermark.SubstanceFabricWatermark;
import org.jvnet.substance.watermark.SubstanceGenericNoiseWatermark;
import org.jvnet.substance.watermark.SubstanceKatakanaWatermark;
import org.jvnet.substance.watermark.SubstanceMagneticFieldWatermark;
import org.jvnet.substance.watermark.SubstanceMazeWatermark;
import org.jvnet.substance.watermark.SubstanceMetalWallWatermark;
import org.jvnet.substance.watermark.SubstanceStripeWatermark;
import org.jvnet.substance.watermark.SubstanceWatermark;
import org.jvnet.substance.watermark.SubstanceWoodWatermark;

/**
 * Ventana que contiene la interfaz gráfica de la aplicación.
 * <p>
 * Iconos tomados del tema de escritorio Tango de linux
 * (http://tango.freedesktop.org/Tango_Icon_Gallery).
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Ventana extends JFrame {

	private static final long serialVersionUID = -3549855005952631901L;

	/**
	 * Panel de la parte superior de panel_herramientas. Contiene los botones de
	 * las distintas herramientas.
	 */
	private JToolBar panel;

	/**
	 * Panel de la parte izquierda de la interfaz gráfica.
	 */
	private JPanel panel_herramientas;

	/**
	 * Barra de menús de la parte superior de la interfaz.
	 */
	private JMenuBar menuBar;

	/**
	 * Barra de edición del panel de arriba
	 */
	private JPanel superior_arriba;
	/**
	 * Modelo asociado a la interfaz.
	 */
	private IModelo modelo;

	/**
	 * Controlador asociado a la interfaz.
	 */
	private IControlador controlador;

	/**
	 * Escucha de ratón actual.
	 */
	EscuchaRaton escucha;

	/**
	 * Panel para editar los mapas. Puede ser que haya que cambiarlo por una
	 * JLabel.
	 */
	private PanelMapa panel_mapa;

	/**
	 * Panel con los iconos de la parte superior.
	 */
	private JPanel superior;

	private JLabel posicionX;

	private JLabel posicionY;

	/**
	 * Oyentes para teclado y ratón.
	 */
	
	//MLSeleccionarNodos escuchaSeleccion;

	MLSeleccionarElementos escuchaSeleccionNodosYTramos;//esto sobra?

	//MLSeleccionarTramos escuchaSeleccionTramos;

	MLSeleccionaNodoBDerecho escuchaSeleccionNodoBDerecho;//esto sobra?
	
	MLSeleccionaTramoBDerecho escuchaSeleccionTramoBDerecho;//esto sobra?
	
	MLPegar escuchaPegar;
	
	MLSeleccionarYMover escuchaSeleccionar;
	
	MLMapaBDerecho escuchaMapaBDerecho;

	EscuchaTeclado escuchaTeclado;

	private JPanel superior_abajo;

	private BarraSeleccionar barraSeleccionar;

	private boolean panel_añadido;

	/**
	 * Elementos de la barra Crear Nodo
	 */
	private BarraCrearNodo barraCrearNodo;

	/**
	 * Elementos de la barra Crear Tramo
	 */
	private BarraCrearTramo barraCrearTramo;
	
	/**
	 * Elementos de la barra Redimensionar Imagen
	 */
	private BarraRedimensionarImagen barraRedimensionarImagen;

	private JTextField ayudaDinamica;
	
	private JMenuItem pegarSeleccion;
	
	private JPopupMenu emergenteMapa;
	
	private JPopupMenu emergenteTerminar;

	private JMenuItem copiarSeleccion;

	private JMenuItem cortarSeleccion;
	
	private Log popUpLog;

	/**
	 * Constructor de la ventana.
	 * 
	 * @param modelo
	 *            Modelo asociado a la interfaz gráfica.
	 * @param controlador
	 *            Controlador asociado a la interfaz gráfica.
	 */
	public Ventana(IModelo modelo, IControlador controlador) {
		this.modelo = modelo;
		this.controlador = controlador;
		setSize(800, 600);
		setTitle(Messages.getString("Ventana.1")); //$NON-NLS-1$
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // La ventana aparece
		// maximizada.
		this.escucha = null;
		panel_mapa = new PanelMapa(200, 200);
		

		panel_añadido = false;

		crearBarraEstado();

		EscuchaRaton escuchaSiempre = new MLEscuchaSiempre(modelo, controlador,
				panel_mapa, posicionX, posicionY, this);
		this.panel_mapa.addMouseListener(escuchaSiempre);
		this.panel_mapa.addMouseMotionListener(escuchaSiempre);
		/*
		escuchaSeleccion = new MLSeleccionarNodos(modelo, controlador,
				panel_mapa);
		escuchaSeleccionTramos = new MLSeleccionarTramos(modelo, controlador,
				panel_mapa);
		*/
		/*
		escuchaSeleccionNodosYTramos = new MLSeleccionarElementos(modelo,
				controlador, panel_mapa);
		 */

		escuchaSeleccionTramoBDerecho = new MLSeleccionaTramoBDerecho(modelo,
				controlador, panel_mapa);
		this.panel_mapa.addMouseListener(escuchaSeleccionTramoBDerecho);

		escuchaSeleccionNodoBDerecho = new MLSeleccionaNodoBDerecho(modelo,
				controlador, panel_mapa);
		this.panel_mapa.addMouseListener(escuchaSeleccionNodoBDerecho);
		
		escuchaSeleccionNodoBDerecho = new MLSeleccionaNodoBDerecho(modelo,
				controlador, panel_mapa);
		this.panel_mapa.addMouseListener(escuchaSeleccionNodoBDerecho);
		
		escuchaMapaBDerecho = new MLMapaBDerecho(modelo,controlador,panel_mapa,this);
		this.panel_mapa.addMouseListener(escuchaMapaBDerecho);
		
		/*
		escuchaPegar = new MLPegar(modelo,controlador,panel_mapa);
		this.panel_mapa.addMouseListener(escuchaPegar);
		*/
		
		/*escuchaSeleccionar = new MLSeleccionarYMover (modelo,controlador,panel_mapa);
		this.panel_mapa.addMouseListener(escuchaSeleccionar);*/

		escuchaTeclado = new EscuchaTeclado(panel_mapa, escuchaSeleccionNodosYTramos);

		crearBotonesSuperiores();

		crearBarraMenu();

		crearHerramientas();

		añadirPanelMapa();

		panel.setFocusable(true);
		
		añadirMenuEmergenteNodo();
		añadirMenuEmergenteTramo();
		añadirMenuEmergenteMapa();
		añadirMenuEmergenteTerminar();
	}

	
	/**
	 * Crea el popUp emergente que aparecerá al pulsar el botón derecho
	 */
	public void añadirMenuEmergenteNodo(){
		JPopupMenu emergenteNodo = new JPopupMenu(Messages.getString("Ventana.2")); //$NON-NLS-1$
		JMenuItem eliminarNodo = new JMenuItem(Messages.getString("Ventana.3")); //$NON-NLS-1$
		eliminarNodo.addActionListener(new AccionEliminarNodo(modelo,
				controlador, panel_mapa));		
		JMenuItem propiedades = new JMenuItem(Messages.getString("Ventana.4")); //$NON-NLS-1$
		propiedades.addActionListener(new AccionPropiedadesNodo(modelo,
				controlador, panel_mapa));		
		
		emergenteNodo.add(eliminarNodo);	
		emergenteNodo.add(propiedades);

		panel_mapa.setMenuEmergenteNodo(emergenteNodo);
	}
	public void añadirMenuEmergenteTramo(){
		JPopupMenu emergenteTramo = new JPopupMenu(Messages.getString("Ventana.5")); //$NON-NLS-1$
		JMenuItem eliminarTramo = new JMenuItem(Messages.getString("Ventana.6")); //$NON-NLS-1$
		eliminarTramo.addActionListener(new AccionEliminarTramo(modelo,
				controlador, panel_mapa));
		JMenuItem propiedades = new JMenuItem(Messages.getString("Ventana.7"));		 //$NON-NLS-1$
		propiedades.addActionListener(new AccionPropiedadesTramo(modelo,
				controlador, panel_mapa));		
		
		emergenteTramo.add(eliminarTramo);
		emergenteTramo.add(propiedades);
	
		panel_mapa.setMenuEmergenteTramo(emergenteTramo);
	}	
	/**
	 * 
	 */
	public void añadirMenuEmergenteMapa(){
		emergenteMapa = new JPopupMenu(Messages.getString("Ventana.8"));	 //$NON-NLS-1$
		
		JMenuItem cambiarRepresentacion = new JMenuItem(Messages.getString("Ventana.9")); //$NON-NLS-1$
		cambiarRepresentacion.addActionListener(new AccionCambiarRep(panel_mapa,
				new RepresentacionAvanzada(), new RepresentacionSimple()));
		cambiarRepresentacion.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.10"), this)); //$NON-NLS-1$
		
		JMenuItem zoomIn = new JMenuItem(Messages.getString("Ventana.11")); //$NON-NLS-1$
		zoomIn.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonZoomIn().doClick();
					}
				}
		);
		zoomIn.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.12"), this)); //$NON-NLS-1$
		
		JMenuItem zoomOut = new JMenuItem(Messages.getString("Ventana.13")); //$NON-NLS-1$
		zoomOut.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonZoomOut().doClick();
					}
				}
		);
		zoomOut.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.14"),this)); //$NON-NLS-1$
		
		JMenuItem comenzarSimulación = new JMenuItem(Messages.getString("Ventana.15")); //$NON-NLS-1$
		comenzarSimulación.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonSimular().doClick();
					}
				}
		);
		comenzarSimulación.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.16"),this)); //$NON-NLS-1$
		
		JMenuItem detenerSimulación = new JMenuItem(Messages.getString("Ventana.17")); //$NON-NLS-1$
		detenerSimulación.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonDetener().doClick();
					}
				}
		);
		detenerSimulación.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.18"), this)); //$NON-NLS-1$
		
		copiarSeleccion = new JMenuItem(Messages.getString("Ventana.19")); //$NON-NLS-1$
		copiarSeleccion.addActionListener(new AccionCopiar(
				modelo, controlador, panel_mapa));
		copiarSeleccion.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.20"),this)); //$NON-NLS-1$
		
		cortarSeleccion = new JMenuItem(Messages.getString("Ventana.21")); //$NON-NLS-1$
		cortarSeleccion.addActionListener(new AccionCortar(
				modelo, controlador, panel_mapa));
		cortarSeleccion.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.22"),this)); //$NON-NLS-1$
		
		pegarSeleccion = new JMenuItem(Messages.getString("Ventana.23")); //$NON-NLS-1$
		pegarSeleccion.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonPegar().doClick();
					}
				}
		);
		pegarSeleccion.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.24"),this)); //$NON-NLS-1$
		
		JMenuItem eliminarSeleccion = new JMenuItem(Messages.getString("Ventana.25")); //$NON-NLS-1$
		eliminarSeleccion.addActionListener(new AccionEliminarSeleccion(
				modelo, controlador, panel_mapa));
		eliminarSeleccion.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.26"), this)); //$NON-NLS-1$
		
		JMenuItem desplazarMapa = new JMenuItem(Messages.getString("Ventana.27")); //$NON-NLS-1$
		desplazarMapa.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonDesplazar().doClick();
					}
				}
		);
		desplazarMapa.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.28"), this)); //$NON-NLS-1$
		
		
		JMenu submenuSeleccion = new JMenu(Messages.getString("Ventana.29")); //$NON-NLS-1$
		
		JMenuItem seleccion = new JMenuItem(Messages.getString("Ventana.30")); //$NON-NLS-1$
		seleccion.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonSeleccionar().doClick();
					}
				}
		);
		seleccion.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.31"), this)); //$NON-NLS-1$
		submenuSeleccion.add(seleccion);
		
		JMenuItem seleccionVias = new JMenuItem(Messages.getString("Ventana.32")); //$NON-NLS-1$
		seleccionVias.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonSeleccionarVias().doClick();
					}
				}
		);
		seleccionVias.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.33"), this)); //$NON-NLS-1$
		submenuSeleccion.add(seleccionVias);
		
		JMenu submenuInsertar = new JMenu(Messages.getString("Ventana.34")); //$NON-NLS-1$
		
		JMenuItem insertarNodo = new JMenuItem (Messages.getString("Ventana.35")); //$NON-NLS-1$
		insertarNodo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonAñadirNodo().doClick();
					}
				}
		);
		insertarNodo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.36"), this)); //$NON-NLS-1$
		submenuInsertar.add(insertarNodo);
		
		JMenuItem insertarTramo = new JMenuItem (Messages.getString("Ventana.37")); //$NON-NLS-1$
		insertarTramo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonAñadirTramo().doClick();
					}
				}
		);
		insertarTramo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.38"), this)); //$NON-NLS-1$
		submenuInsertar.add(insertarTramo);
		
		JMenuItem insertarVia = new JMenuItem (Messages.getString("Ventana.39")); //$NON-NLS-1$
		insertarVia.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonAñadirVia().doClick();
					}
				}
		);
		insertarVia.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.40"), this)); //$NON-NLS-1$
		submenuInsertar.add(insertarVia);
		
		JMenuItem insertarLineaBus = new JMenuItem (Messages.getString("Ventana.41")); //$NON-NLS-1$
		insertarLineaBus.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonAñadirBus().doClick();
					}
				}
		);
		insertarLineaBus.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.42"), this)); //$NON-NLS-1$
		submenuInsertar.add(insertarLineaBus);
		
		JMenu submenuEliminar = new JMenu(Messages.getString("Ventana.43")); //$NON-NLS-1$
		
		JMenuItem eliminarNodo = new JMenuItem (Messages.getString("Ventana.44")); //$NON-NLS-1$
		eliminarNodo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonEliminarNodo().doClick();
					}
				}
		);
		eliminarNodo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.45"), this)); //$NON-NLS-1$
		submenuEliminar.add(eliminarNodo);
		
		JMenuItem eliminarTramo = new JMenuItem (Messages.getString("Ventana.46")); //$NON-NLS-1$
		eliminarTramo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonEliminarTramo().doClick();
					}
				}
		);
		eliminarTramo.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.47"), this)); //$NON-NLS-1$
		submenuEliminar.add(eliminarTramo);

		emergenteMapa.add(cambiarRepresentacion);
		emergenteMapa.add(zoomIn);
		emergenteMapa.add(zoomOut);
		emergenteMapa.addSeparator();
		emergenteMapa.add(comenzarSimulación);
		emergenteMapa.add(detenerSimulación);
		emergenteMapa.addSeparator();
		emergenteMapa.add(copiarSeleccion);
		emergenteMapa.add(cortarSeleccion);
		emergenteMapa.add(pegarSeleccion);
		emergenteMapa.addSeparator();
		emergenteMapa.add(eliminarSeleccion);
		emergenteMapa.addSeparator();
		emergenteMapa.add(desplazarMapa);
		emergenteMapa.add(submenuSeleccion);
		emergenteMapa.add(submenuInsertar);
		emergenteMapa.add(submenuEliminar);
		
		panel_mapa.setMenuEmergenteMapa(emergenteMapa);
	}
	
	public void añadirMenuEmergenteTerminar(){
		
		emergenteTerminar = new JPopupMenu(Messages.getString("Ventana.48"));	 //$NON-NLS-1$
		
		JMenuItem terminar = new JMenuItem(Messages.getString("Ventana.49")); //$NON-NLS-1$
		terminar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						escucha.notificar(-5);
					}
					
				}
		);
		terminar.addMouseMotionListener(new EscuchaAyuda(Messages.getString("Ventana.50"), this)); //$NON-NLS-1$
		
		emergenteTerminar.add(terminar);
		
		
	}

	/**
	 * Crea la barra de menús.
	 */
	public void crearBarraMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		crearMenuArchivo();

		crearMenuEdicion();

		crearMenuVis();

		crearMenuMapa();

		crearMenuSimulacion();

		crearMenuApariencia();
		
		crearMenuAyuda();
		
	}

	private void crearMenuApariencia() 
	{
		JMenu aparienciaMenu = new JMenu();
		aparienciaMenu.setText("Apariencia");
		aparienciaMenu.setMnemonic('P');
		menuBar.add(aparienciaMenu);
		
		JMenuItem cambio = new JMenuItem("Cambiar Look and Feel");
		aparienciaMenu.add(cambio);
		cambio.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) 
			{
				if (JOptionPane.showConfirmDialog(null, "Para que se apliquen los cambios, se cerrará el programa y deberá volver a iniciarlo. ¿Está seguro?", "Cambio de apariencia", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					try 
					{
						FileWriter fw = new FileWriter(new File(".\\look.conf"));
						fw.write(UIManager.getLookAndFeel().getName() + "\n");
						fw.close();
						System.exit(0);
					}
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JMenu watermark = new JMenu();
		watermark.setText("Marca de agua");
		aparienciaMenu.add(watermark);
		
		crearMenuItemWatermark("Binario", watermark, new SubstanceBinaryWatermark());
		crearMenuItemWatermark("Burbujas", watermark, new SubstanceBubblesWatermark());
		crearMenuItemWatermark("Placa de cobre", watermark, new SubstanceCopperplateEngravingWatermark());
		crearMenuItemWatermark("Crosshatch", watermark, new SubstanceCrosshatchWatermark());
		crearMenuItemWatermark("Fabric", watermark, new SubstanceFabricWatermark());
		crearMenuItemWatermark("Madera", watermark, new SubstanceWoodWatermark());
		crearMenuItemWatermark("Ruido genérico", watermark, new SubstanceGenericNoiseWatermark());
		crearMenuItemWatermark("Katakana", watermark, new SubstanceKatakanaWatermark());
		crearMenuItemWatermark("Campo magnético", watermark, new SubstanceMagneticFieldWatermark());
		crearMenuItemWatermark("Muro de metal ", watermark, new SubstanceMetalWallWatermark());
		crearMenuItemWatermark("Barras", watermark, new SubstanceStripeWatermark());
		crearMenuItemWatermark("Laberinto", watermark, new SubstanceMazeWatermark());
		
		JMenu theme = new JMenu();
		theme.setText("Tema");
		aparienciaMenu.add(theme);
		
		crearMenuItemTheme("Otoñal", theme, new AutumnSkin());
		crearMenuItemTheme("Acero azul", theme, new BusinessBlueSteelSkin());
		crearMenuItemTheme("Crema", theme, new CremeSkin());
		crearMenuItemTheme("Esmeralda", theme, new EmeraldDuskSkin());
		crearMenuItemTheme("Campo de avena", theme, new FieldOfWheatSkin());
		crearMenuItemTheme("Magma", theme, new MagmaSkin());
		crearMenuItemTheme("Mango", theme, new MangoSkin());
		crearMenuItemTheme("Raven", theme, new RavenSkin());
		crearMenuItemTheme("Luces de calle", theme, new StreetlightsSkin());
		crearMenuItemTheme("Challenger Deep", theme, new ChallengerDeepSkin());
		crearMenuItemTheme("Sahara", theme, new SaharaSkin());
		crearMenuItemTheme("Nemo", theme, new FindingNemoSkin());
		crearMenuItemTheme("Office Silver 2007", theme, new OfficeSilver2007Skin());
		
		JMenu button = new JMenu();
		button.setText("Tipo de botones");
		aparienciaMenu.add(button);
		crearMenuItemButtonShape("Clasico", button, new ClassicButtonShaper());
		crearMenuItemButtonShape("Redondeado", button, new StandardButtonShaper());
	}


	private void crearMenuItemWatermark(String string, JMenu menupadre, final SubstanceWatermark watermark) 
	{
		JMenuItem menu = new JMenuItem(string);
		menupadre.add(menu);
		menu.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						SubstanceLookAndFeel.setCurrentWatermark(watermark);
						repaint();
					}
				});
	}
	
	private void crearMenuItemButtonShape(String string, JMenu menupadre, final SubstanceButtonShaper button) 
	{
		JMenuItem menu = new JMenuItem(string);
		menupadre.add(menu);
		menu.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						SubstanceLookAndFeel.setCurrentButtonShaper(button);
						repaint();
					}
				});
	}
	
	private void crearMenuItemTheme(String string, JMenu menupadre, final SubstanceSkin theme) 
	{
		JMenuItem menu = new JMenuItem(string);
		menupadre.add(menu);
		menu.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						SubstanceWatermark water = SubstanceLookAndFeel.getCurrentWatermark();
						SubstanceLookAndFeel.setSkin(theme);
						SubstanceLookAndFeel.setCurrentWatermark(water);
						repaint();
					}
				});
	}


	/**
	 * Crea el menú Archivo.
	 */
	public void crearMenuArchivo() {
		JMenu archivoMenu = new JMenu();
		archivoMenu.setText(Messages.getString("Ventana.51")); //$NON-NLS-1$
		archivoMenu.setMnemonic('A');
		menuBar.add(archivoMenu);

		
		JMenuItem nuevoMapaMenuItem = new JMenuItem();
		nuevoMapaMenuItem
				.addActionListener(new AccionNuevo(controlador, panel_mapa));
		nuevoMapaMenuItem.setText(Messages.getString("Ventana.52")); //$NON-NLS-1$
		nuevoMapaMenuItem.setMnemonic('N');
		nuevoMapaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		archivoMenu.add(nuevoMapaMenuItem);

		archivoMenu.addSeparator();

		JMenuItem cargarMapaMenuItem = new JMenuItem();
		cargarMapaMenuItem.addActionListener(new AccionCargar(controlador,
				panel_mapa));
		cargarMapaMenuItem.setText(Messages.getString("Ventana.53")); //$NON-NLS-1$
		cargarMapaMenuItem.setMnemonic('M');
		cargarMapaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
		archivoMenu.add(cargarMapaMenuItem);
		
		JMenuItem descargarMapaMenuItem = new JMenuItem();
		descargarMapaMenuItem.addActionListener(new AccionDescargar(controlador,panel_mapa));
		descargarMapaMenuItem.setText(Messages.getString("Ventana.54")); //$NON-NLS-1$
		descargarMapaMenuItem.setMnemonic('D');
		archivoMenu.add(descargarMapaMenuItem);

		JMenuItem guardarmapaMenuItem = new JMenuItem();
		guardarmapaMenuItem.addActionListener(new AccionGuardar(controlador));
		guardarmapaMenuItem.setText(Messages.getString("Ventana.55")); //$NON-NLS-1$
		guardarmapaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));
		guardarmapaMenuItem.setMnemonic('G');
		archivoMenu.add(guardarmapaMenuItem);

		archivoMenu.addSeparator();
		
		JMenuItem imprimirMapaMenuItem = new JMenuItem();
		imprimirMapaMenuItem.addActionListener(new AccionImprimir(panel_mapa));
		imprimirMapaMenuItem.setText(Messages.getString("Ventana.56")); //$NON-NLS-1$
		imprimirMapaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
		imprimirMapaMenuItem.setMnemonic('P');
		archivoMenu.add(imprimirMapaMenuItem);

		archivoMenu.addSeparator();

		JMenuItem salirMenuItem = new JMenuItem();
		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		salirMenuItem.setText(Messages.getString("Ventana.57")); //$NON-NLS-1$
		salirMenuItem.setMnemonic('S');
		salirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		archivoMenu.add(salirMenuItem);

	}

	/**
	 * Crea el menú Edicion.
	 */
	public void crearMenuEdicion() {
		JMenu edicionMenu = new JMenu();
		edicionMenu.setText(Messages.getString("Ventana.58")); //$NON-NLS-1$
		edicionMenu.setMnemonic('E');
		menuBar.add(edicionMenu);

		JMenuItem deshacerMenuItem = new JMenuItem();
		deshacerMenuItem.addActionListener(new AccionDeshacer(controlador,
				panel_mapa));
		deshacerMenuItem.setText(Messages.getString("Ventana.59")); //$NON-NLS-1$
		deshacerMenuItem.setMnemonic('D');
		deshacerMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
		edicionMenu.add(deshacerMenuItem);

		edicionMenu.addSeparator();

		JMenuItem cortarMenuItem = new JMenuItem();
		cortarMenuItem.addActionListener(new AccionCortar(modelo, controlador,
				panel_mapa));
		// copiarMenuItem.addActionListener(new AccionCopiar());
		cortarMenuItem.setText(Messages.getString("Ventana.60")); //$NON-NLS-1$
		cortarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		edicionMenu.add(cortarMenuItem);

		JMenuItem copiarMenuItem = new JMenuItem();
		copiarMenuItem.addActionListener(new AccionCopiar(modelo, controlador,
				panel_mapa));
		// copiarMenuItem.addActionListener(new AccionCopiar());
		copiarMenuItem.setText(Messages.getString("Ventana.61")); //$NON-NLS-1$
		copiarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
		edicionMenu.add(copiarMenuItem);

		JMenuItem pegarMenuItem = new JMenuItem();
		pegarMenuItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonPegar().doClick();
					}
				}
		);
		pegarMenuItem.setText(Messages.getString("Ventana.62")); //$NON-NLS-1$
		pegarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		edicionMenu.add(pegarMenuItem);

		edicionMenu.addSeparator();

		JMenuItem posicionar = new JMenuItem();
		posicionar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evento){
						try{
						double lat = Double.parseDouble(JOptionPane.showInputDialog("Introduzca latitud (número entero)"));
						double lon = Double.parseDouble(JOptionPane.showInputDialog("Introduzca longitud (número entero)"));
						panel_mapa.centrarEnPosicion(lat, lon);
						JOptionPane.showMessageDialog(null,"Posición central:\n"+
								"Lat: "+Representacion.pasarAGrados(lat)+" ; " +
								"Lon: " + Representacion.pasarAGrados(lon));
						}
						catch( java.lang.NumberFormatException excepcion){
							JOptionPane.showMessageDialog(null,"Valor incorrecto");
						}
					}
				}
		);
		posicionar.setText("Posicionar"); //$NON-NLS-1$
	//	posicionar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		edicionMenu.add(posicionar);

		JMenuItem imagenMenuItem = new JMenuItem();
		imagenMenuItem.addActionListener(new AccionCargarImagen(controlador,
				panel_mapa));
		imagenMenuItem.setText(Messages.getString("Ventana.63")); //$NON-NLS-1$
		imagenMenuItem.setMnemonic('i');
		edicionMenu.add(imagenMenuItem);

		edicionMenu.addSeparator();

		JMenuItem selecMenuItem = new JMenuItem();
		selecMenuItem.addActionListener(new AccionSobreMapa(
				new MLSeleccionarYMover(modelo, controlador, panel_mapa), this,
				escuchaTeclado, -1));
		selecMenuItem
				.addActionListener(new AccionBarra(this, barraSeleccionar));
		selecMenuItem.setText(Messages.getString("Ventana.64")); //$NON-NLS-1$
		edicionMenu.add(selecMenuItem);

		JMenuItem moverMenuItem = new JMenuItem();
		moverMenuItem.addActionListener(new AccionSobreMapa(new MLMover(modelo,
				controlador, panel_mapa), this, escuchaTeclado, -1));
		moverMenuItem.setText(Messages.getString("Ventana.65")); //$NON-NLS-1$
		edicionMenu.add(moverMenuItem);

		JMenu subMenuVer = new JMenu(Messages.getString("Ventana.66")); //$NON-NLS-1$
		edicionMenu.add(subMenuVer);
		
		JMenuItem verNodosMenuItem = new JMenuItem();
		verNodosMenuItem.addActionListener(new AccionVerNodos(controlador,
				modelo, this,panel_mapa,Messages.getString("Ventana.67"), Messages.getString("Ventana.68"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("Ventana.69"))); //$NON-NLS-1$
		verNodosMenuItem.setText(Messages.getString("Ventana.70")); //$NON-NLS-1$
		subMenuVer.add(verNodosMenuItem);
		
		JMenuItem verTramosMenuItem = new JMenuItem();
		verTramosMenuItem.addActionListener(new AccionVerTramos(controlador,
				modelo, this,panel_mapa,Messages.getString("Ventana.71"), Messages.getString("Ventana.72"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("Ventana.73"))); //$NON-NLS-1$
		verTramosMenuItem.setText(Messages.getString("Ventana.74")); //$NON-NLS-1$
		subMenuVer.add(verTramosMenuItem);
		
		JMenuItem verViasMenuItem = new JMenuItem();
		verViasMenuItem.addActionListener(new AccionVerVias(controlador,
				modelo, this,panel_mapa,Messages.getString("Ventana.75"), Messages.getString("Ventana.76"), //$NON-NLS-1$ //$NON-NLS-2$
				Messages.getString("Ventana.77"))); //$NON-NLS-1$
		verViasMenuItem.setText(Messages.getString("Ventana.78")); //$NON-NLS-1$
		subMenuVer.add(verViasMenuItem);
		
		JMenuItem verlineasBusMenuItem = new JMenuItem();
		verlineasBusMenuItem.addActionListener(new AccionVerLineasBus(controlador,
				modelo, this,panel_mapa));
		verlineasBusMenuItem.setText(Messages.getString("Ventana.79")); //$NON-NLS-1$
		subMenuVer.add(verlineasBusMenuItem);
	}

	/**
	 * Método que crea el menú con el nombre de "Visualiazación".
	 * <p>
	 * En este menú se encuentran distinas herramientas relaciónadas con la
	 * visualización del mapa por el usuario. Consta de las siguientes opciones: *
	 * zoom in: para acercar la representacion * zoom out: para alejarla *
	 * cambiar representacion: permite alternar entre las distintas
	 * representaciones disponibles
	 * 
	 */
	public void crearMenuVis() {
		JMenu menuVis = new JMenu();
		menuVis.setText(Messages.getString("Ventana.80")); //$NON-NLS-1$
		menuBar.add(menuVis);
		menuVis.setMnemonic('V');

		JMenuItem zoomIn = new JMenuItem();
		zoomIn.addActionListener(new AccionZoom(panel_mapa, 0.5));
		zoomIn.setText(Messages.getString("Ventana.81")); //$NON-NLS-1$
		zoomIn.setMnemonic('i');
		zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS,InputEvent.CTRL_MASK));
		menuVis.add(zoomIn);

		JMenuItem zoomOut = new JMenuItem();
		zoomOut.addActionListener(new AccionZoom(panel_mapa, 2));
		zoomOut.setText(Messages.getString("Ventana.82")); //$NON-NLS-1$
		zoomOut.setMnemonic('o');
		zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,InputEvent.CTRL_MASK));
		menuVis.add(zoomOut);

		menuVis.addSeparator();

		JMenuItem cambiarRep = new JMenuItem();
		cambiarRep.addActionListener(new AccionCambiarRep(panel_mapa,
				new RepresentacionAvanzada(), new RepresentacionSimple()));
		cambiarRep.setText(Messages.getString("Ventana.83")); //$NON-NLS-1$
		cambiarRep.setMnemonic('C');
		menuVis.add(cambiarRep);
	}

	/**
	 * 
	 * 
	 */
	public void crearMenuMapa() {
		JMenu mapaMenu = new JMenu();
		mapaMenu.setText(Messages.getString("Ventana.84")); //$NON-NLS-1$
		mapaMenu.setMnemonic('M');
		menuBar.add(mapaMenu);

		JMenuItem añadirNodo = new JMenuItem();
		añadirNodo
				.addActionListener(new AccionSobreMapa(new MLAñadirNodo(modelo,
						controlador, panel_mapa, this), this, escuchaTeclado, 0));
		añadirNodo.setText(Messages.getString("Ventana.85")); //$NON-NLS-1$
		añadirNodo.setMnemonic('n');
		mapaMenu.add(añadirNodo);

		JMenuItem añadirTramo = new JMenuItem();
		añadirTramo
				.addActionListener(new AccionSobreMapa(new MLAñadirTramo(
						modelo, controlador, panel_mapa, this), this,
						escuchaTeclado, 1));
		añadirTramo.setText(Messages.getString("Ventana.86")); //$NON-NLS-1$
		añadirTramo.setMnemonic('t');		
		mapaMenu.add(añadirTramo);

		mapaMenu.addSeparator();

		JMenuItem eliminarNodo = new JMenuItem();
		eliminarNodo.addActionListener(new AccionSobreMapa(new MLEliminarNodo(
				modelo, controlador, panel_mapa), this, escuchaTeclado, 2));
		eliminarNodo.setText(Messages.getString("Ventana.87")); //$NON-NLS-1$
		mapaMenu.add(eliminarNodo);

		JMenuItem elminarTramo = new JMenuItem();
		elminarTramo.addActionListener(new AccionSobreMapa(new MLEliminarTramo(
				modelo, controlador, panel_mapa), this, escuchaTeclado, 3));
		elminarTramo.setText(Messages.getString("Ventana.88")); //$NON-NLS-1$
		mapaMenu.add(elminarTramo);

		mapaMenu.addSeparator();
		
		JMenuItem buscarElemento = new JMenuItem();
		buscarElemento.addActionListener(new AccionBuscar(controlador,panel_mapa));
		buscarElemento.addActionListener(new AccionBarra(this, null));
		buscarElemento.setText(Messages.getString("Ventana.89")); //$NON-NLS-1$
		buscarElemento.setMnemonic('B');
		mapaMenu.add(buscarElemento);
		
		JMenuItem mejorCamino = new JMenuItem();
		mejorCamino.addActionListener(new AccionSobreMapa(new MLEscuchaItinerario(
				modelo, controlador, panel_mapa), this,
				escuchaTeclado, 8));
		mejorCamino.addActionListener(new AccionBarra(this, null));
		mejorCamino.setText(Messages.getString("Ventana.90")); //$NON-NLS-1$
		mejorCamino.setMnemonic('c');
		mapaMenu.add(mejorCamino);
		
	}

	/**
	 * 
	 * 
	 */
	public void crearMenuSimulacion() {
		JMenu simMenu = new JMenu();
		simMenu.setText(Messages.getString("Ventana.91")); //$NON-NLS-1$
		simMenu.setMnemonic('S');
		menuBar.add(simMenu);

		JMenuItem añadirBus = new JMenuItem();
		añadirBus.addActionListener(new AccionSobreMapa(
				new MLAñadirLineaAutobus(modelo, controlador, panel_mapa),
				this, escuchaTeclado, 4));
		añadirBus.setText(Messages.getString("Ventana.92")); //$NON-NLS-1$
		añadirBus.setMnemonic('l');
		simMenu.add(añadirBus);

		JMenuItem añadirVia = new JMenuItem();
		añadirVia.addActionListener(new AccionSobreMapa(new MLAñadirVia(modelo,
				controlador, panel_mapa), this, escuchaTeclado, 6));
		añadirVia.setText(Messages.getString("Ventana.93")); //$NON-NLS-1$
		añadirVia.setMnemonic('v');
		simMenu.add(añadirVia);

		simMenu.addSeparator();

		JMenuItem comenarSim = new JMenuItem();
		comenarSim.addActionListener(new AccionComenzarSimulacion(this,controlador, modelo.getSimulacion().getParam()));
		comenarSim.setText(Messages.getString("Ventana.94")); //$NON-NLS-1$
		comenarSim.setMnemonic('C');
		comenarSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,InputEvent.CTRL_MASK));
		simMenu.add(comenarSim);

		JMenuItem pausarSim = new JMenuItem();
		pausarSim.addActionListener(new AccionPausarSimulacion(controlador));
		pausarSim.setText(Messages.getString("Ventana.95")); //$NON-NLS-1$
		pausarSim.setMnemonic('P');
		pausarSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6,InputEvent.CTRL_MASK));
		simMenu.add(pausarSim);

		JMenuItem terminarSim = new JMenuItem();
		terminarSim.addActionListener(new AccionDetenerSimulacion(controlador));
		terminarSim.setText(Messages.getString("Ventana.96")); //$NON-NLS-1$
		terminarSim.setMnemonic('D');
		terminarSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F7,InputEvent.CTRL_MASK));
		simMenu.add(terminarSim);

	}

	/**
	 * 
	 * 
	 */
	public void crearMenuAyuda() {
		JMenu ayudaMenu = new JMenu();
		ayudaMenu.setText(Messages.getString("Ventana.97")); //$NON-NLS-1$
		ayudaMenu.setMnemonic('y');
		menuBar.add(ayudaMenu);

		JMenuItem abrirAyuda = new JMenuItem();
		abrirAyuda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//new Ayuda("http://simtraffic.helker.com/Manual2/manual.html");
				String url= Messages.getString("Ventana.98"); //$NON-NLS-1$
				/**
				 * Válido en principio para cualquier navegador. Solo en windows?
				 * No funciona en el puesto Pto 1108 
				 */
				/*try {
					Runtime.getRuntime().exec(Messages.getString("Ventana.99") + url); //$NON-NLS-1$
				try {
					Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
				} catch (IOException e1) {
					System.out.println(Messages.getString("Ventana.100")); //$NON-NLS-1$
				}
				*/
				/**
				 * Solo válido para internet Explorer 
				 */
				try {
					Runtime.getRuntime().exec("cmd /c start iexplore "+url);
				} catch (IOException e1) {
					System.out.println(Messages.getString("Ventana.0"));  //$NON-NLS-1$
				} 
			}
		});
		abrirAyuda.setText(Messages.getString("Ventana.101")); //$NON-NLS-1$
		ayudaMenu.add(abrirAyuda);

		JMenuItem log = new JMenuItem();
		log.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				popUpLog = new Log(controlador.getHistorial());
			}
		});
		log.setText(Messages.getString("Ventana.102")); //$NON-NLS-1$
		log.setMnemonic('h');
		ayudaMenu.add(log);

		ayudaMenu.addSeparator();

		JMenuItem elegirIdioma = new JMenuItem();
		elegirIdioma.addActionListener(new AccionIdioma());
		elegirIdioma.setText(Messages.getString("Ventana.107")); //$NON-NLS-1$
		ayudaMenu.add(elegirIdioma);

		
		ayudaMenu.addSeparator();

		
		JMenuItem acercaDE = new JMenuItem();
		acercaDE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AcercaDe();
			}
		});
		acercaDE.setText(Messages.getString("Ventana.103")); //$NON-NLS-1$
		ayudaMenu.add(acercaDE);
	}

	/**
	 * Crea el panel de herramientas de la parte izquierda de la interfaz
	 * gráfica.
	 */
	public void crearHerramientas() {
		panel_herramientas = new JPanel();
		panel_herramientas.setLayout(new BorderLayout());
		getContentPane().add(panel_herramientas, BorderLayout.WEST);

		panel = new BarraHerramientas(controlador, modelo, this);
		panel_herramientas.add(panel, BorderLayout.NORTH);

	}

	/**
	 * Crea los botones de la parte superior.
	 */
	public void crearBotonesSuperiores() {
		superior_arriba = new BarraSuperior(controlador, modelo, this);
		superior_abajo = new JPanel();
		superior = new JPanel(new GridLayout(0, 1));
		getContentPane().add(superior, BorderLayout.NORTH);

		superior.add(superior_arriba);

		crearBarraPropiedades();

		superior_abajo.setLayout(new BoxLayout(superior_abajo,
				BoxLayout.LINE_AXIS));

	}

	/**
	 * 
	 * 
	 */
	private void crearBarraPropiedades() {
		barraCrearNodo = new BarraCrearNodo();
		barraCrearTramo = new BarraCrearTramo();
		barraRedimensionarImagen = new BarraRedimensionarImagen();
		// crearBarraEliminar();
		//barraSeleccionar = new BarraSeleccionar(this, escuchaSeleccionTramos,
		//	escuchaSeleccionNodosYTramos, escuchaSeleccion, escuchaTeclado);
	}

	/**
	 * Crea la barra de estado
	 * 
	 */
	public void crearBarraEstado() {
		JPanel barraEstado = new JPanel(new BorderLayout());
		JPanel barraPosicion = new JPanel();
		barraPosicion.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		JPanel barraAyudaDinamica = new JPanel();
		barraAyudaDinamica.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		//barraAyudaDinamica.setLayout(new BorderLayout());

		JLabel etiquetaPosicion = new JLabel(Messages.getString("Ventana.104")); //$NON-NLS-1$
		JLabel puntitos = new JLabel(Messages.getString("Ventana.105")); //$NON-NLS-1$
		posicionX = new JLabel();
		posicionY = new JLabel();
		ayudaDinamica = new JTextField(Messages.getString("Ventana.106"),85); //$NON-NLS-1$
		ayudaDinamica.setEditable(false);

		barraAyudaDinamica.add(ayudaDinamica,BorderLayout.CENTER);
		
		barraPosicion.add(etiquetaPosicion);
		barraPosicion.add(posicionX);
		barraPosicion.add(puntitos);
		barraPosicion.add(posicionY);

		getContentPane().add(barraEstado, BorderLayout.SOUTH);
		barraEstado.add(barraAyudaDinamica, BorderLayout.CENTER);
		barraEstado.add(barraPosicion, BorderLayout.EAST);
	}

	/**
	 * Añade el panel del mapa, en la parte central.
	 */
	public void añadirPanelMapa() {

		// scrollPane = new JScrollPane();
		// getContentPane().add(scrollPane);
		// panel_mapa = new PanelMapa(200, 200);
		getContentPane().add(panel_mapa);
		// scrollPane.setViewportView(panel_mapa);
		panel_mapa.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel_mapa.setBackground(Color.WHITE);
		panel_mapa.setModelo(modelo);

	}

	/**
	 * Método que reemplaza el escucha actual del panel_mapa con uno nuevo
	 * <p>
	 * 
	 * @param escucha
	 *            Implementación de EscuchaRaton herramientas sobre el mapa.
	 */
	public void cambiarEscucha(EscuchaRaton escucha) {
		if (this.escucha != null)
		{
			if (this.escucha instanceof MLAñadirVia)
				((MLAñadirVia)this.escucha).reiniciarEscucha();
			this.escucha.desactivar();
		}
		panel_mapa.sugerir(null);
		panel_mapa.setAyudaInf(escucha.getAyuda());
		this.escucha = escucha;
	}

	public void update(Graphics g)
	{
		paint(g);
	}
	
	public void paint(Graphics g) {
		// panel_mapa.dibujar();
		paintComponents(g);
	}

	public void mostrar(JToolBar bar) {
		if (!panel_añadido) {
			superior.add(superior_abajo);
			panel_añadido = true;
		}
		superior_abajo.removeAll();
		superior_abajo.add(bar);
		repaint();
	}

	public void ocultarBarraSuperior() {
		superior_abajo.removeAll();
		superior.remove(superior_abajo);
		panel_añadido = false;
		repaint();
	}

	public JToolBar getBarraSeleccionar() {
		return barraSeleccionar;
	}

	public void setBarraSeleccionar(BarraSeleccionar barraSeleccionar) {
		this.barraSeleccionar = barraSeleccionar;
	}

	public Nodo prepararNodo(Posicion p) {
		return barraCrearNodo.prepararNodo(p);
	}

	public Tramo prepararTramo(Nodo i, Nodo f) {
		return barraCrearTramo.prepararTramo(i, f);
	}
	
	public void prepararImagen(Image im,Posicion pos){
		barraRedimensionarImagen.prepararImagen(im,pos,panel_mapa);
	}
	

	public JToolBar getBarraCrearNodo() {
		return barraCrearNodo;
	}

	public JToolBar getBarraCrearTramo() {
		return barraCrearTramo;
	}

	public PanelMapa getPanel_mapa() {
		return panel_mapa;
	}

	public EscuchaTeclado getEscuchaTeclado() {
		return escuchaTeclado;
	}

	public void cambiarAyuda(String string) {
		ayudaDinamica.setText(string);
		// ayudaDinamica.setVisible(true);
		ayudaDinamica.repaint();
	}
	
	public JMenuItem getBotonPegar(){
		return this.pegarSeleccion;
	}
	public JPopupMenu getEmergenteMapa(){
		return this.emergenteMapa;
	}
	
	public JPopupMenu getEmergenteTerminar(){
		return this.emergenteTerminar;
	}


	public JMenuItem getCopiarSeleccion() {
		return copiarSeleccion;
	}


	public JMenuItem getCortarSeleccion() {
		return cortarSeleccion;
	}
	
	public JPanel getBarraArriba() {
		return superior_arriba;
	}


	public BarraRedimensionarImagen getBarraRedimensionarImagen() {
		return barraRedimensionarImagen;
	}


	public void setBarraRedimensionarImagen(
			BarraRedimensionarImagen barraRedimensionarImagen) {
		this.barraRedimensionarImagen = barraRedimensionarImagen;
		//add(this.barraRedimensionarImagen);
	}
	
	public MLSeleccionarYMover getEscuchaSeleccionar() {
		return escuchaSeleccionar;
	}
	public void setEscuchaSeleccionar(MLSeleccionarYMover m) {
		escuchaSeleccionar=m;
	}
}