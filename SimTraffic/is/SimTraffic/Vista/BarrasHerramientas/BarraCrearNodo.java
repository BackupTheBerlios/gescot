package is.SimTraffic.Vista.BarrasHerramientas;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Señal;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoHighway;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Vista.Acciones.PanelNodo.AccionSeleccionarTipo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BarraCrearNodo extends Barra 
{
	private static final long serialVersionUID = 1L;
	private JComboBox comboTipo;
	private JComboBox comboValor;
	private JComboBox comboInt;
	private JSpinner valorSalen;
	private JSpinner valorEntran;
	private JTextField nombre;
	private int[] porcentajeEntrada = new int[3];
	private int[] porcentajeSalida = new int[3];


	public BarraCrearNodo()
	{
		JLabel etiquetaTipo = new JLabel(Messages.getString("BarraCrearNodo.0")); //$NON-NLS-1$
		String[] tiposNodos = { Messages.getString("BarraCrearNodo.1"), Messages.getString("BarraCrearNodo.2"), Messages.getString("BarraCrearNodo.3"), Messages.getString("BarraCrearNodo.4"), Messages.getString("BarraCrearNodo.5")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		comboTipo = new JComboBox(tiposNodos);
		
		JLabel etiquetaValor = new JLabel(Messages.getString("BarraCrearNodo.6")); //$NON-NLS-1$
		String[] valorNodos = { Messages.getString("BarraCrearNodo.7")}; //$NON-NLS-1$
		comboValor = new JComboBox(valorNodos);
		
		JLabel etiquetaIntervalo = new JLabel(Messages.getString("BarraCrearNodo.8")); //$NON-NLS-1$
		String[] valorIntervalo = {Messages.getString("BarraCrearNodo.9"), Messages.getString("BarraCrearNodo.10"), Messages.getString("BarraCrearNodo.18")}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		comboInt = new JComboBox(valorIntervalo);
		final JLabel etiquetaSalen = new JLabel(Messages.getString("BarraCrearNodo.11")); //$NON-NLS-1$
		valorSalen = new JSpinner(new SpinnerNumberModel(0,0,9999,50));
		etiquetaSalen.setEnabled(true);
		valorSalen.setEnabled(true);
		
		final JLabel etiquetaEntran = new JLabel(Messages.getString("BarraCrearNodo.19")); //$NON-NLS-1$
		valorEntran = new JSpinner(new SpinnerNumberModel(0,0,9999,50));
		etiquetaEntran.setEnabled(true);
		valorEntran.setEnabled(true);
		
		JLabel etiquetaNombre = new JLabel(Messages.getString("BarraCrearNodo.12")); //$NON-NLS-1$
		nombre = new JTextField();
		
		comboTipo.addActionListener(new AccionSeleccionarTipo(comboTipo,comboValor));
		
		valorSalen.addChangeListener(new ChangeListener(){			
			public void stateChanged(ChangeEvent e) {
				ActualizarVectorPorcentajesSalen();
			}

		});

		valorEntran.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
				ActualizarVectorPorcentajesEntran();
			}
		});
		
		comboInt.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				ActualizarVectorPorcentajes();
				
			}
			
		});
	
		
		add(etiquetaTipo);
		add(comboTipo);
		add(etiquetaValor);
		add(comboValor);
		add(etiquetaIntervalo);
		add(comboInt);
		add(etiquetaSalen);
		add(valorSalen);
		add(etiquetaEntran);
		add(valorEntran);		
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
		EntradaSalida es = new EntradaSalida(porcentajeEntrada,porcentajeSalida);
		//Nodo(EntradaSalida es, int id, String nombre, Posicion pos, Señal señal, ITipoElemento tipo, List<Tramo> tramos)
		Nodo nodo = new Nodo(es,Nodo.id, nombre.getText(), p, null,tipo,new ArrayList<Tramo>());
		return nodo;			
	}
	private void ActualizarVectorPorcentajesEntran() {
		int i=this.comboInt.getSelectedIndex();
		this.porcentajeEntrada[i]=((Integer)this.valorEntran.getValue()).intValue();		
	}
	private void ActualizarVectorPorcentajesSalen() {
		int i=this.comboInt.getSelectedIndex();
		this.porcentajeSalida[i]=((Integer)this.valorSalen.getValue()).intValue();		
	}
	public void ActualizarVectorPorcentajes(){
		int i=this.comboInt.getSelectedIndex();
		//this.porcentajeEntrada[i]=((Integer)this.valorEntran.getValue()).intValue();
		//this.porcentajeSalida[i]=((Integer)this.valorSalen.getValue()).intValue();
		this.valorEntran.setValue(new Integer(this.porcentajeEntrada[i]));
		this.valorSalen.setValue(new Integer(this.porcentajeSalida[i]));
	}
	


}
