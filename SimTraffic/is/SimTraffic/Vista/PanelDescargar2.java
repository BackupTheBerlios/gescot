package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HCargarMapa;
import is.SimTraffic.Herramientas.HDescargarMapa;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
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
	
	private ClassLoader cl;

	private JButton jbMadrid; 
	private JButton jbLondres; 
	private JButton jbParis; 
	private JButton jbHamburgo;
	private JButton jbBarcelona; 
	private JButton jbCuenca; 
	private JButton jbLisboa; //$NON-NLS-1$


	IControlador controlador;
	PanelMapa panel;
	


	public PanelDescargar2(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel=panel;
		
//		HDescargarMapa hdc = new HDescargarMapa(controlador,panel,new File("hola"));
		
		this.setTitle(Messages.getString("PanelDescargar2.0")); //$NON-NLS-1$
		this.setResizable(false);
		
		this.cl = this.getClass().getClassLoader();

		URL ur = cl.getResource("is/SimTraffic/Vista/Imagenes/" + Messages.getString("PanelDescargar2.1")); //$NON-NLS-1$ //$NON-NLS-2$
		
		jbLisboa = new JButton(new ImageIcon(cl
					.getResource("is/SimTraffic/Vista/Imagenes/" + Messages.getString("PanelDescargar2.1")))); //$NON-NLS-1$ //$NON-NLS-2$
		jbHamburgo = new JButton(new ImageIcon(cl
				.getResource("is/SimTraffic/Vista/Imagenes/" + Messages.getString("PanelDescargar2.2")))); //$NON-NLS-1$ //$NON-NLS-2$
		jbMadrid = new JButton(new ImageIcon(cl
				.getResource("is/SimTraffic/Vista/Imagenes/" + Messages.getString("PanelDescargar2.3")))); //$NON-NLS-1$ //$NON-NLS-2$
		jbLondres = new JButton(new ImageIcon(cl
				.getResource("is/SimTraffic/Vista/Imagenes/" + Messages.getString("PanelDescargar2.4")))); //$NON-NLS-1$ //$NON-NLS-2$
		jbParis = new JButton(new ImageIcon(cl
				.getResource("is/SimTraffic/Vista/Imagenes/" + Messages.getString("PanelDescargar2.5")))); //$NON-NLS-1$ //$NON-NLS-2$
		jbBarcelona = new JButton(new ImageIcon(cl
				.getResource("is/SimTraffic/Vista/Imagenes/" + Messages.getString("PanelDescargar2.6")))); //$NON-NLS-1$ //$NON-NLS-2$
		jbCuenca = new JButton(new ImageIcon(cl
				.getResource("is/SimTraffic/Vista/Imagenes/" + Messages.getString("PanelDescargar2.7")))); //$NON-NLS-1$ //$NON-NLS-2$
		
		jbLisboa.setEnabled(false);
		jbHamburgo.setEnabled(false);
		jbBarcelona.setEnabled(false);

		
		
		
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
		jpPrincipal.add(jbCuenca);
		jpPrincipal.add((JButton) vBotonesAux.get(7));
		jpPrincipal.add(jbLisboa);
		
		jbMadrid.setToolTipText(Messages.getString("PanelDescargar2.8")); //$NON-NLS-1$
		jbLondres.setToolTipText(Messages.getString("PanelDescargar2.9")); //$NON-NLS-1$
		jbParis.setToolTipText(Messages.getString("PanelDescargar2.10")); //$NON-NLS-1$
		jbHamburgo.setToolTipText(Messages.getString("PanelDescargar2.11")); //$NON-NLS-1$
		jbBarcelona.setToolTipText(Messages.getString("PanelDescargar2.12")); //$NON-NLS-1$
		jbCuenca.setToolTipText(Messages.getString("PanelDescargar2.13")); //$NON-NLS-1$
		jbLisboa.setToolTipText(Messages.getString("PanelDescargar2.14")); //$NON-NLS-1$


		jbMadrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				llamarHerramienta("PanelDescargar2.15"); //$NON-NLS-1$
				
			}
		});

		jbLondres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarHerramienta("PanelDescargar2.16"); //$NON-NLS-1$
			}
		});
		jbParis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarHerramienta("PanelDescargar2.17"); //$NON-NLS-1$
			}
		});
		jbHamburgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarHerramienta("PanelDescargar2.18"); //$NON-NLS-1$
			}
		});
		jbBarcelona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarHerramienta("PanelDescargar2.19"); //$NON-NLS-1$
			}
		});
		jbCuenca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarHerramienta("PanelDescargar2.20"); //$NON-NLS-1$
			}
		});
		jbLisboa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarHerramienta("PanelDescargar2.21"); //$NON-NLS-1$
			}
		});


		this.setLocation(80, 125);
		this.setSize(750, 250);
		this.setVisible(true);

	}

	private void llamarHerramienta(String s) {
		File f = new File(cl.getResource("is/SimTraffic/Ejemplos_Ciudades/").getFile().replaceAll("%20", " ")+Messages.getString(s)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		controlador.herramienta(new HDescargarMapa(controlador,panel,f));
		this.setVisible(false);
	}

}
