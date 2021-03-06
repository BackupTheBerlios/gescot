package is.SimTraffic.Vista.PanelesSimulacion;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
import is.SimTraffic.Herramientas.HComenzar;
import is.SimTraffic.Simulacion.ParametrosSimulacion;
import is.SimTraffic.Simulacion.Simulacion;
import is.SimTraffic.Vista.Ventana;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * 
 * @author Grupo ISTrafico
 * 
 */
public class PanelVehiculos extends JFrame {

	private static final long serialVersionUID = 1L;

	private IControlador controlador;

	private JScrollBar vehiculos[] = new JScrollBar[3];

	private ParametrosSimulacion param;

	private JComboBox porcentajes[] = new JComboBox[6];
	
	private JSpinner horas;
	
	private JSpinner minutos;
	
	private Ventana ventana;

	public PanelVehiculos(Ventana ventana, IControlador controlador, ParametrosSimulacion param) {
		this.controlador = controlador;
		this.param = param;
		this.ventana = ventana;
		this.setResizable(false);
		this.setTitle(Messages.getString("PanelVehiculos.0")); //$NON-NLS-1$

		String[] cantidades = { Messages.getString("PanelVehiculos.1"), Messages.getString("PanelVehiculos.2"), Messages.getString("PanelVehiculos.3"), Messages.getString("PanelVehiculos.4"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				Messages.getString("PanelVehiculos.5"), Messages.getString("PanelVehiculos.6") }; //$NON-NLS-1$ //$NON-NLS-2$
		for (int i = 0; i < 6; i++)
			porcentajes[i] = new JComboBox(cantidades);

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		JLabel etiquetaTitulo = new JLabel(Messages.getString("PanelVehiculos.7")); //$NON-NLS-1$
		etiquetaTitulo.setFont(new Font(null, Font.BOLD, 15));
		panelTitulo.add(etiquetaTitulo);

		JPanel panelCantidades = panelCantidades();

		JPanel panelTurismo = crearOpcionesVehiculo(porcentajes[0],
				Messages.getString("PanelVehiculos.8"), Messages.getString("PanelVehiculos.9"), 4); //$NON-NLS-1$ //$NON-NLS-2$

		JPanel panelTaxi = crearOpcionesVehiculo(porcentajes[1], Messages.getString("PanelVehiculos.10"), //$NON-NLS-1$
				Messages.getString("PanelVehiculos.11"), 0); //$NON-NLS-1$

		JPanel panelCamion = crearOpcionesVehiculo(porcentajes[2],
				Messages.getString("PanelVehiculos.12"), Messages.getString("PanelVehiculos.13"), 0); //$NON-NLS-1$ //$NON-NLS-2$

		JPanel panelBus = crearOpcionesVehiculo(porcentajes[3], Messages.getString("PanelVehiculos.14"), //$NON-NLS-1$
				Messages.getString("PanelVehiculos.15"), 0); //$NON-NLS-1$
/*
		JPanel panelMoto = crearOpcionesVehiculo(porcentajes[4], "moto.jpg",
				"Moto     ", 0);
*/
		JPanel panelAmbulancia = crearOpcionesVehiculo(porcentajes[5],
				Messages.getString("PanelVehiculos.16"), Messages.getString("PanelVehiculos.17"), 0); //$NON-NLS-1$ //$NON-NLS-2$

		if (!controlador.mapaTieneHospital())
			porcentajes[5].setEnabled(false);
		else
			porcentajes[5].setEnabled(true);

		if (!controlador.mapaTieneLineasBus())
			porcentajes[3].setEnabled(false);
		else
			porcentajes[3].setEnabled(true);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));
		JButton botonAceptar = new JButton(Messages.getString("PanelVehiculos.18")); //$NON-NLS-1$
		botonAceptar.addActionListener(new accionAceptar());
		JButton botonCancelar = new JButton(Messages.getString("PanelVehiculos.19")); //$NON-NLS-1$
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelVehiculos.this.dispose();
			}
		});

		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);

		int cont = 0;

		gbc.gridx = 0;
		gbc.gridy = cont;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		this.add(panelTitulo, gbc);
		
		JPanel panelHora = new JPanel(new GridLayout(1,3));
		JLabel labelInfoHora = new JLabel(Messages.getString("PanelVehiculos.20")); //$NON-NLS-1$
		JPanel panelValorHoras = new JPanel();
		JPanel panelValorMins = new JPanel();
		
		horas = new JSpinner(new SpinnerNumberModel(17,0,23,1));
		minutos = new JSpinner(new SpinnerNumberModel(30,0,59,1));
		JLabel horasEtiq = new JLabel(Messages.getString("PanelVehiculos.21")); //$NON-NLS-1$
		JLabel minsEtiq = new JLabel(Messages.getString("PanelVehiculos.22")); //$NON-NLS-1$

		panelValorHoras.add(horasEtiq);
		panelValorHoras.add(horas);
		
		panelValorMins.add(minsEtiq);
		panelValorMins.add(minutos);
		
		panelHora.add(labelInfoHora);
		panelHora.add(panelValorHoras);		
		panelHora.add(panelValorMins);
		
		gbc.gridy = ++cont;
		cont += 2;
		gbc.gridheight = 3;
		this.add(panelHora, gbc);
		
		gbc.gridy = ++cont;
		cont += 2;
		gbc.gridheight = 3;
		this.add(panelCantidades, gbc);

		gbc.gridy = ++cont;
		gbc.gridheight = 1;
		this.add(panelTurismo, gbc);

		gbc.gridy = ++cont;
		this.add(panelTaxi, gbc);

		gbc.gridy = ++cont;
		this.add(panelCamion, gbc);

		gbc.gridy = ++cont;
		this.add(panelBus, gbc);
/*
		gbc.gridy = ++cont;
		this.add(panelMoto, gbc);
*/
		gbc.gridy = ++cont;
		this.add(panelAmbulancia, gbc);

		gbc.gridy = ++cont;
		gbc.weighty = 0.2;
		this.add(panelBotones, gbc);
	}

	private void addPanel(JPanel p, JComponent comp, GridBagLayout gb,
			GridBagConstraints c) {
		gb.setConstraints(comp, c);
		p.add(comp);
	}

	private JPanel crearOpcionesVehiculo(JComboBox combo, String icono,
			String nombre, int inicial) {

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 19, 5));
		JPanel panelaux1 = new JPanel();
		panelaux1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		ClassLoader cl = this.getClass().getClassLoader();
		JLabel iconoTurismo = new JLabel((new ImageIcon(cl
				.getResource(Messages.getString("PanelVehiculos.23") + icono)))); //$NON-NLS-1$
		JLabel etiquetaTurismo = new JLabel(nombre);
		panelaux1.add(iconoTurismo);
		panelaux1.add(etiquetaTurismo);
		JPanel panelaux2 = new JPanel();
		panelaux2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		combo.setSelectedIndex(inicial);
		panelaux2.add(combo);
		panel.add(panelaux1);
		panel.add(panelaux2);
		return panel;
	}

	private JPanel panelCantidades() {
		int min = 20;
		int max = Simulacion.maxVehiculos;

		JPanel panelCantidades = new JPanel();
		panelCantidades.setSize(300, 100);
		GridBagLayout bg = new GridBagLayout();
		panelCantidades.setLayout(bg);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		JTextField text1 = new JTextField(2);
		text1.setEditable(false);
		c.weightx = 0.3;
		addPanel(panelCantidades, new JLabel(Messages.getString("PanelVehiculos.25")), bg, c); //$NON-NLS-1$
		addPanel(panelCantidades, text1, bg, c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[0] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		vehiculos[0].setValue(100);
		text1.setText(Messages.getString("PanelVehiculos.26") + vehiculos[0].getValue()); //$NON-NLS-1$
		vehiculos[0].addAdjustmentListener(new ajuste(text1));
		addPanel(panelCantidades, vehiculos[0], bg, c);

		c.gridwidth = GridBagConstraints.BOTH;
		JTextField text2 = new JTextField(2);
		text2.setEditable(false);
		c.weightx = 0.3;
		addPanel(panelCantidades, new JLabel(Messages.getString("PanelVehiculos.27")), bg, c); //$NON-NLS-1$
		addPanel(panelCantidades, text2, bg, c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[1] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		vehiculos[1].setValue(100);
		text2.setText(Messages.getString("PanelVehiculos.28") + vehiculos[1].getValue()); //$NON-NLS-1$
		vehiculos[1].addAdjustmentListener(new ajuste(text2));
		addPanel(panelCantidades, vehiculos[1], bg, c);

		c.gridwidth = GridBagConstraints.BOTH;
		JTextField text3 = new JTextField(2);
		text3.setEditable(false);
		c.weightx = 0.3;
		addPanel(panelCantidades, new JLabel(Messages.getString("PanelVehiculos.29")), bg, c); //$NON-NLS-1$
		addPanel(panelCantidades, text3, bg, c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[2] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		vehiculos[2].setValue(100);
		text3.setText(Messages.getString("PanelVehiculos.30") + vehiculos[2].getValue()); //$NON-NLS-1$
		vehiculos[2].addAdjustmentListener(new ajuste(text3));
		addPanel(panelCantidades, vehiculos[2], bg, c);

		return panelCantidades;
	}

	private class ajuste implements AdjustmentListener {
		JTextField text;

		public ajuste(JTextField text) {
			this.text = text;
		}

		public void adjustmentValueChanged(AdjustmentEvent ae) {
			int value = ae.getValue();
			String st = Integer.toString(value);
			text.setText(st);
		}
	}

	private class accionAceptar implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			//Se guardan la hora y los minutos introducidos por el usuario.
			int hr = ((Integer)horas.getValue()).intValue();
			int mn = ((Integer)minutos.getValue()).intValue();
			int segundos = 0; //No se piden al usuario, no tiene demasiada importancia.
			int[] totales = new int[3];
			for (int i = 0; i < 3; i++) {
				try {
					totales[i] = vehiculos[i].getValue();
				} catch (Exception e) {
					totales[i] = 100;
				}
			}
			
			
			
			param.setNumVehiculos(totales);
			int[] por = new int[6];
			int total = 0;
			
			// coches y taxis
			for (int i = 0; i < 2; i++) {
				por[i] = porcentajes[i].getSelectedIndex() * 20;
				total = total + por[i];
			}
			
			// cambiones
			por[2] = porcentajes[2].getSelectedIndex() * 12;
			total = total + por[2];
	
			
			// autobuses
			por[3] = porcentajes[3].getSelectedIndex() * 8;
			total = total + por[3];
			
			
			// ambulacias
			por[5] = porcentajes[5].getSelectedIndex() * 2;
			total = total + por[5];
			
			for (int i = 0; i < 6; i++) {
				por[i] = (100 * por[i]) / total;
			}
			
			param.setPorcentajeTipo(por);
			controlador.herramienta(new HComenzar(ventana, hr, mn, segundos));
			PanelVehiculos.this.setVisible(false);
		}

	}
}
