package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoHighway;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade;
import is.SimTraffic.Vista.PanelMapa;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;

public class AccionAceptar implements ActionListener {

	private Nodo nodo;
	private JComboBox comboTipo;
	private JComboBox comboValor;
	private JTextField campoFrecuencia;
	private JTextField campoNombre;
	private PanelNodo panel;
	private PanelMapa mapa;

	public AccionAceptar(Nodo nodo, JComboBox comboTipo, JComboBox comboValor, JTextField campoFrecuencia, JTextField campoNombre, PanelNodo panel, PanelMapa mapa) {
		// TODO Auto-generated constructor stub
		this.nodo = nodo;
		this.comboTipo = comboTipo;
		this.comboValor = comboValor;
		this.campoFrecuencia = campoFrecuencia;
		this.campoNombre = campoNombre;
		this.panel = panel;
		this.mapa = mapa;
	}

	public void actionPerformed(ActionEvent arg0) {
		ITipoElemento tipo=null;
		if (comboTipo.getSelectedItem().equals("Carretera")) {
			tipo = new TipoNodoHighway((String)(comboValor.getSelectedItem()));
		}
		else if (comboTipo.getSelectedItem().equals("Tiempo Libre")) {
			tipo = new TipoNodoLeisure((String)(comboValor.getSelectedItem()));
		}
		else if (comboTipo.getSelectedItem().equals("Construcción")) {
			tipo = new TipoNodoManMade((String)(comboValor.getSelectedItem()));
		}
		else if (comboTipo.getSelectedItem().equals("Infraestructura")) {
			tipo = new TipoNodoAmenity((String)(comboValor.getSelectedItem()));
		}
		else { //No se ha seleccionado ninguno concreto
			//nodo.setTipo(tipo);
		}
		nodo.setTipo(tipo);
		
		String nuevoNombre=campoNombre.getText();
		//System.out.println(nuevoNombre);
		
		if (nuevoNombre.equals("")) 
			nodo.setNombre(null);
		else {
			nodo.setNombre(nuevoNombre);
		}
	
		mapa.repaint();
		mapa.recrearMapa();
		panel.dispose();
	}

}
