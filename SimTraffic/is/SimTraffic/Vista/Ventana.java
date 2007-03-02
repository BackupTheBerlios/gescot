package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;

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

public class Ventana extends JFrame 
{
	private JMenuItem pegarMenuItem;
	private JMenuItem copiarMenuItem;
	private JMenu edicionMenu;
	private JMenuItem salirMenuItem;
	private JPanel panel;
	private JLabel herramientasLabel;
	private JScrollPane scrollPane;
	private JButton otrosButton;
	private JButton eliminartramoButton;
	private JButton eliminarNodoButton;
	private JButton añadirTramoButton;
	private JButton añadirNodoButton;
	private JPanel panel_herramientas;
	private JMenuItem guardarmapaMenuItem;
	private JMenuItem cargarMapaMenuItem;
	private JMenuItem nuevoMapaMenuItem;
	private JMenu archivoMenu;
	private JMenuBar menuBar;
	private IModelo modelo;
	
	/**
	 * Panel para editar los mapas. Puede ser que haya que cambiarlo por una JLabel.
	 * */
	private JPanel panel_edicion;
	private JPanel superior;
	
	public Ventana(IModelo modelo)
	{
		this.modelo = modelo;
		setSize(800,600);
		setTitle("SimTraffic™ v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		archivoMenu = new JMenu();
		archivoMenu.setText("Archivo");
		menuBar.add(archivoMenu);

		nuevoMapaMenuItem = new JMenuItem();
		nuevoMapaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
			
			}
		});
		nuevoMapaMenuItem.setText("Nuevo Mapa");
		archivoMenu.add(nuevoMapaMenuItem);

		archivoMenu.addSeparator();

		cargarMapaMenuItem = new JMenuItem();
		cargarMapaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		cargarMapaMenuItem.setText("Cargar Mapa");
		archivoMenu.add(cargarMapaMenuItem);

		guardarmapaMenuItem = new JMenuItem();
		guardarmapaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		guardarmapaMenuItem.setText("GuardarMapa");
		archivoMenu.add(guardarmapaMenuItem);

		archivoMenu.addSeparator();

		salirMenuItem = new JMenuItem();
		salirMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				System.exit(0);
			}
		});
		salirMenuItem.setText("Salir");
		archivoMenu.add(salirMenuItem);

		edicionMenu = new JMenu();
		edicionMenu.setText("Edición");
		menuBar.add(edicionMenu);

		copiarMenuItem = new JMenuItem();
		copiarMenuItem.setText("Copiar");
		edicionMenu.add(copiarMenuItem);

		pegarMenuItem = new JMenuItem();
		pegarMenuItem.setText("Pegar");
		edicionMenu.add(pegarMenuItem);

		panel_herramientas = new JPanel();
		panel_herramientas.setLayout(new BorderLayout());
		getContentPane().add(panel_herramientas, BorderLayout.WEST);

		panel = new JPanel();
		final GridLayout gridLayout = new GridLayout(0, 1);
		gridLayout.setVgap(2);
		panel.setLayout(gridLayout);
		panel_herramientas.add(panel, BorderLayout.NORTH);

		herramientasLabel = new JLabel();
		panel.add(herramientasLabel);
		herramientasLabel.setHorizontalAlignment(SwingConstants.CENTER);
		herramientasLabel.setText("Herramientas");

		añadirNodoButton = new JButton();
		añadirNodoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		panel.add(añadirNodoButton);
		añadirNodoButton.setText("Añadir Nodo(s)");

		añadirTramoButton = new JButton();
		añadirTramoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		panel.add(añadirTramoButton);
		añadirTramoButton.setText("Añadir Tramo(s)");

		eliminarNodoButton = new JButton();
		eliminarNodoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		panel.add(eliminarNodoButton);
		eliminarNodoButton.setText("Eliminar Nodo(s)");

		eliminartramoButton = new JButton();
		eliminartramoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		panel.add(eliminartramoButton);
		eliminartramoButton.setText("Eliminar Tramo(s)");

		otrosButton = new JButton();
		panel.add(otrosButton);
		otrosButton.setText("Otros...");

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		panel_edicion = new JPanel();
		scrollPane.setViewportView(panel_edicion);
		panel_edicion.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel_edicion.setBackground(Color.WHITE);
		
		superior = new JPanel();
		getContentPane().add(superior, BorderLayout.NORTH);
		
		JToolBar archivoTB = new JToolBar();
		JToolBar edicionTB = new JToolBar();
		superior.add(archivoTB);
		superior.add(edicionTB);
		
		superior.setLayout(new BoxLayout(superior, BoxLayout.LINE_AXIS));
		JButton nuevoTB = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\nuevo.gif"));
		nuevoTB.setMargin(new Insets(1,1,1,1));
		JButton cargarTB = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\abrir.gif"));
		cargarTB.setMargin(new Insets(1,1,1,1));
		JButton guardarTB = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\guardar.gif"));
		guardarTB.setMargin(new Insets(1,1,1,1));
		JButton copiarTB = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\copiar.gif"));
		copiarTB.setMargin(new Insets(1,1,1,1));
		JButton pegarTB = new JButton(new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\pegar.gif"));
		pegarTB.setMargin(new Insets(1,1,1,1));
		
		archivoTB.add(nuevoTB);
		archivoTB.add(cargarTB);
		archivoTB.add(guardarTB);
		edicionTB.add(copiarTB);
		edicionTB.add(pegarTB);
	}
	
	public void paint(Graphics g)
	{
		paintComponents(g);
	}

}
