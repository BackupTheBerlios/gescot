package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Utils.ChequeoInputVentanas;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

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
				if (chequeo.esDigito(width.getText())&& chequeo.esDigito(height.getText())){
					
					//imagen2=imagen1.getScaledInstance(Integer.parseInt(width.getText()),Integer.parseInt(height.getText()),Image.SCALE_SMOOTH);
					Toolkit.getDefaultToolkit().prepareImage(imagen1,Integer.parseInt(width.getText()),Integer.parseInt(height.getText()),null);
					//imagen2=escalaImatge(imagen1,Integer.parseInt(width.getText()),Integer.parseInt(height.getText()));
					//imagen2.flush();
					//imagen2=imagen1;
					//panel.getRepresentacion().removeImage(imagen1);
					panel.getRepresentacion().addImage(imagen1,posicion);
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
				if (chequeo.esDigito(p_width.getText())&& chequeo.esDigito(p_height.getText())){
					//imagen2=imagen1.getScaledInstance(Integer.parseInt(p_width.getText())*imagen1.getWidth(null),Integer.parseInt(height.getText())*imagen1.getHeight(null),0);
					imagen2 =escalaImatge(imagen1,Integer.parseInt(p_width.getText())*imagen1.getWidth(null),Integer.parseInt(height.getText())*imagen1.getHeight(null));
					}
			}
		});
		
		JButton eliminar = new JButton("Eliminar");
		eliminar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent e){
			  panel.getRepresentacion().removeImage(imagen1);
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
	public Image escalaImatge(Image rimage,int ancho,int alto) { 
		BufferedImage tnsImg = new BufferedImage(ancho,alto, 
		BufferedImage.TYPE_INT_RGB); 
		Graphics2D graphics2D = tnsImg.createGraphics(); 
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
		RenderingHints.VALUE_INTERPOLATION_BILINEAR); 
		graphics2D.drawImage(rimage, 0, 0, ancho, alto, null); 
		return tnsImg; 
		}
	
}
