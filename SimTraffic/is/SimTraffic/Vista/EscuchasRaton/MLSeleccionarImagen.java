package is.SimTraffic.Vista.EscuchasRaton;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.Ventana;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.Iterator;

public class MLSeleccionarImagen extends EscuchaRaton{

	private Image seleccionado;
	
	private Posicion posicion;
	
	private Ventana ventana;
	
	public MLSeleccionarImagen(IModelo modelo, IControlador controlador, PanelMapa panel,Ventana ventana) {
		super(modelo, controlador, panel);
		this.ventana=ventana;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		seleccionado = buscarImagen(arg0.getX(), arg0.getY());
		if (seleccionado != null)
		 ventana.prepararImagen(seleccionado,posicion);
		else
			return;
	}
	
	
	private Image buscarImagen(int argX,int argY){
		Iterator<Posicion> itPosiciones = panel.getRepresentacion().getPosiciones().iterator();
		Iterator<Image> itImagenes = panel.getRepresentacion().getImagenes().iterator();
		Image sel = null;
		Posicion pos =null;
		boolean encontrado = false;
		while (!encontrado && itPosiciones.hasNext()) {
			Posicion posicion = itPosiciones.next();
			int  posX = panel.getRepresentacion().x_MapaARep(posicion.getLat());
			int  posY = panel.getRepresentacion().y_MapaARep(posicion.getLon());
			Image imagen = itImagenes.next(); 
			if (posX<=argX&& posX+imagen.getWidth(null)>=argX) 
				if (posY<=argY&& posY+imagen.getHeight(null)>=argY)
			{
				encontrado = true;
				sel = imagen;
				pos=posicion;
				System.out.println("IMAGEN ENCONTRADA");
				panel.getGraphics().draw3DRect(posX,posY,imagen.getWidth(null),imagen.getHeight(null),true);
			}
		}
		if (encontrado)
			return sel;
		else
			return null;
		
	}
	
	private void crearPanelDimensionarImagen(){
		
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
