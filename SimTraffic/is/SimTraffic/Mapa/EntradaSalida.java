package is.SimTraffic.Mapa;

/**
 * Esta clase define las propiedades de los nodos que estan configurados como
 * nodos de E/S
 * 
 * @author Grupo ISTrafico
 */
public class EntradaSalida {

	/**
	 * Este valor representa el peso de este nodo en la entrada de coches al mapa.
	 * <p>
	 * Este valor es dado a lo largo del dia, despues se deberá explicitar cuantos de
	 * estos entran en cada franja horaria.
	 */
	int entran;
	
	/**
	 * Este valor representa el peso de este nodo en la salida de coches al mapa.
	 * <p>
	 * Este valor es dado a lo largo del dia, despues se deberá explicitar cuantos de
	 * estos salen en cada franja horaria.
	 */
	int salen;
	

	/**
	 * Porcentaje del total de coches que entran que lo hacen en cada franja horaria de
	 * 2 horas.
	 */
	int[] porcentajesEntrada = new int[12];
	
	/**
	 * Porcentaje del total de coches que salen de este nodo que lo hacen en cada franja
	 * horaria de 2 horas.
	 */
	int[] porcentajesSalida = new int[12];

	public EntradaSalida(int entran, int salen, int[] porcentajesEntrada, int[] porcentajesSalida) {
		this.entran = entran;
		this.salen = salen;
		this.porcentajesEntrada = normalizar(porcentajesEntrada);
		this.porcentajesSalida = normalizar(porcentajesSalida);

	}
	
	public String transformaOSM() {
		String valor = "<tag k='entradasalida' v='";
		valor += entran + ",";
		for (int i = 0; i < 12; i++)
			valor += porcentajesEntrada[i] + ",";
		valor += salen;
		for (int i = 0; i < 12; i++)
			valor += "," + porcentajesSalida[i];
		valor += "' />\n";
		return valor;
	}
	
	private int[] normalizar(int [] porcentajes) {
		int cont = 0;
		int[] nuevos = new int[12];
		for (int i = 0; i < 12; i++) {
			cont += porcentajes[i];
		}
		for (int i = 0; i < 12; i++) {
			nuevos[i] = (int) (porcentajes[i] * 100 / cont);
		}
		return nuevos;
	}

	public int getEntran() {
		return entran;
	}

	public void setEntran(int entran) {
		this.entran = entran;
	}

	public int[] getPorcentajesEntrada() {
		return porcentajesEntrada;
	}

	public void setPorcentajesEntrada(int[] porcentajesEntrada) {
		this.porcentajesEntrada = porcentajesEntrada;
	}

	public int[] getPorcentajesSalida() {
		return porcentajesSalida;
	}

	public void setPorcentajesSalida(int[] porcentajesSalida) {
		this.porcentajesSalida = porcentajesSalida;
	}

	public int getSalen() {
		return salen;
	}

	public void setSalen(int salen) {
		this.salen = salen;
	}

	
}
