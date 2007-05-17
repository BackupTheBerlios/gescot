package is.SimTraffic.Mapa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import is.SimTraffic.Messages;
import is.SimTraffic.Mapa.TipoElemento.ITipoElemento;

public class Via implements ElementoMapa, Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8773701330979615756L;

	/**
	 * Identificador en OSM de la vía. Cada vía tiene un identificador entero único, que puede
	 * ser positivo o negativo pero siempre distinto de 0.
	 */
	private int ID;
	
	/**
	 * Indica el tipo de vía (de acuerdo al formato osm).
	 */
	private ITipoElemento tipo;
	
	/**
	 * Atributo opcional que permitirá al usuario dar nombre concreto a una vía (función 
	 * meramente complementaria, ya que dicho nombre no determina de forma unívoca el nodo).
	 */
	private String nombre;
	
	/**
	 * Una vía está formada por 1 o más tramos. Se supone ordenada (es decir, que el tramo i 
	 * tiene como nodo final el nodo inicial del tramo i+1). No hay limitación en que sea 
	 * abierto o cerrado (que el nodo inicial del primer tramo coincida con el nodo final
	 * del último tramo de la lista).
	 */
	private ArrayList<Tramo> Tramos;
	
	

	public Via() {
		Tramos = new ArrayList<Tramo>();
		//ID=asignarIDunico();
	}
	
	public int getID() {
		return ID;
	}
	
	/**
	 * Devuelve un string con la traducción de la vía al formato osm(way), necesario 
	 * para el proceso de guardar el mapa.
	 */
	public String transformaraOSM() {
		String s=new String();
		Iterator<Tramo> tram = Tramos.iterator();
		s=s.concat(Messages.getString("Via.0")+ID+Messages.getString("Via.1")); //$NON-NLS-1$ //$NON-NLS-2$
		while (tram.hasNext()) {
			Tramo tramoaux = tram.next();
			s=s.concat(Messages.getString("Via.2")+tramoaux.getID()+Messages.getString("Via.3")); //$NON-NLS-1$ //$NON-NLS-2$
		}
		if (tipo!=null) s=s.concat(Messages.getString("Via.4")+tipo.getTipo()+Messages.getString("Via.5")+tipo.getValorTipo()+Messages.getString("Via.6")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		if (nombre!=null) s=s.concat(Messages.getString("Via.7")+nombre+Messages.getString("Via.8")); //$NON-NLS-1$ //$NON-NLS-2$
		s=s.concat(Messages.getString("Via.9")); //$NON-NLS-1$
		s=s.concat(Messages.getString("Via.10")); //$NON-NLS-1$
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
	
	public boolean tieneTramo(Tramo tramo) {
		Iterator it = Tramos.iterator();
		while (it.hasNext())
			if (it.next() == tramo) return true;
		return false;
	}

	public void setTramos(ArrayList<Tramo> tramos) {
		Tramos = tramos;
	}
	
	/**
	 * En principio no se incluyen comprobaciones de si el tramo ya está, 
	 * pero puede añadirse para mayor seguridad.
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
