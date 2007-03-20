package is.SimTraffic.Mapa;

import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

/**
 * @author Grupo ISTrafico
 */
public class Tramo implements ElementoMapa {
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
	 * private int tipo;
	 * 
	 */
	
	/**
	 * Identificador en OSM del nodo. Cada nodo tiene un identificador entero �nico, que puede
	 * ser positivo o negativo pero siempre distinto de 0.
	 */
	private int ID;
	
	/**
	 * Indica el tipo de v�a (inicialmente crearemos para cada tramo una v�a con dicho tramo, para 
	 * simplificar la tarea y debido a que no aprovechamos aun las posibilidades de las v�as 
	 * (de momento su uso se reduce a las lineas de autobuses)), posteriormente puede moverse 
	 * su inclusi�n a una futura clase v�a. No conviene redefinirlo a tipoTramo por cuestiones 
	 * de compatibilidad, pues el formato osm etiqueta las v�as, no los tramos.
	 */
	private ITipoElemento tipo;
	
	/**
	 * Atributo opcional que permitir� al usuario dar nombre concreto a una v�a (funci�n 
	 * meramente complementaria, ya que dicho nombre no determina de forma un�voca el nodo).
	 * (Tambi�n le afecta el comentario de tipoV�a).
	 */
	private String nombre;

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
		ID=asignarIDunico();
	}
	
	/**
	 * Constructor de la clase Tramo con id (para la herramienta cargar mapa), 
	 * nodos inicial y final.
	 * <p>
	 * Este constructuro crea un nuevo tramo a partir de dos nodos, y le da el
	 * resto de los valores por defecto. Esto ser�, un carril en cada sentido,
	 * una velocidad m�xima de 40 y un tipo 0.
	 * 
	 * @roseuid 45B8B3A800B7
	 */
	public Tramo(int ID, Nodo nodoInicial, Nodo nodoFinal) {
		this.nodoInicial = nodoInicial;
		this.nodoFinal = nodoFinal;
		numCarrilesDir1 = 1;
		numCarrilesDir2 = 1;
		velocidadMax = 40;
		this.ID=ID;
	}

	/**
	 * M�todo que genera el ID de un tramo tomando los IDs de sus nodos (y 
	 * como los IDs de los nodos son �nicos, este es tambi�n �nico)
	 * @return el ID generado para identificar el tramo.
	 */
	public int asignarIDunico() {
		String IDstring=""+nodoInicial.getID()+nodoFinal.getID();
		int IDtramo=Integer.parseInt(IDstring);
		return IDtramo;
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
				&& this.tieneNodo(tramo.nodoFinal))
			return true;
		return false;
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

	public Nodo getNodoInicial() {
		return nodoInicial;
	}

	public Nodo getNodoFinal() {
		return nodoFinal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getID() {
		return ID;
	}

	public void setID(int id) {
		ID = id;
	}

	/**
	 * Devuelve un string con la traducci�n del tramo al formato osm(segment), necesario 
	 * para el proceso de guardar el mapa.
	 */
	public String transformaraOSM() {
		String s=("<segment id='"+ID+"' from='" + nodoInicial.getID() + "' to='" + nodoFinal.getID() + "'>\n");
		String s2=("<tag k='nCarrilesIda' v='"+this.numCarrilesDir1+"' />\n");
		String s3=("<tag k='nCarrilesVuelta' v='"+this.numCarrilesDir2+"' />\n");
		String s4=("<tag k='velMax' v='"+this.velocidadMax+"' />\n");
		String s5=("</segment>");
		return s+s2+s3+s4+s5;
	}

	public ITipoElemento getTipo() {
		return tipo;
	}
}
