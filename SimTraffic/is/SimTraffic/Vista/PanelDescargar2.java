package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
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
	
	private JButton jbMadrid = new JButton(new ImageIcon(Messages.getString("PanelDescargar2.12"))); //$NON-NLS-1$
	private JButton jbLondres = new JButton(new ImageIcon(Messages.getString("PanelDescargar2.11"))); //$NON-NLS-1$
	private JButton jbParis = new JButton(new ImageIcon(Messages.getString("PanelDescargar2.10"))); //$NON-NLS-1$
	private JButton jbHamburgo = new JButton(new ImageIcon(Messages.getString("PanelDescargar2.9"))); //$NON-NLS-1$
	private JButton jbBarcelona = new JButton(new ImageIcon(Messages.getString("PanelDescargar2.8"))); //$NON-NLS-1$
	private JButton jbBerlin = new JButton(new ImageIcon(Messages.getString("PanelDescargar2.7"))); //$NON-NLS-1$
	private JButton jbLisboa = new JButton(new ImageIcon(Messages.getString("PanelDescargar2.6"))); //$NON-NLS-1$


	IControlador controlador;
	PanelMapa panel;

	public PanelDescargar2(IControlador controlador, PanelMapa panel) {
		this.controlador = controlador;
		this.panel=panel;
		
		this.setTitle(Messages.getString("PanelDescargar2.13")); //$NON-NLS-1$

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
		
		jbMadrid.setToolTipText(Messages.getString("PanelDescargar2.5")); //$NON-NLS-1$
		jbLondres.setToolTipText(Messages.getString("PanelDescargar2.4")); //$NON-NLS-1$
		jbParis.setToolTipText(Messages.getString("PanelDescargar2.3")); //$NON-NLS-1$
		jbHamburgo.setToolTipText(Messages.getString("PanelDescargar2.2")); //$NON-NLS-1$
		jbHamburgo.setToolTipText(Messages.getString("PanelDescargar2.1")); //$NON-NLS-1$
		jbBerlin.setToolTipText(Messages.getString("PanelDescargar2.0")); //$NON-NLS-1$
		jbLisboa.setToolTipText(Messages.getString("PanelDescargar2.21")); //$NON-NLS-1$


		jbMadrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(Messages.getString("PanelDescargar2.14")); //$NON-NLS-1$
				llamarHerramienta(f);
				
			}
		});

		jbLondres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(Messages.getString("PanelDescargar2.15")); //$NON-NLS-1$
				llamarHerramienta(f);
			}
		});
		jbParis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				File f = new File(Messages.getString("PanelDescargar2.16")); //$NON-NLS-1$

				llamarHerramienta(f);
			}
		});
		jbHamburgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(Messages.getString("PanelDescargar2.17")); //$NON-NLS-1$
				llamarHerramienta(f);
			}
		});
		jbBarcelona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(Messages.getString("PanelDescargar2.18")); //$NON-NLS-1$
				llamarHerramienta(f);
			}
		});
		jbBerlin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(Messages.getString("PanelDescargar2.19")); //$NON-NLS-1$
				llamarHerramienta(f);
			}
		});
		jbLisboa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File f = new File(Messages.getString("PanelDescargar2.20")); //$NON-NLS-1$
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
