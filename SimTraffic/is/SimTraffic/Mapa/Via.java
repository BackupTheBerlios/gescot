package is.SimTraffic.Mapa;

import java.util.ArrayList;
import java.util.Iterator;

import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

public class Via implements ElementoMapa {

	/**
	 * Identificador en OSM de la v�a. Cada v�a tiene un identificador entero �nico, que puede
	 * ser positivo o negativo pero siempre distinto de 0.
	 */
	private int ID;
	
	/**
	 * Indica el tipo de v�a (de acuerdo al formato osm).
	 */
	private ITipoElemento tipo;
	
	/**
	 * Atributo opcional que permitir� al usuario dar nombre concreto a una v�a (funci�n 
	 * meramente complementaria, ya que dicho nombre no determina de forma un�voca el nodo).
	 */
	private String nombre;
	
	/**
	 * Una v�a est� formada por 1 o m�s tramos. Se supone ordenada (es decir, que el tramo i 
	 * tiene como nodo final el nodo inicial del tramo i+1). No hay limitaci�n en que sea 
	 * abierto o cerrado (que el nodo inicial del primer tramo coincida con el nodo final
	 * del �ltimo tramo de la lista).
	 */
	private ArrayList<Tramo> Tramos;
	
	public Via() {
		Tramos = new ArrayList<Tramo>();
	}

	public int getID() {
		return ID;
	}
	
	/**
	 * Devuelve un string con la traducci�n de la v�a al formato osm(way), necesario 
	 * para el proceso de guardar el mapa.
	 */
	public String transformaraOSM() {
		String s=new String();
		Iterator<Tramo> tram = Tramos.iterator();
		s=s.concat("<way id='"+ID+"'>\n");
		while (tram.hasNext()) {
			Tramo tramoaux = tram.next();
			s=s.concat("<seg id='"+tramoaux.getID()+"'/>\n");
		}
		if (tipo!=null) s=s.concat("<tag k='"+tipo.getTipo()+"' v='"+tipo.getValorTipo()+"' />\n");
		if (nombre!=null) s=s.concat("<tag k='nombre' v='"+nombre+"' />\n");
		s=s.concat("</way>\n");
		return s;
	}

	public ITipoElemento getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Tramo> getTramos() {
		return Tramos;
	}

	public void setTramos(ArrayList<Tramo> tramos) {
		Tramos = tramos;
	}
	
	/**
	 * En principio no se incluyen comprobaciones de si el tramo ya est�, 
	 * pero puede a�adirse para mayor seguridad.
	 * @param tramo
	 */
	public void addTramo(Tramo tramo) {
		Tramos.add(tramo);
	}

	public void setID(int id) {
		ID = id;
	}

	public void setTipo(ITipoElemento tipo) {
		this.tipo = tipo;
	}

}
