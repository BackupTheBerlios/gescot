package is.SimTraffic.Vista.BarrasHerramientas;

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
		JLabel etiquetaTipo = new JLabel("  Tipo  ");
		String[] tiposNodos = { "                  ", "Carretera", "Tiempo Libre", "Construcción", "Infraestructura", "No definido"};
		comboTipo = new JComboBox(tiposNodos);
		
		JLabel etiquetaValor = new JLabel("  Valor  ");
		String[] valorNodos = { "                  "};
		comboValor = new JComboBox(valorNodos);
		
		JLabel etiquetaFuncionalidad = new JLabel("  Funcionalidad  ");
		String[] valorFun = {"Normal", "Entrada/Salida"};
		comboFun = new JComboBox(valorFun);
		final JLabel etiquetaFrecuencia = new JLabel("  Frecuencia  ");
		valorFrec = new JSpinner(new SpinnerNumberModel(50,1,100,1));
		etiquetaFrecuencia.setEnabled(false);
		valorFrec.setEnabled(false);
		
		JLabel etiquetaNombre = new JLabel("  Nombre  ");
		nombre = new JTextField();
		
		comboTipo.addActionListener(new AccionSeleccionarTipo(comboTipo,comboValor));
		comboFun.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if (comboFun.getSelectedItem().equals("Entrada/Salida"))
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
		if (type.equals("Carretera"))
			tipo = new TipoNodoHighway((String) comboValor.getSelectedItem());
		else if (type.equals("Infraestructura"))
			tipo = new TipoNodoAmenity((String) comboValor.getSelectedItem());
		else if (type.equals("Tiempo Libre"))
			tipo = new TipoNodoLeisure((String) comboValor.getSelectedItem());
		else if (type.equals("Construcción"))
			tipo = new TipoNodoManMade((String) comboValor.getSelectedItem());
		else 
			tipo = null;
		Nodo nodo = new Nodo(Nodo.id, nombre.getText(), p, tipo);
		return nodo;
	}
}
