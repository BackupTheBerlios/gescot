package is.SimTraffic.Vista.Acciones.PanelTramo;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HModificarTramo;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelTramo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class AccionAceptarTramo implements ActionListener
{
	private Tramo tramo;
	private JRadioButton seleccionadoUnidireccional;
	private JRadioButton seleccionadoSentido1;
	private JSpinner numCarr1;
	private JSpinner numCarr2;
	private JSpinner vel;
	private PanelMapa mapa;
	private PanelTramo panel;
	private int auxCarriles1,auxCarriles2;
	private IControlador controlador;
	private JTextField campoNombre;
	private String nombre;
	//Info de vía
	private JTextField nombreVia;
	private JComboBox combo_tipoVia;
	private String nombreStringVia;
	private String tipoStringVia;
	
	public AccionAceptarTramo(PanelMapa mapa, PanelTramo panel,IControlador controlador,Tramo tramo, JRadioButton radioUnidireccional, JRadioButton radioSentido1, JSpinner campoCarril1Numero, JSpinner campoCarril2Numero, JSpinner campoVelocidad, JTextField campoNombre,JTextField nombreVia, JComboBox combo_tipoVia) 
	{
		this.panel = panel;
		this.mapa = mapa;
		this.controlador =controlador;
		this.tramo = tramo;
		this.seleccionadoUnidireccional = radioUnidireccional;
		this.seleccionadoSentido1 = radioSentido1;
		this.numCarr1 = campoCarril1Numero;
		this.numCarr2 = campoCarril2Numero;
		this.vel = campoVelocidad;
		this.auxCarriles1=0;
		this.auxCarriles2=0;
		this.campoNombre = campoNombre;
		this.nombreVia = nombreVia;
		this.combo_tipoVia = combo_tipoVia;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (seleccionadoUnidireccional.isSelected())
		{
			if (seleccionadoSentido1.isSelected())
			{
				auxCarriles1=((Integer)(numCarr1.getValue())).intValue();
			}
			else
			{
				auxCarriles2=((Integer)(numCarr2.getValue())).intValue();
			}
		}
		else
		{
			auxCarriles1=((Integer)(numCarr1.getValue())).intValue();
		    auxCarriles2=((Integer)(numCarr2.getValue())).intValue();
		}
		
		nombre = campoNombre.getText();
		//TODO fijarse que error ahi aqui
		
		//nombreStringVia = nombreVia.getText();
		//tipoStringVia = (String) combo_tipoVia.getSelectedItem();
		
		System.out.println(tipoStringVia);
		HModificarTramo nueva = new HModificarTramo(tramo,auxCarriles1,auxCarriles2,((Integer)(vel.getValue())).floatValue(),nombre,nombreStringVia,tipoStringVia);
		controlador.herramienta(nueva);
		mapa.repaint();
		mapa.recrearMapa();
		panel.dispose();
	}

}
