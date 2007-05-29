/**
 * 
 */
package is.SimTraffic.Vista;

import is.SimTraffic.IControlador;
import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.Acciones.PanelTramo.AccionAceptarTramo;
import is.SimTraffic.Vista.Acciones.PanelTramo.AccionPropComunes;

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
	private JTabbedPane panelDatos;
	
	//Info de vía
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
		
		setResizable(false);
		//Antes comentado
		this.add(panelDatos);
		
		this.add(panelBotones,BorderLayout.SOUTH);
		
	}
	
	public void creaPanelDatos(){
		
		JPanel panelPropiedades = new JPanel();
				
		JPanel panelVias = crearPanelVías();
		
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
		
		panelPropiedades.add(panelSentido);
		panelPropiedades.add(panelNumCarriles);
		panelPropiedades.add(panelVelocidad);  
		this.add(panelPropiedades);
		
		panelDatos.addTab(Messages.getString("PanelTramo.33"),null, panelPropiedades, Messages.getString("PanelTramo.34")); //$NON-NLS-1$ //$NON-NLS-2$
		panelDatos.addTab(Messages.getString("PanelTramo.35"),null, panelVias, Messages.getString("PanelTramo.36")); //$NON-NLS-1$ //$NON-NLS-2$
		panelDatos.setSelectedIndex(0);
		
		/*panelDatos.addTab("Propiedades",null, panelPropiedades, "Propiedades del Tramo");
		 panelDatos.setSelectedIndex(0);
		 panelDatos.addTab("Señales", null, panelSeñales,"Señales asociadas al Tramo");
		 panelDatos.addTab("Nodos", null, panelNodos,"Nodos asociados al Tramo");
		 */
	}
	
	private JPanel crearPanelVelocidad() 
	{
		JPanel panelSelección = new JPanel(new BorderLayout());
		JPanel labels = new JPanel(new GridLayout(3,1,2,2));
		JPanel selecciones = new JPanel(new GridLayout(3,1,2,2));
		
		JPanel panelVelocidad = new JPanel();
		
		JLabel etiquetaNombre = new JLabel(Messages.getString("PanelTramo.29")); //$NON-NLS-1$
		campoNombre = new JTextField(7);
		if (tramo.getNombre()!=null)
			campoNombre.setText(tramo.getNombre());
		labels.add(etiquetaNombre);
		selecciones.add(campoNombre);	
		
		JLabel etiqVelocidad = new JLabel(Messages.getString("PanelTramo.30")); //$NON-NLS-1$
		JLabel etiqLongitud = new JLabel(Messages.getString("PanelTramo.31")); //$NON-NLS-1$
		
		int longitud = tramo.getLargo();
		String auxLongitud = String.valueOf(longitud);
		JTextField fieldInfoLongitud = new JTextField(auxLongitud);
		fieldInfoLongitud.setHorizontalAlignment(JTextField.RIGHT);
		fieldInfoLongitud.setEditable(false);
		labels.add(etiqLongitud);
		selecciones.add(fieldInfoLongitud);
		
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
	
	private JPanel crearPanelVías() 
	{
		JPanel panelGlobal = new JPanel(new BorderLayout());
		JPanel panelVias = new JPanel(new FlowLayout());
		panelGlobal.add(panelVias);
		if (tramo.getVia() != null) 
		{
			JPanel panelSelección = new JPanel(new BorderLayout());
			JPanel labels = new JPanel(new GridLayout(2,1,2,2));
			JPanel selecciones = new JPanel(new GridLayout(2,1,2,2));
			JLabel asociadoVia = new JLabel(Messages.getString("PanelTramo.0")); //$NON-NLS-1$
			JLabel observacionVia = new JLabel(Messages.getString("PanelTramo.1")); //$NON-NLS-1$
			panelVias.add(asociadoVia);
			panelVias.add(observacionVia);
			panelVias.add(panelSelección);
			JLabel nombreEtiqVia = new JLabel(Messages.getString("PanelTramo.2"));				 //$NON-NLS-1$
			nombreVia = new JTextField(15);
			nombreVia.setText(tramo.getVia().getNombre());
			labels.add(nombreEtiqVia);
			selecciones.add(nombreVia);
			if (tramo.getVia().getTipo()!=null /*&& tramo.getVia().getTipo().equals("highway")*/) {
				JLabel tipoEtiqVia = new JLabel(Messages.getString("PanelTramo.3")); //$NON-NLS-1$
				//JLabel tipoVia = new JLabel(tramo.getVia().getTipo().getValorTipoCastellano());
				String[] tiposVias = { Messages.getString("PanelTramo.4"),  //$NON-NLS-1$
						Messages.getString("PanelTramo.5"), //$NON-NLS-1$
						Messages.getString("PanelTramo.6"),  //$NON-NLS-1$
						Messages.getString("PanelTramo.7"), //$NON-NLS-1$
						Messages.getString("PanelTramo.8"), //$NON-NLS-1$
						Messages.getString("PanelTramo.9"), //$NON-NLS-1$
						Messages.getString("PanelTramo.10"), //$NON-NLS-1$
						Messages.getString("PanelTramo.11"), //$NON-NLS-1$
						Messages.getString("PanelTramo.12"), //$NON-NLS-1$
						Messages.getString("PanelTramo.13"), //$NON-NLS-1$
						Messages.getString("PanelTramo.14"), //$NON-NLS-1$
						Messages.getString("PanelTramo.15"), //$NON-NLS-1$
						Messages.getString("PanelTramo.16"),  //$NON-NLS-1$
						Messages.getString("PanelTramo.17"), //$NON-NLS-1$
						Messages.getString("PanelTramo.18"), //$NON-NLS-1$
						Messages.getString("PanelTramo.19")};  //$NON-NLS-1$
				
				combo_tipoVia = new JComboBox(tiposVias);
				labels.add(tipoEtiqVia);
				selecciones.add(combo_tipoVia);
				
				panelSelección.add(labels, BorderLayout.WEST);
				panelSelección.add(selecciones, BorderLayout.EAST);
				//System.out.println(tramo.getVia().getTipo().getValorTipoCastellano());
				for (int i=0;i<tiposVias.length;i++) {
					if ( tramo.getVia().getTipo().getValorTipoCastellano().equals(tiposVias[i]) )
						combo_tipoVia.setSelectedIndex(i);
				}
			}
			JButton propComunes = new JButton(Messages.getString("PanelTramo.39")); //$NON-NLS-1$
			propComunes.addActionListener(new AccionPropComunes(tramo, this, panel, controlador, nombreVia, combo_tipoVia));
			panelVias.add(propComunes);
		}
		else {
			JLabel noAsociadoVia = new JLabel(Messages.getString("PanelTramo.20")); //$NON-NLS-1$
			panelVias.add(noAsociadoVia);
		}
		return panelGlobal;
	}
	
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
		
		panelBotones.setLayout(new GridLayout(1,2,2,2));
		
		panelBotones.add(botonAceptar);
		panelBotones.add(botonCancelar);
	}
	
	
}
