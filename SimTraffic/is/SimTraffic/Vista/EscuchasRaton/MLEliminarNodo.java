package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HEliminarNodo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

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
				 if(modelo.getMapa().esDeLineasBus(seleccionado)){
					 // Preguntar si desea eliminar las lineas de Bus que contienen el nodo
					 	 int n=JOptionPane.showConfirmDialog(null, 
				                Messages.getString("MLEliminarNodo.1"), //$NON-NLS-1$
				                Messages.getString("MLEliminarNodo.2"), //$NON-NLS-1$
					 	 		JOptionPane.OK_CANCEL_OPTION);
						panel.sugerir(null);
						if (n==0) {
							//Eliminar las lineas de bus que lo contienen		
						}
						else {
							//El usuario aborta la operacion
							return;
						}
				 }
					
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
		return Messages.getString("MLEliminarNodo.0"); //$NON-NLS-1$
	}

}
