package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HEliminarTramo;
import is.SimTraffic.Mapa.LineaBus;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

/**
 * 
 * @author Grupo ISTrafico
 *
 */
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
		 if(modelo.getMapa().esDeLineasBus(seleccionado)){
				 // Preguntar si desea eliminar las lineas de Bus que contienen el tramo
				 	 int n=JOptionPane.showConfirmDialog(null, 
			                Messages.getString("MLEliminarTramo.1"), //$NON-NLS-1$
			                Messages.getString("MLEliminarTramo.2"), //$NON-NLS-1$
				 	 		JOptionPane.OK_CANCEL_OPTION);
					panel.sugerir(null);
					if (n==0) {
						//Eliminar las lineas de bus que lo contienen
						Iterator<LineaBus> it =modelo.getMapa().getLineasAutobuses().iterator();
						ArrayList<LineaBus> lineasAeliminar = new ArrayList<LineaBus>();
						while(it.hasNext()){
							LineaBus linea =it.next();
							if(linea.getTramos().contains(seleccionado))
								lineasAeliminar.add(linea);
						}
						it=lineasAeliminar.iterator();
						while(it.hasNext()){
						 modelo.getMapa().eliminarLineaAutobus(it.next());
						}
					}
					else {
						//El usuario aborta la operacion
						return;
					}
			 }
			controlador.herramienta(new HEliminarTramo(seleccionado));			
			Tramo tramoSeleccion = modelo.getMapa().getSeleccion().existeTramo(seleccionado);
			if (tramoSeleccion!=null) {
				for (int i=0; i<modelo.getMapa().getSeleccion().getTramosSeleccionados().size(); i++) {
					if (modelo.getMapa().getSeleccion().getTramosSeleccionados().get(i).equals(tramoSeleccion)) 
						modelo.getMapa().getSeleccion().getTramosSeleccionados().remove(i);
				}
			}			
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
	public void mouseMoved(MouseEvent e) {
		Tramo tramo = buscarTramo(e.getX(), e.getY());
		if (tramo != null)
			panel.sugerir(tramo);
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
		return Messages.getString("MLEliminarTramo.0"); //$NON-NLS-1$
	}
	
}
