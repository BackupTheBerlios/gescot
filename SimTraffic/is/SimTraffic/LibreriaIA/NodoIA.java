package is.SimTraffic.LibreriaIA;

/**
 * Representa la unidad que tratará cada algoritmo en cada paso. La defición ha sido realizada
 * acorde al paradigma del espacio de estados, y es un nodo genérico que puede adaptarse a
 * problemas muy diferentes. No obstante, si existe un problema que necesita valores adicionales 
 * puede extenderse la clase comos e ha hecho en NodoMinMax, pero se ha mantenido como una clase
 * y no como un interfaz por la similitud en todos los problemas de los métodos de la clase nodo.
 * Contiene el estado actual, el nodo padre, el operador aplicado en ese paso, la profundidad actual
 * y el coste del camino hasta este nodo.
 */
public class NodoIA {

	private IEstado estado;
	private NodoIA nodoPadre;
	private IOperador operador;
	private int profundidad;
	private float coste_camino;
	
	public NodoIA(IEstado estado) {
		this.estado = estado;
		nodoPadre=null;
		operador=null;
		profundidad=0;
		coste_camino=0;
	}

	public float getCoste_camino() {
		return coste_camino;
	}


	public void setCoste_camino(float coste_camino) {
		this.coste_camino = coste_camino;
	}


	public IEstado getEstado() {
		return estado;
	}


	public void setEstado(IEstado estado) {
		this.estado = estado;
	}


	public NodoIA getNodoPadre() {
		return nodoPadre;
	}


	public void setNodoPadre(NodoIA nodoPadre) {
		this.nodoPadre = nodoPadre;
	}


	public IOperador getOperador() {
		return operador;
	}


	public void setOperador(IOperador operador) {
		this.operador = operador;
	}


	public int getProfundidad() {
		return profundidad;
	}


	public void setProfundidad(int profundidad) {
		this.profundidad = profundidad;
	}

	public void mostrarInfo() {
		//System.out.println("Estado: \n"+estado.toString());
		
		//if (nodoPadre!=null) System.out.println("Tiene Nodo padre.");
		//else System.out.println("No tiene Nodo padre.");
		
		//if (operador!=null) System.out.println("Operador: "+operador.getDescripcion());
		//System.out.println("Profundidad: "+ profundidad);
		//System.out.println("Coste camino: "+ coste_camino);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
