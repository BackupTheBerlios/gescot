package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Utils.ChequeoInputVentanas;
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
				if (chequeo.esDigito(width.getText())&& chequeo.esDigito(height.getText()))
					imagen=imagen.getScaledInstance(Integer.parseInt(width.getText()),Integer.parseInt(height.getText()),0);
			}
		});

		
		JLabel porcentaje_ancho = new JLabel(" %Ancho: ");
		p_width = new JTextField();
		
		JLabel porcentaje_alto = new JLabel(" %Alto: ");
		p_height = new JTextField();
		
		JButton p_redimensionar = new JButton("Validar");
		redimensionar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (chequeo.esDigito(p_width.getText())&& chequeo.esDigito(p_height.getText()))
					imagen=imagen.getScaledInstance(Integer.parseInt(p_width.getText())*imagen.getWidth(null),Integer.parseInt(height.getText())*imagen.getHeight(null),0);
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
	}	
	
	public void prepararImagen(Image im,Posicion pos){
		this.imagen=im;
		this.posicion=pos;
	}
}
