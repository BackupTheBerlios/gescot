package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Vista.Acciones.AccionCargar;
import is.SimTraffic.Vista.Acciones.AccionCargarImagen;
import is.SimTraffic.Vista.Acciones.AccionCopiar;
import is.SimTraffic.Vista.Acciones.AccionCortar;
import is.SimTraffic.Vista.Acciones.AccionDeshacer;
import is.SimTraffic.Vista.Acciones.AccionGuardar;
import is.SimTraffic.Vista.Acciones.AccionNuevo;
import is.SimTraffic.Vista.Acciones.AccionPegar;
import is.SimTraffic.Vista.Acciones.AccionBarra;
import is.SimTraffic.Vista.Acciones.AccionSobreMapa;
import is.SimTraffic.Vista.Acciones.AccionZoom;
import is.SimTraffic.Vista.Acciones.PanelNodo.AccionAceptar;
import is.SimTraffic.Vista.Acciones.PanelNodo.AccionSeleccionarTipo;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaRaton;
import is.SimTraffic.Vista.EscuchasRaton.EscuchaTeclado;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirLineaAutobus;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirSemaforo;
import is.SimTraffic.Vista.EscuchasRaton.MLAñadirTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarNodo;
import is.SimTraffic.Vista.EscuchasRaton.MLEliminarTramo;
import is.SimTraffic.Vista.EscuchasRaton.MLEscuchaSiempre;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarElementos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarNodos;
import is.SimTraffic.Vista.EscuchasRaton.MLSeleccionarTramos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
	
	private JToolBar barraSeleccionar;
	
	private boolean panel_añadido;
	
	private JToolBar barraCrearNodo;

	private JComboBox comboTipo;

	private JComboBox comboValor;
	
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
		
		panel_añadido = false;
		
		crearBarraEstado();
		
		EscuchaRaton escuchaSiempre = new MLEscuchaSiempre(modelo,controlador,panel_mapa, posicionX, posicionY);
		this.panel_mapa.addMouseListener(escuchaSiempre);
		this.panel_mapa.addMouseMotionListener(escuchaSiempre);
		
		escuchaSeleccion = new MLSeleccionarNodos(modelo, controlador, panel_mapa);
		escuchaSeleccionTramos = new MLSeleccionarTramos(modelo, controlador, panel_mapa);
		escuchaSeleccionNodosYTramos = new MLSeleccionarElementos(modelo, controlador, panel_mapa);
		
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
		cargarMapaMenuItem.addActionListener(new AccionCargar(controlador,panel_mapa));
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
		
		JMenuItem imagenMenuItem = new JMenuItem();
		imagenMenuItem.addActionListener(new AccionCargarImagen(controlador, panel_mapa));
		imagenMenuItem.setText("Cargar imagen");
		edicionMenu.add(imagenMenuItem);
		
	}
	
	/**
	 * Crea el panel de herramientas de la parte izquierda de la interfaz
	 * gráfica.
	 */
	public void crearHerramientas() {
		panel_herramientas = new JPanel();
		panel_herramientas.setLayout(new BorderLayout());
		getContentPane().add(panel_herramientas, BorderLayout.WEST);
		
		panel = new JToolBar(JToolBar.VERTICAL);
		panel_herramientas.add(panel, BorderLayout.NORTH);
		String imageName;
		/*JButton seleccionarButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodos.png"));
		 seleccionarButton.setMargin(new Insets(1, 1, 1, 1));
		 String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodos-2.png"; 
		 seleccionarButton.setToolTipText("<html>Seleccionar nodos <img src="+imageName+"></html>");
		 seleccionarButton.addActionListener(new AccionSobreMapa(
		 escuchaSeleccion, this, escuchaTeclado));
		 panel.add(seleccionarButton);
		 //!\ OJO (Álex): Para que funcione el oyente, tiene que estar "Enfocado" un objeto con el oyente, por ello
		  //habría que añadir el oyente de teclado a cada boton (componentes enfocables de la ventana) el oyente de 
		   //teclado (aún no he encontrado una manera mejor de conseguir hacerlo).
		    seleccionarButton.addKeyListener(escuchaTeclado);
		    
		    JButton seleccionarTramosButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-tramos.png"));
		    seleccionarTramosButton.setMargin(new Insets(1, 1, 1, 1));
		    imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-tramos-2.png"; 
		    seleccionarTramosButton.setToolTipText("<html>Seleccionar tramos <img src="+imageName+"></html>");
		    seleccionarTramosButton.addActionListener(new AccionSobreMapa(
		    escuchaSeleccionTramos, this, escuchaTeclado));
		    panel.add(seleccionarTramosButton);
		    //!\ OJO (Álex): Para que funcione el oyente, tiene que estar "Enfocado" un objeto con el oyente, por ello
		     //habría que añadir el oyente de teclado a cada boton (componentes enfocables de la ventana) el oyente de 
		      //teclado (aún no he encontrado una manera mejor de conseguir hacerlo).
		       seleccionarTramosButton.addKeyListener(escuchaTeclado);
		       
		       JButton seleccionarNodosYTramosButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodosytramos.png"));
		       seleccionarNodosYTramosButton.setMargin(new Insets(1, 1, 1, 1));
		       imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodosytramos-2.png"; 
		       seleccionarNodosYTramosButton.setToolTipText("<html>Seleccionar nodos y tramos <img src="+imageName+"></html>");
		       seleccionarNodosYTramosButton.addActionListener(new AccionSobreMapa(
		       escuchaSeleccionNodosYTramos, this, escuchaTeclado));
		       panel.add(seleccionarNodosYTramosButton);
		       //!\ OJO (Álex): Para que funcione el oyente, tiene que estar "Enfocado" un objeto con el oyente, por ello
		        //habría que añadir el oyente de teclado a cada boton (componentes enfocables de la ventana) el oyente de 
		         //teclado (aún no he encontrado una manera mejor de conseguir hacerlo).
		          seleccionarNodosYTramosButton.addKeyListener(escuchaTeclado);*/
		
		JButton seleccionarButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-1.png"));
		seleccionarButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-2.png"; 
		seleccionarButton.setToolTipText("<html>Seleccionar <img src="+imageName+"></html>");
		seleccionarButton.addActionListener(new AccionBarra(this, barraSeleccionar));
		panel.add(seleccionarButton);
		
		JButton añadirNodoButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\añadir_nodo.png"));
		añadirNodoButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\añadir_nodo2.png"; 
		añadirNodoButton.setToolTipText("<html>Añadir Nodo <img src="+imageName+"></html>");
		añadirNodoButton.addActionListener(new AccionSobreMapa(
				new MLAñadirNodo(modelo, controlador, panel_mapa), this, escuchaTeclado));
		añadirNodoButton.addActionListener(new AccionBarra(this, barraCrearNodo));
		panel.add(añadirNodoButton);
		//Aquí también habría que añadir el oyente de teclado al añadirNodoButton (y en el resto de botones),
		//pero de momento no lo pongo por si encontramos una alternativa mejor.
		
		JButton añadirTramoButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\añadir_tramo.png"));
		añadirTramoButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\añadir_tramo2.png"; 
		añadirTramoButton.setToolTipText("<html>Añadir Tramo <img src="+imageName+"></html>");
		añadirTramoButton.addActionListener(new AccionSobreMapa(
				new MLAñadirTramo(modelo, controlador, panel_mapa), this, escuchaTeclado));
		panel.add(añadirTramoButton);
		
		JButton eliminarNodoButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\eliminar_nodo.png"));
		eliminarNodoButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\eliminar_nodo2.png"; 
		eliminarNodoButton.setToolTipText("<html>Eliminar Nodo <img src="+imageName+"></html>");
		eliminarNodoButton.addActionListener(new AccionSobreMapa(
				new MLEliminarNodo(modelo, controlador, panel_mapa), this, escuchaTeclado));
		panel.add(eliminarNodoButton);
		
		
		JButton eliminarTramoButton = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\eliminar_tramo.png"));
		eliminarTramoButton.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\eliminar_tramo2.png"; 
		eliminarTramoButton.setToolTipText("<html>Eliminar Tramo <img src="+imageName+"></html>");
		eliminarTramoButton.addActionListener(new AccionSobreMapa(
				new MLEliminarTramo(modelo, controlador, panel_mapa), this, escuchaTeclado));
		panel.add(eliminarTramoButton);	
		
		//Aqui se añadirán los nuevos botones.
		JButton añadirLineaAutobus = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\add_bus.png"));
		añadirLineaAutobus.setMargin(new Insets(1, 1, 1, 1));
		//añadirLineaAutobus.setMaximumSize(new Dimension(25,25));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\add_bus2.png"; 
		añadirLineaAutobus.setToolTipText("<html>Añadir linea de autobus <img src="+imageName+"></html>");
		añadirLineaAutobus.addActionListener(new AccionSobreMapa(
				new MLAñadirLineaAutobus(modelo, controlador, panel_mapa), this, escuchaTeclado));
		panel.add(añadirLineaAutobus);
		
//		 Botón añadir semaforos
		JButton añadirSemaforos = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\semaforo1.png"));
		añadirSemaforos.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\semaforo2.png"; 
		añadirSemaforos.setToolTipText("<html>Añadir semaforo <img src="+imageName+"></html>");
		añadirSemaforos.addActionListener(new AccionSobreMapa(new MLAñadirSemaforo(modelo, controlador, panel_mapa),this,escuchaTeclado));
		panel.add(añadirSemaforos);
		seleccionarButton.addKeyListener(escuchaTeclado);
		
		
		
		
	}
	
	/**
	 * Crea los botones de la parte superior.
	 */
	public void crearBotonesSuperiores() {
		JPanel superior_arriba = new JPanel();
		superior_abajo = new JPanel();
		superior = new JPanel(new GridLayout(0,1));
		getContentPane().add(superior, BorderLayout.NORTH);
		
		superior.add(superior_arriba);
		
		crearBarraPropiedades();
		
		JToolBar archivoTB = new JToolBar();
		JToolBar edicionTB = new JToolBar();
		JToolBar zoomTB = new JToolBar();
		superior_arriba.add(archivoTB);
		superior_arriba.add(edicionTB);
		superior_arriba.add(zoomTB);
		
		superior_arriba.setLayout(new BoxLayout(superior_arriba, BoxLayout.LINE_AXIS));
		superior_abajo.setLayout(new BoxLayout(superior_abajo, BoxLayout.LINE_AXIS));
		JButton nuevoTB = new JButton(new ImageIcon(
		"is\\SimTraffic\\Vista\\Imagenes\\document-new.png"));
		nuevoTB.setMargin(new Insets(1, 1, 1, 1));
		nuevoTB.addActionListener(new AccionNuevo(modelo));
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\document-new2.png"; 
		nuevoTB.setToolTipText("<html>Nuevo mapa <img src="+imageName+"></html>");
		
		JButton cargarTB = new JButton(new ImageIcon(
		"is\\SimTraffic\\Vista\\Imagenes\\document-open.png"));
		cargarTB.setMargin(new Insets(1, 1, 1, 1));
		cargarTB.addActionListener(new AccionCargar(controlador,panel_mapa));
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
		
		JButton zoomin = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\zoom_in.png"));
		zoomin.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\zoom_in2.png";
		zoomin.addActionListener(new AccionZoom(panel_mapa, 2));
		zoomin.setToolTipText("<html>Aumentar tamaño de la representación <img src="+imageName+"></html>");
		
		JButton zoomout = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\zoom_out.png"));
		zoomout.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\zoom_out2.png"; 
		zoomout.addActionListener(new AccionZoom(panel_mapa, 0.5));
		zoomout.setToolTipText("<html>Aumentar tamaño de la representación <img src="+imageName+"></html>");
		
		
		archivoTB.add(nuevoTB);
		archivoTB.add(cargarTB);
		archivoTB.add(guardarTB);
		edicionTB.add(copiarTB);
		edicionTB.add(pegarTB);
		edicionTB.add(deshacerTB);
		zoomTB.add(zoomin);
		zoomTB.add(zoomout);
	}
	
	private void crearBarraPropiedades() 
	{	
		crearBarraNodo();
		crearBarraTramo();
		crearBarraEliminar();
		crearBarraSeleccionar();
	}
	
	private void crearBarraEliminar() {
		// TODO Auto-generated method stub
		
	}
	
	private void crearBarraTramo() {
		// TODO Auto-generated method stub
		
	}
	
	private void crearBarraNodo() 
	{
		barraCrearNodo = new JToolBar(); 
		
		JLabel etiquetaTipo = new JLabel("Tipo");
		String[] tiposNodos = { "                  ", "Carretera", "Tiempo Libre", "Construcción", "Infraestructura", "No definido"};
		comboTipo = new JComboBox(tiposNodos);
		
		JLabel etiquetaValor = new JLabel("Valor");
		String[] valorNodos = { "                  "};
		comboValor = new JComboBox(valorNodos);
	
	    comboTipo.addActionListener(new AccionSeleccionarTipo(comboTipo,comboValor));
	    
	    barraCrearNodo.add(etiquetaTipo);
	    barraCrearNodo.add(comboTipo);
	    barraCrearNodo.add(etiquetaValor);
	    barraCrearNodo.add(comboValor);
	}
	
	private void crearBarraSeleccionar() 
	{
		barraSeleccionar = new JToolBar();
		
		JButton seleccion_nodo = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodos.png"));
		seleccion_nodo.setMargin(new Insets(1, 1, 1, 1));
		String imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodos-2.png"; 
		seleccion_nodo.setToolTipText("<html>Seleccionar nodos <img src="+imageName+"></html>");
		seleccion_nodo.addActionListener(new AccionSobreMapa(
				escuchaSeleccion, this, escuchaTeclado));
		seleccion_nodo.addKeyListener(escuchaTeclado);
		
		JButton seleccion_tramo = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-tramos.png"));
		seleccion_tramo.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-tramos-2.png"; 
		seleccion_tramo.setToolTipText("<html>Seleccionar tramos <img src="+imageName+"></html>");
		seleccion_tramo.addActionListener(new AccionSobreMapa(
				escuchaSeleccionTramos, this, escuchaTeclado));
		seleccion_tramo.addKeyListener(escuchaTeclado);
		
		JButton seleccion_area =  new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodosytramos.png"));
		seleccion_area.setMargin(new Insets(1, 1, 1, 1));
		imageName = "file:is\\SimTraffic\\Vista\\Imagenes\\seleccionar-nodosytramos-2.png"; 
		seleccion_area.setToolTipText("<html>Seleccionar componentes dentro de área <img src="+imageName+"></html>");
		seleccion_area.addActionListener(new AccionSobreMapa(
				escuchaSeleccionNodosYTramos, this, escuchaTeclado));
		seleccion_area.addKeyListener(escuchaTeclado);
		
		barraSeleccionar.add(seleccion_nodo);
		barraSeleccionar.add(seleccion_tramo);
		barraSeleccionar.add(seleccion_area);
	}
	
	/**
	 * Crea la barra de estado
	 *
	 */
	public void crearBarraEstado(){
		
		barraEstado = new JPanel();
		barraEstado.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		
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
	
	public void mostrar(JToolBar bar) 
	{
		if (!panel_añadido)
		{
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
	
	public void setBarraSeleccionar(JToolBar barraSeleccionar) {
		this.barraSeleccionar = barraSeleccionar;
	}
}
