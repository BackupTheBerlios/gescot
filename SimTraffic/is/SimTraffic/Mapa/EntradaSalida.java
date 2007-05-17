package is.SimTraffic.Mapa;

import is.SimTraffic.Messages;

import java.io.Serializable;

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
public class EntradaSalida implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9029063980096954768L;

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
		String valor = Messages.getString("EntradaSalida.0"); //$NON-NLS-1$
		for (int i = 0; i < 2; i++)
			valor += porcentajesEntrada[i] + Messages.getString("EntradaSalida.1"); //$NON-NLS-1$
		valor += porcentajesEntrada[2] + Messages.getString("EntradaSalida.2"); //$NON-NLS-1$
		for (int i = 0; i < 3; i++)
			valor += Messages.getString("EntradaSalida.3") + porcentajesSalida[i]; //$NON-NLS-1$
		valor += Messages.getString("EntradaSalida.4"); //$NON-NLS-1$
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
