package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Acciones.*;
import is.SimTraffic.Vista.EscuchasRaton.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

/**
 * Ventana que contiene la interfaz gráfica de la aplicación.
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
	private JPanel panel;

	/**
	 * Panel central con scroll.
	 */
	private JScrollPane scrollPane; // De momento sin usar, ¿sobra? - Si, debría
									// inclurise de alguna forma en PanelMapa.
									// Podemos dejarlo aqui para recordarnoslo

	/**
	 * Panel de la parte izquierda de la interfaz gráfica.
	 */
	private JPanel panel_herramientas;

	/**
	 * Barra de menús de la parte superior de la interfaz.
	 */
	private JMenuBar menuBar;

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
		this.escucha = null;
		panel_mapa = new PanelMapa(200, 200);
		
		crearBarraMenu();

		crearHerramientas();

		añadirPanelMapa();

		crearBotonesSuperiores();

	}

	/**
	 * Crea la barra de menús.
	 */
	public void crearBarraMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		crearMenuArchivo();

		crearMenuEdicion();
	}

	/**
	 * Crea el menú Archivo.
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
		cargarMapaMenuItem.addActionListener(new AccionAbrir());
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
	 * gráfica.
	 */
	public void crearHerramientas() {
		panel_herramientas = new JPanel();
		panel_herramientas.setLayout(new BorderLayout());
		getContentPane().add(panel_herramientas, BorderLayout.WEST);

		panel = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 1);
		gridLayout.setVgap(2);
		panel.setLayout(gridLayout);
		panel_herramientas.add(panel, BorderLayout.NORTH);

		JLabel herramientasLabel = new JLabel();
		panel.add(herramientasLabel);
		herramientasLabel.setHorizontalAlignment(SwingConstants.CENTER);
		herramientasLabel.setText("Herramientas");

		JButton añadirNodoButton = new JButton();
		añadirNodoButton.addActionListener(new AccionSobreMapa(
				new MLAñadirNodo(modelo, controlador, panel_mapa), this));
		panel.add(añadirNodoButton);
		añadirNodoButton.setText("Añadir Nodo(s)");
		
		JButton añadirTramoButton = new JButton();
		añadirTramoButton.addActionListener(new AccionSobreMapa(
				new MLAñadirTramo(modelo, controlador, panel_mapa), this));
		panel.add(añadirTramoButton);
		añadirTramoButton.setText("Añadir Tramo(s)");

		JButton eliminarNodoButton = new JButton();
		eliminarNodoButton.addActionListener(new AccionSobreMapa(
				new MLEliminarNodo(modelo, controlador, panel_mapa), this));
		panel.add(eliminarNodoButton);
		eliminarNodoButton.setText("Eliminar Nodo(s)");

		JButton StartStopNodoButton = new JButton();
		StartStopNodoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel.add(StartStopNodoButton);
		StartStopNodoButton.setText("Comenzar simulación (Ejecutar)");

		JButton otrosButton = new JButton();
		panel.add(otrosButton);
		otrosButton.setText("Otros...");

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
		cargarTB.addActionListener(new AccionAbrir());
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

}
