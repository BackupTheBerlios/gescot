package is.SimTraffic.Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HDescargarMapa;


public class PanelDescargar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7050161878828636663L;

	private JPanel jpPrincipal;

	private BorderLayout blDistribucion = new BorderLayout();

	private JPanel jpSur;

	private GridLayout glSur = new GridLayout(1, 5);

	private JPanel jpCentral;

	private GridLayout glCentral = new GridLayout(3, 4);

	private JLabel jlMinLat = new JLabel("min lat   ", JLabel.RIGHT);

	private JTextField jtfMinLat = new JTextField();

	private JLabel jlMaxLat = new JLabel("max lat   ", JLabel.RIGHT);

	private JTextField jtfMaxLat = new JTextField();

	private JLabel jlMinLon = new JLabel("min lon   ", JLabel.RIGHT);

	private JTextField jtfMinLon = new JTextField();

	private JLabel jlMaxLon = new JLabel("max lon   ", JLabel.RIGHT);

	private JTextField jtfMaxLon = new JTextField();

	private JLabel jlUrl = new JLabel("URL   ", JLabel.RIGHT);

	private JTextField jtfUrl = new JTextField();

	private JButton jbAceptar = new JButton("Aceptar");

	private JButton jbCancelar = new JButton("Cancelar");

	IControlador controlador;

	public PanelDescargar(IControlador controlador) {
		this.controlador = controlador;

		this.setTitle("Eligir area para descargar");

		Vector<JButton> vBotonesAux = new Vector<JButton>(8);
		for (int i = 0; i < 15; i++) {
			JButton jbAux = new JButton();
			jbAux.setEnabled(false);
			jbAux.setVisible(false);
			vBotonesAux.add(jbAux);
		}

		jpPrincipal = (JPanel) this.getContentPane();
		jpPrincipal.setLayout(blDistribucion);

		jpSur = new JPanel(glSur);
		jpSur.add((JButton) vBotonesAux.get(10));
		jpSur.add(jbAceptar);
		jpSur.add((JButton) vBotonesAux.get(0));
		jpSur.add(jbCancelar);
		jpSur.add((JButton) vBotonesAux.get(11));

		jpCentral = new JPanel(glCentral);

		jpCentral.add(jlMinLat);
		jpCentral.add(jtfMinLat);
		jpCentral.add(jlMinLon);
		jpCentral.add(jtfMinLon);
		jpCentral.add(jlMaxLat);
		jpCentral.add(jtfMaxLat);
		jpCentral.add(jlMaxLon);
		jpCentral.add(jtfMaxLon);
		jpCentral.add((JButton) vBotonesAux.get(3));
		jpCentral.add(jlUrl);
		jpCentral.add(jtfUrl);

		jbAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptarDatos();
			}
		});

		jbCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarFrame();
			}
		});

		jpPrincipal.add(jpCentral, BorderLayout.CENTER);
		jpPrincipal.add(jpSur, BorderLayout.SOUTH);

		this.setLocation(80, 125);
		this.setSize(450, 150);
		this.setVisible(true);

	}

	private void aceptarDatos() {
		try {
			double latMin = Double.parseDouble(jtfMinLat.getText());
			double lonMin = Double.parseDouble(jtfMinLon.getText());
			double latMax = Double.parseDouble(jtfMaxLat.getText());
			double lonMax = Double.parseDouble(jtfMaxLon.getText());
			double lat = (latMax + latMin) / 2;
			double lon = (lonMax + lonMin) / 2;
			// convert to mercator (for calculation of zoom only)
			latMin = Math.log(Math.tan(Math.PI / 4.0 + latMin / 180.0 * Math.PI
					/ 2.0))
					* 180.0 / Math.PI;
			latMax = Math.log(Math.tan(Math.PI / 4.0 + latMax / 180.0 * Math.PI
					/ 2.0))
					* 180.0 / Math.PI;
			double size = Math.max(Math.abs(latMax - latMin), Math.abs(lonMax
					- lonMin));
			int zoom = 0;
			while (zoom <= 20) {
				if (size >= 180)
					break;
				size *= 2;
				zoom++;
			}
			jtfUrl.setText("http://www.openstreetmap.org/index.html?lat=" + lat
					+ "&lon=" + lon + "&zoom=" + zoom);
			llamarHerramienta(latMin,lonMin,latMax,lonMax);
		} catch (NumberFormatException x) {
			jtfUrl.setText("");
		}
		jtfUrl.setCaretPosition(0);

	}

	private void llamarHerramienta(double minlat, double minlon, double maxlat, double maxlon) {
		HDescargarMapa hDescargar = new HDescargarMapa(controlador,minlat, minlon, maxlat, maxlon);

	}

	private void cerrarFrame() {
		this.setVisible(false);
	}

}
