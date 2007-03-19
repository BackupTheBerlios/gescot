package is.SimTraffic.Vista.Acciones.PanelTramo;

import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelTramo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccionAceptarTramo implements ActionListener
{
	private Tramo tramo;
	private boolean seleccionadoUnidireccional;
	private boolean seleccionadoSentido1;
	private int numCarr1, numCarr2;
	private float vel;
	private PanelMapa mapa;
	private PanelTramo panel;
	
	
	
	public AccionAceptarTramo(PanelMapa mapa, PanelTramo panel,Tramo tramo, boolean seleccionadoUnidireccional, boolean seleccionadoSentido1, int numCarr1, int numCarr2, int vel) 
	{
		this.panel = panel;
		this.mapa = mapa;
		this.tramo = tramo;
		this.seleccionadoUnidireccional = seleccionadoUnidireccional;
		this.seleccionadoSentido1 = seleccionadoSentido1;
		this.numCarr1 = numCarr1;
		this.numCarr2 = numCarr2;
		this.vel = vel;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (seleccionadoUnidireccional)
		{
			if (seleccionadoSentido1)
			{
				tramo.setNumCarrilesDir1(numCarr1);
				tramo.setNumCarrilesDir2(0);
				tramo.setVelMax(vel);
			}
			else
			{
				tramo.setNumCarrilesDir2(numCarr2);
				tramo.setNumCarrilesDir2(0);
				tramo.setVelMax(vel);
			}
		}
		else
		{
			tramo.setNumCarrilesDir1(numCarr1);
			tramo.setNumCarrilesDir2(numCarr2);
			tramo.setVelMax(vel); 
		}
		mapa.repaint();
		mapa.recrearMapa();
		panel.dispose();
	}

}
