package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.Utils.ChequeoInputVentanas;
import is.SimTraffic.Vista.BarrasHerramientas.BarraRedimensionarImagen;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionRedimensionarImagen implements ActionListener{

	private BarraRedimensionarImagen barra;
	
	private boolean porcentaje;
	
	private Image imagen2;
	
	public AccionRedimensionarImagen(BarraRedimensionarImagen barra,boolean porcentaje) {
		super();
		this.barra = barra;
		this.porcentaje = porcentaje;
	}
	
	public void actionPerformed(ActionEvent e) {
		ChequeoInputVentanas chequeo=new ChequeoInputVentanas();
		if (barra.getImagen1()!=null){
		 if (porcentaje){
		  if (chequeo.esDigito(barra.getP_width().getText())&& chequeo.esDigito(barra.getP_height().getText()))	 
			imagen2=barra.getImagen1().getScaledInstance(Integer.parseInt(barra.getP_width().getText())*barra.getImagen1().getWidth(null)/100,Integer.parseInt(barra.getP_height().getText())*barra.getImagen1().getHeight(null)/100,Image.SCALE_SMOOTH);
		    recargarImagen();
		 }else{
		  if (chequeo.esDigito(barra.get_Width().getText())&& chequeo.esDigito(barra.get_Height().getText()))	 
			imagen2=barra.getImagen1().getScaledInstance(Integer.parseInt(barra.get_Width().getText()),Integer.parseInt(barra.get_Height().getText()),Image.SCALE_SMOOTH);
		  	recargarImagen();
		  } 
		 }  
	}

	/**
	 * Metodo que espera a que se carguen correctamente las nuevas instancias de las 
	 * imagenes para recargarlas correctamente en la representacion
	 *
	 */
	private void recargarImagen(){
		//Objeto mediaTracker que nos permite hacer el seguimiento del redimensionamiento de la imagen
		  MediaTracker tracker = new MediaTracker(barra.getPanel());
			tracker.addImage(barra.getImagen1(),1);
			tracker.addImage(imagen2,1);
			  try{
			      // Se bloquea la tarea durante el tiempo necesario para la carga
			      // de todas las imágenes
			      tracker.waitForAll();
			    } catch( InterruptedException exception ) {
			      System.out.println( exception );
			      }
			barra.getPanel().getRepresentacion().removeImage(barra.getImagen1());
			barra.getPanel().getRepresentacion().addImage(imagen2,barra.getPosicion());
			barra.getPanel().setModoSeleccion(false);
			barra.getPanel().recrear();
			barra.getPanel().recrearMapa();
			barra.getPanel().repaint(); 
		
	}
		
}
