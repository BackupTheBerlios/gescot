package is.SimTraffic.Vista.Acciones.PanelNodo;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.EntradaSalida;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Se�ales.IntervaloSemaforo;
import is.SimTraffic.Mapa.Se�ales.Semaforo;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Acci�n que se controla la pulsaci�n sobre el bot�n "Aceptar" del panel nodo, que contiene las propiedades del
 * nodo y adem�s permite la creaci�n de sem�foros.
 * @author Grupo IS SimTraffic
 */
public class AccionAceptar implements ActionListener {

	private Nodo nodo;
	private JComboBox comboTipoNodo;
	private JComboBox comboValorNodo;
	private EntradaSalida es;
	private JTextField campoNombreNodo;
	private PanelNodo panelNodo;
	private JComboBox comboTipoSe�ales;
	private JComboBox comboTipoSemaforos;
	private JTextField campoTiempoCicloSemaforo;
	private PanelMapa mapa;

	public AccionAceptar(Nodo nodo, JComboBox comboTipoNodo, JComboBox comboValorNodo,
			EntradaSalida es,	JTextField campoNombreNodo, PanelNodo panelNodo, 
			JComboBox comboTipoSemaforos, JTextField campoTiempoCicloSemaforo, JComboBox comboTipoSe�ales,
			PanelMapa mapa) {
		
		this.nodo = nodo;
		this.comboTipoNodo = comboTipoNodo;
		this.comboValorNodo = comboValorNodo;
		this.es = es;
		this.campoNombreNodo = campoNombreNodo;
		this.panelNodo = panelNodo;
		this.comboTipoSe�ales=comboTipoSe�ales;
		this.comboTipoSemaforos=comboTipoSemaforos;
		this.campoTiempoCicloSemaforo=campoTiempoCicloSemaforo;
		this.mapa = mapa;
	}

	public void actionPerformed(ActionEvent arg0) {
		ITipoElemento tipo=null;
		if (comboTipoNodo.getSelectedItem().equals(Messages.getString("AccionAceptar.0"))) { //$NON-NLS-1$
			tipo = new TipoNodoHighway((String)(comboValorNodo.getSelectedItem()));
		}
		else if (comboTipoNodo.getSelectedItem().equals(Messages.getString("AccionAceptar.1"))) { //$NON-NLS-1$
			tipo = new TipoNodoLeisure((String)(comboValorNodo.getSelectedItem()));
		}
		else if (comboTipoNodo.getSelectedItem().equals(Messages.getString("AccionAceptar.2"))) { //$NON-NLS-1$
			tipo = new TipoNodoManMade((String)(comboValorNodo.getSelectedItem()));
		}
		else if (comboTipoNodo.getSelectedItem().equals(Messages.getString("AccionAceptar.3"))) { //$NON-NLS-1$
			tipo = new TipoNodoAmenity((String)(comboValorNodo.getSelectedItem()));
		}
		else { //No se ha seleccionado ninguno concreto
			//nodo.setTipo(tipo);
		}
		nodo.setTipo(tipo);
		
		nodo.setEs(panelNodo.generarEs());
		
		String nuevoNombre=campoNombreNodo.getText();
		//System.out.println(nuevoNombre);
		
		if (nuevoNombre.equals(Messages.getString("AccionAceptar.4")))  //$NON-NLS-1$
			nodo.setNombre(null);
		else {
			nodo.setNombre(nuevoNombre);
		}
		
		
		// TODO Guardar los atributos del nodo referentes a las se�ales

		boolean error = compruebaIntervalosSemaforo();
		
	
		if (error == false){
			mapa.repaint();
			mapa.recrearMapa();
			panelNodo.dispose();
		}
	}
	
	/**
	 * Comprueba que los intervalos que ha creado el usuario en el sem�foro no se solapen entre s� y que recorran
	 * el tiempo total del sem�foro de principio a fin.
	 * @return Si los intervalos son correctos o no.
	 */
	private boolean compruebaIntervalosSemaforo(){
		boolean error = false;
		int intervaloError = 0;
		int tiempoAnterior = 0;
		
		if(nodo.getSe�al() != null){
			for(int i=0; i<((Semaforo)nodo.getSe�al()).getListaIntervalos().size(); i++){
				IntervaloSemaforo iActual = ((Semaforo)nodo.getSe�al()).getListaIntervalos().get(i);
				//Comprueba que el primer intervalo empieza en 0
				if (i==0){
					if(iActual.getTiempoInicial() != 0){
						error = true;
						intervaloError = i;
					}
				}
				
				//Comprueba que el ultimo intervalo acaba en el tiempoFinal del semaforo
				if (i == ((Semaforo)nodo.getSe�al()).getListaIntervalos().size()-1){
					if(iActual.getTiempoFinal() != ((Semaforo)nodo.getSe�al()).getTiempoTotal()){
						error = true;
						intervaloError = i;
					}
					if(iActual.getTiempoInicial() != tiempoAnterior){
						error = true;
						intervaloError = i;
					}
				}
				
				//Comprueba el solapamiento de intervalos
				if (i > 0 && i < ((Semaforo)nodo.getSe�al()).getListaIntervalos().size()-1){
					if(iActual.getTiempoInicial() != tiempoAnterior){
						error = true;
						intervaloError = i;
					}
					
				}
				tiempoAnterior = iActual.getTiempoFinal();
			}
		}
		
		if(error){
			JOptionPane.showMessageDialog(panelNodo,
			    Messages.getString("AccionAceptar.5")+intervaloError, //$NON-NLS-1$
			    Messages.getString("AccionAceptar.6"), //$NON-NLS-1$
			    JOptionPane.ERROR_MESSAGE);
		}

		
	return error;	
	}

}
