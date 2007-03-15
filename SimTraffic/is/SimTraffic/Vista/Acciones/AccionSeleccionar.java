package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Vista.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionSeleccionar implements ActionListener {
	
	private Ventana panel;
	
	public AccionSeleccionar(Ventana ventana) 
	{
		this.panel = ventana;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		panel.mostrar(panel.getBarraSeleccionar());
	}
	
}
