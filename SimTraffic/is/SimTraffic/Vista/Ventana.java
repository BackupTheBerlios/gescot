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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
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
	 * Barra de estado situada en la parte inferior de la interfaz
	 */
	private JPanel barraEstado;

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
	MLSeleccionarNodos escuchaSeleccion;

	MLSeleccionarElementos escuchaSeleccionNodosYTramos;

	MLSeleccionarTramos escuchaSeleccionTramos;

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
		setTitle("SimTraffic™ v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // La ventana aparece
		// maximizada.
		this.escucha = null;
		panel_mapa = new PanelMapa(200, 200);

		panel_añadido = false;

		crearBarraEstado();

		EscuchaRaton escuchaSiempre = new MLEscuchaSiempre(modelo, controlador,
				panel_mapa, posicionX, posicionY);
		this.panel_mapa.addMouseListener(escuchaSiempre);
		this.panel_mapa.addMouseMotionListener(escuchaSiempre);

		escuchaSeleccion = new MLSeleccionarNodos(modelo, controlador,
				panel_mapa);
		escuchaSeleccionTramos = new MLSeleccionarTramos(modelo, controlador,
				panel_mapa);
		escuchaSeleccionNodosYTramos = new MLSeleccionarElementos(modelo,
				controlador, panel_mapa);

		escuchaTeclado = new EscuchaTeclado(panel_mapa, escuchaSeleccion);

		crearBotonesSuperiores();

		crearBarraMenu();

		crearHerramientas();

		añadirPanelMapa();

		panel.setFocusable(true);
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
		menuBar.add(archivoMenu);

		JMenuItem nuevoMapaMenuItem = new JMenuItem();
		nuevoMapaMenuItem.addActionListener(new AccionNuevo(modelo));
		nuevoMapaMenuItem.setText("Nuevo Mapa");
		archivoMenu.add(nuevoMapaMenuItem);

		archivoMenu.addSeparator();

		JMenuItem cargarMapaMenuItem = new JMenuItem();
		cargarMapaMenuItem.addActionListener(new AccionCargar(controlador,
				panel_mapa));
		cargarMapaMenuItem.setText("Cargar Mapa");
		archivoMenu.add(cargarMapaMenuItem);

		JMenuItem guardarmapaMenuItem = new JMenuItem();
		guardarmapaMenuItem.addActionListener(new AccionGuardar(controlador));
		guardarmapaMenuItem.setText("GuardarMapa");
		archivoMenu.add(guardarmapaMenuItem);

		archivoMenu.addSeparator();

		JMenuItem salirMenuItem = new JMenuItem();
		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		salirMenuItem.setText("Salir");
		archivoMenu.add(salirMenuItem);

	}

	/**
	 * Crea el menú Edicion.
	 */
	public void crearMenuEdicion() {
		JMenu edicionMenu = new JMenu();
		edicionMenu.setText("Edición");
		menuBar.add(edicionMenu);

		JMenuItem deshacerMenuItem = new JMenuItem();
		deshacerMenuItem.addActionListener(new AccionDeshacer(controlador,
				panel_mapa));
		deshacerMenuItem.setText("Deshacer");
		edicionMenu.add(deshacerMenuItem);

		edicionMenu.addSeparator();

		JMenuItem cortarMenuItem = new JMenuItem();
		cortarMenuItem.addActionListener(new AccionCortar(modelo, controlador,
				panel_mapa));
		// copiarMenuItem.addActionListener(new AccionCopiar());
		cortarMenuItem.setText("Cortar");
		edicionMenu.add(cortarMenuItem);

		JMenuItem copiarMenuItem = new JMenuItem();
		copiarMenuItem.addActionListener(new AccionCopiar(modelo, controlador,
				panel_mapa));
		// copiarMenuItem.addActionListener(new AccionCopiar());
		copiarMenuItem.setText("Copiar");
		edicionMenu.add(copiarMenuItem);

		JMenuItem pegarMenuItem = new JMenuItem();
		pegarMenuItem.addActionListener(new AccionPegar());
		pegarMenuItem.setText("Pegar");
		edicionMenu.add(pegarMenuItem);

		edicionMenu.addSeparator();

		JMenuItem imagenMenuItem = new JMenuItem();
		imagenMenuItem.addActionListener(new AccionCargarImagen(controlador,
				panel_mapa));
		imagenMenuItem.setText("Cargar imagen");
		edicionMenu.add(imagenMenuItem);

		edicionMenu.addSeparator();

		JMenuItem selecMenuItem = new JMenuItem();
		selecMenuItem.addActionListener(new AccionSobreMapa(
				new MLSeleccionarNodos(modelo, controlador, panel_mapa), this,
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
	}

	/**
	 * 
	 * 
	 */
	public void crearMenuVis() {
		JMenu menuVis = new JMenu();
		menuVis.setText("Visualización");
		menuBar.add(menuVis);

		JMenuItem zoomIn = new JMenuItem();
		zoomIn.addActionListener(new AccionZoom(panel_mapa, 0.5));
		zoomIn.setText("Zoom in");
		menuVis.add(zoomIn);

		JMenuItem zoomOut = new JMenuItem();
		zoomOut.addActionListener(new AccionZoom(panel_mapa, 2));
		zoomOut.setText("Zoom out");
		menuVis.add(zoomOut);

		menuVis.addSeparator();

		JMenuItem cambiarRep = new JMenuItem();
		// TODO falta que se pueda elegir entre representaciones
		cambiarRep.addActionListener(new AccionCambiarRep(panel_mapa,
				new RepresentacionAvanzada()));
		cambiarRep.setText("Cambiar representación");
		menuVis.add(cambiarRep);
	}

	/**
	 * 
	 * 
	 */
	public void crearMenuMapa() {
		JMenu mapaMenu = new JMenu();
		mapaMenu.setText("Mapa");
		menuBar.add(mapaMenu);

		JMenuItem añadirNodo = new JMenuItem();
		añadirNodo
				.addActionListener(new AccionSobreMapa(new MLAñadirNodo(modelo,
						controlador, panel_mapa, this), this, escuchaTeclado, 0));
		añadirNodo.setText("Añadir nodo");
		mapaMenu.add(añadirNodo);

		JMenuItem añadirTramo = new JMenuItem();
		añadirTramo
				.addActionListener(new AccionSobreMapa(new MLAñadirTramo(
						modelo, controlador, panel_mapa, this), this,
						escuchaTeclado, 1));
		añadirTramo.setText("Añadir tramo");
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

	}

	/**
	 * 
	 * 
	 */
	public void crearMenuSimulacion() {
		JMenu simMenu = new JMenu();
		simMenu.setText("Simulación");
		menuBar.add(simMenu);

		JMenuItem añadirBus = new JMenuItem();
		añadirBus.addActionListener(new AccionSobreMapa(
				new MLAñadirLineaAutobus(modelo, controlador, panel_mapa),
				this, escuchaTeclado, 4));
		añadirBus.setText("Añadir linea autobus");
		simMenu.add(añadirBus);

		JMenuItem añadirSem = new JMenuItem();
		añadirSem.addActionListener(new AccionSobreMapa(new MLAñadirSemaforo(
				modelo, controlador, panel_mapa), this, escuchaTeclado, 5));
		añadirSem.setText("Añadir semáforo");
		simMenu.add(añadirSem);

		JMenuItem añadirVia = new JMenuItem();
		añadirVia.addActionListener(new AccionSobreMapa(new MLAñadirVia(modelo,
				controlador, panel_mapa), this, escuchaTeclado, 6));
		añadirVia.setText("Añadir vía");
		simMenu.add(añadirVia);

		simMenu.addSeparator();

		JMenuItem comenarSim = new JMenuItem();
		comenarSim.addActionListener(new AccionComenzarSimulacion(controlador,
				modelo));
		comenarSim.setText("Comenzar");
		simMenu.add(comenarSim);

		JMenuItem pausarSim = new JMenuItem();
		// TODO falta el action listener
		pausarSim.addActionListener(null);
		pausarSim.setText("Pausar");
		simMenu.add(pausarSim);

		JMenuItem terminarSim = new JMenuItem();
		// TODO falta el action listener
		terminarSim.addActionListener(null);
		terminarSim.setText("Detener");
		simMenu.add(terminarSim);

	}

	/**
	 * 
	 * 
	 */
	public void crearMenuAyuda() {
		JMenu ayudaMenu = new JMenu();
		ayudaMenu.setText("Ayuda");
		menuBar.add(ayudaMenu);

		JMenuItem abrirAyuda = new JMenuItem();
		// TODO ventana de ayuda
		abrirAyuda.addActionListener(null);
		abrirAyuda.setText("Abrir ayuda");
		ayudaMenu.add(abrirAyuda);

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
		JPanel superior_arriba = new BarraSuperior(controlador, modelo, this);
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
		// crearBarraEliminar();
		barraSeleccionar = new BarraSeleccionar(this, escuchaSeleccionTramos,
				escuchaSeleccionNodosYTramos, escuchaSeleccion, escuchaTeclado);
	}

	/**
	 * Crea la barra de estado
	 * 
	 */
	public void crearBarraEstado() {

		barraEstado = new JPanel();
		barraEstado.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 5));

		JLabel etiquetaPosicion = new JLabel("Posición: ");
		JLabel puntitos = new JLabel(" : ");
		posicionX = new JLabel();
		posicionY = new JLabel();

		barraEstado.add(etiquetaPosicion);
		barraEstado.add(posicionX);
		barraEstado.add(puntitos);
		barraEstado.add(posicionY);

		getContentPane().add(barraEstado, BorderLayout.SOUTH);
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
			this.escucha.desactivar();
		panel_mapa.sugerir(null);
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
}
