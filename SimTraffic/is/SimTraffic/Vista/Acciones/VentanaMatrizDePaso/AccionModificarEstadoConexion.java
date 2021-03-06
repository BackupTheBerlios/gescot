package is.SimTraffic.Vista.Acciones.VentanaMatrizDePaso;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�ales.Semaforo;
import is.SimTraffic.Vista.PanelNodo;
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
		
		botonFuente.cambiaEstado();
		
		Nodo nodo = ((VentanaMatrizDePaso)ventanaPadre).getNodo();
		((Semaforo)nodo.getSe�al()).getListaIntervalos().get(numIntervalo).getMatrizDePaso().modificarPaso(tramoOrigen,tramoDestino);
		
		
		//Pintamos el bot�n del color correspondiente.
		if (((Semaforo)nodo.getSe�al()).getListaIntervalos().get(numIntervalo).getMatrizDePaso().sePuedePasar(tramoOrigen,tramoDestino) == 0){
			botonFuente.setBackground(Color.GREEN);
		} else if (((Semaforo)nodo.getSe�al()).getListaIntervalos().get(numIntervalo).getMatrizDePaso().sePuedePasar(tramoOrigen,tramoDestino) == 1){
			botonFuente.setBackground(Color.RED);
		}
		
		//Pintamos la flecha para que cambie de color
		int estado = ((BotonDeConexion)(arg0.getSource())).getEstado();
		((PanelNodo)((VentanaMatrizDePaso)ventanaPadre).getVentanaPadre()).getMapa().crearFlecha(nodo, nodo.getTramos().get(tramoOrigen), nodo.getTramos().get(tramoDestino), estado);		
		((VentanaMatrizDePaso)ventanaPadre).informa(Messages.getString("EscuchaBotonInterconexion.0")+tramoOrigen+Messages.getString("EscuchaBotonInterconexion.1")+tramoDestino); //$NON-NLS-1$ //$NON-NLS-2$
		((VentanaMatrizDePaso)ventanaPadre).validate();
	}	
}
