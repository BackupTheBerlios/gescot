package is.SimTraffic.Vista.BarrasHerramientas;

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
	
	private ChequeoInputVentanas chequeo;
	
	private JTextField height;

	private JTextField width;
	
	private JTextField p_height;
	
	private JTextField p_width;

	public BarraRedimensionarImagen(){
		
		chequeo = new ChequeoInputVentanas();
		JLabel label1 = new JLabel("Imagen:");
		JLabel nombre_fichero = new JLabel("       ");
		
		JLabel ancho = new JLabel(" Ancho: ");
		width = new JTextField();
		
		JLabel alto = new JLabel(" Alto: ");
		height = new JTextField();
		
		JButton redimensionar = new JButton("Validar");
		redimensionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (chequeo.esDigito(width.getText())&& chequeo.esDigito(height.getText())&&imagen1!=null){
					imagen2=imagen1.getScaledInstance(Integer.parseInt(width.getText()),Integer.parseInt(height.getText()),Image.SCALE_SMOOTH);
					//Objeto mediaTracker que nos permite hacer el seguimiento del redimensionamiento de la imagen
					MediaTracker tracker = new MediaTracker(panel);
					tracker.addImage(imagen1,1);
					tracker.addImage(imagen2,1);
					  try{
					      // Se bloquea la tarea durante el tiempo necesario para la carga
					      // de todas las im�genes
					      tracker.waitForAll();
					    } catch( InterruptedException exception ) {
					      System.out.println( exception );
					      }
					panel.getRepresentacion().removeImage(imagen1);
					panel.getRepresentacion().addImage(imagen2,posicion);
					panel.setModoSeleccion(false);
					panel.recrear();
					panel.recrearMapa();
					panel.repaint();  
			}	
			}
		});

		
		JLabel porcentaje_ancho = new JLabel(" %Ancho: ");
		p_width = new JTextField();
		
		JLabel porcentaje_alto = new JLabel(" %Alto: ");
		p_height = new JTextField();
		
		JButton p_redimensionar = new JButton("Validar");
		redimensionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (chequeo.esDigito(p_width.getText())&& chequeo.esDigito(p_height.getText())&&imagen1!=null){
					imagen2=imagen1.getScaledInstance(Integer.parseInt(p_width.getText())*imagen1.getWidth(null),Integer.parseInt(height.getText())*imagen1.getHeight(null),Image.SCALE_SMOOTH);
					//Objeto mediaTracker que nos permite hacer el seguimiento del redimensionamiento de la imagen
					MediaTracker tracker = new MediaTracker(panel);
					tracker.addImage(imagen1,1);
					tracker.addImage(imagen2,1);
					  try{
					      // Se bloquea la tarea durante el tiempo necesario para la carga
					      // de todas las im�genes
					      tracker.waitForAll();
					    } catch( InterruptedException exception ) {
					      System.out.println( exception );
					      }
					panel.getRepresentacion().removeImage(imagen1);
					panel.getRepresentacion().addImage(imagen2,posicion);
					panel.setModoSeleccion(false);
					panel.recrear();
					panel.recrearMapa();
					panel.repaint();  
					}
			}
		});
		
		JButton eliminar = new JButton("Eliminar");
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
	
}
