package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;
import java.util.Iterator;

public class MLEliminarNodo extends EscuchaRaton
{

	public MLEliminarNodo(IModelo modelo, IControlador controlador, PanelMapa panel) 
	{
		super(modelo, controlador, panel);
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (e.getButton() != MouseEvent.BUTTON1)
			return;
		
		Nodo seleccionado = buscarNodo(e.getX(), e.getY());
		if (seleccionado != null)
		{
			if (seleccionado.getTramos().isEmpty())   //Solo se eliminan los nodos que no tienen ningun tramo. ¿Eliminar también los tramos?
			{
				controlador.herramienta(new HEliminarNodo(seleccionado));
				panel.repaint();
			}
		}	
	}
	
	
	private Nodo buscarNodo(int x, int y) 
	{
		Iterator<Nodo> iter = modelo.getMapa().getNodos().iterator();
		Nodo sel = null;
		boolean encontrado = false;
		while (!encontrado && iter.hasNext())
		{
			Nodo next = iter.next();
			if ((next.getPos().getPosX() <= x) && 
				(next.getPos().getPosX() + 6 >= x) &&
				(next.getPos().getPosY() <= y) &&
				(next.getPos().getPosY() + 6 >= y))
			{
				encontrado = true;
				sel = next;
			}
		}
		if (encontrado)
			return sel;
		else
			return null;
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
		// TODO Auto-generated method stub
		
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
