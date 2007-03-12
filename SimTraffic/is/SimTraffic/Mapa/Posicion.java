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
	 * @param lat
	 *            Double que representa la latitud del punto
	 * @param lon
	 *            Double que representa la longitud del punto
	 */
	public Posicion(double lat, double lon) {
		this.lat = lat;
		this.lon = lon;
		double[] xy = new double[2];
		zone=2; //Con 2 tampoco va (antes la linea de abajo)
		//zone = (int) (Math.floor ((lon + 180.0) / 6) + 1);
		xy = ConversorUTM.LatLonToUTMXY (lat, lon, zone);
		posX = (int)xy[0];
		posY = (int)xy[1];
	}
	
	public Posicion(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.zone = 2;
		double[] latlon = new double[2];
		latlon = ConversorUTM.UTMXYToLatLon (posX,posY, zone, false);
		//Antes al revés, se asignaba a lat = latlon[1] y viceversa con lon
		this.lat = latlon[0];
		this.lon = latlon[1];
	}
	
	public void setZone(int zone) {
		this.zone = zone;
		double[] latlon = new double[2];
		latlon = ConversorUTM.UTMXYToLatLon (posX,posY, zone, false);
		//Antes al revés, se asignaba a lat = latlon[1] y viceversa con lon
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
		if (pos.posX != this.posX)
			return false;
		if (pos.posY != this.posY)
			return false;
		return true;
	}

	public int hashCode() {
		int hash = 7;
		hash = 61 * hash + posX;
		hash = 37 * hash + posY;
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
