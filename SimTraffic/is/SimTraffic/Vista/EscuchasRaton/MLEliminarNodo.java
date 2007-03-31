package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;
/**
 * 
 * @author Grupo ISTrafico
 *
 */
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
				panel.sugerir(null);
				panel.recrearMapa();
				panel.repaint();
			}
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
