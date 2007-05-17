package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoHighway;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade;
import is.SimTraffic.Vista.Acciones.PanelNodo.AccionSeleccionarTipo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class BarraCrearNodo extends Barra 
{
	private static final long serialVersionUID = 1L;
	private JComboBox comboTipo;
	private JComboBox comboValor;
	private JComboBox comboFun;
	private JSpinner valorFrec;
	private JTextField nombre;

	public BarraCrearNodo()
	{
		JLabel etiquetaTipo = new JLabel(Messages.getString("BarraCrearNodo.0")); //$NON-NLS-1$
		String[] tiposNodos = { Messages.getString("BarraCrearNodo.1"), Messages.getString("BarraCrearNodo.2"), Messages.getString("BarraCrearNodo.3"), Messages.getString("BarraCrearNodo.4"), Messages.getString("BarraCrearNodo.5")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		comboTipo = new JComboBox(tiposNodos);
		
		JLabel etiquetaValor = new JLabel(Messages.getString("BarraCrearNodo.6")); //$NON-NLS-1$
		String[] valorNodos = { Messages.getString("BarraCrearNodo.7")}; //$NON-NLS-1$
		comboValor = new JComboBox(valorNodos);
		
		JLabel etiquetaFuncionalidad = new JLabel(Messages.getString("BarraCrearNodo.8")); //$NON-NLS-1$
		String[] valorFun = {Messages.getString("BarraCrearNodo.9"), Messages.getString("BarraCrearNodo.10")}; //$NON-NLS-1$ //$NON-NLS-2$
		comboFun = new JComboBox(valorFun);
		final JLabel etiquetaFrecuencia = new JLabel(Messages.getString("BarraCrearNodo.11")); //$NON-NLS-1$
		valorFrec = new JSpinner(new SpinnerNumberModel(50,1,100,1));
		etiquetaFrecuencia.setEnabled(false);
		valorFrec.setEnabled(false);
		
		JLabel etiquetaNombre = new JLabel(Messages.getString("BarraCrearNodo.12")); //$NON-NLS-1$
		nombre = new JTextField();
		
		comboTipo.addActionListener(new AccionSeleccionarTipo(comboTipo,comboValor));
		comboFun.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if (comboFun.getSelectedItem().equals(Messages.getString("BarraCrearNodo.13"))) //$NON-NLS-1$
				{
					etiquetaFrecuencia.setEnabled(true);
					valorFrec.setEnabled(true);
				}
				else
				{
					etiquetaFrecuencia.setEnabled(false);
					valorFrec.setEnabled(false);
				}
				
			}
		});
		
		add(etiquetaTipo);
		add(comboTipo);
		add(etiquetaValor);
		add(comboValor);
		add(etiquetaFuncionalidad);
		add(comboFun);
		add(etiquetaFrecuencia);
		add(valorFrec);
		add(etiquetaNombre);
		add(nombre);
	}
	
	public Nodo prepararNodo(Posicion p) 
	{
		ITipoElemento tipo;
		String type = (String) comboTipo.getSelectedItem();
		if (type.equals(Messages.getString("BarraCrearNodo.14"))) //$NON-NLS-1$
			tipo = new TipoNodoHighway((String) comboValor.getSelectedItem());
		else if (type.equals(Messages.getString("BarraCrearNodo.15"))) //$NON-NLS-1$
			tipo = new TipoNodoAmenity((String) comboValor.getSelectedItem());
		else if (type.equals(Messages.getString("BarraCrearNodo.16"))) //$NON-NLS-1$
			tipo = new TipoNodoLeisure((String) comboValor.getSelectedItem());
		else if (type.equals(Messages.getString("BarraCrearNodo.17"))) //$NON-NLS-1$
			tipo = new TipoNodoManMade((String) comboValor.getSelectedItem());
		else 
			tipo = null;
		Nodo nodo = new Nodo(Nodo.id, nombre.getText(), p, tipo);
		return nodo;
	}
}
