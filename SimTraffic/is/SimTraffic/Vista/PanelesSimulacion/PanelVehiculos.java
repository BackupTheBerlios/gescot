package is.SimTraffic.Vista.PanelesSimulacion;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HComenzar;
import is.SimTraffic.Simulacion.ParametrosSimulacion;
import is.SimTraffic.Simulacion.Simulacion;

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
import javax.swing.JTextField;

/**
 * 
 * @author Grupo ISTrafico
 * 
 */
public class PanelVehiculos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IControlador controlador;

	private JScrollBar vehiculos[] = new JScrollBar[3];

	private ParametrosSimulacion param;

	private JComboBox porcentajes[] = new JComboBox[6];

	public PanelVehiculos(IControlador controlador, ParametrosSimulacion param) {
		this.controlador = controlador;
		this.param = param;
		this.setResizable(false);
		this.setTitle("Tipos de Vehículos");

		String[] cantidades = { "No aparece", "Muy pocos", "Algunos", "Normal",
				"Bastantes", "Muchos" };
		for (int i = 0; i < 6; i++)
			porcentajes[i] = new JComboBox(cantidades);

		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		JLabel etiquetaTitulo = new JLabel("CANTIDAD DE VEHÍCULOS");
		etiquetaTitulo.setFont(new Font(null, Font.BOLD, 15));
		panelTitulo.add(etiquetaTitulo);

		JPanel panelCantidades = panelCantidades();

		JPanel panelTurismo = crearOpcionesVehiculo(porcentajes[0],
				"Turismo.jpg", "Turismo  ", 4);

		JPanel panelTaxi = crearOpcionesVehiculo(porcentajes[1], "taxi.jpg",
				"Taxi      ", 0);

		JPanel panelCamion = crearOpcionesVehiculo(porcentajes[2],
				"camion.jpg", "Camion    ", 0);

		JPanel panelBus = crearOpcionesVehiculo(porcentajes[3], "autobus.jpg",
				"Bus         ", 0);

		JPanel panelMoto = crearOpcionesVehiculo(porcentajes[4], "moto.jpg",
				"Moto     ", 0);

		JPanel panelAmbulancia = crearOpcionesVehiculo(porcentajes[5],
				"ambulancia.jpg", "Ambulancia", 0);

		if (!controlador.mapaTieneHospital())
			porcentajes[5].setEnabled(false);
		else
			porcentajes[5].setEnabled(true);

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new accionAceptar());
		JButton botonCancelar = new JButton("Cancelar");
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

		gbc.gridy = ++cont;
		this.add(panelMoto, gbc);

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
		JLabel iconoTurismo = new JLabel((new ImageIcon(
				"is\\SimTraffic\\Vista\\Imagenes\\" + icono)));
		JLabel etiquetaTurismo = new JLabel(nombre);
		panelaux1.add(iconoTurismo);
		panelaux1.add(etiquetaTurismo);
		JPanel panelaux2 = new JPanel();
		panelaux2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
		combo.setSelectedIndex(inicial);
		JButton botonPropiedadesTurismo = new JButton("Propiedades");
		panelaux2.add(combo);
		panelaux2.add(botonPropiedadesTurismo);
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
		addPanel(panelCantidades, new JLabel("Mañana"), bg, c);
		addPanel(panelCantidades, text1, bg, c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[0] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		vehiculos[0].setValue(100);
		text1.setText("" + vehiculos[0].getValue());
		vehiculos[0].addAdjustmentListener(new ajuste(text1));
		addPanel(panelCantidades, vehiculos[0], bg, c);

		c.gridwidth = GridBagConstraints.BOTH;
		JTextField text2 = new JTextField(2);
		text2.setEditable(false);
		c.weightx = 0.3;
		addPanel(panelCantidades, new JLabel("Tarde"), bg, c);
		addPanel(panelCantidades, text2, bg, c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[1] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		vehiculos[1].setValue(100);
		text2.setText("" + vehiculos[1].getValue());
		vehiculos[1].addAdjustmentListener(new ajuste(text2));
		addPanel(panelCantidades, vehiculos[1], bg, c);

		c.gridwidth = GridBagConstraints.BOTH;
		JTextField text3 = new JTextField(2);
		text3.setEditable(false);
		c.weightx = 0.3;
		addPanel(panelCantidades, new JLabel("Noche"), bg, c);
		addPanel(panelCantidades, text3, bg, c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[2] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		vehiculos[2].setValue(100);
		text3.setText("" + vehiculos[2].getValue());
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
			for (int i = 0; i < 5; i++) {
				por[i] = porcentajes[i].getSelectedIndex() * 20;
				total = total + por[i];
			}
			// hago esto porque no tiene sentido que haya tantas ambulancias
			// como coches
			por[5] = porcentajes[5].getSelectedIndex() * 3;
			total = total + por[5];
			for (int i = 0; i < 6; i++) {
				por[i] = (100 * por[i]) / total;
			}
			param.setPorcentajeTipo(por);
			controlador.herramienta(new HComenzar());
			PanelVehiculos.this.setVisible(false);
		}

	}
}
