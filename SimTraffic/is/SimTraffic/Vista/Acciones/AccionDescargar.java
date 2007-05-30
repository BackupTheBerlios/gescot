package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.IModelo;
import is.SimTraffic.Messages;


import is.SimTraffic.Herramientas.HGuardarMapa;
import is.SimTraffic.Mapa.Mapa;
import is.SimTraffic.Vista.PanelDescargar2;
import is.SimTraffic.Vista.PanelEsperaCargando;
import is.SimTraffic.Vista.PanelMapa;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


import javax.swing.JFrame;
import javax.swing.JOptionPane;




/**
 * Clase que extiende ActionListener para permitir a los botones o elementos del
 * menú descargar un nuevo fichero de mapa desde un servidor OSM.
 * <p>
 * <p>
 * Esta clase tomara el controlador y el panel con la representación del mapa
 * como parámetros en su construcutr y lo almacenará para que cuando se ejecute
 * la acción poder llamar a la herramienta HCargarMapa encargada de
 * efectivamente cargar el mapa.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class AccionDescargar implements ActionListener{
	

	/**
	 * 
	 */
	IControlador controlador;

	/**
	 * 
	 */
	PanelMapa panel;


	protected File file;

	/**
	 * Variable del mapa cargado, definida como protected porque es utilizada
	 * por el thread que carga el mapa.
	 */
	protected Mapa mapaNuevo;

	/**
	 * Variable del modelo, utilizada por el thread que carga el mapa.
	 */
	protected IModelo modelo;

	/**
	 * Panel para mostrar el mensaje de cargando...
	 * <p>
	 * Se utiliza una variable estatica para que nos se tengan que cargar sus
	 * componentes cada vez.
	 */
	public static PanelEsperaCargando p = new PanelEsperaCargando(Messages.getString("AccionDescargar.0"), Messages.getString("AccionDescargar.1")); //$NON-NLS-1$ //$NON-NLS-2$



	public AccionDescargar(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel=panel;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (controlador.cambiosEnMapa()) {
			// Preguntar si desea guardar el mapa actual: Si el usuario dice que
			// sí,
			// crear aquí una herramienta de guardar mapa (Faltaría crear
			// ventana para ello).
			Object[] options = { Messages.getString("AccionDescargar.2"), Messages.getString("AccionDescargar.3"), Messages.getString("AccionDescargar.4") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			int n = JOptionPane.showOptionDialog(null,
					Messages.getString("AccionDescargar.5"), Messages.getString("AccionDescargar.6"), //$NON-NLS-1$ //$NON-NLS-2$
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[2]);
			if (n==0) {
				// TODO guardar mapa
				controlador.herramienta(new HGuardarMapa());
				JFrame ventanaDescargar = new PanelDescargar2(controlador,panel);
				//controlador.herramienta(new HCargarMapa(controlador,panel));
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
			else if (n==1) {
				JFrame ventanaDescargar = new PanelDescargar2(controlador,panel);
				//controlador.herramienta(new HCargarMapa(controlador,panel));
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
		}
		else {
			JFrame ventanaDescargar = new PanelDescargar2(controlador,panel);
			panel.recrear();
			panel.recrearMapa();
			panel.repaint();
			
		}
		
		



		
		/*if (controlador != null) {
			controlador.herramienta(new HDescargarMapa(controlador, panel));
			panel.repaint();
			panel.recrear();
		}*/
	}

}
