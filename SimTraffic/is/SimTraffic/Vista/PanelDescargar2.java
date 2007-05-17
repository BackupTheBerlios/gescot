package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HDescargarMapa;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelDescargar2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7050161878828636663L;

	private JPanel jpPrincipal;

	private BorderLayout blDistribucion = new BorderLayout();

	private GridLayout glDistribucion = new GridLayout(2, 6);
	
	private JButton jbMadrid = new JButton(new ImageIcon("is/SimTraffic/Vista/Imagenes/Madrid.png"));
	private JButton jbLondres = new JButton(new ImageIcon("is/SimTraffic/Vista/Imagenes/London.png"));
	private JButton jbParis = new JButton(new ImageIcon("is/SimTraffic/Vista/Imagenes/Paris.png"));
	private JButton jbHamburgo = new JButton(new ImageIcon("is/SimTraffic/Vista/Imagenes/Hamburgo.png"));
	private JButton jbBarcelona = new JButton(new ImageIcon("is/SimTraffic/Vista/Imagenes/Barcelona.png"));
	private JButton jbBerlin = new JButton(new ImageIcon("is/SimTraffic/Vista/Imagenes/Berlin.png"));
	private JButton jbLisboa = new JButton(new ImageIcon("is/SimTraffic/Vista/Imagenes/Lisboa.png"));


	IControlador controlador;
	PanelMapa panel;

	public PanelDescargar2(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel=panel;
		
		this.setTitle("Eligir ejemplo a cargar");

		Vector<JButton> vBotonesAux = new Vector<JButton>(8);
		for (int i = 0; i < 15; i++) {
			JButton jbAux = new JButton();
			jbAux.setEnabled(false);
			jbAux.setVisible(false);
			vBotonesAux.add(jbAux);
		}

		jpPrincipal = (JPanel) this.getContentPane();
		jpPrincipal.setLayout(glDistribucion);

		jpPrincipal.add((JButton) vBotonesAux.get(0));
		jpPrincipal.add(jbMadrid);
		jpPrincipal.add((JButton) vBotonesAux.get(1));
		jpPrincipal.add(jbLondres);
		jpPrincipal.add((JButton) vBotonesAux.get(2));
		jpPrincipal.add(jbParis);
		jpPrincipal.add((JButton) vBotonesAux.get(3));
		jpPrincipal.add(jbHamburgo);
		jpPrincipal.add((JButton) vBotonesAux.get(5));
		jpPrincipal.add(jbBarcelona);
		jpPrincipal.add((JButton) vBotonesAux.get(6));
		jpPrincipal.add(jbBerlin);
		jpPrincipal.add((JButton) vBotonesAux.get(7));
		jpPrincipal.add(jbLisboa);
		
		jbMadrid.setToolTipText("Madrid");
		jbLondres.setToolTipText("Londres");
		jbParis.setToolTipText("Paris");
		jbHamburgo.setToolTipText("Barcelona");
		jbHamburgo.setToolTipText("Hamburgo");
		jbBerlin.setToolTipText("Berlin");
		jbLisboa.setToolTipText("Lisboa");


		jbMadrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File("is/SimTraffic/Ejemplos_ciudades/madrid_centro.osm");
				llamarHerramienta(f);
				
			}
		});

		jbLondres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(".//is/SimTraffic/Ejemplos_ciudades/London_centro_1.osm");
				llamarHerramienta(f);
			}
		});
		jbParis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(".//is/SimTraffic/Ejemplos_ciudades/London_centro_1.osm");
				llamarHerramienta(f);
			}
		});
		jbHamburgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(".//is/SimTraffic/Ejemplos_ciudades/London_centro_1.osm");
				llamarHerramienta(f);
			}
		});
		jbBarcelona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(".//is/SimTraffic/Ejemplos_ciudades/London_centro_1.osm");
				llamarHerramienta(f);
			}
		});
		jbBerlin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(".//is/SimTraffic/Ejemplos_ciudades/London_centro_1.osm");
				llamarHerramienta(f);
			}
		});
		jbLisboa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(".//is/SimTraffic/Ejemplos_ciudades/London_centro_1.osm");
				llamarHerramienta(f);
			}
		});


		this.setLocation(80, 125);
		this.setSize(750, 250);
		this.setVisible(true);

	}



	
	private void llamarHerramienta(File f) {
		controlador.herramienta(new HDescargarMapa(controlador,panel,f));
		this.setVisible(false);
	}

}
