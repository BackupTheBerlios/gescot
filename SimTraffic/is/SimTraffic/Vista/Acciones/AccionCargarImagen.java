package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Vista.PanelMapa;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

/**
 * Método que implementa ActionListener para la acción de cargar una imagen de fondo.<p>
 * 
 * 
 * @author Grupo ISTrafico
 *
 */
public class AccionCargarImagen implements ActionListener {
	//private IControlador controlador;

	private PanelMapa panel;

	public AccionCargarImagen(IControlador controlador, PanelMapa panel) {
		//this.controlador = controlador;
		this.panel = panel;
	}

	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();

			Image imagen = Toolkit.getDefaultToolkit().getImage(
					file.getAbsolutePath());
			long time = System.currentTimeMillis();
			while (imagen.getWidth(null) < 0
					&& System.currentTimeMillis() - time < 2000) {
			}
			if (imagen.getWidth(null) >= 0) {
				panel.getRepresentacion().addImage(imagen, new Posicion(panel.getLat0(), panel.getLon0()));
				//panel.getRepresentacion().setImagenes(new ArrayList<Image>());
				panel.getModelo().getMapa().setCambios_en_mapa(true);
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
		}
	}

}
