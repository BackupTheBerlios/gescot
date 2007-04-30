package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.TipoElemento.TipoElemento;
import is.SimTraffic.Mapa.TipoElemento.TipoNodoAmenity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class AccionSeleccionarTipo implements ActionListener {

	JComboBox comboTipo;
	JComboBox comboValor;
	
	public AccionSeleccionarTipo(JComboBox comboTipo,JComboBox comboValor) {
		this.comboTipo = comboTipo;
		this.comboValor = comboValor;
		comboValor.setEnabled(false);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (comboTipo.getSelectedItem().equals("Carretera")) {
			comboValor.setEnabled(true);
			String[] s1={"Mini-rotonda","Stop","Cruce","Port�n para veh�culos", "Cambio De Rasante", "Puente", "Viaducto"};
			comboValor.removeAllItems();
			for (int i=0;i<s1.length;i++)
				comboValor.addItem(s1[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Tiempo Libre")) {
			comboValor.setEnabled(true);
			String[] s2={"Campo de golf","Estadio","Marina","Pista de carreras", "Campo de deporte", "Parque acu�tico", "Parque", "Jard�n"};
			comboValor.removeAllItems();
			for (int i=0;i<s2.length;i++)
				comboValor.addItem(s2[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Construcci�n")) {
			comboValor.setEnabled(true);
			String[] s3={"Planta e�lica","Planta hidroel�ctrica","Central el�ctrica","Central nuclear","Faro"};
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
