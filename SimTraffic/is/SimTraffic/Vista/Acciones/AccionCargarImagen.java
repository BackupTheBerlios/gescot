package is.SimTraffic.Vista.Acciones;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Utils.ChequeoInputVentanas;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.Representaciones.Representacion;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
		if(JOptionPane.NO_OPTION==JOptionPane.showConfirmDialog(null,
				Messages.getString("AccionCargarImagen.0") + //$NON-NLS-1$
				Messages.getString("AccionCargarImagen.1"), //$NON-NLS-1$
				Messages.getString("AccionCargarImagen.2"), //$NON-NLS-1$
				JOptionPane.YES_NO_OPTION)){
			
				ChequeoInputVentanas chequeo=new ChequeoInputVentanas();
				String aux;
				aux=JOptionPane.showInputDialog(Messages.getString("AccionCargarImagen.3")); //$NON-NLS-1$
				 if(aux!=null&&chequeo.esLatitud(aux)){
				  double lat = Double.parseDouble(aux);
           		  aux=JOptionPane.showInputDialog(Messages.getString("AccionCargarImagen.4"));		 //$NON-NLS-1$
           		  if(aux!=null&&chequeo.esLongitud(aux)){
           		   double lon = Double.parseDouble(aux);
           		   panel.centrarEnPosicion(lat, lon);
           		   JOptionPane.showMessageDialog(null,Messages.getString("AccionCargarImagen.5")+ //$NON-NLS-1$
						Messages.getString("AccionCargarImagen.6")+Representacion.pasarAGrados(lat)+" ; " + //$NON-NLS-1$ //$NON-NLS-2$
						Messages.getString("AccionCargarImagen.8") + Representacion.pasarAGrados(lon)); //$NON-NLS-1$
           		  }else return;
				 }else return;		
		}
		
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
				//panel.getModelo().getMapa().setCambios_en_mapa(true);
				this.esperarCargaImagen(imagen);
				panel.recrear();
				panel.recrearMapa();
				panel.repaint();
			}
		}
	}
	private void esperarCargaImagen(Image imagen){
		MediaTracker tracker = new MediaTracker(panel);
		tracker.addImage(imagen,1);
		  try{
		      tracker.waitForID(1);
		    } catch( InterruptedException exception ) {
		      System.out.println( exception );
		      }
	}

}
