package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarTramo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;

public class MLEliminarTramo extends EscuchaRaton
{
	
	public MLEliminarTramo(IModelo modelo, IControlador controlador, PanelMapa panel) 
	{
		super(modelo, controlador, panel);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getButton() != MouseEvent.BUTTON1)
			return;
		
		Tramo seleccionado = buscarTramo(e.getX(), e.getY());
		if (seleccionado != null)
		{
			controlador.herramienta(new HEliminarTramo(seleccionado));
			panel.sugerir(null);
			panel.recrearMapa();
			panel.repaint();
		}	
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		Nodo nodo = buscarNodo(arg0.getX(), arg0.getY());
		if (nodo != null)
			panel.sugerir(nodo);
		else
			panel.sugerir(null);
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
