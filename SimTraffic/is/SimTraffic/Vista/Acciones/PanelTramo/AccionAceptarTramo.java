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
	private int numCarr1, numCarr2, velSent1, velSent2;
	private PanelMapa mapa;
	private PanelTramo panel;
	
	
	
	public AccionAceptarTramo(PanelMapa mapa, PanelTramo panel,Tramo tramo, boolean seleccionadoUnidireccional, boolean seleccionadoSentido1, int numCarr1, int numCarr2, int velSent1, int velSent2) 
	{
		this.panel = panel;
		this.mapa = mapa;
		this.tramo = tramo;
		this.seleccionadoUnidireccional = seleccionadoUnidireccional;
		this.seleccionadoSentido1 = seleccionadoSentido1;
		this.numCarr1 = numCarr1;
		this.numCarr2 = numCarr2;
		this.velSent1 = velSent1;
		this.velSent2 = velSent2;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		if (seleccionadoUnidireccional)
		{
			if (seleccionadoSentido1)
			{
				tramo.setNumCarrilesDir1(numCarr1);
				tramo.setNumCarrilesDir2(0);
				tramo.setVelMax(velSent1);
			}
			else
			{
				tramo.setNumCarrilesDir2(numCarr2);
				tramo.setNumCarrilesDir2(0);
				tramo.setVelMax(velSent2);
			}
		}
		else
		{
			tramo.setNumCarrilesDir1(numCarr1);
			tramo.setNumCarrilesDir2(numCarr2);
			tramo.setVelMax(velSent1);  //¿Falta un atributo en los tramos para que ambos sentidos puedan tener velocidades distintas
										// o sobra un spinner en la ventana de propiedades de los tramos?
		}
		mapa.repaint();
		mapa.recrearMapa();
		panel.dispose();
	}

}
