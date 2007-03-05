package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Acciones.*;
import is.SimTraffic.Vista.EsuchasRaton.EscuchaRaton;
import is.SimTraffic.Vista.EsuchasRaton.MLAñadirNodo;
import is.SimTraffic.Vista.EsuchasRaton.MLAñadirTramo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

// No tiene un solo comentario!!!
// no pude haber métdos tan largos!!!
/**
 * @author Grupo ISTrafico
 * 
 */
public class Ventana extends JFrame {

	private static final long serialVersionUID = -3549855005952631901L;

	private JPanel panel;

	private JScrollPane scrollPane;

	private JPanel panel_herramientas;

	private JMenuBar menuBar;

	private IModelo modelo;

	private IControlador controlador;

	EscuchaRaton escucha;

	/**
	 * Panel para editar los mapas. Puede ser que haya que cambiarlo por una
	 * JLabel.
	 */
	private PanelMapa panel_mapa;

	/**
	 * 
	 */
	private JPanel superior;

	/**
	 * @param modelo
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
	 * 
	 */
	public void crearBarraMenu() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		crearMenuArchivo();

		crearMenuEdicion();
	}

	/**
	 * 
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
		guardarmapaMenuItem.addActionListener(new AccionGuardar());
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
	 * 
	 */
	public void crearMenuEdicion() {
		JMenu edicionMenu = new JMenu();
		edicionMenu.setText("Edición");
		menuBar.add(edicionMenu);

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
	 * 
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
				new MLAñadirNodo(modelo, controlador, panel_mapa),
				this));
		panel.add(añadirNodoButton);
		añadirNodoButton.setText("Añadir Nodo(s)");

		JButton añadirTramoButton = new JButton();
		añadirTramoButton.addActionListener(new AccionSobreMapa(
				new MLAñadirTramo(modelo, controlador, panel_mapa),this));
		panel.add(añadirTramoButton);
		añadirTramoButton.setText("Añadir Tramo(s)");

		JButton eliminarNodoButton = new JButton();
		eliminarNodoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
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
	 * 
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

		JButton cargarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\document-open.png"));
		cargarTB.setMargin(new Insets(1, 1, 1, 1));
		cargarTB.addActionListener(new AccionAbrir());

		JButton guardarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\document-save.png"));
		guardarTB.setMargin(new Insets(1, 1, 1, 1));
		guardarTB.addActionListener(new AccionGuardar());

		JButton copiarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\edit-copy.png"));
		copiarTB.setMargin(new Insets(1, 1, 1, 1));
		copiarTB.addActionListener(new AccionCopiar());

		JButton pegarTB = new JButton(new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\edit-paste.png"));
		pegarTB.setMargin(new Insets(1, 1, 1, 1));
		pegarTB.addActionListener(new AccionCortar());

		archivoTB.add(nuevoTB);
		archivoTB.add(cargarTB);
		archivoTB.add(guardarTB);
		edicionTB.add(copiarTB);
		edicionTB.add(pegarTB);

	}

	/**
	 * 
	 */
	public void añadirPanelMapa() {

		//scrollPane = new JScrollPane();
		//getContentPane().add(scrollPane);
		//panel_mapa = new PanelMapa(200, 200);
		getContentPane().add(panel_mapa);
		//scrollPane.setViewportView(panel_mapa);
		panel_mapa.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel_mapa.setBackground(Color.WHITE);
		panel_mapa.setModelo(modelo);

	}

	/**
	 * Método que reemplasa el escucha actual del panel_mapa con uno nuevo<p>
	 * 
	 * @param escucha
	 * Implementación de EscuchaRaton herramientas sobre el mapa.
	 */
	public void cambiarEscucha(EscuchaRaton escucha) {
		if (this.escucha != null)
			this.escucha.desactivar();
		this.escucha = escucha;
	}

	public void paint(Graphics g) {
		// panel_mapa.dibujar();
		paintComponents(g);
	}

}
