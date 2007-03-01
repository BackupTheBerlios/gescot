package is.SimTraffic.Mapa;

/**
 * @author Grupo ISTrafico
 */
public class Tramo {
	/**
	 * Velocidad maxima a la que se puede cirucular por el tramo
	 */
	private float velocidadMax;

	/**
	 * Numero de carriles que tiene el tramo en la direcci�n del nodo incial al
	 * final
	 */
	private int numCarrilesDir1;

	/**
	 * N�mero de carriles que tiene el tramo en la direcci�n del nodo final al
	 * inicial
	 */
	private int numCarrilesDir2;

	/**
	 * Tipo de nodo
	 */
	private int tipo;

	/**
	 * Almacena la referencia al nodo donde comienza el tramo
	 */
	private Nodo nodoInicial;

	/**
	 * Almacena la referencia al nodo donde termina el tramo
	 */
	private Nodo nodoFinal;

	/**
	 * Constructor de la clase Tramo con nodos inicial y final.
	 * <p>
	 * Este constructuro crea un nuevo tramo a partir de dos nodos, y le da el
	 * resto de los valores por defecto. Esto ser�, un carril en cada sentido,
	 * una velocidad m�xima de 40 y un tipo 0.
	 * 
	 * @roseuid 45B8B3A800B7
	 */
	public Tramo(Nodo nodoInicial, Nodo nodoFinal) {
		this.nodoInicial = nodoInicial;
		this.nodoFinal = nodoFinal;
		numCarrilesDir1 = 1;
		numCarrilesDir2 = 1;
		velocidadMax = 40;
		tipo = 0; // ???
	}

	/**
	 * M�todo para determinar si un nodo es uno de los de la lista.
	 * <p>
	 * 
	 * @param nodo
	 *            Nodo a comprobar
	 * @return Cierto si pertenece, falso en otro caso.
	 */
	public boolean tieneNodo(Nodo nodo) {
		if (nodoInicial.equals(nodo))
			return true;
		if (nodoFinal.equals(nodo))
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object objeto) {
		if (objeto == null)
			return false;
		if (objeto.getClass() != this.getClass())
			return false;
		Tramo tramo = (Tramo) objeto;
		if (this.tieneNodo(tramo.nodoInicial)
				&& this.tieneNodo(tramo.nodoInicial))
			return false;
		return true;
	}

	public int hashCode() {
		int hash = 11;
		hash = 211 * hash + nodoInicial.hashCode() + nodoFinal.hashCode();
		return hash;
	}

	public void setVelMax(float velmax) {
		velocidadMax = velmax;
	}

	public float getVelMax() {
		return velocidadMax;
	}

	public void setNumCarrilesDir1(int num) {
		this.numCarrilesDir1 = num;
	}

	public int getNumCarrilesDir1() {
		return numCarrilesDir1;
	}

	public void setNumCarrilesDir2(int num) {
		this.numCarrilesDir2 = num;
	}

	public int getNumCarrilesDir2() {
		return numCarrilesDir2;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getTipo() {
		return tipo;
	}

	public Nodo getNodoInicial() {
		return nodoInicial;
	}

	public Nodo getNodoFinal() {
		return nodoFinal;
	}
}
