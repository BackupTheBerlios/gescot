package is.SimTraffic.Herramientas;

import java.util.List;

import is.SimTraffic.IModelo;
import is.SimTraffic.Mapa.Nodo;
import is.SimTraffic.Mapa.Posicion;
import is.SimTraffic.Mapa.Seleccion;
import is.SimTraffic.Mapa.Tramo;
import is.SimTraffic.Utils.Tiempo;

/**
 * Herramienta para la operación cortar
 * En método hacer corta la selección.
 * En el método deshacer restaura el estado del mapa anterior.
 */


public class HCortar implements IHerramienta {

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
	
	private Nodo antiguoNodoReferencia;

	/**
	 * Construye la herramienta guardando los datos de nodos y
	 * tramos seleccionados
	 * @param nodos
	 * @param tramos
	 */
	public HCortar (List<Nodo> nodos, List<Tramo> tramos) {
		this.nodos = nodos;
		this.tramos = tramos;		
	}

	/**
	 * Deshace la operación de cortar.
	 * Restaura el valor del portapapeles anterior a la opción de cortar
	 * y reinserta los nodos y tramos que había antes en el mapa.
	 */
	public int deshacer(IModelo modelo) {
		// TODO Auto-generated method stub
		//modelo.getMapa().setPortapapeles(portapapelesAntiguo);
		//modelo.getMapa().setNodoReferenciaPortapapeles(antiguoNodoReferencia);
		for (int i=0; i<nodos.size(); i++)
			modelo.getMapa().insertar(nodos.get(i));	

		for (int i=0; i<tramos.size(); i++)
			modelo.getMapa().insertar(tramos.get(i));
		return 0;
	}

	/**
	 * Realiza la función de cortar, si hay algo seleccionado
	 * Guarda el valor del portapapeles anterior.
	 * Copia todos los tramos seleccionados y los nodos. Calcula una posicion
	 * que será la referencia a la hora de pegar. Ésta posición será el nodo
	 * situado más a la izquierda.
	 * Después elimina del mapa los elementos que se han cortado.
	 * Sin embargo, deja en el mapa nodos que, aún habiendo sido cortados, tienen
	 * otros tramos no cortados en el mapa.
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
			Posicion posMinima= new Posicion(Double.MAX_VALUE,Double.MAX_VALUE);
			for (int i=0; i<modelo.getMapa().getPortapapeles().getNodosSeleccionados().size();i++){
				Nodo nodoTemp = modelo.getMapa().getPortapapeles().getNodosSeleccionados().get(i);
				if (nodoTemp.getPos().getLon()<=posMinima.getLon()) {
					//if (nodoTemp.getPos().getLat()<=posMinima.getLat()) {
					posMinima = nodoTemp.getPos();
					modelo.getMapa().setNodoReferenciaPortapapeles(nodoTemp);

				}
			}


			for (int i=0; i<modelo.getMapa().getTramos().size();i++) {
				Tramo tramoTemp = modelo.getMapa().getTramos().get(i);
				if (modelo.getMapa().getSeleccion().getTramosSeleccionados().contains(tramoTemp)) {
					modelo.getMapa().getTramos().remove(i);
					i--;
				}

			}

			for (int i=0; i<modelo.getMapa().getSeleccion().getNodosSeleccionados().size();i++) {
				Nodo nodoTemp = modelo.getMapa().getSeleccion().getNodosSeleccionados().get(i);
				boolean existeTramoEnMapa=false;
				for (int j=0; j<modelo.getMapa().getTramos().size()&& !existeTramoEnMapa;j++) {
					if (modelo.getMapa().getTramos().get(j).getNodoInicial().equals(nodoTemp)
							|| modelo.getMapa().getTramos().get(j).getNodoFinal().equals(nodoTemp))
						existeTramoEnMapa=true;

				}
				if (!existeTramoEnMapa) {
					boolean nodoEliminado = false;
					for (int k=0; k<modelo.getMapa().getNodos().size()&& !nodoEliminado; k++) {
						if (modelo.getMapa().getNodos().get(k).equals(nodoTemp)) {
							modelo.getMapa().getNodos().remove(k);
							nodoEliminado=true;
						}
					}
				}
			}
		}
		return 0;
	}
	public String toString(){
		return Tiempo.Hora()+" - "+"Elemento cortado";
	}

	public List<Nodo> getNodos() {
		return nodos;
	}

	public void setNodos(List<Nodo> nodos) {
		this.nodos = nodos;
	}

	public List<Tramo> getTramos() {
		return tramos;
	}

	public void setTramos(List<Tramo> tramos) {
		this.tramos = tramos;
	}
}
