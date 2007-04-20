package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Tramo;
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
			if (seleccionado.getTramos().isEmpty())   //Solo se eliminan los nodos que no tienen ningun tramo. �Eliminar tambi�n los tramos?
			{
				controlador.herramienta(new HEliminarNodo(seleccionado));
				Nodo nodoSeleccion = modelo.getMapa().getSeleccion().existeNodo(seleccionado);
				if (nodoSeleccion!=null) {
					for (int i=0; i<modelo.getMapa().getSeleccion().getNodosSeleccionados().size(); i++) {
						if (modelo.getMapa().getSeleccion().getNodosSeleccionados().get(i).equals(nodoSeleccion)) 
							modelo.getMapa().getSeleccion().getNodosSeleccionados().remove(i);
					}
				}
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

	@Override
	public String getAyuda() {
		return "Haga click sobre el nodo que desea eliminar.";
	}

}
