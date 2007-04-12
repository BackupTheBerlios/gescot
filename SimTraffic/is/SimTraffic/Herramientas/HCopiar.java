package is.SimTraffic.Herramientas;

import java.util.List;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;

/**
 * Herramienta para la operaci�n copiar
 * En m�todo hacer se copia la selecci�n.
 * En el m�todo deshacer restaura el portapapeles anterior.
 */

public class HCopiar implements IHerramienta {

	/**
	 * Lista de nodos seleccionados
	 */
	private List<Nodo> nodos;
	/**
	 * Lista de tramos seleccionados
	 */
	private List<Tramo> tramos;
	/**
	 * Guarda la informaci�n del portapapeles que hab�a antes
	 * de cortar
	 */
	private Seleccion portapapelesAntiguo;

	/**
	 * Guarda la informaci�n del antiguo nodo de referencia
	 * para pegar
	 */
	private Nodo antiguoNodoReferencia;

	/**
	 * Constructora de la herramienta.
	 * Guarda los datos de los nodos y tramos que se van a copiar
	 * @param nodos nodos seleccionados
	 * @param tramos tramos seleccionados
	 */
	public HCopiar (List<Nodo> nodos, List<Tramo> tramos) {
		this.nodos = nodos;
		this.tramos = tramos;
	}

	/**
	 * M�todo que se ejecuta al deshacer la funci�n de la herramienta copiar.
	 * Se restaura el valor del antiguo portapapeles y
	 * se restaura el valor del antiguo nodo de referencia para pegar.
	 */
	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		modelo.getMapa().setPortapapeles(portapapelesAntiguo);
		modelo.getMapa().setNodoReferenciaPortapapeles(antiguoNodoReferencia);
		return 0;
	}

	/**
	 * M�todo que se ejecuta cuando se ejecuta la herramienta.
	 * Copia en el portapapeles todos los tramos y nodos seleccionados.
	 * Guarda en el nodo de referencia la posici�n del nodo m�s a la izquierda
	 * para tener una posici�n de referencia en el momento de pegar. 
	 */
	public int hacer(IModelo modelo) {
		// TODO Auto-generated method stub
		if (modelo.getMapa().getSeleccion()!=null) {
			portapapelesAntiguo = modelo.getMapa().getPortapapeles();
			antiguoNodoReferencia = modelo.getMapa().getNodoReferenciaPortapapeles();
			modelo.getMapa().setPortapapeles(new Seleccion());
			for (int j=0; j<modelo.getMapa().getSeleccion().getTramosSeleccionados().size(); j++) {
				Tramo tramoMapa = modelo.getMapa().getSeleccion().
								getTramosSeleccionados().get(j);

				Nodo nodoInicialEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(tramoMapa.getNodoInicial());
				if (nodoInicialEnPortapapeles==null) {					
					nodoInicialEnPortapapeles = tramoMapa.getNodoInicial().pseudoClone();
					modelo.getMapa().getPortapapeles().a�adirNodo(nodoInicialEnPortapapeles);
				}

				Nodo nodoFinalEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(tramoMapa.getNodoFinal());
				if (nodoFinalEnPortapapeles==null) {							
					nodoFinalEnPortapapeles = tramoMapa.getNodoFinal().pseudoClone();
					modelo.getMapa().getPortapapeles().a�adirNodo(nodoFinalEnPortapapeles);
				}
				Tramo tramoPortapapeles = tramoMapa.pseudoClone(nodoInicialEnPortapapeles,nodoFinalEnPortapapeles);
				modelo.getMapa().getPortapapeles().a�adirTramo(tramoPortapapeles);
				nodoInicialEnPortapapeles.a�adirTramo(tramoPortapapeles);
				nodoFinalEnPortapapeles.a�adirTramo(tramoPortapapeles);									
			}
			for (int i=0; i<modelo.getMapa().getSeleccion().getNodosSeleccionados().size(); i++) {
				Nodo nodoTemp = modelo.getMapa().getSeleccion().getNodosSeleccionados().get(i);
				Nodo nodoPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(nodoTemp);
				if (nodoPortapapeles==null) {
					nodoPortapapeles = nodoTemp.pseudoClone();
					modelo.getMapa().getPortapapeles().a�adirNodo(nodoPortapapeles);
				}
			}
		}
		Posicion posMinima= new Posicion(Double.MAX_VALUE,Double.MAX_VALUE);
		for (int i=0; i<modelo.getMapa().getPortapapeles().getNodosSeleccionados().size();i++){
			Nodo nodoTemp = modelo.getMapa().getPortapapeles().getNodosSeleccionados().get(i);
			if (nodoTemp.getPos().getLon()<=posMinima.getLon()) {
				//if (nodoTemp.getPos().getLat()<=posMinima.getLat()) {
					posMinima = nodoTemp.getPos();
					modelo.getMapa().setNodoReferenciaPortapapeles(nodoTemp);
				//}
			}
		}

		return 0;
	}

}
