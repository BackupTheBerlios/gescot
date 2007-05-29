package is.SimTraffic.Vista.Acciones.PanelTramo;

import is.SimTraffic.IControlador;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelPropComunes;
import is.SimTraffic.Vista.PanelTramo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AccionPropComunes implements ActionListener
{

	private Tramo tramo;
	private PanelTramo panelTramo;
	private PanelMapa panelMapa;
	private IControlador controlador;
	private JTextField nombreVia;
	private JComboBox tipoVia;
	
	public AccionPropComunes(Tramo tramo, PanelTramo ptramo, PanelMapa panel, IControlador controlador, JTextField nombreVia, JComboBox tipoVia) 
	{
		this.tramo = tramo;
		this.panelTramo = ptramo;
		this.panelMapa = panel;
		this.controlador = controlador;
		this.nombreVia = nombreVia;
		this.tipoVia = tipoVia;
	}

	public void actionPerformed(ActionEvent arg0) 
	{
		PanelPropComunes panelPC = new PanelPropComunes(tramo, panelTramo, panelMapa, controlador, nombreVia, tipoVia);
		panelPC.setTitle("Establecer propiedades comunes"); //$NON-NLS-1$
		panelPC.setBounds(80, 120, 303, 313);
		panelPC.setVisible(true);
	}

}
