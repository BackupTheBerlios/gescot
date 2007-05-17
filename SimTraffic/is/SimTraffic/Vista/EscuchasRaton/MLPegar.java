package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Herramientas.HPegar;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Clase que extiende a la escucha del ratón. 
 * Se encarga de escuchar a la acción de pegar.
 * @author Grupo ISTrafico
 *
 */
public class MLPegar extends EscuchaRaton {

	/**
	 * Constructora que llama a la constructora padre 
	 * @param modelo
	 * @param controlador
	 * @param panel
	 */
	public MLPegar(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
		
	}
	
	@Override
	/**
	 * Método que se llama al hacer un clic.
	 * Se guarda el punto sobre el que se ha pegado y se transforma
	 * a coordenadas del mapa. A continuación se construye una herramienta
	 * de pegar, se crea desde el controlador y se repinta.
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getButton()!= MouseEvent.BUTTON1)
				return;
		double x = panel.lon_RepAMapa(arg0.getX());
		double y = panel.lat_RepAMapa(arg0.getY());
		Point2D puntoPegar = new Point2D.Double(x,y);		
		HPegar herramientaPegar = new HPegar (puntoPegar);
		controlador.herramienta(herramientaPegar);
		panel.setRecrear(true);
		panel.repaint();		
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
		//Creamos un nodo "fantasma" que ayuda a saber en qué 
		//posición se insertará el contenido del portapapeles.
		double y = panel.lon_RepAMapa(arg0.getX());
		double x = panel.lat_RepAMapa(arg0.getY());
		Posicion puntoAyuda = new Posicion(x,y);
		Nodo nodoAyuda = new Nodo(puntoAyuda);
		
		panel.sugerir(nodoAyuda);
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
		return Messages.getString("MLPegar.0"); //$NON-NLS-1$
	}
	

}
