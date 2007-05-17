package is.SimTraffic.Vista.VentanaMatrizPaso;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Vista.PanelNodo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EscuchaBotonInterconexion implements MouseListener {

	VentanaMatrizDePaso ventanaPadre;
	
	public EscuchaBotonInterconexion(VentanaMatrizDePaso ventanaPadre){
		this.ventanaPadre = ventanaPadre;
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int tramoOrigen = ((BotonDeConexion)arg0.getSource()).getTramoOrigen();
		int tramoDestino = ((BotonDeConexion)arg0.getSource()).getTramoDestino();
		Nodo nodo = ((PanelNodo)ventanaPadre.getVentanaPadre()).getNodo();
		
		//((PanelNodo)ventanaPadre.getVentanaPadre()).getMapa().sugerir(nodo.getTramos().get(tramoOrigen));
		//((PanelNodo)ventanaPadre.getVentanaPadre()).getMapa().sugerir2(nodo.getTramos().get(tramoDestino));
		
		// Aquí se podría dibujar una flecha para entender mejor la dirección del semáforo.
		//Habría que crear algún método en el panel mapa y en la representación para crearla.
		//Ahi va la flecha...
		((PanelNodo)ventanaPadre.getVentanaPadre()).getMapa().crearFlecha(nodo, nodo.getTramos().get(tramoOrigen), nodo.getTramos().get(tramoDestino));
		
		ventanaPadre.informa(Messages.getString("EscuchaBotonInterconexion.0")+tramoOrigen+Messages.getString("EscuchaBotonInterconexion.1")+tramoDestino); //$NON-NLS-1$ //$NON-NLS-2$
		ventanaPadre.validate();
	}

	public void mouseExited(MouseEvent arg0) 
	{
		//((PanelNodo)ventanaPadre.getVentanaPadre()).getMapa().sugerir(null);
		//((PanelNodo)ventanaPadre.getVentanaPadre()).getMapa().sugerir2(null);
		ventanaPadre.informa(Messages.getString("EscuchaBotonInterconexion.2")); //$NON-NLS-1$
		((PanelNodo)ventanaPadre.getVentanaPadre()).getMapa().quitarFlecha();
	}
}
