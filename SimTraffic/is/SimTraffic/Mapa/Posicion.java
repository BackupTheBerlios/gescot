package is.SimTraffic.Mapa;

/**
 * Clase que almacena la posición en 2 dimensiones.
 * <p>
 * Esta clase representará las posiciones de los distintos compontente en el
 * mapa. Consta de dos dimenciones, X e Y, y tiene valores del tipo float.
 * 
 * @author Grupo ISTrafico
 * 
 */
public class Posicion {
	/**
	 * Float de posicion a lo largo del eje X
	 */
	private float posX;

	/**
	 * Float de posicion a lo largo del eje Y
	 */
	private float posY;

	/**
	 * Constructur de la clase, toma las coordenadas X e Y en formato float.
	 * 
	 * @param posX
	 *            Float que representa la pos a lo largo del eje X.
	 * @param posY
	 *            Float que representa la pos a lo largo del eje Y.
	 */
	public Posicion(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
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
		hash = 61 * hash + (int) (posX * 100000);
		hash = 37 * hash + (int) (posY * 100000);
		return hash;
	}

	public float getPosX() {
		return posX;
	}

	public float getPosY() {
		return posY;
	}
}
