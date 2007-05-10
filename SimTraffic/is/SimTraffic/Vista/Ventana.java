package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Acciones.*;
import is.SimTraffic.Vista.BarrasHerramientas.*;
import is.SimTraffic.Vista.EscuchasRaton.*;
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

import javax.swing.*;
import javax.swing.border.BevelBorder;

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
	
	@SuppressWarnings("unused")
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
		setTitle("SimTraffic v 2.0");
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
		JPopupMenu emergenteNodo = new JPopupMenu("Menu Emergente Nodo");
		JMenuItem eliminarNodo = new JMenuItem("Eliminar Nodo");
		eliminarNodo.addActionListener(new AccionEliminarNodo(modelo,
				controlador, panel_mapa));		
		JMenuItem propiedades = new JMenuItem("Propiedades del nodo");
		propiedades.addActionListener(new AccionPropiedadesNodo(modelo,
				controlador, panel_mapa));		
		
		emergenteNodo.add(eliminarNodo);	
		emergenteNodo.add(propiedades);

		panel_mapa.setMenuEmergenteNodo(emergenteNodo);
	}
	public void añadirMenuEmergenteTramo(){
		JPopupMenu emergenteTramo = new JPopupMenu("Menu Emergente Tramo");
		JMenuItem eliminarTramo = new JMenuItem("Eliminar Tramo");
		eliminarTramo.addActionListener(new AccionEliminarTramo(modelo,
				controlador, panel_mapa));
		JMenuItem propiedades = new JMenuItem("Propiedades del tramo");		
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
		emergenteMapa = new JPopupMenu("Menu Emergente Mapa");	
		
		JMenuItem cambiarRepresentacion = new JMenuItem("Cambiar Representación");
		cambiarRepresentacion.addActionListener(new AccionCambiarRep(panel_mapa,
				new RepresentacionAvanzada(), new RepresentacionSimple()));
		cambiarRepresentacion.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para cambiar la representación del mapa.", this));
		
		JMenuItem zoomIn = new JMenuItem("Zoom in");
		zoomIn.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonZoomIn().doClick();
					}
				}
		);
		zoomIn.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para acercar el zoom.", this));
		
		JMenuItem zoomOut = new JMenuItem("Zoom out");
		zoomOut.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonZoomOut().doClick();
					}
				}
		);
		zoomOut.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para alejar el zoom.",this));
		
		JMenuItem comenzarSimulación = new JMenuItem("Comenzar simulación");
		comenzarSimulación.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonSimular().doClick();
					}
				}
		);
		comenzarSimulación.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para comenzar una nueva simulación sobre el mapa actual.",this));
		
		JMenuItem detenerSimulación = new JMenuItem("Detener simulación");
		detenerSimulación.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonDetener().doClick();
					}
				}
		);
		detenerSimulación.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para parar la simulación actual.", this));
		
		copiarSeleccion = new JMenuItem("Copiar");
		copiarSeleccion.addActionListener(new AccionCopiar(
				modelo, controlador, panel_mapa));
		copiarSeleccion.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para copiar los elementos seleccionados.",this));
		
		cortarSeleccion = new JMenuItem("Cortar");
		cortarSeleccion.addActionListener(new AccionCortar(
				modelo, controlador, panel_mapa));
		cortarSeleccion.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para cortar los elementos seleccionados.",this));
		
		pegarSeleccion = new JMenuItem("Pegar");
		pegarSeleccion.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraSuperior)superior_arriba).getBotonPegar().doClick();
					}
				}
		);
		pegarSeleccion.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para pegar los elementos cortados o copiados.",this));
		
		JMenuItem eliminarSeleccion = new JMenuItem("Eliminar Seleccion");
		eliminarSeleccion.addActionListener(new AccionEliminarSeleccion(
				modelo, controlador, panel_mapa));
		eliminarSeleccion.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para eliminar los nodos y tramos seleccionados en el mapa.", this));
		
		JMenuItem desplazarMapa = new JMenuItem("Desplazar el mapa");
		desplazarMapa.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonDesplazar().doClick();
					}
				}
		);
		desplazarMapa.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para desplazar el mapa.", this));
		
		
		JMenu submenuSeleccion = new JMenu("Modo Seleccionar");
		
		JMenuItem seleccion = new JMenuItem("Seleccionar nodos y tramos");
		seleccion.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonSeleccionar().doClick();
					}
				}
		);
		seleccion.addMouseMotionListener(new EscuchaAyuda("Seleccione nodos y tramos y arrástrelos para moverlos por el mapa.", this));
		submenuSeleccion.add(seleccion);
		
		JMenuItem seleccionVias = new JMenuItem("Seleccionar Vías");
		seleccionVias.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonSeleccionarVias().doClick();
					}
				}
		);
		seleccionVias.addMouseMotionListener(new EscuchaAyuda("Seleccione vias en el mapa.", this));
		submenuSeleccion.add(seleccionVias);
		
		JMenu submenuInsertar = new JMenu("Modo Insertar");
		
		JMenuItem insertarNodo = new JMenuItem ("Insertar Nodo");
		insertarNodo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonAñadirNodo().doClick();
					}
				}
		);
		insertarNodo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para añadir un nuevo nodo.", this));
		submenuInsertar.add(insertarNodo);
		
		JMenuItem insertarTramo = new JMenuItem ("Insertar tramo");
		insertarTramo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonAñadirTramo().doClick();
					}
				}
		);
		insertarTramo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para añadir un nuevo tramo.", this));
		submenuInsertar.add(insertarTramo);
		
		JMenuItem insertarVia = new JMenuItem ("Insertar vía");
		insertarVia.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonAñadirVia().doClick();
					}
				}
		);
		insertarVia.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para añadir un nuevo tramo.", this));
		submenuInsertar.add(insertarVia);
		
		JMenuItem insertarLineaBus = new JMenuItem ("Insertar línea de bus");
		insertarLineaBus.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonAñadirBus().doClick();
					}
				}
		);
		insertarLineaBus.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para añadir una nueva línea de autobús.", this));
		submenuInsertar.add(insertarLineaBus);
		
		JMenu submenuEliminar = new JMenu("Modo Eliminar");
		
		JMenuItem eliminarNodo = new JMenuItem ("Eliminar nodo");
		eliminarNodo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonEliminarNodo().doClick();
					}
				}
		);
		eliminarNodo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para eliminar un nodo.", this));
		submenuEliminar.add(eliminarNodo);
		
		JMenuItem eliminarTramo = new JMenuItem ("Eliminar tramo");
		eliminarTramo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						((BarraHerramientas)panel).getBotonEliminarTramo().doClick();
					}
				}
		);
		eliminarTramo.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para eliminar un tramo.", this));
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
		
		emergenteTerminar = new JPopupMenu("Menu Emergente Finalizar");	
		
		JMenuItem terminar = new JMenuItem("Terminar");
		terminar.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						// TODO cancelar accion
						escucha.notificar(-5);
					}
					
				}
		);
		terminar.addMouseMotionListener(new EscuchaAyuda("Pulse aquí para terminar.", this));
		
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

		crearMenuAyuda();
	}

	/**
	 * Crea el menú Archivo.
	 */
	public void crearMenuArchivo() {
		JMenu archivoMenu = new JMenu();
		archivoMenu.setText("Archivo");
		archivoMenu.setMnemonic('A');
		menuBar.add(archivoMenu);

		
		JMenuItem nuevoMapaMenuItem = new JMenuItem();
		nuevoMapaMenuItem
				.addActionListener(new AccionNuevo(controlador, panel_mapa));
		nuevoMapaMenuItem.setText("Nuevo Mapa");
		nuevoMapaMenuItem.setMnemonic('N');
		nuevoMapaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
		archivoMenu.add(nuevoMapaMenuItem);

		archivoMenu.addSeparator();

		JMenuItem cargarMapaMenuItem = new JMenuItem();
		cargarMapaMenuItem.addActionListener(new AccionCargar(controlador,
				panel_mapa));
		cargarMapaMenuItem.setText("Cargar Mapa");
		cargarMapaMenuItem.setMnemonic('M');
		cargarMapaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,InputEvent.CTRL_MASK));
		archivoMenu.add(cargarMapaMenuItem);
		
		JMenuItem descargarMapaMenuItem = new JMenuItem();
		descargarMapaMenuItem.addActionListener(new AccionDescargar(controlador));
		descargarMapaMenuItem.setText("Descargar Mapa");
		descargarMapaMenuItem.setMnemonic('D');
		archivoMenu.add(descargarMapaMenuItem);

		JMenuItem guardarmapaMenuItem = new JMenuItem();
		guardarmapaMenuItem.addActionListener(new AccionGuardar(controlador));
		guardarmapaMenuItem.setText("GuardarMapa");
		guardarmapaMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK));
		guardarmapaMenuItem.setMnemonic('G');
		archivoMenu.add(guardarmapaMenuItem);

		archivoMenu.addSeparator();

		JMenuItem salirMenuItem = new JMenuItem();
		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		salirMenuItem.setText("Salir");
		salirMenuItem.setMnemonic('S');
		salirMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
		archivoMenu.add(salirMenuItem);

	}

	/**
	 * Crea el menú Edicion.
	 */
	public void crearMenuEdicion() {
		JMenu edicionMenu = new JMenu();
		edicionMenu.setText("Edición");
		edicionMenu.setMnemonic('E');
		menuBar.add(edicionMenu);

		JMenuItem deshacerMenuItem = new JMenuItem();
		deshacerMenuItem.addActionListener(new AccionDeshacer(controlador,
				panel_mapa));
		deshacerMenuItem.setText("Deshacer");
		deshacerMenuItem.setMnemonic('D');
		deshacerMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
		edicionMenu.add(deshacerMenuItem);

		edicionMenu.addSeparator();

		JMenuItem cortarMenuItem = new JMenuItem();
		cortarMenuItem.addActionListener(new AccionCortar(modelo, controlador,
				panel_mapa));
		// copiarMenuItem.addActionListener(new AccionCopiar());
		cortarMenuItem.setText("Cortar");
		cortarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
		edicionMenu.add(cortarMenuItem);

		JMenuItem copiarMenuItem = new JMenuItem();
		copiarMenuItem.addActionListener(new AccionCopiar(modelo, controlador,
				panel_mapa));
		// copiarMenuItem.addActionListener(new AccionCopiar());
		copiarMenuItem.setText("Copiar");
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
		pegarMenuItem.setText("Pegar");
		pegarMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
		edicionMenu.add(pegarMenuItem);

		edicionMenu.addSeparator();

		JMenuItem imagenMenuItem = new JMenuItem();
		imagenMenuItem.addActionListener(new AccionCargarImagen(controlador,
				panel_mapa));
		imagenMenuItem.setText("Cargar imagen");
		imagenMenuItem.setMnemonic('i');
		edicionMenu.add(imagenMenuItem);

		edicionMenu.addSeparator();

		JMenuItem selecMenuItem = new JMenuItem();
		selecMenuItem.addActionListener(new AccionSobreMapa(
				new MLSeleccionarYMover(modelo, controlador, panel_mapa), this,
				escuchaTeclado, -1));
		selecMenuItem
				.addActionListener(new AccionBarra(this, barraSeleccionar));
		selecMenuItem.setText("Seleccionar...");
		edicionMenu.add(selecMenuItem);

		JMenuItem moverMenuItem = new JMenuItem();
		moverMenuItem.addActionListener(new AccionSobreMapa(new MLMover(modelo,
				controlador, panel_mapa), this, escuchaTeclado, -1));
		moverMenuItem.setText("Mover...");
		edicionMenu.add(moverMenuItem);

		JMenuItem lineasBusMenuItem = new JMenuItem();
		lineasBusMenuItem.addActionListener(new AccionVerLineasBus(controlador,
				modelo, this,panel_mapa));
		lineasBusMenuItem.setText("Ver Lineas Autobuses");
		edicionMenu.add(lineasBusMenuItem);
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
		menuVis.setText("Visualización");
		menuBar.add(menuVis);
		menuVis.setMnemonic('V');

		JMenuItem zoomIn = new JMenuItem();
		zoomIn.addActionListener(new AccionZoom(panel_mapa, 0.5));
		zoomIn.setText("Zoom in");
		zoomIn.setMnemonic('i');
		zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS,InputEvent.CTRL_MASK));
		menuVis.add(zoomIn);

		JMenuItem zoomOut = new JMenuItem();
		zoomOut.addActionListener(new AccionZoom(panel_mapa, 2));
		zoomOut.setText("Zoom out");
		zoomOut.setMnemonic('o');
		zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,InputEvent.CTRL_MASK));
		menuVis.add(zoomOut);

		menuVis.addSeparator();

		JMenuItem cambiarRep = new JMenuItem();
		cambiarRep.addActionListener(new AccionCambiarRep(panel_mapa,
				new RepresentacionAvanzada(), new RepresentacionSimple()));
		cambiarRep.setText("Cambiar representación");
		cambiarRep.setMnemonic('C');
		menuVis.add(cambiarRep);
	}

	/**
	 * 
	 * 
	 */
	public void crearMenuMapa() {
		JMenu mapaMenu = new JMenu();
		mapaMenu.setText("Mapa");
		mapaMenu.setMnemonic('M');
		menuBar.add(mapaMenu);

		JMenuItem añadirNodo = new JMenuItem();
		añadirNodo
				.addActionListener(new AccionSobreMapa(new MLAñadirNodo(modelo,
						controlador, panel_mapa, this), this, escuchaTeclado, 0));
		añadirNodo.setText("Añadir nodo");
		añadirNodo.setMnemonic('n');
		mapaMenu.add(añadirNodo);

		JMenuItem añadirTramo = new JMenuItem();
		añadirTramo
				.addActionListener(new AccionSobreMapa(new MLAñadirTramo(
						modelo, controlador, panel_mapa, this), this,
						escuchaTeclado, 1));
		añadirTramo.setText("Añadir tramo");
		añadirTramo.setMnemonic('t');		
		mapaMenu.add(añadirTramo);

		mapaMenu.addSeparator();

		JMenuItem eliminarNodo = new JMenuItem();
		eliminarNodo.addActionListener(new AccionSobreMapa(new MLEliminarNodo(
				modelo, controlador, panel_mapa), this, escuchaTeclado, 2));
		eliminarNodo.setText("Eliminar nodo");
		mapaMenu.add(eliminarNodo);

		JMenuItem elminarTramo = new JMenuItem();
		elminarTramo.addActionListener(new AccionSobreMapa(new MLEliminarTramo(
				modelo, controlador, panel_mapa), this, escuchaTeclado, 3));
		elminarTramo.setText("Eliminar tramo");
		mapaMenu.add(elminarTramo);

		mapaMenu.addSeparator();
		
		JMenuItem buscarElemento = new JMenuItem();
		buscarElemento.addActionListener(new AccionBuscar(controlador,panel_mapa));
		buscarElemento.addActionListener(new AccionBarra(this, null));
		buscarElemento.setText("Buscar elemento");
		buscarElemento.setMnemonic('B');
		mapaMenu.add(buscarElemento);
		
		JMenuItem mejorCamino = new JMenuItem();
		mejorCamino.addActionListener(new AccionSobreMapa(new MLEscuchaItinerario(
				modelo, controlador, panel_mapa), this,
				escuchaTeclado, 8));
		mejorCamino.addActionListener(new AccionBarra(this, null));
		mejorCamino.setText("Encontrar mejor camino");
		mejorCamino.setMnemonic('c');
		mapaMenu.add(mejorCamino);
		
	}

	/**
	 * 
	 * 
	 */
	public void crearMenuSimulacion() {
		JMenu simMenu = new JMenu();
		simMenu.setText("Simulación");
		simMenu.setMnemonic('S');
		menuBar.add(simMenu);

		JMenuItem añadirBus = new JMenuItem();
		añadirBus.addActionListener(new AccionSobreMapa(
				new MLAñadirLineaAutobus(modelo, controlador, panel_mapa),
				this, escuchaTeclado, 4));
		añadirBus.setText("Añadir linea autobus");
		añadirBus.setMnemonic('l');
		simMenu.add(añadirBus);

		JMenuItem añadirVia = new JMenuItem();
		añadirVia.addActionListener(new AccionSobreMapa(new MLAñadirVia(modelo,
				controlador, panel_mapa), this, escuchaTeclado, 6));
		añadirVia.setText("Añadir vía");
		añadirVia.setMnemonic('v');
		simMenu.add(añadirVia);

		simMenu.addSeparator();

		JMenuItem comenarSim = new JMenuItem();
		comenarSim.addActionListener(new AccionComenzarSimulacion(controlador, modelo.getSimulacion().getParam()));
		comenarSim.setText("Comenzar");
		comenarSim.setMnemonic('C');
		comenarSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,InputEvent.CTRL_MASK));
		simMenu.add(comenarSim);

		JMenuItem pausarSim = new JMenuItem();
		pausarSim.addActionListener(new AccionPausarSimulacion(controlador));
		pausarSim.setText("Pausar");
		pausarSim.setMnemonic('P');
		pausarSim.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6,InputEvent.CTRL_MASK));
		simMenu.add(pausarSim);

		JMenuItem terminarSim = new JMenuItem();
		terminarSim.addActionListener(new AccionDetenerSimulacion(controlador));
		terminarSim.setText("Detener");
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
		ayudaMenu.setText("Ayuda");
		ayudaMenu.setMnemonic('y');
		menuBar.add(ayudaMenu);

		JMenuItem abrirAyuda = new JMenuItem();
		abrirAyuda.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				new Ayuda("http://simtraffic.helker.com/Manual2/manual.html");
			}
		});
		abrirAyuda.setText("Abrir ayuda");
		ayudaMenu.add(abrirAyuda);

		JMenuItem log = new JMenuItem();
		log.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				popUpLog = new Log(controlador.getHistorial());
			}
		});
		log.setText("Mostrar historial de eventos");
		log.setMnemonic('h');
		ayudaMenu.add(log);

		ayudaMenu.addSeparator();

		JMenuItem acercaDE = new JMenuItem();
		acercaDE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AcercaDe();
			}
		});
		acercaDE.setText("Acerca de SimTraffic");
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

		JLabel etiquetaPosicion = new JLabel("Posición: ");
		JLabel puntitos = new JLabel(" : ");
		posicionX = new JLabel();
		posicionY = new JLabel();
		ayudaDinamica = new JTextField("  Ayuda",70);
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
	}
}