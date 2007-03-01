package is.SimTraffic.Vista;

import is.SimTraffic.IModelo;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ventana extends JFrame 
{
	private IModelo modelo;
	
	/**
	 * Panel para editar los mapas. Puede ser que haya que cambiarlo por una JLabel.
	 * */
	private JPanel panel_edicion;
	
	public Ventana(IModelo modelo)
	{
		this.modelo = modelo;
		panel_edicion = new JPanel();
		add(panel_edicion);
		setSize(800,600);
		setTitle("SimTraffic v1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void paint(Graphics g)
	{
		
	}

}
