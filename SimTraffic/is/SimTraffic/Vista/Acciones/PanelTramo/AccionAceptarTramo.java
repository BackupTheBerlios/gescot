package is.SimTraffic.Vista.Acciones.PanelTramo;

import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelTramo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JSpinner;

public class AccionAceptarTramo implements ActionListener
{
	private Tramo tramo;
	private JRadioButton seleccionadoUnidireccional;
	private JRadioButton seleccionadoSentido1;
	JSpinner numCarr1;
	private JSpinner numCarr2;
	private JSpinner vel;
	private PanelMapa mapa;
	private PanelTramo panel;
	
	
	
	public AccionAceptarTramo(PanelMapa mapa, PanelTramo panel,Tramo tramo, JRadioButton radioUnidireccional, JRadioButton radioSentido1, JSpinner campoCarril1Numero, JSpinner campoCarril2Numero, JSpinner campoVelocidad) 
	{
		this.panel = panel;
		this.mapa = mapa;
		this.tramo = tramo;
		this.seleccionadoUnidireccional = radioUnidireccional;
		this.seleccionadoSentido1 = radioSentido1;
		this.numCarr1 = campoCarril1Numero;
		this.numCarr2 = campoCarril2Numero;
		this.vel = campoVelocidad;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (seleccionadoUnidireccional.isSelected())
		{
			if (seleccionadoSentido1.isSelected())
			{
				tramo.setNumCarrilesDir1(((Integer)(numCarr1.getValue())).intValue());
				tramo.setNumCarrilesDir2(0);
			}
			else
			{
				tramo.setNumCarrilesDir2(((Integer)(numCarr2.getValue())).intValue());
				tramo.setNumCarrilesDir1(0);
			}
		}
		else
		{
			tramo.setNumCarrilesDir1(((Integer)(numCarr1.getValue())).intValue());
			tramo.setNumCarrilesDir2(((Integer)(numCarr2.getValue())).intValue());
		}
		tramo.setVelMax(((Integer)(vel.getValue())).floatValue()); 
		mapa.repaint();
		mapa.recrearMapa();
		panel.dispose();
	}

}
