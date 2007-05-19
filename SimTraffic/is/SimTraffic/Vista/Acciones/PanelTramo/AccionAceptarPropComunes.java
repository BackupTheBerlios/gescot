package is.SimTraffic.Vista.Acciones.PanelTramo;

import is.SimTraffic.IControlador;
import is.SimTraffic.Herramientas.HModificarPropComunes;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelPropComunes;
import is.SimTraffic.Vista.PanelTramo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

public class AccionAceptarPropComunes implements ActionListener
{
	private Tramo tramo;
	private JRadioButton seleccionadoUnidireccional;
	private JRadioButton seleccionadoSentido1;
	private JSpinner numCarr1;
	private JSpinner numCarr2;
	private JSpinner vel;
	private PanelMapa mapa;
	private PanelPropComunes panelpc;
	private PanelTramo panelTramo;
	private int auxCarriles1,auxCarriles2;
	private IControlador controlador;
	private JTextField nombreVia;
	private JComboBox tipoVia;

	public AccionAceptarPropComunes(PanelMapa panel, PanelPropComunes ppc, PanelTramo pt, IControlador controlador, Tramo tramo, JRadioButton radioUnidireccional, JRadioButton radioSentido1, JSpinner campoCarril1Numero, JSpinner campoCarril2Numero, JSpinner campoVelocidad, JTextField nombreVia, JComboBox tipoVia) 
	{
		this.mapa = panel;
		this.controlador =controlador;
		this.tramo = tramo;
		this.seleccionadoUnidireccional = radioUnidireccional;
		this.seleccionadoSentido1 = radioSentido1;
		this.numCarr1 = campoCarril1Numero;
		this.numCarr2 = campoCarril2Numero;
		this.vel = campoVelocidad;
		this.auxCarriles1=0;
		this.auxCarriles2=0;
		this.panelpc = ppc;
		this.panelTramo = pt;
		this.nombreVia = nombreVia;
		this.tipoVia = tipoVia;
	}

	public void actionPerformed(ActionEvent e) 
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
		String nombreStringVia = nombreVia.getText();
		String tipoStringVia = (String) tipoVia.getSelectedItem();
		
		HModificarPropComunes nueva = new HModificarPropComunes(tramo,auxCarriles1,auxCarriles2,((Integer)(vel.getValue())).floatValue(), nombreStringVia, tipoStringVia);
		controlador.herramienta(nueva);
		mapa.repaint();
		mapa.recrearMapa();
		panelpc.dispose();
		panelTramo.dispose();
	}

}
