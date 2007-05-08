package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.Iterator;

public class MLSeleccionarImagen extends EscuchaRaton{

	private Image seleccionado;
	
	public MLSeleccionarImagen(IModelo modelo, IControlador controlador, PanelMapa panel) {
		super(modelo, controlador, panel);
	
	}
	
	public void mouseClicked(MouseEvent arg0) {
		seleccionado = buscarImagen(arg0.getX(), arg0.getY());
		//if (seleccionado != null)
         //crearPanelDimensionarImagen();
		 
	}
	
	
	public Image buscarImagen(int lat,int lon){
		Iterator<Posicion> itPosiciones = panel.getRepresentacion().getPosiciones().iterator();
		Iterator<Image> itImagenes = panel.getRepresentacion().getImagenes().iterator();
		Image sel = null;
		Posicion pos =null;
		boolean encontrado = false;
		while (!encontrado && itPosiciones.hasNext()) {
			Posicion posicion = itPosiciones.next();
			Image imagen = itImagenes.next(); 
			if (posicion.getLat()<=lat&& posicion.getLat()+imagen.getHeight(null)>=lat) 
				if (posicion.getLon()<=lon&& posicion.getLon()+imagen.getWidth(null)>=lon) 
			{
				encontrado = true;
				sel = imagen;
				pos=posicion;
				System.out.println("IMAGEN ENCONTRADA");
			}
		}
		if (encontrado)
			return sel;
		else
			return null;
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAyuda() {
		// TODO Auto-generated method stub
		return null;
	}

}
