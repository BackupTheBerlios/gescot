package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.TipoElemento.TipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoHighway;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoLeisure;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoManMade;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class AccionSeleccionarTipo implements ActionListener {

	JComboBox comboTipo;
	JComboBox comboValor;
	
	public AccionSeleccionarTipo(JComboBox comboTipo,JComboBox comboValor) {
		this.comboTipo = comboTipo;
		this.comboValor = comboValor;
		if (comboTipo.getSelectedIndex()==0)
			comboValor.setEnabled(false);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (comboTipo.getSelectedItem().equals("Carretera")) {
			comboValor.setEnabled(true);
			TipoElemento inf=new TipoNodoHighway("Cambio De Rasante");
			String[] s1=inf.devolverListaValores();
			//String[] s1={"Cambio De Rasante", "Cruce", "Mini-rotonda", "Port�n para veh�culos", "Puente", "Stop", "Viaducto"};
			comboValor.removeAllItems();
			for (int i=0;i<s1.length;i++)
				comboValor.addItem(s1[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Tiempo Libre")) {
			comboValor.setEnabled(true);
			TipoElemento inf=new TipoNodoLeisure("Campo de deporte");
			String[] s2=inf.devolverListaValores();
			//String[] s2={"Campo de deporte", "Campo de golf", "Estadio", "Jard�n", "Marina", "Parque", "Parque acu�tico", "Pista de carreras"};
			comboValor.removeAllItems();
			for (int i=0;i<s2.length;i++)
				comboValor.addItem(s2[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Construcci�n")) {
			comboValor.setEnabled(true);
			TipoElemento inf=new TipoNodoManMade("Central el�ctrica");
			String[] s3=inf.devolverListaValores();
			//String[] s3={"Central el�ctrica", "Central nuclear", "Faro", "Planta e�lica","Planta hidroel�ctrica"};
			comboValor.removeAllItems();
			for (int i=0;i<s3.length;i++)
				comboValor.addItem(s3[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Infraestructura")) {
			comboValor.setEnabled(true);
			TipoElemento inf=new TipoNodoAmenity("Pub");
			String[] s4=inf.devolverListaValores();
			comboValor.removeAllItems();
			for (int i=0;i<s4.length;i++)
				comboValor.addItem(s4[i]);
		}
		
		else {
			comboValor.removeAllItems();
			comboValor.addItem("                     ");
			comboValor.setEnabled(false);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
