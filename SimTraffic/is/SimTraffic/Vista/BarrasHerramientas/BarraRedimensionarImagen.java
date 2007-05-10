package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Vista.Log;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BarraRedimensionarImagen extends Barra {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image imagen;
	
	private Posicion posicion;

	public BarraRedimensionarImagen(){
		JLabel label1 = new JLabel("Imagen:");
		JLabel nombre_fichero = new JLabel("       ");
		
		JLabel ancho = new JLabel(" Ancho: ");
		JTextField width = new JTextField();
		
		JLabel alto = new JLabel(" Alto: ");
		JTextField height = new JTextField();
		
		JLabel porcentaje_ancho = new JLabel(" %Ancho: ");
		JTextField p_width = new JTextField();
		
		JLabel porcentaje_alto = new JLabel(" %Alto: ");
		JTextField p_height = new JTextField();
		
		JButton redimensionar = new JButton("Redimensionar");
		redimensionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		add(label1);
		add(nombre_fichero);
		add(ancho);
		add(width);
		add(alto);
		add(height);
		add(porcentaje_ancho);
		add(p_width);
		add(porcentaje_alto);
		add(p_height);
	}	
	
	public void prepararImagen(Image im,Posicion pos){
		this.imagen=im;
		this.posicion=pos;
	}
}
