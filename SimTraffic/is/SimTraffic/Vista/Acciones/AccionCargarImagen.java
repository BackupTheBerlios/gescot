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

public class AccionCargarImagen implements ActionListener {
	private IControlador controlador;

	private PanelMapa panel;

	public AccionCargarImagen(IControlador controlador, PanelMapa panel) {

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
				panel.getRepresentacion().addImage(imagen, new Posicion(0, 0));
			} else {
			}
		}
		panel.recrearMapa();
	}

}
