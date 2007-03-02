package is.SimTraffic.Mapa;

/**
 * Clase que almacena la posición en 2 dimensiones.
 * <p>
 * Esta clase representará las posiciones de los distintos compontente en el
 * mapa. Consta de dos dimenciones, X e Y, y tiene valores del tipo float.
 * <br>
 * Para mas infomación sobre las coordenada UTM, visitar: 
 * http://www.sigcpic.info/GEOUTM.htm , 
 * http://www.elgps.com/documentos/utm/coordenadas_utm.htm
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
	 * Coordenada UTM en X
	 */
	private int posX;
	
	/**
	 * Coordenada UTM en Y
	 */
	private int posY;
	
	
	/**
	 * Zona del formato UTM
	 */
	private int zone;
	
	/**
	 * Constructur de la clase, latitudes y longitudes
	 * 
	 * @param d
	 *            Double que representa la latitud del punto
	 * @param e
	 *            Double que representa la longitud del punto
	 */
	public Posicion(double d, double e) {
		this.lat = lat;
		this.lon = lon;
		double[] xy = new double[2];
		zone = (int) (Math.floor ((lon + 180.0) / 6) + 1);
		xy = ConversorUTM.LatLonToUTMXY (ConversorUTM.DegToRad (lat), ConversorUTM.DegToRad (lon), zone);
		posX = (int)xy[0];
		posY = (int)xy[1];
	}
	
	public Posicion(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		double[] latlon = new double[2];
		latlon = ConversorUTM.UTMXYToLatLon (posX, posY, zone, false);
		this.lat = latlon[0];
		this.lon = latlon[1];
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
		if (pos.lon != this.lon)
			return false;
		if (pos.lat != this.lat)
			return false;
		return true;
	}

	public int hashCode() {
		int hash = 7;
		hash = 61 * hash + (int) (lat * 100000);
		hash = 37 * hash + (int) (lon * 100000);
		return hash;
	}

	public double getLat() {
		return lat;
	}

	public double getLon() {
		return lon;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public static void main(String[] args) {
		Posicion pos = new Posicion(5.0,5.0);
		System.out.println(pos.getPosX()+ " " + pos.getPosY());
	}
}
