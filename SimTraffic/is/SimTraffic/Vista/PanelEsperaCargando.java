package is.SimTraffic.Vista;

import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PanelEsperaCargando extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4426934915163670765L;

	private JLabel imagen;
	
	public PanelEsperaCargando() {
		setLocation(200,200);
		setResizable(false);
		setTitle("Cargando...");
		
		imagen = new JLabel((new ImageIcon(
			"is\\SimTraffic\\Vista\\Imagenes\\loading.gif" )));
		setLayout(new FlowLayout());
		add(new JLabel("Cargando mapa..."));
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
