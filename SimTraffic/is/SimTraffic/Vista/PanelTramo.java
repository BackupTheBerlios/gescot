/**
 * 
 */
package is.SimTraffic.Vista;

import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Acciones.PanelTramo.AccionAceptarTramo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

/**
 * Es el panel de propiedades del tramo. Falta terminarla
 *
 */
public class PanelTramo extends JFrame 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTabbedPane panelDatos;
	private JPanel panelBotones;
	
	private Tramo tramo;
	private PanelMapa panel;

	private JRadioButton radioUnidireccional;
	private JRadioButton radioBidireccional;

	private JRadioButton radioSentido1;

	private JRadioButton radioSentido2;

	private JSpinner campoCarril1Numero;

	private JSpinner campoCarril2Numero;

	private JSpinner campoCarril1Velocidad;

	private JSpinner campoCarril2Velocidad;
	
	
	
	public PanelTramo(Tramo tramoAux, PanelMapa panel)
	{
		tramo = tramoAux;
		this.panel = panel;
		
		this.setLayout(new BorderLayout(2,2));
		
		panelDatos = new JTabbedPane();
		creaPanelDatos();
		
		panelBotones = new JPanel();
		creaPanelBotones();
		
	    this.add(panelDatos,BorderLayout.NORTH);
	    this.add(panelBotones,BorderLayout.SOUTH);
	    
	}

	public void creaPanelDatos(){
				
		JPanel panelPropiedades = new JPanel();
		JPanel panelNodos = new JPanel();
	    JPanel panelSeñales = new JPanel();
	      
	    JPanel panelSentido = new JPanel();
	    panelSentido.setLayout(new BorderLayout());
	    
	    JPanel panelRadio1 = new JPanel();
	    panelRadio1.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
	    radioUnidireccional = new JRadioButton("Unidireccional");
	    radioBidireccional = new JRadioButton("Bidireccional");
	    radioUnidireccional.setSelected(true);
	    ButtonGroup radioGrupo1 = new ButtonGroup();
	    radioGrupo1.add(radioUnidireccional);
	    radioGrupo1.add(radioBidireccional);
	    panelRadio1.add(radioUnidireccional);
	    panelRadio1.add(radioBidireccional);
	    
	    JPanel panelRadio2 = new JPanel();
	    panelRadio2.setLayout(new GridLayout(2,1));
	    radioSentido1 = new JRadioButton("Sentido 1");
	    radioSentido2 = new JRadioButton("Sentido 2");
	    //radioSentido1.setSelected(true);
	    ButtonGroup radioGrupo2 = new ButtonGroup();
	    radioGrupo2.add(radioSentido1);
	    radioGrupo2.add(radioSentido2);
	    panelRadio2.add(radioSentido1);
	    panelRadio2.add(radioSentido2);
	    
	    JPanel aux = new JPanel();
	    aux.setLayout(new FlowLayout(FlowLayout.LEFT,40,5));
	    JPanel vacio = new JPanel();
	    aux.add(vacio);
	    aux.add(panelRadio2);
	    
	    panelSentido.setBorder(BorderFactory.createTitledBorder("Sentido del Tramo"));
	    panelSentido.add(panelRadio1, BorderLayout.NORTH);
	    panelSentido.add(aux, BorderLayout.SOUTH);
	    
	    JPanel panelNumCarriles = new JPanel();
	    panelNumCarriles.setLayout(new BorderLayout());
	  
	    JPanel panelEtiquetasNumero = new JPanel();
	    panelEtiquetasNumero.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	    JLabel etiquetaCarril1Numero = new JLabel("Carril sentido 1");
	    JLabel etiquetaCarril2Numero = new JLabel("Carril sentido 2");
	    panelEtiquetasNumero.add(etiquetaCarril1Numero);
	    panelEtiquetasNumero.add(etiquetaCarril2Numero);
	    
	    JPanel panelCamposNumero = new JPanel();
	    panelCamposNumero.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	    campoCarril1Numero = new JSpinner(new SpinnerNumberModel(2,1,4,1));
	    campoCarril2Numero = new JSpinner(new SpinnerNumberModel(2,1,4,1));
	    campoCarril2Numero.setEnabled(false);
	    panelCamposNumero.add(campoCarril1Numero);
	    panelCamposNumero.add(campoCarril2Numero);
	    
	    panelNumCarriles.setBorder(BorderFactory.createTitledBorder("Número de carriles"));
	    panelNumCarriles.add(panelEtiquetasNumero,BorderLayout.NORTH);
	    panelNumCarriles.add(panelCamposNumero,BorderLayout.SOUTH);
	    
	    JPanel panelVelocidad = new JPanel();
	    panelVelocidad.setLayout(new BorderLayout());
	  
	    JPanel panelEtiquetasVelocidad = new JPanel();
	    panelEtiquetasVelocidad.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	    JLabel etiquetaCarril1Velocidad = new JLabel("Carril sentido 1");
	    JLabel etiquetaCarril2Velocidad = new JLabel("Carril sentido 2");
	    panelEtiquetasVelocidad.add(etiquetaCarril1Velocidad);
	    panelEtiquetasVelocidad.add(etiquetaCarril2Velocidad);
	    
	    JPanel panelCamposVelocidad = new JPanel();
	    panelCamposVelocidad.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	    campoCarril1Velocidad = new JSpinner(new SpinnerNumberModel(60,20,120,10));
	    campoCarril2Velocidad = new JSpinner(new SpinnerNumberModel(60,20,120,10));
	    campoCarril2Velocidad.setEnabled(false);
	    panelCamposVelocidad.add(campoCarril1Velocidad);
	    panelCamposVelocidad.add(campoCarril2Velocidad);
	    
	    panelVelocidad.setBorder(BorderFactory.createTitledBorder("Velocidad de los carriles"));
	    panelVelocidad.add(panelEtiquetasVelocidad,BorderLayout.NORTH);
	    panelVelocidad.add(panelCamposVelocidad,BorderLayout.SOUTH);
	      
	    
	    //Mostramos los valores actuales del tramo:
	    int numCarr1 = tramo.getNumCarrilesDir1();
	    int numCarr2 = tramo.getNumCarrilesDir2();
	    float vel = tramo.getVelMax();
	    
	    
	    radioBidireccional.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (radioBidireccional.isSelected())
						{
							radioSentido1.setEnabled(false);
							radioSentido2.setEnabled(false);
							campoCarril1Numero.setEnabled(true);
							campoCarril1Velocidad.setEnabled(true);
							campoCarril2Numero.setEnabled(true);
							campoCarril2Velocidad.setEnabled(true);
						}
            }
	    });
	    
	    radioUnidireccional.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (radioUnidireccional.isSelected())
						{
							if (radioSentido1.isSelected())
							{
								radioSentido1.setEnabled(true);
								radioSentido2.setEnabled(true);
								campoCarril1Numero.setEnabled(true);
								campoCarril1Velocidad.setEnabled(true);
								campoCarril2Numero.setEnabled(false);
								campoCarril2Velocidad.setEnabled(false);
								}
							else
							{
								radioSentido1.setEnabled(true);
								radioSentido2.setEnabled(true);
								campoCarril1Numero.setEnabled(false);
								campoCarril1Velocidad.setEnabled(false);
								campoCarril2Numero.setEnabled(true);
								campoCarril2Velocidad.setEnabled(true);
			
							}
						}
            }
	    });
	    
	    radioSentido1.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (radioUnidireccional.isSelected() && radioSentido1.isSelected())
						{
							campoCarril1Numero.setEnabled(true);
							campoCarril2Numero.setEnabled(false);
							campoCarril1Velocidad.setEnabled(true);
							campoCarril2Velocidad.setEnabled(false);
						}
            }
	    });
	    
	    radioSentido2.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (radioUnidireccional.isSelected() && radioSentido2.isSelected()){
							campoCarril1Numero.setEnabled(false);
							campoCarril2Numero.setEnabled(true);
							campoCarril1Velocidad.setEnabled(false);
							campoCarril2Velocidad.setEnabled(true);
						}
            }
	    });
	    
	    panelPropiedades.setLayout(new BorderLayout(10,20));
	    panelPropiedades.add(panelSentido,BorderLayout.NORTH);
	    panelPropiedades.add(panelNumCarriles,BorderLayout.CENTER);
	    panelPropiedades.add(panelVelocidad,BorderLayout.SOUTH);
	    
	    panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Tramo");
	    panelDatos.setSelectedIndex(0);
		
	    panelDatos.addTab("Señales", null, panelSeñales,"Señales asociadas al Tramo");
	    panelDatos.addTab("Tramos", null, panelNodos,"Nodos asociados al Tramo");
	}

	public void creaPanelBotones(){
	
		JButton botonAceptar;
		JButton botonCancelar;
	
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		
		final PanelTramo panelPpal = this;
		AccionAceptarTramo accion = new AccionAceptarTramo(panel, this, tramo, radioUnidireccional.isSelected(), 
				radioSentido1.isSelected(), ((Integer)(campoCarril1Numero.getValue())).intValue(), ((Integer)(campoCarril2Numero.getValue())).intValue(),
				((Integer)(campoCarril1Velocidad.getValue())).intValue(), ((Integer)(campoCarril2Velocidad.getValue())).intValue());
		botonAceptar.addActionListener(accion);
		botonCancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				panelPpal.dispose();
			}
		});
	
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER,80,28));
	
		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);
		
		panelBotones.setBorder(BorderFactory.createEtchedBorder());
}
	
}
