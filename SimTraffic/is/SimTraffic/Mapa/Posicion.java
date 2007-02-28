package is.SimTraffic.Mapa;

public class Posicion {
	private float posX;

	private float posY;

	/**
	 * @roseuid 45C3056800F2
	 */
	public Posicion(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}

	/* (non-Javadoc)
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
