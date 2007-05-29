package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Acciones.PanelTramo.AccionAceptarPropComunes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class PanelPropComunes extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelMapa panel;
	private JPanel panelBotones;
	private PanelTramo panelTramo;
	private IControlador controlador;
	private Tramo tramo;
	private JRadioButton radioUnidireccional;
	private JRadioButton radioBidireccional;
	private JRadioButton radioSentido1;
	private JRadioButton radioSentido2;
	private JSpinner campoCarril1Numero;
	private JSpinner campoCarril2Numero;
	private JSpinner campoVelocidad;
	private JPanel panelDatos;
	private JTextField nombreVia;
	private JComboBox tipoVia;
	private JTextField campoNombre;
	
	public PanelPropComunes(Tramo tramoAux, PanelTramo ptramo, PanelMapa panel, IControlador controlador, JTextField nombreVia, JComboBox tipoVia) 
	{
		this.panel = panel;
		tramo = tramoAux;
		this.controlador = controlador;
		panelDatos = new JPanel();
		this.panelTramo = ptramo;
		this.nombreVia = nombreVia;
		this.tipoVia = tipoVia;
		
		creaPanelDatos();
		
		panelBotones = new JPanel();
		creaPanelBotones();
		
		this.setLayout(new BorderLayout(2,2));
		
		this.add(panelDatos);
		this.add(panelBotones,BorderLayout.SOUTH);
	}


	public void creaPanelDatos(){
		
		JPanel panelPropiedades = new JPanel();
		
		//Antes comentado
		
		JPanel panelSentido = crearPanelSentido();
		
		JPanel panelNumCarriles = crearPanelNumCarriles();
	
		JPanel panelVelocidad = crearPanelVelocidad();
		
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
		
		panelPropiedades.setLayout(new BoxLayout(panelPropiedades, BoxLayout.PAGE_AXIS));
		panelPropiedades.add(panelSentido);
		panelPropiedades.add(panelNumCarriles);
		panelPropiedades.add(panelVelocidad);  
		this.add(panelPropiedades);
		
		panelDatos.add(panelPropiedades); //$NON-NLS-1$ //$NON-NLS-2$
		
		/*panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Tramo");
		 panelDatos.setSelectedIndex(0);
		 panelDatos.addTab("Señales", null, panelSeñales,"Señales asociadas al Tramo");
		 panelDatos.addTab("Nodos", null, panelNodos,"Nodos asociados al Tramo");
		 */
	}
	
	private JPanel crearPanelVelocidad() 
	{
		JPanel panelSelección = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(1,1,2,2));
		JPanel selecciones = new JPanel(new GridLayout(1,1,2,2));
		
		JPanel panelVelocidad = new JPanel();
		
		JLabel etiqVelocidad = new JLabel(Messages.getString("PanelTramo.30")); //$NON-NLS-1$
		campoVelocidad = new JSpinner(new SpinnerNumberModel(60,20,120,10));
		labels.add(etiqVelocidad);
		selecciones.add(campoVelocidad);
		
		
		panelVelocidad.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelTramo.32"))); //$NON-NLS-1$
		panelSelección.add(labels,BorderLayout.WEST);
		panelSelección.add(selecciones,BorderLayout.EAST);
		panelVelocidad.add(panelSelección);
		
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
		return panelVelocidad;
	}

	private JPanel crearPanelNumCarriles() 
	{
		JPanel panelNumCarriles = new JPanel();
		panelNumCarriles.setLayout(new BorderLayout());
		
		JPanel panelEtiquetasNumero = new JPanel();
		panelEtiquetasNumero.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		JLabel etiquetaCarril1Numero = new JLabel(Messages.getString("PanelTramo.26")); //$NON-NLS-1$
		JLabel etiquetaCarril2Numero = new JLabel(Messages.getString("PanelTramo.27")); //$NON-NLS-1$
		panelEtiquetasNumero.add(etiquetaCarril1Numero);
		panelEtiquetasNumero.add(etiquetaCarril2Numero);
		
		JPanel panelCamposNumero = new JPanel();
		panelCamposNumero.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		campoCarril1Numero = new JSpinner(new SpinnerNumberModel(2,1,4,1));
		campoCarril2Numero = new JSpinner(new SpinnerNumberModel(2,1,4,1));
		campoCarril2Numero.setEnabled(false);
		panelCamposNumero.add(campoCarril1Numero);
		panelCamposNumero.add(campoCarril2Numero);
		
		panelNumCarriles.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelTramo.28"))); //$NON-NLS-1$
		panelNumCarriles.add(panelEtiquetasNumero,BorderLayout.NORTH);
		panelNumCarriles.add(panelCamposNumero,BorderLayout.SOUTH);
		
		return panelNumCarriles;
	}
	
	private JPanel crearPanelSentido() 
	{
		JPanel panelSentido = new JPanel();
		panelSentido.setLayout(new BorderLayout());
		
		JPanel panelRadio1 = new JPanel();
		panelRadio1.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		radioUnidireccional = new JRadioButton(Messages.getString("PanelTramo.21")); //$NON-NLS-1$
		radioBidireccional = new JRadioButton(Messages.getString("PanelTramo.22")); //$NON-NLS-1$
		ButtonGroup radioGrupo1 = new ButtonGroup();
		radioGrupo1.add(radioUnidireccional);
		radioGrupo1.add(radioBidireccional);
		panelRadio1.add(radioUnidireccional);
		panelRadio1.add(radioBidireccional);
		
		JPanel panelRadio2 = new JPanel();
		panelRadio2.setLayout(new GridLayout(2,1));
		radioSentido1 = new JRadioButton(Messages.getString("PanelTramo.23")); //$NON-NLS-1$
		radioSentido2 = new JRadioButton(Messages.getString("PanelTramo.24")); //$NON-NLS-1$
		ButtonGroup radioGrupo2 = new ButtonGroup();
		radioGrupo2.add(radioSentido1);
		radioGrupo2.add(radioSentido2);
		panelRadio2.add(radioSentido1);
		panelRadio2.add(radioSentido2);
		
		JPanel aux = new JPanel();
		aux.setLayout(new FlowLayout(FlowLayout.CENTER));
		JPanel vacio = new JPanel();
		aux.add(vacio);
		aux.add(panelRadio2);
		
		panelSentido.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelTramo.25"))); //$NON-NLS-1$
		panelSentido.add(panelRadio1, BorderLayout.NORTH);
		panelSentido.add(aux, BorderLayout.SOUTH);
		return panelSentido;
	}
	
	public void creaPanelBotones(){		
		JButton botonAceptar;
		JButton botonCancelar;
		
		botonAceptar = new JButton(Messages.getString("PanelTramo.37")); //$NON-NLS-1$
		botonCancelar = new JButton(Messages.getString("PanelTramo.38")); //$NON-NLS-1$
		
		final PanelPropComunes panelPpal = this;
		AccionAceptarPropComunes accion = new AccionAceptarPropComunes(panel, this, panelTramo, controlador, tramo, radioUnidireccional, 
				radioSentido1,campoCarril1Numero, campoCarril2Numero, campoVelocidad, nombreVia, tipoVia);
		botonAceptar.addActionListener(accion);
		botonCancelar.addActionListener(new ActionListener()
				{
			public void actionPerformed(ActionEvent arg0) 
			{
				panelPpal.dispose();
			}
				});
		
		panelBotones.setLayout(new GridLayout(1,2,2,2));
		
		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);
	}
	
	/*private void creaPanelBotones() 
	{
		JButton botonAceptar;
		JButton botonCancelar;
	
		botonAceptar = new JButton(Messages.getString("PanelTramo.37")); //$NON-NLS-1$
		botonCancelar = new JButton(Messages.getString("PanelTramo.38")); //$NON-NLS-1$
		
		final PanelPropComunes panelPpal = this;
		
		AccionAceptarPropComunes accion = new AccionAceptarPropComunes(panel, this, panelTramo, controlador, tramo, radioUnidireccional, 
				radioSentido1,campoCarril1Numero, campoCarril2Numero, campoVelocidad, nombreVia, tipoVia);
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

	private void creaPanelDatos() 
	{
		JPanel panelSentido = new JPanel();
	    panelSentido.setLayout(new BorderLayout());
	    
	    JPanel panelRadio1 = new JPanel();
	    panelRadio1.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
	    radioUnidireccional = new JRadioButton(Messages.getString("PanelTramo.21")); //$NON-NLS-1$
	    radioBidireccional = new JRadioButton(Messages.getString("PanelTramo.22")); //$NON-NLS-1$
	    ButtonGroup radioGrupo1 = new ButtonGroup();
	    radioGrupo1.add(radioUnidireccional);
	    radioGrupo1.add(radioBidireccional);
	    panelRadio1.add(radioUnidireccional);
	    panelRadio1.add(radioBidireccional);
	    
	    JPanel panelRadio2 = new JPanel();
	    panelRadio2.setLayout(new GridLayout(2,1));
	    radioSentido1 = new JRadioButton(Messages.getString("PanelTramo.23")); //$NON-NLS-1$
	    radioSentido2 = new JRadioButton(Messages.getString("PanelTramo.24")); //$NON-NLS-1$
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
	    
	    panelSentido.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelTramo.25"))); //$NON-NLS-1$
	    panelSentido.add(panelRadio1, BorderLayout.NORTH);
	    panelSentido.add(aux, BorderLayout.SOUTH);
	    
	    JPanel panelNumCarriles = new JPanel();
	    panelNumCarriles.setLayout(new BorderLayout());
	  
	    JPanel panelEtiquetasNumero = new JPanel();
	    panelEtiquetasNumero.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
	    JLabel etiquetaCarril1Numero = new JLabel(Messages.getString("PanelTramo.26")); //$NON-NLS-1$
	    JLabel etiquetaCarril2Numero = new JLabel(Messages.getString("PanelTramo.27")); //$NON-NLS-1$
	    panelEtiquetasNumero.add(etiquetaCarril1Numero);
	    panelEtiquetasNumero.add(etiquetaCarril2Numero);
	    
	    JPanel panelCamposNumero = new JPanel();
	    panelCamposNumero.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
	    campoCarril1Numero = new JSpinner(new SpinnerNumberModel(2,1,4,1));
	    campoCarril2Numero = new JSpinner(new SpinnerNumberModel(2,1,4,1));
	    campoCarril2Numero.setEnabled(false);
	    panelCamposNumero.add(campoCarril1Numero);
	    panelCamposNumero.add(campoCarril2Numero);
	    
	    panelNumCarriles.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelTramo.28"))); //$NON-NLS-1$
	    panelNumCarriles.add(panelEtiquetasNumero,BorderLayout.NORTH);
	    panelNumCarriles.add(panelCamposNumero,BorderLayout.SOUTH);
	    
	    
	    JPanel panelVelocidad = new JPanel();
	    panelVelocidad.setLayout(new BorderLayout());
	  
	    JPanel panelEtiquetasVelocidad = new JPanel();
	    panelEtiquetasVelocidad.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	    
	    JPanel panelCamposVelocidad = new JPanel();
	    panelCamposVelocidad.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
	    JLabel etiqVelocidad = new JLabel(Messages.getString("PanelTramo.30")); //$NON-NLS-1$
	    
	    campoVelocidad = new JSpinner(new SpinnerNumberModel(60,20,120,10));
	    panelCamposVelocidad.add(etiqVelocidad);
	    panelCamposVelocidad.add(campoVelocidad);
	    
	    
	    panelVelocidad.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelTramo.32"))); //$NON-NLS-1$
	    panelVelocidad.add(panelCamposVelocidad,BorderLayout.NORTH);  
	    
	    JPanel panelPropiedades = new JPanel();
	    panelPropiedades.setLayout(new BorderLayout(10,40));
	    panelPropiedades.add(panelSentido,BorderLayout.NORTH);
	    panelPropiedades.add(panelNumCarriles,BorderLayout.CENTER);
	    panelPropiedades.add(panelVelocidad,BorderLayout.SOUTH);  
	    panelDatos.add(panelPropiedades);
	    
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
		
	}*/

}
