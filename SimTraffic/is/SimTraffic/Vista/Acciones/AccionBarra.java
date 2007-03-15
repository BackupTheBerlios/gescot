package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;

public class AccionBarra implements ActionListener {
	
	private Ventana panel;
	
	private JToolBar barra;
	
	public AccionBarra(Ventana ventana, JToolBar barra) 
	{
		this.panel = ventana;
		this.barra = barra;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		panel.mostrar(barra);
	}
	
}
