package is.SimTraffic.Vista.Acciones.VentanaMatrizDePaso;

import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Señales.Semaforo;
import is.SimTraffic.Vista.VentanaMatrizPaso.BotonDeConexion;
import is.SimTraffic.Vista.VentanaMatrizPaso.VentanaMatrizDePaso;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class AccionModificarEstadoConexion implements ActionListener {

	JFrame ventanaPadre;
	
	public AccionModificarEstadoConexion(JFrame ventanaPadre) {
		this.ventanaPadre = ventanaPadre;
	}

	public void actionPerformed(ActionEvent arg0) {
		BotonDeConexion botonFuente = ((BotonDeConexion)arg0.getSource());
		int tramoOrigen = botonFuente.getTramoOrigen();
		int tramoDestino = botonFuente.getTramoDestino();
		int numIntervalo = ((VentanaMatrizDePaso)ventanaPadre).getNumIntervalo();
		
		Nodo nodo = ((VentanaMatrizDePaso)ventanaPadre).getNodo();
		((Semaforo)nodo.getSeñal()).getListaIntervalos().get(numIntervalo).getMatrizDePaso().modificarPaso(tramoOrigen,tramoDestino);
		
		
		//Por último, pintamos el botón del color correspondiente.
		if (((Semaforo)nodo.getSeñal()).getListaIntervalos().get(numIntervalo).getMatrizDePaso().sePuedePasar(tramoOrigen,tramoDestino) == 0){
			botonFuente.setBackground(Color.GREEN);
		} else if (((Semaforo)nodo.getSeñal()).getListaIntervalos().get(numIntervalo).getMatrizDePaso().sePuedePasar(tramoOrigen,tramoDestino) == 1){
			botonFuente.setBackground(Color.RED);
		}
	}
	
	

}
