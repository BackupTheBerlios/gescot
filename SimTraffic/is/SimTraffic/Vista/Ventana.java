package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Ventana extends JFrame 
{
	private JLabel herramientasLabel;
	private JScrollPane scrollPane;
	private JButton button_4;
	private JButton button_3;
	private JButton button_2;
	private JButton button_1;
	private JButton button;
	private JPanel panel_herramientas;
	private JMenuItem menuItem_2;
	private JMenuItem menuItem_1;
	private JMenuItem menuItem;
	private JMenu menu;
	private JMenuBar menuBar;
	private IModelo modelo;
	
	/**
	 * Panel para editar los mapas. Puede ser que haya que cambiarlo por una JLabel.
	 * */
	private JPanel panel_edicion;
	
	public Ventana(IModelo modelo)
	{
		this.modelo = modelo;
		setSize(800,600);
		setTitle("SimTraffic v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menu = new JMenu();
		menu.setText("New JMenu");
		menuBar.add(menu);

		menuItem = new JMenuItem();
		menuItem.setText("New JMenuItem");
		menu.add(menuItem);

		menuItem_1 = new JMenuItem();
		menuItem_1.setText("New JMenuItem");
		menu.add(menuItem_1);

		menuItem_2 = new JMenuItem();
		menuItem_2.setText("New JMenuItem");
		menu.add(menuItem_2);

		panel_herramientas = new JPanel();
		panel_herramientas.setLayout(new BoxLayout(panel_herramientas, BoxLayout.PAGE_AXIS));
		getContentPane().add(panel_herramientas, BorderLayout.WEST);

		herramientasLabel = new JLabel();
		herramientasLabel.setHorizontalAlignment(SwingConstants.CENTER);
		herramientasLabel.setText("     Herramientas");
		panel_herramientas.add(herramientasLabel);

		button = new JButton();
		button.setText("New JButton");
		panel_herramientas.add(button);

		button_1 = new JButton();
		button_1.setText("New JButton");
		panel_herramientas.add(button_1);

		button_2 = new JButton();
		button_2.setText("New JButton");
		panel_herramientas.add(button_2);

		button_3 = new JButton();
		button_3.setText("New JButton");
		panel_herramientas.add(button_3);

		button_4 = new JButton();
		button_4.setText("New JButton");
		panel_herramientas.add(button_4);

		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane);
		panel_edicion = new JPanel();
		scrollPane.setViewportView(panel_edicion);
		panel_edicion.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panel_edicion.setBackground(Color.WHITE);
	}
	
	public void paint(Graphics g)
	{
		
	}

}
