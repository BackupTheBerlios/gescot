package is.SimTraffic.Vista;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelEsperaCargando extends JFrame {

	private JLabel etiquetaCargando;
	private JButton botonCarga;
	private JLabel imagen;
	
	public PanelEsperaCargando() {
		
		this.setLocation(200,200);
		this.setSize(400,200);
		this.setResizable(false);
		this.setTitle("Cargando...");
		//this.validate();
		
		JPanel panel = new JPanel();
		//panel.setSize(400,200);
		imagen = new JLabel((new ImageIcon(
			"is\\SimTraffic\\Vista\\Imagenes\\autobus.jpg" )));	
		etiquetaCargando = new JLabel("Cargando...");
		this.setLayout(new FlowLayout());
		panel.add(etiquetaCargando);
		panel.add(imagen);
		panel.setVisible(true);
		this.add(panel);
		this.setVisible(true);
		this.validate();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
