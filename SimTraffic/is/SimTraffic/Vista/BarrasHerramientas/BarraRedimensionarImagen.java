package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Utils.ChequeoInputVentanas;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class BarraRedimensionarImagen extends Barra {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Imagen que estamos tratando
	private Image imagen1;
	
	//Copia de la imagen que lleva los cambios realizados
	private Image imagen2;
	
	private Posicion posicion;
	
	private PanelMapa panel;
	
	private JTextField height;

	private JTextField width;
	
	private JTextField p_height;
	
	private JTextField p_width;

	public BarraRedimensionarImagen(){
		
		JLabel label1 = new JLabel(Messages.getString("BarraRedimensionarImagen.0")); //$NON-NLS-1$
		JLabel nombre_fichero = new JLabel(Messages.getString("BarraRedimensionarImagen.1")); //$NON-NLS-1$
		
		JLabel ancho = new JLabel(Messages.getString("BarraRedimensionarImagen.2")); //$NON-NLS-1$
		width = new JTextField();
		
		JLabel alto = new JLabel(Messages.getString("BarraRedimensionarImagen.3")); //$NON-NLS-1$
		height = new JTextField();
		
		JButton redimensionar = new JButton(Messages.getString("BarraRedimensionarImagen.4")); //$NON-NLS-1$
		redimensionar.addActionListener(new AccionRedimensionarImagen(false));
		
		JLabel porcentaje_ancho = new JLabel(Messages.getString("BarraRedimensionarImagen.5")); //$NON-NLS-1$
		p_width = new JTextField();
		
		JLabel porcentaje_alto = new JLabel(Messages.getString("BarraRedimensionarImagen.6")); //$NON-NLS-1$
		p_height = new JTextField();
		
		JButton p_redimensionar = new JButton(Messages.getString("BarraRedimensionarImagen.7")); //$NON-NLS-1$
		p_redimensionar.addActionListener(new AccionRedimensionarImagen(true));
		
		JButton eliminar = new JButton(Messages.getString("BarraRedimensionarImagen.8")); //$NON-NLS-1$
		eliminar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
			  panel.getRepresentacion().removeImage(imagen1);
			  panel.setModoSeleccion(false);
			  imagen1=null;
			  panel.recrear();
			  panel.recrearMapa();
			  panel.repaint();
			}
			
		});
		
		
		add(label1);
		add(nombre_fichero);
		add(ancho);
		add(width);
		add(alto);
		add(height);
		add(redimensionar);
		add(porcentaje_ancho);
		add(p_width);
		add(porcentaje_alto);
		add(p_height);
		add(p_redimensionar);
		add(eliminar);
	}	
	
	public void prepararImagen(Image im,Posicion pos,PanelMapa panel){
		this.imagen1=im;
		this.posicion=pos;
		this.panel =panel;
	}

	private class AccionRedimensionarImagen implements ActionListener{
	
		private boolean porcentaje;
		
		public AccionRedimensionarImagen(boolean porcentaje) {
			super();
			this.porcentaje = porcentaje;
		}
		
		public void actionPerformed(ActionEvent e) {
			ChequeoInputVentanas chequeo=new ChequeoInputVentanas();
			if (imagen1!=null){
			 if (porcentaje){
			  if (chequeo.esDigito(p_width.getText())&& chequeo.esDigito(p_height.getText()))	 
				imagen2=imagen1.getScaledInstance(Integer.parseInt(p_width.getText())*imagen1.getWidth(null)/100,Integer.parseInt(p_height.getText())*imagen1.getHeight(null)/100,Image.SCALE_SMOOTH);
			    recargarImagen();
			 }else{
			  if (chequeo.esDigito(width.getText())&& chequeo.esDigito(height.getText()))	 
				imagen2=imagen1.getScaledInstance(Integer.parseInt(width.getText()),Integer.parseInt(height.getText()),Image.SCALE_SMOOTH);
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
			  MediaTracker tracker = new MediaTracker(panel);
				tracker.addImage(imagen1,1);
				tracker.addImage(imagen2,1);
				  try{
				      // Se bloquea la tarea durante el tiempo necesario para la carga
				      // de todas las imágenes
				      tracker.waitForAll();
				    } catch( InterruptedException exception ) {
				      System.out.println( exception );
				      }
				panel.getRepresentacion().removeImage(imagen1);
				panel.getRepresentacion().addImage(imagen2,posicion);
				imagen1=imagen2;
				panel.setModoSeleccion(false);
				panel.recrear();
				panel.recrearMapa();
				panel.repaint(); 
			
		}
			
	}
	
}
