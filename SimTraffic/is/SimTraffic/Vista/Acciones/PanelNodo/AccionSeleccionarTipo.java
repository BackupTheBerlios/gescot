package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Messages;
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
		if (comboTipo.getSelectedItem().equals(Messages.getString("AccionSeleccionarTipo.0"))) { //$NON-NLS-1$
			comboValor.setEnabled(true);
			TipoElemento inf=new TipoNodoHighway(Messages.getString("AccionSeleccionarTipo.1")); //$NON-NLS-1$
			String[] s1=inf.devolverListaValores();
			//String[] s1={"Cambio De Rasante", "Cruce", "Mini-rotonda", "Portón para vehículos", "Puente", "Stop", "Viaducto"};
			comboValor.removeAllItems();
			for (int i=0;i<s1.length;i++)
				comboValor.addItem(s1[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals(Messages.getString("AccionSeleccionarTipo.2"))) { //$NON-NLS-1$
			comboValor.setEnabled(true);
			TipoElemento inf=new TipoNodoLeisure(Messages.getString("AccionSeleccionarTipo.3")); //$NON-NLS-1$
			String[] s2=inf.devolverListaValores();
			//String[] s2={"Campo de deporte", "Campo de golf", "Estadio", "Jardín", "Marina", "Parque", "Parque acuático", "Pista de carreras"};
			comboValor.removeAllItems();
			for (int i=0;i<s2.length;i++)
				comboValor.addItem(s2[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals(Messages.getString("AccionSeleccionarTipo.4"))) { //$NON-NLS-1$
			comboValor.setEnabled(true);
			TipoElemento inf=new TipoNodoManMade(Messages.getString("AccionSeleccionarTipo.5")); //$NON-NLS-1$
			String[] s3=inf.devolverListaValores();
			//String[] s3={"Central eléctrica", "Central nuclear", "Faro", "Planta eólica","Planta hidroeléctrica"};
			comboValor.removeAllItems();
			for (int i=0;i<s3.length;i++)
				comboValor.addItem(s3[i]);
		}
		
		else if (comboTipo.getSelectedItem().equals(Messages.getString("AccionSeleccionarTipo.6"))) { //$NON-NLS-1$
			comboValor.setEnabled(true);
			TipoElemento inf=new TipoNodoAmenity(Messages.getString("AccionSeleccionarTipo.7")); //$NON-NLS-1$
			String[] s4=inf.devolverListaValores();
			comboValor.removeAllItems();
			for (int i=0;i<s4.length;i++)
				comboValor.addItem(s4[i]);
		}
		
		else {
			comboValor.removeAllItems();
			comboValor.addItem(Messages.getString("AccionSeleccionarTipo.8")); //$NON-NLS-1$
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
