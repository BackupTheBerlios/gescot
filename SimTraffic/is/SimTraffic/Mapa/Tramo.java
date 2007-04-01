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
	 * Numero de carriles que tiene el tramo en la dirección del nodo incial al
	 * final
	 */
	private int numCarrilesDir1;

	/**
	 * Número de carriles que tiene el tramo en la dirección del nodo final al
	 * inicial
	 */
	private int numCarrilesDir2;

	/**
	 * Tipo de nodo
	 * private int tipo;
	 * 
	 */
	
	/**
	 * Identificador en OSM del nodo. Cada nodo tiene un identificador entero único, que puede
	 * ser positivo o negativo pero siempre distinto de 0.
	 */
	private int ID;
	
	/**
	 * Indica el tipo de vía (inicialmente crearemos para cada tramo una vía con dicho tramo, para 
	 * simplificar la tarea y debido a que no aprovechamos aun las posibilidades de las vías 
	 * (de momento su uso se reduce a las lineas de autobuses)), posteriormente puede moverse 
	 * su inclusión a una futura clase vía. No conviene redefinirlo a tipoTramo por cuestiones 
	 * de compatibilidad, pues el formato osm etiqueta las vías, no los tramos.
	 */
	private ITipoElemento tipo;
	
	/**
	 * Atributo opcional que permitirá al usuario dar nombre concreto a una vía (función 
	 * meramente complementaria, ya que dicho nombre no determina de forma unívoca el nodo).
	 * (También le afecta el comentario de tipoVía).
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
	 * Double que almacea el angulo del tramo para evitar recacularlo
	 */
	private double angulo;
	
	/**
	 * Constructor de la clase Tramo con nodos inicial y final.
	 * <p>
	 * Este constructuro crea un nuevo tramo a partir de dos nodos, y le da el
	 * resto de los valores por defecto. Esto será, un carril en cada sentido,
	 * una velocidad máxima de 40 y un tipo 0.
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
		double largo = nodoInicial.getPos().getLon() - nodoFinal.getPos().getLon();
		double alto = nodoInicial.getPos().getLat() - nodoFinal.getPos().getLat();
		angulo = Math.atan(-alto / largo);
	}
	
	/**
	 * Constructor de la clase Tramo con id (para la herramienta cargar mapa), 
	 * nodos inicial y final.
	 * <p>
	 * Este constructuro crea un nuevo tramo a partir de dos nodos, y le da el
	 * resto de los valores por defecto. Esto será, un carril en cada sentido,
	 * una velocidad máxima de 40 y un tipo 0.
	 * 
	 * @roseuid 45B8B3A800B7
	 */
	public Tramo(int ID, Nodo nodoInicial, Nodo nodoFinal) {
		this(nodoInicial, nodoFinal);
		this.ID=ID;
	}

	/**
	 * Método que genera el ID de un tramo tomando los IDs de sus nodos (y 
	 * como los IDs de los nodos son únicos, este es también único)
	 * @return el ID generado para identificar el tramo.
	 */
	public int asignarIDunico() {
		String IDstring=""+nodoInicial.getID()+nodoFinal.getID();
		int IDtramo=Integer.parseInt(IDstring);
		return IDtramo;
	}

	/**
	 * Método para determinar si un nodo es uno de los de la lista.
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

	public double getAngulo() {
		return angulo;
	}
	
	/**
	 * Devuelve un string con la traducción del tramo al formato osm(segment), necesario 
	 * para el proceso de guardar el mapa.
	 */
	public String transformaraOSM() {
		String s=("<segment id='"+ID+"' from='" + nodoInicial.getID() + "' to='" + nodoFinal.getID() + "'>\n");
		if (this.numCarrilesDir1 >0)
			s = s + ("<tag k='nCarrilesIda' v='"+this.numCarrilesDir1+"' />\n");
		if (this.numCarrilesDir2 > 0)
			s= s + ("<tag k='nCarrilesVuelta' v='"+this.numCarrilesDir2+"' />\n");
		s= s + ("<tag k='velMax' v='"+this.velocidadMax+"' />\n");
		s= s + ("</segment>");
		return s;
	}

	public ITipoElemento getTipo() {
		return tipo;
	}
	
	public Tramo pseudoClone(Nodo nodo1, Nodo nodo2) {
		Tramo clon = new Tramo (nodo1, nodo2);
		clon.setNombre(this.nombre);
		clon.setVelMax(this.velocidadMax);
		clon.setNumCarrilesDir1(this.numCarrilesDir1);
		clon.setNumCarrilesDir2(this.numCarrilesDir2);
		return clon;
				
	}
}
