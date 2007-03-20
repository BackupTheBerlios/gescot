package is.SimTraffic.Mapa;

/**
 * Clase que almacena la posición en 2 dimensiones.
 * <p>
 * Esta clase representará las posiciones de los distintos compontente en el
 * mapa. Consta de dos dimenciones, X e Y, y tiene valores del tipo float.
 * 
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Posicion {
	
	/**
	 * double latitud
	 */
	private double lat;

	/**
	 * Double longitud
	 */
	private double lon;

	/**
	 * Constructur de la clase, latitudes y longitudes
	 * 
	 * @param lat
	 *            Double que representa la latitud del punto
	 * @param lon
	 *            Double que representa la longitud del punto
	 */
	public Posicion(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
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
		Posicion pos = (Posicion) objeto;
		if (pos.lat != this.lat)
			return false;
		if (pos.lon != this.lon)
			return false;
		return true;
	}

	public int hashCode() {
		int hash = 7;
		hash = 61 * hash + (int) lon*100000;
		hash = 37 * hash + (int) lat*100000;
		return hash;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	public Posicion clone () {
		Posicion clon = new Posicion(lat,lon);
		return clon;
	}
}
