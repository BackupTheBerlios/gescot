package is.SimTraffic.Herramientas;

import java.util.List;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;

/**
 * Herramienta para la operación copiar
 * En método hacer se copia la selección.
 * En el método deshacer restaura el portapapeles anterior.
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
	 * Guarda la información del portapapeles que había antes
	 * de cortar
	 */
	private Seleccion portapapelesAntiguo;

	/**
	 * Guarda la información del antiguo nodo de referencia
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
	 * Método que se ejecuta al deshacer la función de la herramienta copiar.
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
	 * Método que se ejecuta cuando se ejecuta la herramienta.
	 * Copia en el portapapeles todos los tramos y nodos seleccionados.
	 * Guarda en el nodo de referencia la posición del nodo más a la izquierda
	 * para tener una posición de referencia en el momento de pegar. 
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
					modelo.getMapa().getPortapapeles().añadirNodo(nodoInicialEnPortapapeles);
				}

				Nodo nodoFinalEnPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(tramoMapa.getNodoFinal());
				if (nodoFinalEnPortapapeles==null) {							
					nodoFinalEnPortapapeles = tramoMapa.getNodoFinal().pseudoClone();
					modelo.getMapa().getPortapapeles().añadirNodo(nodoFinalEnPortapapeles);
				}
				Tramo tramoPortapapeles = tramoMapa.pseudoClone(nodoInicialEnPortapapeles,nodoFinalEnPortapapeles);
				modelo.getMapa().getPortapapeles().añadirTramo(tramoPortapapeles);
				nodoInicialEnPortapapeles.añadirTramo(tramoPortapapeles);
				nodoFinalEnPortapapeles.añadirTramo(tramoPortapapeles);									
			}
			for (int i=0; i<modelo.getMapa().getSeleccion().getNodosSeleccionados().size(); i++) {
				Nodo nodoTemp = modelo.getMapa().getSeleccion().getNodosSeleccionados().get(i);
				Nodo nodoPortapapeles = modelo.getMapa().getPortapapeles().existeNodo(nodoTemp);
				if (nodoPortapapeles==null) {
					nodoPortapapeles = nodoTemp.pseudoClone();
					modelo.getMapa().getPortapapeles().añadirNodo(nodoPortapapeles);
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
