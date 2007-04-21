package is.SimTraffic.Mapa;

/**
 * Esta clase define las propiedades de los nodos que estan configurados como
 * nodos de E/S.
 * <p>
 * En el programa se consta de tres franjas horarias, mañana, tarde y noche.
 * Para cada una de estas horas el nodo puede ser de entrada, salida, ambas o
 * ninguna, y esto se representa por los valores que toman dos vectores de tres
 * enteros. Los valores se normalizan a lo largo del mapa para representar
 * porcentajes.
 * 
 * @author Grupo ISTrafico
 */
public class EntradaSalida {

	/**
	 * Peso de este nodo como entrada en cada fraja horaria. Este valor se
	 * normalizara.
	 */
	int[] porcentajesEntrada = new int[3];

	/**
	 * Peso de este nodo como salida en cada fraja horaria. Este valor se
	 * normalizara.
	 */
	int[] porcentajesSalida = new int[3];

	public EntradaSalida(int[] porcentajesEntrada, int[] porcentajesSalida) {
		this.porcentajesEntrada = porcentajesEntrada;
		this.porcentajesSalida = porcentajesSalida;

	}

	public EntradaSalida() {
		for (int i = 0; i < 3; i++) {
			porcentajesEntrada[i] = 0;
			porcentajesSalida[i] = 0;
		}
	}
	
	public boolean equals(Object objeto) {
		if (objeto == null)
			return false;
		if (objeto.getClass() != this.getClass())
			return false;
		EntradaSalida es = (EntradaSalida) objeto;
		boolean iguales =true;
		for (int i=0;i<3;i++)
		{
			if ((this.porcentajesEntrada[i]!=es.getPorcentajesEntrada()[i])||(this.porcentajesSalida[i]!=es.getPorcentajesSalida()[i]))
				iguales=false;
		}
		return iguales;
	}
	

	public String transformaOSM() {
		String valor = "<tag k='entradasalida' v='";
		for (int i = 0; i < 2; i++)
			valor += porcentajesEntrada[i] + ",";
		valor += porcentajesEntrada[2] + ",";
		for (int i = 0; i < 3; i++)
			valor += "," + porcentajesSalida[i];
		valor += "' />\n";
		return valor;
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
}
