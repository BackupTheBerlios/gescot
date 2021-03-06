package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;

import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.Ventana;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class MLSeleccionarImagen extends EscuchaRaton{

	private Image seleccionado;
	
	private Posicion posicion;
	
	private Ventana ventana;
	
	private boolean encontrado;
	
	private boolean presionado;
	
	
	public MLSeleccionarImagen(IModelo modelo, IControlador controlador, PanelMapa panel,Ventana ventana) {
		super(modelo, controlador, panel);
		this.ventana=ventana;
		this.presionado=false;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		buscarImagen(arg0.getX(), arg0.getY());
		if (encontrado)
		 ventana.prepararImagen(seleccionado,posicion);
		else{
			seleccionado = null;
			encontrado=false;
		}
			
	}
	
	
	private void buscarImagen(int argX,int argY){
		Iterator<Posicion> itPosiciones = panel.getRepresentacion().getPosiciones().iterator();
		Iterator<Image> itImagenes = panel.getRepresentacion().getImagenes().iterator();
		encontrado = false;
		while (!encontrado && itPosiciones.hasNext()) {
			Posicion pos = itPosiciones.next();
			int  posX = panel.getRepresentacion().x_MapaARep(pos.getLon());
			int  posY = panel.getRepresentacion().y_MapaARep(pos.getLat());
			double zoom = panel.getRepresentacion().getZoom();
			Image imagen = itImagenes.next(); 
			if (posX<=argX&& posX+imagen.getWidth(null)*zoom>=argX) 
				if (posY<=argY&& posY+imagen.getHeight(null)*zoom>=argY)
			{
				encontrado = true;
				seleccionado = imagen;
				posicion=pos;
				panel.setModoSeleccion(true);
				panel.setRectanguloSeleccion(new Rectangle(posX,posY,(int)(imagen.getWidth(null)*zoom),(int)(imagen.getHeight(null)*zoom)));
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
		}	
	}
	

	public void mousePressed(MouseEvent e) {
		if (encontrado)
		 presionado=true;

	}

	public void mouseReleased(MouseEvent e) {
		if (encontrado&&presionado){
			if (panel.getRepresentacion().getImagenes().contains(seleccionado)){	
			 int indice =panel.getRepresentacion().getImagenes().indexOf(seleccionado);
			 double y = panel.lon_RepAMapa(e.getX());
			 double x = panel.lat_RepAMapa(e.getY());
			 posicion = new Posicion(x,y);
			 panel.getRepresentacion().getPosiciones().set(indice,posicion);
			 panel.setModoSeleccion(false);
			 ventana.prepararImagen(null,posicion);
			 panel.recrear();
			 panel.recrearMapa();
			 panel.repaint();
			 presionado=false;
			 encontrado=false;
			}
			}

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
	
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAyuda() {
		return Messages.getString("MLSeleccionarImagen.0"); //$NON-NLS-1$
	}

}
