package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;
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
	}

	public void actionPerformed(ActionEvent arg0) {
		if (comboTipo.getSelectedItem().equals("Carretera")) {
			String[] s1={"Mini-rotonda","Stop","Cruce","Portón para vehículos", "Cambio De Rasante", "Puente", "Viaducto"};
			comboValor.removeAllItems();
			for (int i=0;i<s1.length;i++)
				comboValor.addItem(s1[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Tiempo Libre")) {
			String[] s2={"Campo de golf","Estadio","Marina","Pista de carreras", "Campo de deporte", "Parque acuático", "Parque", "Jardín"};
			comboValor.removeAllItems();
			for (int i=0;i<s2.length;i++)
				comboValor.addItem(s2[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Construcción")) {
			String[] s3={"Planta eólica","Planta hidroeléctrica","Central eléctrica","Central nuclear","Faro"};
			comboValor.removeAllItems();
			for (int i=0;i<s3.length;i++)
				comboValor.addItem(s3[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals("Infraestructura")) {
			TipoElemento inf=new TipoNodoAmenity("Pub");
			String[] s4=inf.devolverListaValores();
			comboValor.removeAllItems();
			for (int i=0;i<s4.length;i++)
				comboValor.addItem(s4[i]);
		}
		
		else {
			comboValor.removeAllItems();
			comboValor.addItem("No definido");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
