package is.SimTraffic.Vista;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Sergio Perez Jimenez
 *
 */
public class PanelVehiculos extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelVehiculos(){
		
		String[] cantidadVehiculos = { "No aparece", "Muy pocos", "Algunos", "Normal", "Bastantes", "Muchos" };
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER,5,10));
		JLabel etiquetaTitulo = new JLabel("CANTIDAD DE VEHÍCULOS");
		etiquetaTitulo.setFont(new Font(null,Font.BOLD,15));
		panelTitulo.add(etiquetaTitulo);
		
		JPanel panelTurismo = new JPanel();
		panelTurismo.setLayout(new FlowLayout(FlowLayout.CENTER,19,5));
		JPanel panelTurismo1 = new JPanel();
		panelTurismo1.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JLabel iconoTurismo = new JLabel((new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\turismo.jpg")));
		JLabel etiquetaTurismo = new JLabel("Turismo");
		panelTurismo1.add(iconoTurismo);
		panelTurismo1.add(etiquetaTurismo);
		JPanel panelTurismo2 = new JPanel();
		panelTurismo2.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
	    JComboBox comboTurismo = new JComboBox(cantidadVehiculos);
	    JButton botonPropiedadesTurismo = new JButton("Propiedades");
	    panelTurismo2.add(comboTurismo);
	    panelTurismo2.add(botonPropiedadesTurismo);
	    panelTurismo.add(panelTurismo1);
	    panelTurismo.add(panelTurismo2);
	    
	    JPanel panelTaxi = new JPanel();
		panelTaxi.setLayout(new FlowLayout(FlowLayout.CENTER,42,5));
	    JPanel panelTaxi1 = new JPanel();
	    panelTaxi1.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JLabel iconoTaxi = new JLabel((new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\taxi.jpg")));
		JLabel etiquetaTaxi = new JLabel("Taxi");
		panelTaxi1.add(iconoTaxi);
		panelTaxi1.add(etiquetaTaxi);
		JPanel panelTaxi2 = new JPanel();
	    panelTaxi2.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
	    JComboBox comboTaxi = new JComboBox(cantidadVehiculos);
	    JButton botonPropiedadesTaxi = new JButton("Propiedades");
	    panelTaxi2.add(comboTaxi);
	    panelTaxi2.add(botonPropiedadesTaxi);
	    panelTaxi.add(panelTaxi1);
	    panelTaxi.add(panelTaxi2);
	    
	    JPanel panelCamion = new JPanel();
		panelCamion.setLayout(new FlowLayout(FlowLayout.CENTER,23,5));
	    JPanel panelCamion1 = new JPanel();
	    panelCamion1.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JLabel iconoCamion = new JLabel((new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\camion.jpg")));
		JLabel etiquetaCamion = new JLabel("Camion");
	    panelCamion1.add(iconoCamion);
	    panelCamion1.add(etiquetaCamion);
	    JPanel panelCamion2 = new JPanel();
	    panelCamion2.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JComboBox comboCamion = new JComboBox(cantidadVehiculos);
	    JButton botonPropiedadesCamion = new JButton("Propiedades");
	    panelCamion2.add(comboCamion);
	    panelCamion2.add(botonPropiedadesCamion);
	    panelCamion.add(panelCamion1);
	    panelCamion.add(panelCamion2);
	    
	    JPanel panelBus = new JPanel();
		panelBus.setLayout(new FlowLayout(FlowLayout.CENTER,44,5));
	    JPanel panelBus1 = new JPanel();
	    panelBus1.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JLabel iconoBus = new JLabel((new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\autobus.jpg")));
		JLabel etiquetaBus = new JLabel("Bus");
		panelBus1.add(iconoBus);
		panelBus1.add(etiquetaBus);
		JPanel panelBus2 = new JPanel();
	    panelBus2.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JComboBox comboBus = new JComboBox(cantidadVehiculos);
	    JButton botonPropiedadesBus = new JButton("Propiedades");
	    panelBus2.add(comboBus);
	    panelBus2.add(botonPropiedadesBus);
	    panelBus.add(panelBus1);
	    panelBus.add(panelBus2);
	    
	    JPanel panelMoto = new JPanel();
		panelMoto.setLayout(new FlowLayout(FlowLayout.CENTER,39,5));
	    JPanel panelMoto1 = new JPanel();
	    panelMoto1.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JLabel iconoMoto = new JLabel((new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\moto.jpg")));
		JLabel etiquetaMoto = new JLabel("Moto");
		panelMoto1.add(iconoMoto);
	    panelMoto1.add(etiquetaMoto);
	    JPanel panelMoto2 = new JPanel();
	    panelMoto2.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
	    JComboBox comboMoto = new JComboBox(cantidadVehiculos);
	    JButton botonPropiedadesMoto = new JButton("Propiedades");
	    panelMoto2.add(comboMoto);
	    panelMoto2.add(botonPropiedadesMoto);
	    panelMoto.add(panelMoto1);
	    panelMoto.add(panelMoto2);
	    
	    JPanel panelAmbulancia = new JPanel();
	    panelAmbulancia.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
	    JPanel panelAmbulancia1 = new JPanel();
	    panelAmbulancia1.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JLabel iconoAmbulancia = new JLabel((new ImageIcon("is\\SimTraffic\\Vista\\Imagenes\\ambulancia.jpg")));
		JLabel etiquetaAmbulancia = new JLabel("Ambulancia");
		panelAmbulancia1.add(iconoAmbulancia);
	    panelAmbulancia1.add(etiquetaAmbulancia);
	    JPanel panelAmbulancia2 = new JPanel();
	    panelAmbulancia2.setLayout(new FlowLayout(FlowLayout.CENTER,5,0));
		JComboBox comboAmbulancia = new JComboBox(cantidadVehiculos);
	    JButton botonPropiedadesAmbulancia = new JButton("Propiedades");
	    panelAmbulancia2.add(comboAmbulancia);
	    panelAmbulancia2.add(botonPropiedadesAmbulancia);
	    panelAmbulancia.add(panelAmbulancia1);
	    panelAmbulancia.add(panelAmbulancia2);
	    
	    JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,40,5));
		JButton botonAceptar = new JButton("Aceptar");
		JButton botonCancelar = new JButton("Cancelar");		
		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);
	    
	    GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        
        gbc.gridx = 0; 
		gbc.gridy = 0; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		//gbc.weighty = 0.1; 
		this.add (panelTitulo, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 1; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		//gbc.weighty = 0.1; 
		this.add (panelTurismo, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 2; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		//gbc.weighty = 0.1; 
		this.add (panelTaxi, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 3; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		//gbc.weighty = 0.1; 
		this.add (panelCamion, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 4; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		//gbc.weighty = 0.1; 
		this.add (panelBus, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 5; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		//gbc.weighty = 0.1; 
		this.add (panelMoto, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 6; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		//gbc.weighty = 0.1; 
		this.add (panelAmbulancia, gbc);
		
		gbc.gridx = 0; 
		gbc.gridy = 7; 
		gbc.gridwidth = 1; 
		gbc.gridheight = 1; 
		gbc.weighty = 0.2; 
		this.add (panelBotones, gbc);
		
		// Funcionalidad del panel
		
		botonPropiedadesTurismo.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JFrame ventanaTurismo = new JFrame();
						
						PanelTurismo panelTur = new PanelTurismo();
						
						ventanaTurismo.setContentPane(panelTur);
						ventanaTurismo.setTitle("Parámetros del Turismo");
						
						//ventanaTurismo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						// Donde va a aparecer la ventana son las dos primera componentes
						// El tamaño de la ventana son las dos ultimas componentes
						ventanaTurismo.setBounds(505,120,300,200);
						ventanaTurismo.setVisible(true);	
					}
				}
		);
	}
}
