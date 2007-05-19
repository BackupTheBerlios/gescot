/**
 * 
 */
package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
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
import javax.swing.JComboBox;
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
 *    No tiene pesta�as de propiedades de los nodos extremos
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
	private JTabbedPane panelDatos;
	
	//Info de v�a
	private JTextField nombreVia;
	private JComboBox combo_tipoVia;
	
	
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
		
		//Antes comentado
		panelDatos = new JTabbedPane();
		creaPanelDatos();
		
		panelBotones = new JPanel();
		creaPanelBotones();
		
		//Antes comentado
	    this.add(panelDatos,BorderLayout.NORTH);
	    
	    this.add(panelBotones,BorderLayout.SOUTH);
	    
	}

	/**
	 * 
	 *
	 */
	public void creaPanelDatos(){
				
		JPanel panelPropiedades = new JPanel();
		
		//Antes comentado
		JPanel panelVias = new JPanel();
		if (tramo.getVia() != null) {
			JLabel asociadoVia = new JLabel(Messages.getString("PanelTramo.0")); //$NON-NLS-1$
			JLabel observacionVia = new JLabel(Messages.getString("PanelTramo.1")); //$NON-NLS-1$
			panelVias.add(asociadoVia);
			panelVias.add(observacionVia);
			JLabel nombreEtiqVia=new JLabel(Messages.getString("PanelTramo.2"));				 //$NON-NLS-1$
			nombreVia=new JTextField(30);
			nombreVia.setText(tramo.getVia().getNombre());
			panelVias.add(nombreEtiqVia);
			panelVias.add(nombreVia);
			if (tramo.getVia().getTipo()!=null /*&& tramo.getVia().getTipo().equals("highway")*/) {
				JLabel tipoEtiqVia = new JLabel(Messages.getString("PanelTramo.3")); //$NON-NLS-1$
				//JLabel tipoVia = new JLabel(tramo.getVia().getTipo().getValorTipoCastellano());
				String[] tiposVias = { Messages.getString("PanelTramo.4"), Messages.getString("PanelTramo.5"),Messages.getString("PanelTramo.6"), Messages.getString("PanelTramo.7"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					Messages.getString("PanelTramo.8"),Messages.getString("PanelTramo.9"),Messages.getString("PanelTramo.10"),Messages.getString("PanelTramo.11") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					,Messages.getString("PanelTramo.12"),Messages.getString("PanelTramo.13"),Messages.getString("PanelTramo.14"),Messages.getString("PanelTramo.15"),Messages.getString("PanelTramo.16"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
					Messages.getString("PanelTramo.17"),Messages.getString("PanelTramo.18"),Messages.getString("PanelTramo.19")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			    combo_tipoVia = new JComboBox(tiposVias);
				panelVias.add(combo_tipoVia);
				panelVias.add(tipoEtiqVia);
				//System.out.println(tramo.getVia().getTipo().getValorTipoCastellano());
				for (int i=0;i<tiposVias.length;i++) {
					if ( tramo.getVia().getTipo().getValorTipoCastellano().equals(tiposVias[i]) )
						combo_tipoVia.setSelectedIndex(i);
				}
				panelVias.add(combo_tipoVia);
			}
			JButton propComunes = new JButton("Establecer propiedades comunes a todos los tramos");
			propComunes.addActionListener(new ActionListener() 
			{
			
				public void actionPerformed(ActionEvent e) 
				{
					PanelPropComunes panelPC = new PanelPropComunes(panel);
					panelPC.setTitle("Establecer propiedades comunes"); //$NON-NLS-1$
					panelPC.setBounds(80, 120, 500, 300);
					panelPC.setVisible(true);
				}
			
			});
			panelVias.add(propComunes);
		}
		else {
			JLabel noAsociadoVia = new JLabel(Messages.getString("PanelTramo.20")); //$NON-NLS-1$
			panelVias.add(noAsociadoVia);
		}
		
		//JPanel panelNodos = new JPanel();
	    //JPanel panelSe�ales = new JPanel();
	      
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
	    
	    //A�adido para el nombre
	    JPanel panelNombre = new JPanel();
	    panelNombre.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
	    JLabel etiquetaNombre = new JLabel(Messages.getString("PanelTramo.29")); //$NON-NLS-1$
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
	    JLabel etiqVelocidad = new JLabel(Messages.getString("PanelTramo.30")); //$NON-NLS-1$
	    JLabel etiqLongitud = new JLabel(Messages.getString("PanelTramo.31")); //$NON-NLS-1$

	    int longitud = tramo.getLargo();
	    String auxLongitud = String.valueOf(longitud);
	    JLabel etiqInfoLongitud = new JLabel(auxLongitud);
	    panelCamposVelocidad.add(etiqLongitud);
	    panelCamposVelocidad.add(etiqInfoLongitud);
	    
	    campoVelocidad = new JSpinner(new SpinnerNumberModel(60,20,120,10));
	    panelCamposVelocidad.add(etiqVelocidad);
	    panelCamposVelocidad.add(campoVelocidad);
	    
	    
	    panelVelocidad.setBorder(BorderFactory.createTitledBorder(Messages.getString("PanelTramo.32"))); //$NON-NLS-1$
	    panelVelocidad.add(panelCamposVelocidad,BorderLayout.NORTH);
	    //A�adido
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
	    
	    panelDatos.addTab(Messages.getString("PanelTramo.33"),null, panelPropiedades, Messages.getString("PanelTramo.34")); //$NON-NLS-1$ //$NON-NLS-2$
	    panelDatos.addTab(Messages.getString("PanelTramo.35"),null, panelVias, Messages.getString("PanelTramo.36")); //$NON-NLS-1$ //$NON-NLS-2$
	    panelDatos.setSelectedIndex(0);
	    
	  /*panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Tramo");
	    panelDatos.setSelectedIndex(0);
		panelDatos.addTab("Se�ales", null, panelSe�ales,"Se�ales asociadas al Tramo");
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
	
		botonAceptar = new JButton(Messages.getString("PanelTramo.37")); //$NON-NLS-1$
		botonCancelar = new JButton(Messages.getString("PanelTramo.38")); //$NON-NLS-1$
		
		final PanelTramo panelPpal = this;
		AccionAceptarTramo accion = new AccionAceptarTramo(panel,this,controlador, tramo, radioUnidireccional, 
				radioSentido1,campoCarril1Numero, campoCarril2Numero, campoVelocidad, campoNombre, nombreVia,
				combo_tipoVia);
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
