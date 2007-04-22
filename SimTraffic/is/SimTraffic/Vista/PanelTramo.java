/**
 * 
 */
package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
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
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Es el panel de propiedades del tramo.
 *    No tiene pestañas de propiedades de los nodos extremos
 */
public class PanelTramo extends JFrame 
{
	private static final long serialVersionUID = 1L;
	
	private JTextField campoNombre;
	private JPanel panelBotones;
	private IControlador controlador;
	private Tramo tramo;
	private PanelMapa panel;
	private JRadioButton radioUnidireccional;
	private JRadioButton radioBidireccional;
	private JRadioButton radioSentido1;
	private JRadioButton radioSentido2;
	private JSpinner campoCarril1Numero;
	private JSpinner campoCarril2Numero;
	private JSpinner campoVelocidad;
//	private JTabbedPane panelDatos;
	
	
	/**
	 * @param tramoAux
	 * @param panel
	 * @param controlador
	 */
	public PanelTramo(Tramo tramoAux, PanelMapa panel,IControlador controlador)
	{
		tramo = tramoAux;
		this.controlador=controlador;
		this.panel = panel;
		
		this.setLayout(new BorderLayout(2,2));
		
		//panelDatos = new JTabbedPane();
		creaPanelDatos();
		
		panelBotones = new JPanel();
		creaPanelBotones();
		
	    //this.add(panelDatos,BorderLayout.NORTH);
	    this.add(panelBotones,BorderLayout.SOUTH);
	    
	}

	/**
	 * 
	 *
	 */
	public void creaPanelDatos(){
				
		JPanel panelPropiedades = new JPanel();
		// JPanel panelNodos = new JPanel();
	    // JPanel panelSeñales = new JPanel();
	      
	    JPanel panelSentido = new JPanel();
	    panelSentido.setLayout(new BorderLayout());
	    
	    JPanel panelRadio1 = new JPanel();
	    panelRadio1.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
	    radioUnidireccional = new JRadioButton("Unidireccional");
	    radioBidireccional = new JRadioButton("Bidireccional");
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
	    panelEtiquetasNumero.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
	    JLabel etiquetaCarril1Numero = new JLabel("Carril sentido 1");
	    JLabel etiquetaCarril2Numero = new JLabel("Carril sentido 2");
	    panelEtiquetasNumero.add(etiquetaCarril1Numero);
	    panelEtiquetasNumero.add(etiquetaCarril2Numero);
	    
	    JPanel panelCamposNumero = new JPanel();
	    panelCamposNumero.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
	    campoCarril1Numero = new JSpinner(new SpinnerNumberModel(2,1,4,1));
	    campoCarril2Numero = new JSpinner(new SpinnerNumberModel(2,1,4,1));
	    campoCarril2Numero.setEnabled(false);
	    panelCamposNumero.add(campoCarril1Numero);
	    panelCamposNumero.add(campoCarril2Numero);
	    
	    panelNumCarriles.setBorder(BorderFactory.createTitledBorder("Número de carriles"));
	    panelNumCarriles.add(panelEtiquetasNumero,BorderLayout.NORTH);
	    panelNumCarriles.add(panelCamposNumero,BorderLayout.SOUTH);
	    
	    //Añadido para el nombre
	    JPanel panelNombre = new JPanel();
	    panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
	    JLabel etiquetaNombre = new JLabel("Nombre");
	    campoNombre = new JTextField(14);
	    if (tramo.getNombre()!=null)
	    	campoNombre.setText(tramo.getNombre());
	    panelNombre.add(etiquetaNombre);
	    panelNombre.add(campoNombre);
	    
	    JPanel panelVelocidad = new JPanel();
	    panelVelocidad.setLayout(new BorderLayout());
	  
	    JPanel panelEtiquetasVelocidad = new JPanel();
	    panelEtiquetasVelocidad.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	    
	    JPanel panelCamposVelocidad = new JPanel();
	    panelCamposVelocidad.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	    JLabel etiqVelocidad = new JLabel("Velocidad");
	    JLabel etiqLongitud = new JLabel("Longitud (en metros)");

	    int longitud = tramo.getLargo();
	    String auxLongitud = String.valueOf(longitud);
	    JLabel etiqInfoLongitud = new JLabel(auxLongitud);
	    panelCamposVelocidad.add(etiqLongitud);
	    panelCamposVelocidad.add(etiqInfoLongitud);
	    
	    campoVelocidad = new JSpinner(new SpinnerNumberModel(60,20,120,10));
	    panelCamposVelocidad.add(etiqVelocidad);
	    panelCamposVelocidad.add(campoVelocidad);
	    
	    
	    panelVelocidad.setBorder(BorderFactory.createTitledBorder("Otras propiedades"));
	    panelVelocidad.add(panelCamposVelocidad,BorderLayout.NORTH);
	    //Añadido
	    panelVelocidad.add(panelNombre,BorderLayout.SOUTH);    
	    
	    //Mostramos los valores actuales del tramo:
	    int numCarr1 = tramo.getNumCarrilesDir1();
	    int numCarr2 = tramo.getNumCarrilesDir2();
	    float vel = tramo.getVelMax();
	    if (numCarr1 > 0 && numCarr2 > 0)
	    {
	    	radioBidireccional.setSelected(true);
	    	radioSentido1.setEnabled(false);
	    	radioSentido2.setEnabled(false);
	    	campoCarril1Numero.setEnabled(true);
	    	campoCarril2Numero.setEnabled(true);
	    	campoCarril1Numero.setValue(numCarr1);
	    	campoCarril2Numero.setValue(numCarr2);
	    }
	    else
	    {
	    	radioUnidireccional.setSelected(true);
	    	if (numCarr1 > 0)
	    	{
	    		radioSentido1.setSelected(true);
	    		campoCarril1Numero.setEnabled(true);
	    		campoCarril2Numero.setEnabled(false);
	    		campoCarril1Numero.setValue(numCarr1);
	    		campoCarril2Numero.setValue(0);
	    	}
	    	else
	    	{
	    		radioSentido2.setSelected(true);
	    		campoCarril1Numero.setEnabled(false);
	    		campoCarril2Numero.setEnabled(true);
	    		campoCarril1Numero.setValue(0);
	    		campoCarril2Numero.setValue(numCarr2);
	    	}
	    }
    	campoVelocidad.setValue((int)vel);
	    
	    radioBidireccional.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (radioBidireccional.isSelected())
						{
							radioSentido1.setEnabled(false);
							radioSentido2.setEnabled(false);
							campoCarril1Numero.setEnabled(true);
							campoCarril2Numero.setEnabled(true);
							if (((Integer)campoCarril1Numero.getValue()).intValue() == 0)
								campoCarril1Numero.setValue(1);
							if (((Integer)campoCarril2Numero.getValue()).intValue() == 0)
								campoCarril2Numero.setValue(1);
						}
            }
	    });
	    
	    radioUnidireccional.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if (radioUnidireccional.isSelected())
						{
								radioSentido1.setSelected(true);
								radioSentido1.setEnabled(true);
								if (((Integer)campoCarril1Numero.getValue()).intValue() == 0)
									campoCarril1Numero.setValue(1);
								radioSentido2.setEnabled(true);
								campoCarril1Numero.setEnabled(true);
								campoCarril2Numero.setEnabled(false);
						}
					}
				}
	    	);
	    
	    radioSentido1.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (radioUnidireccional.isSelected() && radioSentido1.isSelected())
						{
							campoCarril1Numero.setEnabled(true);
							campoCarril2Numero.setEnabled(false);
							if (((Integer)campoCarril1Numero.getValue()).intValue() == 0)
								campoCarril1Numero.setValue(1);
						}
            }
	    });
	    
	    radioSentido2.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if (radioUnidireccional.isSelected() && radioSentido2.isSelected()){
							campoCarril1Numero.setEnabled(false);
							campoCarril2Numero.setEnabled(true);
							if (((Integer)campoCarril2Numero.getValue()).intValue() == 0)
								campoCarril2Numero.setValue(1);
						}
            }
	    });
	    
	    panelPropiedades.setLayout(new BorderLayout(10,40));
	    panelPropiedades.add(panelSentido,BorderLayout.NORTH);
	    panelPropiedades.add(panelNumCarriles,BorderLayout.CENTER);
	    panelPropiedades.add(panelVelocidad,BorderLayout.SOUTH);  
	    this.add(panelPropiedades);
	    
	  /*panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Tramo");
	    panelDatos.setSelectedIndex(0);
		panelDatos.addTab("Señales", null, panelSeñales,"Señales asociadas al Tramo");
	    panelDatos.addTab("Nodos", null, panelNodos,"Nodos asociados al Tramo");
	    */
	}

	/**
	 * 
	 *
	 */
	public void creaPanelBotones(){
	
		JButton botonAceptar;
		JButton botonCancelar;
	
		botonAceptar = new JButton("Aceptar");
		botonCancelar = new JButton("Cancelar");
		
		final PanelTramo panelPpal = this;
		AccionAceptarTramo accion = new AccionAceptarTramo(panel,this,controlador, tramo, radioUnidireccional, 
				radioSentido1,campoCarril1Numero, campoCarril2Numero, campoVelocidad, campoNombre);
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
	public void accionBotones(){
		
		
	}
	
}
