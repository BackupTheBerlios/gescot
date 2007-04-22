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

	public PanelVehiculos(IControlador controlador, ParametrosSimulacion param) {
		this.controlador = controlador;
		this.param = param;
		this.setResizable(false);
		this.setTitle("Tipos de Vehículos");

		String[] cantidadVehiculos = { "No aparece", "Muy pocos", "Algunos",
				"Normal", "Bastantes", "Muchos" };
		String[] cantidadTurismos = { "Muy pocos", "Algunos",
				"Normal", "Bastantes", "Muchos" };

		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		JLabel etiquetaTitulo = new JLabel("CANTIDAD DE VEHÍCULOS");
		etiquetaTitulo.setFont(new Font(null, Font.BOLD, 15));
		panelTitulo.add(etiquetaTitulo);

		JPanel panelCantidades = panelCantidades();
		
		JPanel panelTurismo = crearOpcionesVehiculo(cantidadTurismos,
				"Turismo.jpg", "Turismo  ");

		JPanel panelTaxi = crearOpcionesVehiculo(cantidadVehiculos, "taxi.jpg",
				"Taxi      ");

		JPanel panelCamion = crearOpcionesVehiculo(cantidadVehiculos,
				"camion.jpg", "Camion    ");

		JPanel panelBus = crearOpcionesVehiculo(cantidadVehiculos,
				"autobus.jpg", "Bus         ");

		JPanel panelMoto = crearOpcionesVehiculo(cantidadVehiculos,
				"moto.jpg", "Moto     ");

		JPanel panelAmbulancia = crearOpcionesVehiculo(cantidadVehiculos,
				"ambulancia.jpg", "Ambulancia");

		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new accionAceptar());
		JButton botonCancelar = new JButton("Cancelar");
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

		// Funcionalidad del panel

		/*
		 * botonPropiedadesTurismo.addActionListener( new ActionListener(){
		 * public void actionPerformed(ActionEvent e){ JFrame ventanaTurismo =
		 * new JFrame();
		 * 
		 * PanelTurismo panelTur = new PanelTurismo();
		 * 
		 * ventanaTurismo.setContentPane(panelTur);
		 * ventanaTurismo.setTitle("Parámetros del Turismo");
		 * 
		 * //ventanaTurismo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
		 * Donde va a aparecer la ventana son las dos primera componentes // El
		 * tamaño de la ventana son las dos ultimas componentes
		 * ventanaTurismo.setBounds(505,120,300,200);
		 * ventanaTurismo.setVisible(true); } } );
		 */
	}

	private void addPanel(JPanel p, JComponent comp, GridBagLayout gb,
			GridBagConstraints c) {
		gb.setConstraints(comp, c);
		p.add(comp);
	}

	private JPanel crearOpcionesVehiculo(String[] cantidades, String icono,
			String nombre) {
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
		JComboBox combo = new JComboBox(cantidades);
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
		addPanel(panelCantidades, text1, bg,c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[0] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		text1.setText(""+ vehiculos[0].getValue());
		vehiculos[0].addAdjustmentListener(new ajuste(text1));
		addPanel(panelCantidades, vehiculos[0], bg, c);

		c.gridwidth = GridBagConstraints.BOTH;
		JTextField text2 = new JTextField(2);
		text2.setEditable(false);
		c.weightx = 0.3;
		addPanel(panelCantidades, new JLabel("Tarde"), bg, c);
		addPanel(panelCantidades, text2, bg,c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[1] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		text2.setText(""+ vehiculos[1].getValue());
		vehiculos[1].addAdjustmentListener(new ajuste(text2));
		addPanel(panelCantidades, vehiculos[1], bg, c);

		c.gridwidth = GridBagConstraints.BOTH;
		JTextField text3 = new JTextField(2);
		text3.setEditable(false);
		c.weightx = 0.3;
		addPanel(panelCantidades, new JLabel("Noche"), bg, c);
		addPanel(panelCantidades, text3, bg,c);
		c.weightx = 4.0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		vehiculos[2] = new JScrollBar(JScrollBar.HORIZONTAL, min + (max - min)
				/ 10, 100, min, max);
		text3.setText(""+ vehiculos[2].getValue());
		vehiculos[2].addAdjustmentListener(new ajuste(text3));
		addPanel(panelCantidades, vehiculos[2], bg, c);

		return panelCantidades;
	}

	private class ajuste implements AdjustmentListener{
		JTextField text;
		
		public ajuste(JTextField text) {
			this.text = text;
		}
		
	    public void adjustmentValueChanged(AdjustmentEvent ae){
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
				//totales[i] = 200;
			}
			param.setNumVehiculos(totales);
			controlador.herramienta(new HComenzar());
			PanelVehiculos.this.setVisible(false);
		}

	}
}
