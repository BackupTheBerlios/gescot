package is.SimTraffic.Vista;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PanelEsperaCargando extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4426934915163670765L;

	private JLabel imagen;
	
	public PanelEsperaCargando(String titulo, String mensaje) {
		setLocation(200,200);
		setResizable(false);
		setTitle(titulo); //"Cargando...");
		
		// TODO cargar imagen desde el jar
		ClassLoader cl = this.getClass().getClassLoader();
		BufferedImage imagentemp = null;
		try {
			imagentemp = ImageIO.read(cl.getResource("is/SimTraffic/Vista/Imagenes/loading.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		imagen = new JLabel((new ImageIcon(imagentemp)));
			//"is\\SimTraffic\\Vista\\Imagenes\\loading.gif" )));
		setLayout(new FlowLayout());
		add(new JLabel(mensaje));//"Cargando mapa..."));
		add(imagen);
		pack();
	}
	
	/**
	 * Método que permite terminar la ejecución del thread
	 */
	public synchronized void terminar() {
		setVisible(false);
	}


	/* (non-Javadoc)
	 * Este método se ejecuta al llamar al thread.<p>
	 * Se encarga de mostrar la ventana. Como la ventana esta visible, el thread
	 * continua en ejecución hasta que esta se destruye (o hace invisible).
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		this.setVisible(true);
	}

}
