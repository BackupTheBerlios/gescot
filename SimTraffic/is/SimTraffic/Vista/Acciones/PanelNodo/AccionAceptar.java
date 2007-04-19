package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.EntradaSalida;
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
	private JComboBox comboTipoNodo;
	private JComboBox comboValorNodo;
	private EntradaSalida es;
	private JTextField campoNombreNodo;
	private PanelNodo panelNodo;
	private JComboBox comboTipoSeñales;
	private JComboBox comboTipoSemaforos;
	private JTextField campoTiempoCicloSemaforo;
	private PanelMapa mapa;

	public AccionAceptar(Nodo nodo, JComboBox comboTipoNodo, JComboBox comboValorNodo,
			EntradaSalida es,	JTextField campoNombreNodo, PanelNodo panelNodo, 
			JComboBox comboTipoSemaforos, JTextField campoTiempoCicloSemaforo, JComboBox comboTipoSeñales,
			PanelMapa mapa) {
		
		this.nodo = nodo;
		this.comboTipoNodo = comboTipoNodo;
		this.comboValorNodo = comboValorNodo;
		this.es = es;
		this.campoNombreNodo = campoNombreNodo;
		this.panelNodo = panelNodo;
		this.comboTipoSeñales=comboTipoSeñales;
		this.comboTipoSemaforos=comboTipoSemaforos;
		this.campoTiempoCicloSemaforo=campoTiempoCicloSemaforo;
		this.mapa = mapa;
	}

	public void actionPerformed(ActionEvent arg0) {
		ITipoElemento tipo=null;
		if (comboTipoNodo.getSelectedItem().equals("Carretera")) {
			tipo = new TipoNodoHighway((String)(comboValorNodo.getSelectedItem()));
		}
		else if (comboTipoNodo.getSelectedItem().equals("Tiempo Libre")) {
			tipo = new TipoNodoLeisure((String)(comboValorNodo.getSelectedItem()));
		}
		else if (comboTipoNodo.getSelectedItem().equals("Construcción")) {
			tipo = new TipoNodoManMade((String)(comboValorNodo.getSelectedItem()));
		}
		else if (comboTipoNodo.getSelectedItem().equals("Infraestructura")) {
			tipo = new TipoNodoAmenity((String)(comboValorNodo.getSelectedItem()));
		}
		else { //No se ha seleccionado ninguno concreto
			//nodo.setTipo(tipo);
		}
		nodo.setTipo(tipo);
		
		nodo.setEs(panelNodo.generarEs());
		
		String nuevoNombre=campoNombreNodo.getText();
		//System.out.println(nuevoNombre);
		
		if (nuevoNombre.equals("")) 
			nodo.setNombre(null);
		else {
			nodo.setNombre(nuevoNombre);
		}
		
		
		// TODO Guardar los atributos del nodo referentes a las señales

		
		
	
		mapa.repaint();
		mapa.recrearMapa();
		panelNodo.dispose();
	}

}
