package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Acciones.AccionAbrir;
import is.SimTraffic.Vista.Acciones.AccionCargar;
import is.SimTraffic.Vista.Acciones.AccionCopiar;
import is.SimTraffic.Vista.Acciones.AccionCortar;
import is.SimTraffic.Vista.Acciones.AccionDeshacer;
import is.SimTraffic.Vista.Acciones.AccionGuardar;
import is.SimTraffic.Vista.Acciones.AccionNuevo;
import is.SimTraffic.Vista.Acciones.AccionPegar;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLA�adirTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLEscuchaSiempre;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarNodos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

/**
 * Ventana que contiene la interfaz gr�fica de la aplicaci�n.
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
	 * Panel de la parte izquierda de la interfaz gr�fica.
	 */
	private JPanel panel_herramientas;
	
	/**
	 * Barra de men�s de la parte superior de la interfaz.
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
	 * Escucha de rat�n actual.
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
	 * Constructor de la ventana.
	 * 
	 * @param modelo
	 *            Modelo asociado a la interfaz gr�fica.
	 * @param controlador
	 *            Controlador asociado a la interfaz gr�fica.
	 */
	public Ventana(IModelo modelo, IControlador controlador) {
		this.modelo = modelo;
		this.controlador = controlador;
		setSize(800, 600);
		setTitle("SimTraffic� v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.escucha = null;
		panel_mapa = new PanelMapa(200, 200);
		
		crearBarraEstado();
		
		EscuchaRaton escuchaSiempre = new MLEscuchaSiempre(modelo,controlador,panel_mapa, posicionX, posicionY);
		this.panel_mapa.addMouseListener(escuchaSiempre);
		this.panel_mapa.addMouseMotionListener(escuchaSiempre);
		
		crearBarraMenu();
		
		crearHerramientas();
		
		a�adirPanelMapa();
		
		crearBotonesSuperiores();
		
	}
	
	/**
	 * Crea la barra de men�s.
	 */
	public void crearBarraMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		crearMenuArchivo();
		
		crearMenuEdicion();
	}
	
	/**
	 * Crea el men� Archivo.
	 */
	public void crearMenuArchivo() {
		JMenu archivoMenu = new JMenu();
		archivoMenu.setText("Archivo");
		menuBar.add(archivoMenu);
		
		JMenuItem nuevoMapaMenuItem = new JMenuItem();
		nuevoMapaMenuItem.addActionListener(new AccionNuevo());
		nuevoMapaMenuItem.setText("Nuevo Mapa");
		archivoMenu.add(nuevoMapaMenuItem);
		
		archivoMenu.addSeparator();
		
		JMenuItem cargarMapaMenuItem = new JMenuItem();
		cargarMapaMenuItem.addActionListener(new AccionCargar(controlador));
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
	 * Crea el men� Edicion.
	 */
	public void crearMenuEdicion() {
		JMenu edicionMenu = new JMenu();
		edicionMenu.setText("Edici�n");
		menuBar.add(edicionMenu);
		
		JMenuItem deshacerMenuItem = new JMenuItem();
		deshacerMenuItem.addActionListener(new AccionDeshacer(controlador, panel_mapa));
		deshacerMenuItem.setText("Deshacer");
		edicionMenu.add(deshacerMenuItem);
		
		edicionMenu.addSeparator();
		
		JMenuItem copiarMenuItem = new JMenuItem();
		copiarMenuItem.addActionListener(new AccionCopiar());
		copiarMenuItem.setText("Copiar");
		edicionMenu.add(copiarMenuItem);
		
		JMenuItem pegarMenuItem = new JMenuItem();
		pegarMenuItem.addActionListener(new AccionPegar());
		pegarMenuItem.setText("Pegar");
		edicionMenu.add(pegarMenuItem);
		
	}
	
	/**
	 * Crea el panel de herramientas de la parte izquierda de la interfaz
	 * gr�fica.
	 */
	public void crearHerramientas() {
		panel_herramientas = new JPanel();
		panel_herramientas.setLayout(new BorderLayout());
		getContentPane().add(panel_herramientas, BorderLayout.WEST);
		
		panel = new JToolBar(JToolBar.VERTICAL);
		panel_herramientas.add(panel, BorderLayout.NORTH);
		
		JButton seleccionarButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-1.gif"));
		seleccionarButton.setMargin(new Insets(1, 1, 1, 1));
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-2.gif"; 
		seleccionarButton.setToolTipText("<html>A�adir Nodo <img src="+imageName+"></html>");
		seleccionarButton.addActionListener(new AccionSobreMapa(
				new MLSeleccionarNodos(modelo, controlador, panel_mapa), this));
		panel.add(seleccionarButton);
		
		JButton a�adirNodoButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\a�adir_nodo.png"));
		a�adirNodoButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\a�adir_nodo2.png"; 
		a�adirNodoButton.setToolTipText("<html>A�adir Nodo <img src="+imageName+"></html>");
		a�adirNodoButton.addActionListener(new AccionSobreMapa(
				new MLA�adirNodo(modelo, controlador, panel_mapa), this));
		panel.add(a�adirNodoButton);
		
		JButton a�adirTramoButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\a�adir_tramo.png"));
		a�adirTramoButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\a�adir_tramo2.png"; 
		a�adirTramoButton.setToolTipText("<html>A�adir Tramo <img src="+imageName+"></html>");
		a�adirTramoButton.addActionListener(new AccionSobreMapa(
				new MLA�adirTramo(modelo, controlador, panel_mapa), this));
		panel.add(a�adirTramoButton);
		
		JButton eliminarNodoButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\eliminar_nodo.png"));
		eliminarNodoButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\eliminar_nodo2.png"; 
		eliminarNodoButton.setToolTipText("<html>Eliminar Nodo <img src="+imageName+"></html>");
		eliminarNodoButton.addActionListener(new AccionSobreMapa(
				new MLEliminarNodo(modelo, controlador, panel_mapa), this));
		panel.add(eliminarNodoButton);
		
		
		JButton eliminarTramoButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\eliminar_tramo.png"));
		eliminarTramoButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\eliminar_tramo2.png"; 
		eliminarTramoButton.setToolTipText("<html>Eliminar Tramo <img src="+imageName+"></html>");
		eliminarTramoButton.addActionListener(new AccionSobreMapa(
				new MLEliminarTramo(modelo, controlador, panel_mapa), this));
		panel.add(eliminarTramoButton);
		//Aqui se a�adir�n los nuevos botones.
		
		
	}
	
	/**
	 * Crea los botones de la parte superior.
	 */
	public void crearBotonesSuperiores() {
		superior = new JPanel();
		getContentPane().add(superior, BorderLayout.NORTH);
		
		JToolBar archivoTB = new JToolBar();
		JToolBar edicionTB = new JToolBar();
		superior.add(archivoTB);
		superior.add(edicionTB);
		
		superior.setLayout(new BoxLayout(superior, BoxLayout.LINE_AXIS));
		JButton nuevoTB = new JButton(new ImageIcon(
		"is\\SimTraffic\\Vista\\Imagenes\\document-new.png"));
		nuevoTB.setMargin(new Insets(1, 1, 1, 1));
		nuevoTB.addActionListener(new AccionNuevo());
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\document-new2.png"; 
		nuevoTB.setToolTipText("<html>Nuevo mapa <img src="+imageName+"></html>");
		
		JButton cargarTB = new JButton(new ImageIcon(
		"is\\SimTraffic\\Vista\\Imagenes\\document-open.png"));
		cargarTB.setMargin(new Insets(1, 1, 1, 1));
		cargarTB.addActionListener(new AccionCargar(controlador));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\document-open2.png"; 
		cargarTB.setToolTipText("<html>Cargar mapa <img src="+imageName+"></html>");
		
		JButton guardarTB = new JButton(new ImageIcon(
		"is\\SimTraffic\\Vista\\Imagenes\\document-save.png"));
		guardarTB.setMargin(new Insets(1, 1, 1, 1));
		guardarTB.addActionListener(new AccionGuardar(controlador));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\document-save2.png"; 
		guardarTB.setToolTipText("<html>Guardar mapa <img src="+imageName+"></html>");
		
		JButton copiarTB = new JButton(new ImageIcon(
		"is\\SimTraffic\\Vista\\Imagenes\\edit-copy.png"));
		copiarTB.setMargin(new Insets(1, 1, 1, 1));
		copiarTB.addActionListener(new AccionCopiar());
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\edit-copy2.png"; 
		copiarTB.setToolTipText("<html>Copiar <img src="+imageName+"></html>");
		
		JButton pegarTB = new JButton(new ImageIcon(
		"is\\SimTraffic\\Vista\\Imagenes\\edit-paste.png"));
		pegarTB.setMargin(new Insets(1, 1, 1, 1));
		pegarTB.addActionListener(new AccionCortar());
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\edit-paste2.png"; 
		pegarTB.setToolTipText("<html>Copiar <img src="+imageName+"></html>");
		
		JButton deshacerTB = new JButton(new ImageIcon(
		"is\\SimTraffic\\Vista\\Imagenes\\edit-undo.png"));
		deshacerTB.setMargin(new Insets(1, 1, 1, 1));
		deshacerTB.addActionListener(new AccionDeshacer(controlador,panel_mapa));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\edit-undo2.png"; 
		deshacerTB.setToolTipText("<html>Deshacer <img src="+imageName+"></html>");
		
		archivoTB.add(nuevoTB);
		archivoTB.add(cargarTB);
		archivoTB.add(guardarTB);
		edicionTB.add(copiarTB);
		edicionTB.add(pegarTB);
		edicionTB.add(deshacerTB);
	}
	
	/**
	 * Cra la barra de estado
	 *
	 */
	public void crearBarraEstado(){
		
		barraEstado = new JPanel();
		barraEstado.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		
		JLabel etiquetaPosicion = new JLabel("Posici�n: ");
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
	 * A�ade el panel del mapa, en la parte central.
	 */
	public void a�adirPanelMapa() {
		
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
	 * M�todo que reemplaza el escucha actual del panel_mapa con uno nuevo
	 * <p>
	 * 
	 * @param escucha
	 *            Implementaci�n de EscuchaRaton herramientas sobre el mapa.
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
	
}
