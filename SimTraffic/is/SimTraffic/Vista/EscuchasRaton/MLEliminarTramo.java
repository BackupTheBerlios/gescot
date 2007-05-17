package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HEliminarTramo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.event.MouseEvent;

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
